package fr.univamu.annuaire.model.services.settlement;

import fr.univamu.annuaire.model.beans.Group;
import fr.univamu.annuaire.model.beans.Person;
import fr.univamu.annuaire.model.repository.GroupRepository;
import fr.univamu.annuaire.model.repository.PersonRepository;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashSet;
import java.util.Optional;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Settlement Service using https://api.namefake.com to generate random users
 * Deprecated: Used a more generic approach
 * @see GenericSettlementService
 */
@Service("nameFakeComSettlementService")
@Transactional
@Deprecated
public class NameFakeComSettlementService implements SettlementService {

    private String apiLink;
    private int nbOfPersons, nbOfGroups, maxNbOfUsersPerGroup;

    @Autowired
    PersonRepository personRepository;

    @Autowired
    GroupRepository groupRepository;

    public NameFakeComSettlementService(
            @Value("${annuaire.settlementService.nameFakeComApi.link}") String apiLink,
            @Value("${annuaire.settlementService.nbOfPersonsToGenerate}") int nbOfPersonsToGenerate,
            @Value("${annuaire.settlementService.nbOfGroupsToGenerate}") int nbOfGroupsToGenerate,
            @Value("${annuaire.settlementService.maxNbOfPersonsPerGroup}") int maxNbOfPersonsPerGroup) {
        this.apiLink = apiLink;
        this.nbOfPersons = nbOfPersonsToGenerate;
        this.nbOfGroups = nbOfGroupsToGenerate;
        this.maxNbOfUsersPerGroup = maxNbOfPersonsPerGroup;
    }

    @Override
    public void populateDatabase() throws Exception {
        generatePeople();
        generateGroups();
    }

    public void generatePeople() throws Exception {
        URL url = new URL(apiLink);

        System.out.println("Generating random persons...");
        for (int i = 0; i < nbOfPersons; i++) {
            System.out.printf("%d / %d [%d%%]\n", i, nbOfPersons, (int) (((float) i / nbOfPersons) * 100));
            // System.out.print("\r" + i + " / " + nbOfPersons);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200)
                throw new DataRetrievalFailureException("Unable to contact NameFakeCom api (returned response code: " + conn.getResponseCode());

            conn.getResponseMessage();

            JSONParser parser = new JSONParser();
            String bodyResponse = new BufferedReader(new InputStreamReader(conn.getInputStream())).lines().collect(Collectors.joining("\n"));
            JSONObject json = (JSONObject) parser.parse(bodyResponse);


            Person person = new Person();
            String fullName = (String) json.get("name");
            String firstName = fullName.split(" ")[0];
            person.setFirstName(firstName);
            person.setLastName(fullName.substring(firstName.length() + 1));

            String email = json.get("email_u") + "@" + json.get("email_d");
            person.setEmail(email);

            person.setPassword((String) json.get("password"));
            person.setWebsite((String) json.get("domain"));

            personRepository.save(person);

            conn.disconnect();
        }
        System.out.println("Done.");
    }

    public void generateGroups() throws Exception {
        Random r = new Random();

        for (int i = 0; i < nbOfGroups; i++) {
            Group g = new Group("group" + i);

            for (int j = 0; j < maxNbOfUsersPerGroup; j++) {
                Optional<Person> p = personRepository.findById(r.nextLong(nbOfPersons));
                if (p.isEmpty())
                    throw new DataRetrievalFailureException("Error while creating groups");

                g.addPerson(p.get());
            }

            groupRepository.save(g);
        }
    }
}
