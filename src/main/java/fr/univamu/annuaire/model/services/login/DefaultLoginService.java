package fr.univamu.annuaire.model.services.login;

import fr.univamu.annuaire.model.beans.Person;
import fr.univamu.annuaire.model.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class DefaultLoginService implements LoginService {

    @Autowired
    private PersonRepository personRepository;

    @Override
    public boolean login(String email, String password) {
        Person p = personRepository.findByEmail(email);

        if (p == null) return false;

        return (p.getPassword().equals(password));
    }
}
