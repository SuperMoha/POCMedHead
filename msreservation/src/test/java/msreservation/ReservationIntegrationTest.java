package msreservation;

import com.fasterxml.jackson.databind.ObjectMapper;
import msreservation.model.Reservation;
import msreservation.service.ReservationService;
import msreservation.client.PatientClient;
import msreservation.client.HospitalClient;
import mspatient.model.Patient;
import msreservation.client.HospitalClient;
import msreservation.client.PatientClient;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import mshopital.model.Hopital;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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

        mockPatient.setId(3);

        Hopital mockHopital = new Hopital();

        mockHopital.setId(2);


        doAnswer(invocation -> {
            Object[] args = invocation.getArguments();

            return null;
        }).when(reservationService).createReservation(any(Reservation.class));
        when(reservationService.getReservationById(1)).thenReturn(newReservation);
        when(patientClient.getPatientById(any(Integer.class))).thenReturn(mockPatient);
        when(hospitalClient.getHopitalById(any(Integer.class))).thenReturn(mockHopital);


        mockMvc.perform(post("/reservation")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newReservation)))
                .andExpect(status().isOk());


        mockMvc.perform(get("/reservation/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.hopitalId").value(2))
                .andExpect(jsonPath("$.patientId").value(3));
    }
}
