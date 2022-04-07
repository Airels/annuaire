package fr.univamu.annuaire.exceptions;

import fr.univamu.annuaire.model.PasswordResetToken;

public class ExpiredPasswordResetTokenException extends Exception {

    public ExpiredPasswordResetTokenException(PasswordResetToken token) {
        super("Token " + token.getToken() + " expired");
    }
}
