package fr.univamu.annuaire.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PasswordResetNewPasswordBean {

    private String password, repeatPassword, token;
}
