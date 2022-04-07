package fr.univamu.annuaire.exceptions;

public class PasswordResetTokenNotFoundException extends Exception {

    public PasswordResetTokenNotFoundException(String token) {
        super("Token " + token + " does not exist");
    }
}
