package msreservation;

import msreservation.dao.ReservationRepository;
import msreservation.model.Reservation;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Transactional // Rollback the transaction after each test to keep the database in a clean state
public class ReservationCrudIntegrationTest {

    @Autowired
    private ReservationRepository reservationRepository;

    @Test
    public void testCreateAndRetrieveReservation() {
        // Create a new reservation
        Reservation reservation = new Reservation();
        reservation.setHopitalId(1);
        reservation.setPatientId(1);

        // Save the reservation to the database
        Reservation savedReservation = reservationRepository.save(reservation);

        // Retrieve the reservation from the database by ID
        Reservation retrievedReservation = reservationRepository.findById(savedReservation.getId()).orElse(null);

        // Assert that the retrieved reservation is not null
        assertNotNull(retrievedReservation);

        // Add more assertions as needed to check if the data is correct
        assertEquals(1, retrievedReservation.getId());
        assertEquals(1, retrievedReservation.getHopitalId());
        assertEquals(1, retrievedReservation.getPatientId());
    }
}
