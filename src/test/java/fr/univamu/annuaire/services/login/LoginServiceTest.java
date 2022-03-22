package fr.univamu.annuaire.services.login;

import fr.univamu.annuaire.model.Person;
import fr.univamu.annuaire.repository.PersonRepository;
import fr.univamu.annuaire.services.login.LoginService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class LoginServiceTest {

    @Autowired
    PersonRepository personRepository;

    @Autowired
    LoginService loginService;

    @Test
    void login() {
        Person p = new Person("jean", "michel", "jean.michel@mail.co", "passwd");
        personRepository.save(p);

        Assertions.assertNotNull(loginService.login(p.getEmail(), p.getPassword()));
        Assertions.assertNull(loginService.login("noemail@noemail.co", p.getPassword()));
        Assertions.assertNull(loginService.login(p.getEmail(), "wrongPasswd"));
    }
}