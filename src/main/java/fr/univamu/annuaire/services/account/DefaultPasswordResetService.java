package fr.univamu.annuaire.services.account;

import fr.univamu.annuaire.exceptions.ExpiredPasswordResetTokenException;
import fr.univamu.annuaire.exceptions.PasswordResetTokenNotFoundException;
import fr.univamu.annuaire.model.Person;
import fr.univamu.annuaire.model.PasswordResetNewPasswordBean;
import fr.univamu.annuaire.model.PasswordResetToken;
import fr.univamu.annuaire.repository.PasswordResetTokenRepository;
import fr.univamu.annuaire.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.Date;

@Service
public class DefaultPasswordResetService implements PasswordResetService {

    @Autowired
    private PasswordResetTokenRepository tokenRepository;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private SecureRandom secureRandom;

    @Autowired
    @Qualifier("resetPasswordExpirationDuration")
    private long resetPasswordExpirationDuration;

    @Override
    public PasswordResetToken generateToken(Person user) {
        Person person = personRepository.findByEmail(user.getEmail());
        PasswordResetToken oldToken = tokenRepository.findByPerson(person);
        if (oldToken != null)
            tokenRepository.delete(oldToken);

        byte[] rawToken = new byte[128];
        secureRandom.nextBytes(rawToken);
        String stringToken = Base64.getEncoder().encodeToString(rawToken);

        PasswordResetToken token = new PasswordResetToken();
        token.setToken(stringToken);
        token.setPerson(person);
        token.setExpirationDate(new Date(System.currentTimeMillis() + resetPasswordExpirationDuration * 60000));

        return tokenRepository.save(token);
    }

    @Override
    public PasswordResetToken getToken(String token) {
        return tokenRepository.findByToken(token);
    }

    @Override
    public void useToken(PasswordResetNewPasswordBean newPassword) throws PasswordResetTokenNotFoundException, ExpiredPasswordResetTokenException {
        String tokenString = newPassword.getToken();
        PasswordResetToken token = getToken(tokenString);

        tokenRepository.findAll().forEach(System.out::println);

        if (token == null)
            throw new PasswordResetTokenNotFoundException(tokenString);

        long now = System.currentTimeMillis();
        long expirationDate = token.getExpirationDate().getTime();

        if (now >= expirationDate)
            throw new ExpiredPasswordResetTokenException(token);

        Person person = token.getPerson();
        person.setPassword(passwordEncoder.encode(newPassword.getPassword()));
        personRepository.save(person);

        tokenRepository.delete(token);
    }

    @Override
    public boolean isTokenValid(String tokenString) {
        PasswordResetToken token = getToken(tokenString);

        if (token == null)
            return false;

        long now = System.currentTimeMillis();
        long expirationDate = token.getExpirationDate().getTime();

        return now < expirationDate;
    }
}
