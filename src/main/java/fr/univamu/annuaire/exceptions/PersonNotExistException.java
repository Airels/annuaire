package fr.univamu.annuaire.exceptions;

import fr.univamu.annuaire.model.Person;

public class PersonNotExistException extends Exception {

    public PersonNotExistException(Person p) {
        super(String.format("Person with e-mail %s does not exist", p.getEmail()));
    }
}
