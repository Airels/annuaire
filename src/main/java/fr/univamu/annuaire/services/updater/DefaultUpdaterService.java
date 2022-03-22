package fr.univamu.annuaire.services.updater;

import fr.univamu.annuaire.exceptions.GroupAlreadyExistException;
import fr.univamu.annuaire.exceptions.GroupNotExistException;
import fr.univamu.annuaire.exceptions.PersonAlreadyExistException;
import fr.univamu.annuaire.exceptions.PersonNotExistException;
import fr.univamu.annuaire.model.Group;
import fr.univamu.annuaire.model.Person;
import fr.univamu.annuaire.repository.GroupRepository;
import fr.univamu.annuaire.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("defaultUpdaterService")
@Primary
public class DefaultUpdaterService implements UpdaterService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private GroupRepository groupRepository;


    @Override
    public void addPerson(Person p) throws Exception {
        Person person = personRepository.findByEmail(p.getEmail());
        if (person != null) throw new PersonAlreadyExistException(p);
        personRepository.save(p);
    }

    @Override
    public void addGroup(Group g) throws Exception {
        Group group = groupRepository.findByName(g.getName());
        if (group != null) throw new GroupAlreadyExistException(g);

        groupRepository.save(g);
    }

    @Override
    public void updatePerson(Person p) throws Exception {
        Person person = personRepository.findByEmail(p.getEmail());
        if (person == null) throw new PersonNotExistException(p);

        personRepository.save(p);
    }

    @Override
    public void updateGroup(Group g) throws Exception {
        Optional<Group> group = groupRepository.findById(g.getId());
        if (group.isEmpty()) throw new GroupNotExistException(g);

        Group group2 = groupRepository.findByName(g.getName());
        if (group2 != null) throw new GroupAlreadyExistException(g);

        groupRepository.save(g);
    }

    @Override
    public void removePerson(Person p) throws Exception {
        Person person = personRepository.findByEmail(p.getEmail());
        if (person == null) throw new PersonNotExistException(p);

        personRepository.delete(p);
    }

    @Override
    public void removeGroup(Group g) throws Exception {
        Group group = groupRepository.findByName(g.getName());
        if (group == null) throw new GroupNotExistException(g);

        groupRepository.delete(g);
    }
}
