package fr.univamu.annuaire.repository;

import fr.univamu.annuaire.model.Group;
import fr.univamu.annuaire.model.Person;
import fr.univamu.annuaire.repository.GroupRepository;
import fr.univamu.annuaire.repository.PersonRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

@SpringBootTest
@ActiveProfiles("dev")
class GroupRepositoryTest {

    @Autowired
    GroupRepository repository;

    @Autowired
    PersonRepository personRepository;

    Group g1, g2, g3;

    @BeforeEach
    public void beforeEach() throws Exception {
        repository.deleteAll();

        g1 = new Group("group1");
        g2 = new Group("group2");
        g3 = new Group("group3");
    }

    @Test
    void findByName() {
        repository.save(g1);
        Assertions.assertEquals(g1.getId(), repository.findByName(g1.getName()).getId());
    }

    @Test
    void findByPersonsContaining() {
        Person p1 = new Person("test", "one", "email@out", "123");
        Person p2 = new Person("test", "two", "email@goo", "321");
        Person p3 = new Person("test", "three", "email@amu", "323");

        personRepository.save(p1);
        personRepository.save(p2);
        personRepository.save(p3);

        g1.addPerson(p1);
        g1.addPerson(p2);
        g2.addPerson(p2);
        g2.addPerson(p3);

        repository.save(g1);
        repository.save(g2);

        List<Group> l1 = repository.findByPersonsContaining(p1);
        Assertions.assertEquals(1, l1.size());
        Group gPrime = l1.get(0);
        Assertions.assertEquals(g1.getName(), gPrime.getName());

        List<Group> l2 = repository.findByPersonsContaining(p2);
        Assertions.assertEquals(2, l2.size());
    }
}