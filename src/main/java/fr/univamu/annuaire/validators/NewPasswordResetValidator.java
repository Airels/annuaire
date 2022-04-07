package fr.univamu.annuaire.validators;

import fr.univamu.annuaire.model.web.NewPasswordResetFormModel;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Service
public class NewPasswordResetValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return NewPasswordResetFormModel.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        NewPasswordResetFormModel newPassword = (NewPasswordResetFormModel) target;

        if (!newPassword.getPassword().equals(newPassword.getRepeatPassword()))
            errors.rejectValue("repeatPassword", "password_reset.form.errors.invalid_password");
    }
}
