package fr.univamu.annuaire.services.account;

import fr.univamu.annuaire.exceptions.ExpiredPasswordResetTokenException;
import fr.univamu.annuaire.exceptions.PasswordResetTokenNotFoundException;
import fr.univamu.annuaire.model.PasswordResetNewPasswordBean;
import fr.univamu.annuaire.model.PasswordResetToken;
import fr.univamu.annuaire.model.Person;
import fr.univamu.annuaire.repository.PasswordResetTokenRepository;
import fr.univamu.annuaire.repository.PersonRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("dev")
class DefaultPasswordResetServiceTest {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    PersonRepository personRepository;

    @Autowired
    PasswordResetTokenRepository repository;

    @Autowired
    DefaultPasswordResetService passwordResetService;

    PasswordResetToken t1, t2;
    Person p1, p2;

    @BeforeEach
    void beforeEach() {
        p1 = new Person("First", "Last", "test@email", "passwd");
        p2 = new Person("First2", "Last2", "test2@email", "passwd");

        t1 = new PasswordResetToken();
        t1.setToken("t1");
        t1.setPerson(p1);
        t1.setExpirationDate(new Date(System.currentTimeMillis()));

        t2 = new PasswordResetToken();
        t2.setToken("t2");
        t2.setPerson(p2);
        t2.setExpirationDate(new Date(System.currentTimeMillis() + 60000 * 24));

        personRepository.save(p1);
        personRepository.save(p2);

        repository.save(t1);
        repository.save(t2);
    }

    @AfterEach
    void afterEach() {
        repository.deleteAll();

        personRepository.delete(p1);
        personRepository.delete(p2);
    }

    @Test
    void generateToken() {
        PasswordResetToken token = passwordResetService.generateToken(p1);
        Assertions.assertNotNull(token);
        Assertions.assertNull(repository.findByToken("t1"));

        PasswordResetToken token2 = passwordResetService.generateToken(p1);
        Assertions.assertNotNull(token2);
        Assertions.assertNotEquals(token.getToken(), token2.getToken());
        Assertions.assertNull(repository.findByToken(token.getToken()));
    }

    @Test
    void getToken() {
        PasswordResetToken token = passwordResetService.getToken(t1.getToken());
        Assertions.assertEquals(t1.getToken(), token.getToken());

        token = passwordResetService.getToken("improbable_token");
        Assertions.assertNull(token);
    }

    @Test
    void useToken() {
        PasswordResetNewPasswordBean newPassword = new PasswordResetNewPasswordBean();

        newPassword.setToken("unexistant_token");
        newPassword.setPassword("newpasswd");
        Assertions.assertThrows(PasswordResetTokenNotFoundException.class, () -> passwordResetService.useToken(newPassword));

        newPassword.setToken(t1.getToken());
        Assertions.assertThrows(ExpiredPasswordResetTokenException.class, () -> passwordResetService.useToken(newPassword));

        newPassword.setToken(t2.getToken());
        Assertions.assertDoesNotThrow(() -> passwordResetService.useToken(newPassword));

        Person p = personRepository.findByEmail(t2.getPerson().getEmail());
        Assertions.assertTrue(passwordEncoder.matches("newpasswd", p.getPassword()));
    }

    @Test
    void isTokenValid() {
        Assertions.assertFalse(passwordResetService.isTokenValid(t1.getToken()));
        Assertions.assertTrue(passwordResetService.isTokenValid(t2.getToken()));
        Assertions.assertFalse(passwordResetService.isTokenValid("unexistant_token"));
    }
}