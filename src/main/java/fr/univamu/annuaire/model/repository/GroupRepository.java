package fr.univamu.annuaire.model.repository;

import fr.univamu.annuaire.model.beans.Group;
import fr.univamu.annuaire.model.beans.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GroupRepository extends JpaRepository<Group, Long> {

    Group findByName(String name);

    List<Group> findByPersonsContaining(Person p);
}