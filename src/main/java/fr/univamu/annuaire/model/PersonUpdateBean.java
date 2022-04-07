package fr.univamu.annuaire.model;

import fr.univamu.annuaire.model.Person;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@ToString(callSuper = true)
public class PersonUpdateBean extends Person {

    private String repeatPassword;

    public PersonUpdateBean(Person person) {
        super(
                person.getId(),
                person.getEmail(),
                person.getPassword(),
                person.getLastName(),
                person.getFirstName(),
                person.getBirthday(),
                person.getWebsite(),
                person.getRoles()
        );
    }

    public Person toPerson() {
        return new Person(
                this.getId(),
                this.getEmail(),
                this.getPassword(),
                this.getLastName(),
                this.getFirstName(),
                this.getBirthday(),
                this.getWebsite(),
                this.getRoles()
        );
    }
}
