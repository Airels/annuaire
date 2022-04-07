package fr.univamu.annuaire.repository;

import fr.univamu.annuaire.model.PasswordResetToken;
import fr.univamu.annuaire.model.Person;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PasswordResetTokenRepositoryTest {

    @Autowired
    PasswordResetTokenRepository repository;

    @Autowired
    PersonRepository personRepository;

    PasswordResetToken t1, t2;
    Person p1, p2;

    @BeforeEach
    void beforeEach() throws Exception {
        p1 = new Person("First", "Last", "test@email", "passwd");
        p2 = new Person("First2", "Last2", "test2@email", "passwd");

        t1 = new PasswordResetToken();
        t1.setToken("t1");
        t1.setPerson(p1);

        t2 = new PasswordResetToken();
        t2.setToken("t2");
        t2.setPerson(p2);

        personRepository.save(p1);
        personRepository.save(p2);

        repository.save(t1);
        repository.save(t2);
    }

    @AfterEach
    void afterEach() throws Exception {
        repository.deleteAll();

        personRepository.delete(p1);
        personRepository.delete(p2);
    }

    @Test
    void findByUser() {
        PasswordResetToken token;

        token = repository.findByPerson(p1);
        Assertions.assertNotNull(token);
        Assertions.assertEquals(t1.getToken(), token.getToken());
        Assertions.assertEquals(p1.getEmail(), token.getPerson().getEmail());

        token = repository.findByPerson(p2);
        Assertions.assertNotNull(token);
        Assertions.assertEquals(t2.getToken(), token.getToken());
        Assertions.assertEquals(p2.getEmail(), token.getPerson().getEmail());
    }

    @Test
    void findByToken() {
        PasswordResetToken token;

        token = repository.findByToken(t1.getToken());
        Assertions.assertNotNull(token);
        Assertions.assertEquals(t1.getToken(), token.getToken());
        Assertions.assertEquals(p1.getEmail(), token.getPerson().getEmail());

        token = repository.findByToken(t2.getToken());
        Assertions.assertNotNull(token);
        Assertions.assertEquals(t2.getToken(), token.getToken());
        Assertions.assertEquals(p2.getEmail(), token.getPerson().getEmail());
    }
}