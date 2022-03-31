package fr.univamu.annuaire.config;

import fr.univamu.annuaire.model.SpringRoles;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

@Configuration
public class SpringConfiguration {

    @Bean("defaultPersonRole")
    public SpringRoles defaultPersonRole() {
        return SpringRoles.USER;
    }
}
