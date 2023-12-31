package msreservation;

import com.fasterxml.jackson.databind.ObjectMapper;
import msreservation.client.HospitalClient;
import msreservation.client.PatientClient;
import msreservation.model.Hopital;
import msreservation.model.Patient;
import msreservation.model.Reservation;
import msreservation.service.ReservationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ReservationIntegrationTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ReservationService reservationService;
    @MockBean
    private PatientClient patientClient;
    @MockBean
    private HospitalClient hospitalClient;
    @Autowired
    private ObjectMapper objectMapper;
    @Test
    public void testCreateAndRetrieveReservation() throws Exception {
        Reservation newReservation = new Reservation();
        newReservation.setId(1);
        newReservation.setHopitalId(2);
        newReservation.setPatientId(3);
        Patient mockPatient = new Patient();
        // Initialize mockPatient fields
        mockPatient.setId(3);
        Hopital mockHopital = new Hopital();
        // Initialize mockHopital fields
        mockHopital.setId(2);
        // Mock the behavior of the services and Feign clients
        // Mock the behavior of the services and Feign clients
        doAnswer(invocation -> {
            Object[] args = invocation.getArguments();
            // Additional logic can be added here if necessary
            return null;
        }).when(reservationService).createReservation(any(Reservation.class));
        when(reservationService.getReservationById(1)).thenReturn(newReservation);
        when(patientClient.getPatientById(any(Integer.class))).thenReturn(mockPatient);
        when(hospitalClient.getHopitalById(any(Integer.class))).thenReturn(mockHopital);
        // Create a new reservation
        mockMvc.perform(post("/reservation")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newReservation)))
                .andExpect(status().isOk());
        // Retrieve and validate the created reservation
        mockMvc.perform(get("/reservation/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.hopitalId").value(2))
                .andExpect(jsonPath("$.patientId").value(3));
    }
}