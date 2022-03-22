package fr.univamu.annuaire.exceptions;

import fr.univamu.annuaire.model.Group;

public class GroupAlreadyExistException extends Exception {

    public GroupAlreadyExistException(Group g) {
        super(String.format("Group with name %s already exist", g.getName()));
    }
}
