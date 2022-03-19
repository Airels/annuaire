package fr.univamu.annuaire.model.beans;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Groups")
@Data
@NoArgsConstructor
public class Group implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Basic
    @Column(nullable = false, unique = true)
    private String name;

    public Group(String name) {
        this.name = name;
    }

    @ManyToMany
    @JoinTable(
            name = "Person_Group",
            inverseJoinColumns = { @JoinColumn(name = "id_person") },
            joinColumns = { @JoinColumn(name = "id_group") }
    )
    @ToString.Exclude
    private Set<Person> persons;

    public void addPerson(Person p) {
        if (persons == null)
            persons = new HashSet<>();

        persons.add(p);
    }

    public void removePerson(Person p) {
        if (persons == null)
            persons = new HashSet<>();

        persons.remove(p);
    }
}
