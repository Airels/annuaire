package fr.univamu.annuaire.validators;

import fr.univamu.annuaire.model.Person;
import fr.univamu.annuaire.model.web.PersonResetPassword;
import fr.univamu.annuaire.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Service
public class PasswordResetValidator implements Validator {

    @Autowired
    private PersonRepository personRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return PersonResetPassword.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        PersonResetPassword user = (PersonResetPassword) target;

        Person p = personRepository.findByEmail(user.getEmail());

        if (p == null)
            errors.rejectValue("email", "password_reset.form.errors.invalid_email");
    }
}
