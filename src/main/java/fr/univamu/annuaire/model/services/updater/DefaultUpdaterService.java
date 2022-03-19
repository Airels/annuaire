package fr.univamu.annuaire.model.services.updater;

import fr.univamu.annuaire.exceptions.GroupAlreadyExistException;
import fr.univamu.annuaire.exceptions.PersonAlreadyExistException;
import fr.univamu.annuaire.model.beans.Group;
import fr.univamu.annuaire.model.beans.Person;
import fr.univamu.annuaire.model.repository.GroupRepository;
import fr.univamu.annuaire.model.repository.PersonRepository;
import fr.univamu.annuaire.model.services.settlement.SettlementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

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
    }

    @Override
    public void addGroup(Group g) throws Exception {
        Group group = groupRepository.findByName(g.getName());
        if (group != null) throw new GroupAlreadyExistException(g);
    }

    @Override
    public void updatePerson(Person p) throws Exception {
        personRepository.save(p);
    }

    @Override
    public void updateGroup(Group g) throws Exception {
        groupRepository.save(g);
    }

    @Override
    public void removePerson(Person p) throws Exception {
        personRepository.delete(p);
    }

    @Override
    public void removeGroup(Group g) throws Exception {
        groupRepository.delete(g);
    }
}
