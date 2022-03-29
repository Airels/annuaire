package fr.univamu.annuaire.repository;

import fr.univamu.annuaire.model.Group;
import fr.univamu.annuaire.model.Person;
import org.hibernate.Hibernate;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    Optional<Person> findById(Long id);

    List<Person> findByFirstName(String firstName);

    Person findByEmail(String email);

    List<Person> findByFirstNameLike(String firstName);

    List<Person> findByLastName(String lastName);

    List<Person> findByLastNameLike(String lastName);
}
