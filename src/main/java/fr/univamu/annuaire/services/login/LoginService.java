package fr.univamu.annuaire.services.login;

import fr.univamu.annuaire.model.Person;

public interface LoginService {

    Person login(String email, String password);

    void logout();
}
