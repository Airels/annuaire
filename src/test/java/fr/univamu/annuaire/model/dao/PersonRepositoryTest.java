package fr.univamu.annuaire.model.dao;

import fr.univamu.annuaire.model.beans.Person;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class PersonRepositoryTest {

    @Autowired
    PersonRepository repository;

    private Person p1, p2, p3;

    @BeforeEach
    public void beforeEach() {
        repository.deleteAll();

        p1 = new Person("test", "one");
        p2 = new Person("test", "two");
        p3 = new Person("test", "three");

        repository.save(p1);
        repository.save(p2);
        repository.save(p3);
    }

    @Test
    public void testSavePerson() {
        Person p1 = new Person("ali", "baba");
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
    void findByFirstNameLike() {
        List<Person> persons = repository.findByFirstNameLike("t%");
        Assertions.assertEquals(3, persons.size());

        persons = repository.findByFirstNameLike("%a%");
        Assertions.assertEquals(0, persons.size());
    }

    @Test
    void findByLastName() {
        List<Person> persons = repository.findByLastName(p1.getLastName());
        Assertions.assertEquals(1, persons.size());
    }

    @Test
    void findByLastNameLike() {
        List<Person> persons = repository.findByFirstNameLike("%t%");
        Assertions.assertEquals(3, persons.size());

        persons = repository.findByFirstNameLike("truc");
        Assertions.assertEquals(0, persons.size());
    }
}