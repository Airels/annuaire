package fr.univamu.annuaire.config;

import fr.univamu.annuaire.model.services.settlement.NameFakeComSettlementService;
import fr.univamu.annuaire.model.services.settlement.SettlementService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfiguration {

    @Bean
    @Qualifier("nameFakeComSettlementService")
    public SettlementService buildNameFakeComSettlementService(
            @Value("nameFakeComApiLink") String apiLink,
            @Value("nbOfUsersToGenerate") int nbOfUsers,
            @Value("nbOfGroupsToGenerate") int nbOfGroups,
            @Value("maxNbOfPersonsPerGroup") int maxNbOfPersonsPerGroup) {
        return new NameFakeComSettlementService(apiLink, nbOfUsers, nbOfGroups, maxNbOfPersonsPerGroup);
    }
}
