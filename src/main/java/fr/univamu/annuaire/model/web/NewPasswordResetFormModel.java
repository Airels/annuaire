package fr.univamu.annuaire.model.web;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class NewPasswordResetFormModel {

    private String password, repeatPassword, token;
}
