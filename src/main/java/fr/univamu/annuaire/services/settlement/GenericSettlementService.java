package fr.univamu.annuaire.services.settlement;

import fr.univamu.annuaire.model.Group;
import fr.univamu.annuaire.model.Person;
import fr.univamu.annuaire.model.SpringRoles;
import fr.univamu.annuaire.repository.GroupRepository;
import fr.univamu.annuaire.repository.PersonRepository;
import fr.univamu.annuaire.services.logger.LoggerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.security.Timestamp;
import java.util.Date;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.*;

@Service("genericSettlementService")
@Profile("!dev")
public class GenericSettlementService implements SettlementService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private LoggerService logger;

    @Autowired
    @Qualifier("passwordEncoder")
    private PasswordEncoder passwordEncoder;

    @Autowired
    @Qualifier("defaultPersonRole")
    private SpringRoles defaultPersonRole;

    private int nbOfPersons, nbOfGroups, maxNbOfUsersPerGroup;

    public GenericSettlementService(
            @Value("${annuaire.settlementService.nbOfPersonsToGenerate}") int nbOfPersonsToGenerate,
            @Value("${annuaire.settlementService.nbOfGroupsToGenerate}") int nbOfGroupsToGenerate,
            @Value("${annuaire.settlementService.maxNbOfPersonsPerGroup}") int maxNbOfPersonsPerGroup) {
        this.nbOfPersons = nbOfPersonsToGenerate;
        this.nbOfGroups = nbOfGroupsToGenerate;
        this.maxNbOfUsersPerGroup = maxNbOfPersonsPerGroup;
    }

    @PostConstruct
    public void settle() throws Exception {
        long start, end;

        logger.info("Populating database...");
        start = System.currentTimeMillis();
        populateDatabase();
        end = System.currentTimeMillis();
        logger.info(String.format("Data population done. (%d ms)", end - start));
    }

    @Override
    public void populateDatabase() throws Exception {
        Random r = new Random();

        ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        CompletionService<Person> completionService = new ExecutorCompletionService<>(executor);

        for (int i = 0; i < nbOfPersons; i++) {
            int finalI = i;
            completionService.submit(() -> {
                Person p = new Person("John" + finalI, "Doe" + finalI, "john.doe." + finalI + "@foo", passwordEncoder.encode("password" + finalI));
                p.setWebsite("http://fakewebsitejohndoe" + finalI + ".com");
                p.setBirthday(new Date(r.nextInt()));
                p.addRole(defaultPersonRole);

                return p;
            });
        }

        executor.shutdown();

        for (int i = 0; i < nbOfPersons; i++)
            personRepository.save(completionService.take().get());

        for (int i = 0; i < nbOfGroups; i++) {
            Group g = new Group("group" + i);

            for (int j = 0; j < maxNbOfUsersPerGroup; j++) {
                Optional<Person> p = personRepository.findById(r.nextLong(nbOfPersons) + 1);
                if (p.isEmpty())
                    throw new DataRetrievalFailureException("Error while creating groups");

                g.addPerson(p.get());
            }

            groupRepository.save(g);
        }
    }
}
