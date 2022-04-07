package fr.univamu.annuaire.services.account;

import fr.univamu.annuaire.exceptions.ExpiredPasswordResetTokenException;
import fr.univamu.annuaire.exceptions.PasswordResetTokenNotFoundException;
import fr.univamu.annuaire.model.web.NewPasswordResetFormModel;
import fr.univamu.annuaire.model.web.PasswordResetToken;
import fr.univamu.annuaire.model.web.PersonResetPassword;

public interface PasswordResetService {

    PasswordResetToken generateToken(PersonResetPassword user);

    PasswordResetToken getToken(String token);

    void useToken(NewPasswordResetFormModel newPassword) throws PasswordResetTokenNotFoundException, ExpiredPasswordResetTokenException;

    boolean isTokenValid(String token);
}
