package mshopital;

import mshopital.dao.HopitalRepository;
import mshopital.model.Hopital;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Transactional // Rollback the transaction after each test to keep the database in a clean state
public class HopitalCrudIntegrationTest {

    @Autowired
    private HopitalRepository hopitalRepository;

    @Test
    public void testCreateAndRetrieveHopital() {
        // Create a new hospital
        Hopital hopital = new Hopital();
        hopital.setNom("Hopital A");
        hopital.setAdresse("123 Test St");
        hopital.setSpecialite(new ArrayList<>(List.of("Cardiologie", "Immunologie")));
        hopital.setLits(10);
        hopital.setLongitude(12.345678f); // Set as float
        hopital.setLatitude(34.567890f); // Set as float

        // Save the hospital to the database
        Hopital savedHopital = hopitalRepository.save(hopital);

        // Retrieve the hospital from the database by ID
        Hopital retrievedHopital = hopitalRepository.findById(savedHopital.getId()).orElse(null);

        // Assert that the retrieved hospital is not null
        assertNotNull(retrievedHopital);

        // Add more assertions as needed to check if the data is correct
        assertEquals("Hopital A", retrievedHopital.getNom());
        assertEquals("123 Test St", retrievedHopital.getAdresse());
        assertEquals(2, retrievedHopital.getSpecialite().size()); // Assuming 2 specializations were added
        assertEquals(10, retrievedHopital.getLits());
        assertEquals(12.345678f, retrievedHopital.getLongitude(), 0.00001f);
        assertEquals(34.567890f, retrievedHopital.getLatitude(), 0.00001f);
    }
}
