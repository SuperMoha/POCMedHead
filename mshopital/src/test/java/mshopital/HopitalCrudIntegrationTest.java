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
@Transactional
public class HopitalCrudIntegrationTest {

    @Autowired
    private HopitalRepository hopitalRepository;

    @Test
    public void testCreateAndRetrieveHopital() {

        Hopital hopital = new Hopital();
        hopital.setNom("Hopital A");
        hopital.setAdresse("123 Test St");
        hopital.setSpecialite(new ArrayList<>(List.of("Cardiologie", "Immunologie")));
        hopital.setLits(10);
        hopital.setLongitude(12.345678f);
        hopital.setLatitude(34.567890f);


        Hopital savedHopital = hopitalRepository.save(hopital);


        Hopital retrievedHopital = hopitalRepository.findById(savedHopital.getId()).orElse(null);


        assertNotNull(retrievedHopital);


        assertEquals("Hopital A", retrievedHopital.getNom());
        assertEquals("123 Test St", retrievedHopital.getAdresse());
        assertEquals(2, retrievedHopital.getSpecialite().size());
        assertEquals(10, retrievedHopital.getLits());
        assertEquals(12.345678f, retrievedHopital.getLongitude(), 0.00001f);
        assertEquals(34.567890f, retrievedHopital.getLatitude(), 0.00001f);
    }
}
