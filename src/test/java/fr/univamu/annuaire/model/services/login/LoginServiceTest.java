package fr.univamu.annuaire.model.services.login;

import fr.univamu.annuaire.model.beans.Person;
import fr.univamu.annuaire.model.repository.PersonRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class LoginServiceTest {

    @Autowired
    PersonRepository personRepository;

    @Autowired
    LoginService loginService;

    @Test
    void login() {
        Person p = new Person("jean", "michel", "jean.michel@mail.co", "passwd");
        personRepository.save(p);

        Assertions.assertTrue(loginService.login(p.getEmail(), p.getPassword()));
        Assertions.assertFalse(loginService.login("noemail@noemail.co", p.getPassword()));
        Assertions.assertFalse(loginService.login(p.getEmail(), "wrongPasswd"));
    }
}