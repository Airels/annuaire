package fr.univamu.annuaire.model;

import fr.univamu.annuaire.model.Person;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
public class PasswordResetToken implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Basic
    private String token;

    @OneToOne(fetch = FetchType.EAGER)
    private Person user;

    @Temporal(TemporalType.TIMESTAMP)
    private Date expirationDate;
}
