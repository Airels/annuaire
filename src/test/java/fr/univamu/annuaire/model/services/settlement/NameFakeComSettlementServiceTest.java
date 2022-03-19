package fr.univamu.annuaire.model.services.settlement;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class NameFakeComSettlementServiceTest {

    @Autowired
    @Qualifier("nameFakeComSettlementService")
    private SettlementService comSettlementService;

    @Test
    void populateDatabase() throws Exception {
        comSettlementService.populateDatabase();
    }
}