package fr.univamu.annuaire.repository;

import fr.univamu.annuaire.model.Person;
import fr.univamu.annuaire.model.web.PasswordResetToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long> {

    PasswordResetToken findByUser(Person user);

    PasswordResetToken findByToken(String token);
}
