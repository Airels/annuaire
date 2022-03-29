package fr.univamu.annuaire.repository;

import fr.univamu.annuaire.model.Group;
import fr.univamu.annuaire.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {

    Group findByName(String name);

    List<Group> findByPersonsContaining(Person p);
}