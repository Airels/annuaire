package fr.univamu.annuaire.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Basic
    @Column(nullable = false, unique = true, updatable = false)
    private String email;

    @Basic
    @Column(nullable = false)
    private String password;

    @Basic
    @Column(nullable = false)
    private String lastName;

    @Basic
    @Column(nullable = false)
    private String firstName;

    @Basic
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;

    @Basic
    private String website;

    @ElementCollection(fetch = FetchType.EAGER)
    private Set<SpringRoles> roles;

    public Person(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    public void addRole(SpringRoles role) {
        if (roles == null)
            roles = new HashSet<>();

        roles.add(role);
    }

    public void removeRole(SpringRoles role) {
        if (roles == null) return;

        roles.remove(role);
    }
}
