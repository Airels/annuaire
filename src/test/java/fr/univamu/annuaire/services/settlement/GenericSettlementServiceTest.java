package fr.univamu.annuaire.services.settlement;

import fr.univamu.annuaire.services.settlement.SettlementService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class GenericSettlementServiceTest {

    @Autowired
    @Qualifier("genericSettlementService")
    private SettlementService genericSettlementService;

    @Test
    void populateDatabase() throws Exception {
        genericSettlementService.populateDatabase();
    }
}