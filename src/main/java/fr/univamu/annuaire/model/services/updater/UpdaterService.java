package fr.univamu.annuaire.model.services.updater;

import fr.univamu.annuaire.model.beans.Group;
import fr.univamu.annuaire.model.beans.Person;

public interface UpdaterService {

    void addPerson(Person p) throws Exception;

    void addGroup(Group g) throws Exception;

    void updatePerson(Person p) throws Exception;

    void updateGroup(Group g) throws Exception;

    void removePerson(Person p) throws Exception;

    void removeGroup(Group g) throws Exception;
}
