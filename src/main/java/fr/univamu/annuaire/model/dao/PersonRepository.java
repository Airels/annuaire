package fr.univamu.annuaire.model.dao;

import fr.univamu.annuaire.model.beans.Group;
import fr.univamu.annuaire.model.beans.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person, Long> {

    List<Person> findByFirstName(String firstName);

    List<Person> findByFirstNameLike(String firstName);

    List<Person> findByLastName(String lastName);

    List<Person> findByLastNameLike(String lastName);

    // List<Person> findByGroup(Group g);
}
