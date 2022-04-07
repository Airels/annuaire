package fr.univamu.annuaire.model;

import lombok.Data;
import lombok.NoArgsConstructor;

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
    @Column(unique = true)
    private String token;

    @OneToOne(fetch = FetchType.EAGER)
    private Person person;

    @Temporal(TemporalType.TIMESTAMP)
    private Date expirationDate;
}
