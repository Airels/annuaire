package fr.univamu.annuaire.repository;

import fr.univamu.annuaire.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    Optional<Person> findById(Long id);

    List<Person> findByFirstName(String firstName);

    Person findByEmail(String email);

    List<Person> findByFirstNameLikeIgnoreCase(String firstName);

    List<Person> findByLastName(String lastName);

    List<Person> findByLastNameLikeIgnoreCase(String lastName);
}
