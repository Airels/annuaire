package fr.univamu.annuaire.validators;

import fr.univamu.annuaire.model.Person;
import fr.univamu.annuaire.model.PersonUpdateBean;
import fr.univamu.annuaire.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Collections;

@Service
public class PersonUpdateFormModelValidator implements Validator {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    @Qualifier("passwordEncoder")
    private PasswordEncoder passwordEncoder;

    @Override
    public boolean supports(Class<?> clazz) {
        return PersonUpdateBean.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        PersonUpdateBean person = (PersonUpdateBean) target;
        Person referencePerson = personRepository.findById(person.getId()).get();

        validateEmail(person, referencePerson, errors);
        validatePassword(person, referencePerson, errors);
        validateRoles(person, referencePerson, errors);
    }

    private void validateEmail(PersonUpdateBean person, Person referencePerson, Errors errors) {
        if (!person.getEmail().equals(referencePerson.getEmail())) {
            errors.rejectValue("email", "person.form.error.email");
        }
    }

    private void validatePassword(PersonUpdateBean person, Person referencePerson, Errors errors) {
        if (person.getPassword().isEmpty()) {
            person.setPassword(referencePerson.getPassword());
        } else if (!person.getPassword().equals(person.getRepeatPassword())) {
            errors.rejectValue("repeatPassword", "person.form.error.repeat_password");
        } else if (passwordEncoder.matches(person.getPassword(), referencePerson.getPassword())) {
            errors.rejectValue("password", "person.form.error.password");
        } else {
            person.setPassword(passwordEncoder.encode(person.getPassword()));
        }
    }

    private void validateRoles(PersonUpdateBean person, Person referencePerson, Errors errors) {
        if (Collections.disjoint(person.getRoles(), referencePerson.getRoles())) {
            errors.rejectValue("roles", "person.form.error.roles");
        }
    }
}
