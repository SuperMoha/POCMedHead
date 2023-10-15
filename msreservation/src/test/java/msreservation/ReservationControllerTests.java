package msreservation;

import com.fasterxml.jackson.databind.ObjectMapper;
import mshopital.model.Hopital;
import mshopital.service.HopitalService;
import msreservation.model.Reservation;
import msreservation.service.ReservationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class ReservationControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReservationService reservationService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testGetAllReservations() throws Exception {
        when(reservationService.getAllReservations()).thenReturn(Arrays.asList(new Reservation(), new Reservation()));
        mockMvc.perform(get("/reservation")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetReservationById() throws Exception {
        Reservation reservation = new Reservation();
        when(reservationService.getReservationById(anyInt())).thenReturn(reservation);

        mockMvc.perform(get("/reservation/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testCreateReservation() throws Exception {
        doNothing().when(reservationService).createReservation(any(Reservation.class));

        mockMvc.perform(post("/reservation")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new Reservation())))
                .andExpect(status().isOk());
    }

    @Test
    public void testUpdateReservation() throws Exception {
        Reservation reservation = new Reservation();
        when(reservationService.getReservationById(anyInt())).thenReturn(reservation);
        mockMvc.perform(put("/reservation/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new Reservation())))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteHopital() throws Exception {
        Reservation reservation = new Reservation();
        when(reservationService.getReservationById(anyInt())).thenReturn(reservation);
        mockMvc.perform(delete("/reservation/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
