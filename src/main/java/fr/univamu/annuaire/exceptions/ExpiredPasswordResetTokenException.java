package fr.univamu.annuaire.exceptions;

import fr.univamu.annuaire.model.web.PasswordResetToken;

public class ExpiredPasswordResetTokenException extends Exception {

    public ExpiredPasswordResetTokenException(PasswordResetToken token) {
        super("Token " + token.getToken() + " expired");
    }
}
