package fr.univamu.annuaire.services.updater;

import fr.univamu.annuaire.model.Group;
import fr.univamu.annuaire.model.Person;

public interface UpdaterService {

    void addPerson(Person p) throws Exception;

    void addGroup(Group g) throws Exception;

    void updatePerson(Person p) throws Exception;

    void updateGroup(Group g) throws Exception;

    void removePerson(Person p) throws Exception;

    void removeGroup(Group g) throws Exception;
}
