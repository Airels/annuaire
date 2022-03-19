package fr.univamu.annuaire.exceptions;

import fr.univamu.annuaire.model.beans.Group;
import fr.univamu.annuaire.model.beans.Person;

public class GroupAlreadyExistException extends Exception {

    public GroupAlreadyExistException(Group g) {
        super(String.format("Group with name %s already exist", g.getName()));
    }
}
