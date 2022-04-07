package fr.univamu.annuaire.services.account;

import fr.univamu.annuaire.exceptions.ExpiredPasswordResetTokenException;
import fr.univamu.annuaire.exceptions.PasswordResetTokenNotFoundException;
import fr.univamu.annuaire.model.Person;
import fr.univamu.annuaire.model.PasswordResetNewPasswordBean;
import fr.univamu.annuaire.model.PasswordResetToken;

public interface PasswordResetService {

    PasswordResetToken generateToken(Person user);

    PasswordResetToken getToken(String token);

    void useToken(PasswordResetNewPasswordBean newPassword) throws PasswordResetTokenNotFoundException, ExpiredPasswordResetTokenException;

    boolean isTokenValid(String token);
}
