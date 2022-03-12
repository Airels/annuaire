package fr.univamu.annuaire.model.services.settlement;

import fr.univamu.annuaire.model.beans.Group;
import fr.univamu.annuaire.model.beans.Person;
import fr.univamu.annuaire.model.repository.GroupRepository;
import fr.univamu.annuaire.model.repository.PersonRepository;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.stereotype.Service;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashSet;
import java.util.Optional;
import java.util.Random;
import java.util.Set;

/**
 * Settlement Service using https://api.namefake.com to generate random users
 */
@Service
public class NameFakeComSettlementService implements SettlementService {

    private String apiLink;
    private int nbOfPersons, nbOfGroups, maxNbOfUsersPerGroup;

    public NameFakeComSettlementService(String apiLink, int nbOfUsersToGenerate, int nbOfGroupsToGenerate, int maxNbOfPersonsPerGroup) {
        this.apiLink = apiLink;
        this.nbOfPersons = nbOfPersons;
        this.nbOfGroups = nbOfGroups;
        this.maxNbOfUsersPerGroup = maxNbOfPersonsPerGroup;
    }

    @Autowired
    PersonRepository personRepository;

    @Autowired
    GroupRepository groupRepository;

    @Override
    public void populateDatabase() throws Exception {
        generatePeople();
        generateGroups();
    }

    public void generatePeople() throws Exception {
        URL url = new URL(apiLink);

        for (int i = 0; i < nbOfPersons; i++) {
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200)
                throw new DataRetrievalFailureException("Unable to contact NameFakeCom api (returned response code: " + conn.getResponseCode());

            conn.getResponseMessage();

            JSONParser parser = new JSONParser();
            JSONObject json = (JSONObject) parser.parse(conn.getResponseMessage());


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
    }

    public void generateGroups() throws Exception {
        Random r = new Random();

        for (int i = 0; i < nbOfGroups; i++) {
            Group g = new Group("group" + i);
            Set<Person> persons = new HashSet<>();

            for (int j = 0; j < maxNbOfUsersPerGroup; j++) {
                Optional<Person> p = personRepository.findById(r.nextLong(nbOfPersons));
                if (p.isEmpty())
                    throw new DataRetrievalFailureException("Error while creating groups");

                persons.add(p.get());
            }

            persons.forEach(g::addPerson);

            groupRepository.save(g);
        }
    }
}
