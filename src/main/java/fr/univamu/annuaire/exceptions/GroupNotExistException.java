package fr.univamu.annuaire.exceptions;

import fr.univamu.annuaire.model.Group;

public class GroupNotExistException extends Exception {

    public GroupNotExistException(Group g) {
        super(String.format("Group with ID %d does not exist", g.getId()));
    }
}
