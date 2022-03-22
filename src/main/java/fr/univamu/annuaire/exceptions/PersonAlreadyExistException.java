package fr.univamu.annuaire.exceptions;

import fr.univamu.annuaire.model.Person;

public class PersonAlreadyExistException extends Exception {

    public PersonAlreadyExistException(Person p) {
        super(String.format("Person with e-mail %s already exist", p.getEmail()));
    }
}
