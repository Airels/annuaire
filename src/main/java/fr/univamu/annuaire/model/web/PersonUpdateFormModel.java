package fr.univamu.annuaire.model.web;

import fr.univamu.annuaire.model.Person;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@ToString(callSuper = true)
public class PersonUpdateFormModel extends Person {

    private String repeatPassword;

    public PersonUpdateFormModel(Person person) {
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
