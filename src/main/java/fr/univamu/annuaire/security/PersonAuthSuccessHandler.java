package fr.univamu.annuaire.security;

import fr.univamu.annuaire.model.Person;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class PersonAuthSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        PersonDetails details = (PersonDetails) authentication.getPrincipal();

        Person p = details.getPerson();
        p.setPassword("");

        request.getSession().setAttribute("user", p);

        super.onAuthenticationSuccess(request, response, authentication);
    }
}
