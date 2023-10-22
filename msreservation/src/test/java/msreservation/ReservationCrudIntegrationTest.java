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
@Transactional
public class ReservationCrudIntegrationTest {

    @Autowired
    private ReservationRepository reservationRepository;

    @Test
    public void testCreateAndRetrieveReservation() {

        Reservation reservation = new Reservation();
        reservation.setHopitalId(1);
        reservation.setPatientId(1);


        Reservation savedReservation = reservationRepository.save(reservation);

        Reservation retrievedReservation = reservationRepository.findById(savedReservation.getId()).orElse(null);


        assertNotNull(retrievedReservation);

        assertEquals(1, retrievedReservation.getId());
        assertEquals(1, retrievedReservation.getHopitalId());
        assertEquals(1, retrievedReservation.getPatientId());
    }
}
