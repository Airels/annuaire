package fr.univamu.annuaire.repository;

import fr.univamu.annuaire.model.Person;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

@SpringBootTest
@ActiveProfiles("dev")
class PersonRepositoryTest {

    @Autowired
    PersonRepository repository;

    @Autowired
    GroupRepository groupRepository;

    private Person p1, p2, p3;

    @BeforeEach
    public void beforeEach() {
        groupRepository.deleteAll();
        repository.deleteAll();

        p1 = new Person("test", "one", "email@out", "123");
        p2 = new Person("test", "two", "email@goo", "321");
        p3 = new Person("test", "three", "email@amu", "323");

        repository.save(p1);
        repository.save(p2);
        repository.save(p3);
    }

    @Test
    public void testSavePerson() {
        Person p1 = new Person("ali", "baba", "ali@baba", "666");
        repository.save(p1);

        Person p2 = repository.findById(p1.getId()).get();
        Assertions.assertEquals(p1.getFirstName(), p2.getFirstName());
    }

    @Test
    public void testUpdatePerson() {
        p1.setLastName("toto");
        repository.save(p1);

        Person p = repository.findById(p1.getId()).get();
        Assertions.assertEquals(p1.getLastName(), p.getLastName());
    }

    @Test
    public void testDeletePerson() {
        int nbOfPersons = repository.findAll().size();

        repository.delete(p1);

        Assertions.assertEquals(nbOfPersons - 1, repository.findAll().size());
    }


    @Test
    void findByFirstName() {
        List<Person> persons = repository.findByFirstName(p1.getFirstName());
        Assertions.assertEquals(3, persons.size());
    }

    @Test
    void findByEmail() {
        Assertions.assertEquals(p1.getFirstName(), repository.findByEmail(p1.getEmail()).getFirstName());
    }

    @Test
    void findByFirstNameLike() {
        List<Person> persons = repository.findByFirstNameLikeIgnoreCase("t%");
        Assertions.assertEquals(3, persons.size());

        persons = repository.findByFirstNameLikeIgnoreCase("%a%");
        Assertions.assertEquals(0, persons.size());
    }

    @Test
    void findByLastName() {
        List<Person> persons = repository.findByLastName(p1.getLastName());
        Assertions.assertEquals(1, persons.size());
    }

    @Test
    void findByLastNameLike() {
        List<Person> persons = repository.findByLastNameLikeIgnoreCase("t%");
        Assertions.assertEquals(2, persons.size());

        persons = repository.findByLastNameLikeIgnoreCase("%e");
        Assertions.assertEquals(2, persons.size());
    }
}