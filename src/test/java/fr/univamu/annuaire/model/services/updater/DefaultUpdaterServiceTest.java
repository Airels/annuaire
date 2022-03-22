package fr.univamu.annuaire.model.services.updater;

import fr.univamu.annuaire.exceptions.GroupAlreadyExistException;
import fr.univamu.annuaire.exceptions.GroupNotExistException;
import fr.univamu.annuaire.exceptions.PersonAlreadyExistException;
import fr.univamu.annuaire.exceptions.PersonNotExistException;
import fr.univamu.annuaire.model.beans.Group;
import fr.univamu.annuaire.model.beans.Person;
import fr.univamu.annuaire.model.repository.GroupRepository;
import fr.univamu.annuaire.model.repository.PersonRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DefaultUpdaterServiceTest {

    @Autowired
    @Qualifier("defaultUpdaterService")
    UpdaterService updaterService;

    @Autowired
    PersonRepository personRepository;

    @Autowired
    GroupRepository groupRepository;

    private Person defaultPerson;
    private Group defaultGroup;

    @BeforeEach
    void beforeEach() throws Exception {
        defaultPerson = new Person("before", "each", "before@each.com", "passwd");
        personRepository.save(defaultPerson);

        defaultGroup = new Group("before-each");
        groupRepository.save(defaultGroup);
    }

    @AfterEach
    void afterEach() throws Exception {
        personRepository.delete(defaultPerson);
        groupRepository.delete(defaultGroup);
    }

    @Test
    void addPerson() throws Exception {
        Person p = new Person("first", "last", "a@b.com", "passwd");
        updaterService.addPerson(p);
        Assertions.assertThrows(PersonAlreadyExistException.class, () -> updaterService.addPerson(p));
    }

    @Test
    void addGroup() throws Exception {
        Group g = new Group("grp");
        updaterService.addGroup(g);
        Assertions.assertThrows(GroupAlreadyExistException.class, () -> updaterService.addGroup(g));
    }

    @Test
    void updatePerson() throws Exception {
        defaultPerson.setLastName("aaa");
        updaterService.updatePerson(defaultPerson);
        Assertions.assertEquals("aaa", personRepository.findByEmail(defaultPerson.getEmail()).getLastName());

        Person p = new Person("first", "last", "a@b.com", "passwd");
        Assertions.assertThrows(PersonNotExistException.class, () -> updaterService.updatePerson(p));
    }

    @Test
    void updateGroup() throws Exception {
        defaultGroup.setName("abc");
        updaterService.updateGroup(defaultGroup);
        Assertions.assertEquals("abc", groupRepository.findById(defaultGroup.getId()).get().getName());

        Group g = new Group("grp");
        Assertions.assertThrows(GroupNotExistException.class, () -> updaterService.updateGroup(g));

        g.setId(defaultGroup.getId());
        g.setName("abc");
        Assertions.assertThrows(GroupAlreadyExistException.class, () -> updaterService.updateGroup(g));
    }

    @Test
    void removePerson() throws Exception {
        updaterService.removePerson(defaultPerson);
        Assertions.assertTrue(personRepository.findById(defaultPerson.getId()).isEmpty());

        Assertions.assertThrows(PersonNotExistException.class, () -> updaterService.removePerson(defaultPerson));
    }

    @Test
    void removeGroup() throws Exception {
        updaterService.removeGroup(defaultGroup);
        Assertions.assertTrue(groupRepository.findById(defaultGroup.getId()).isEmpty());

        Assertions.assertThrows(GroupNotExistException.class, () -> updaterService.removeGroup(defaultGroup));
    }
}