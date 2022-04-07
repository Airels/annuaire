package fr.univamu.annuaire.model.web;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
public class PersonResetPassword {

    @Id
    private String email;
}
