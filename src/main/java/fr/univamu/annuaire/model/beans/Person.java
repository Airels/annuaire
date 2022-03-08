package fr.univamu.annuaire.model.beans;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
public class Person implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Basic
    private String lastName;

    @Basic
    private String firstName;

    @Basic
    @Temporal(TemporalType.DATE)
    private Date birthday;

    @Basic
    private String email;

    @Basic
    private String website;

    @Basic
    private String password;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "Person_Group",
            joinColumns = { @JoinColumn(name = "id_person") },
            inverseJoinColumns = { @JoinColumn(name = "id_group") }
    )
    @ToString.Exclude
    private Set<Group> groups;

    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
