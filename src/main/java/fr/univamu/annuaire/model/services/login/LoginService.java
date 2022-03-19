package fr.univamu.annuaire.model.services.login;

import fr.univamu.annuaire.model.beans.Person;

public interface LoginService {

    Person login(String email, String password);

    void logout();
}
