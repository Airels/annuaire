package fr.univamu.annuaire.repository;

import fr.univamu.annuaire.model.Group;
import fr.univamu.annuaire.model.Person;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {

    @EntityGraph(attributePaths = { "persons" })
    Optional<Group> findById(Long id);

    Group findByName(String name);

    List<Group> findByNameLikeIgnoreCase(String name);

    List<Group> findByPersonsContaining(Person p);
}