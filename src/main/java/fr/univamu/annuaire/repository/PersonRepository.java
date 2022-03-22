package fr.univamu.annuaire.repository;

import fr.univamu.annuaire.model.Group;
import fr.univamu.annuaire.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person, Long> {

    List<Person> findByFirstName(String firstName);

    Person findByEmail(String email);

    List<Person> findByFirstNameLike(String firstName);

    List<Person> findByLastName(String lastName);

    List<Person> findByLastNameLike(String lastName);

    List<Person> findByGroupsContaining(Group g);
}
