package fr.univamu.annuaire.services.settlement;

import fr.univamu.annuaire.model.Group;
import fr.univamu.annuaire.model.Person;
import fr.univamu.annuaire.repository.GroupRepository;
import fr.univamu.annuaire.repository.PersonRepository;
import fr.univamu.annuaire.services.logger.LoggerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Optional;
import java.util.Random;

@Service("genericSettlementService")
public class GenericSettlementService implements SettlementService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private LoggerService logger;

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
        logger.info("Populating database...");
        populateDatabase();
        logger.info("Data population done.");
    }

    @Override
    public void populateDatabase() throws Exception {
        for (int i = 0; i < nbOfPersons; i++) {
            Person p = new Person("John" + i, "Doe" + i, "john.doe." + i + "@foo", "password" + i);
            personRepository.save(p);
        }

        Random r = new Random();
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
