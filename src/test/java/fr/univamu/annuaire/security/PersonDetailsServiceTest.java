package fr.univamu.annuaire.security;

import fr.univamu.annuaire.model.Person;
import fr.univamu.annuaire.repository.PersonRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("dev")
class PersonDetailsServiceTest {

    @Autowired
    PersonRepository personRepository;

    @Autowired
    PersonDetailsService personDetailsService;

    Person p1;

    @BeforeEach
    void beforeEach() {
        p1 = new Person("First", "Last", "test@email", "passwd");
        personRepository.save(p1);
    }

    @AfterEach
    void afterEach() {
        personRepository.delete(p1);
    }

    @Test
    void loadUserByUsername() {
        PersonDetails p = (PersonDetails) personDetailsService.loadUserByUsername(p1.getEmail());
        Assertions.assertEquals(p1.getId(), p.getPerson().getId());
    }
}