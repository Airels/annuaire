package fr.univamu.annuaire.validators;

import fr.univamu.annuaire.model.PasswordResetNewPasswordBean;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Service
public class NewPasswordResetValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return PasswordResetNewPasswordBean.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        PasswordResetNewPasswordBean newPassword = (PasswordResetNewPasswordBean) target;

        if (!newPassword.getPassword().equals(newPassword.getRepeatPassword()))
            errors.rejectValue("repeatPassword", "password_reset.form.errors.invalid_password");
    }
}
