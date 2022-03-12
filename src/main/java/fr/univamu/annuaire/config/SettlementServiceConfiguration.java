package fr.univamu.annuaire.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SettlementServiceConfiguration {

    @Bean
    public int nbOfUsersToGenerate() {
        return 1000;
    }

    @Bean
    public int nbOfGroupsToGenerate() {
        return 100;
    }

    @Bean
    public int maxNbOfPersonsPerGroup() {
        return 100;
    }

    @Bean
    public String nameFakeComApiLink() {
        return "https://api.namefake.com/french-france/";
    }
}
