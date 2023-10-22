package mshopital;

import com.fasterxml.jackson.databind.ObjectMapper;
import mshopital.model.Hopital;
import mshopital.service.HopitalService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class HopitalIntegrationTest {

    @MockBean
    private HopitalService hopitalService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testCreateAndRetrieveHopital() throws Exception {
        Hopital newHopital = new Hopital(1, "Hopital A", "Address A", null, 5, 1.0f, 1.0f);

        // Mock the behavior of the service

        doAnswer(invocation -> {
            Object[] args = invocation.getArguments();
            // Additional logic can be added here if necessary
            return null; // Void methods can return null
        }).when(hopitalService).createHopital(any(Hopital.class));
        when(hopitalService.getHopitalById(1)).thenReturn(newHopital);

        // Create a new hospital
        mockMvc.perform(post("/hopitaux")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newHopital)))
                .andExpect(status().isOk());

        // Retrieve and validate the created hospital
        mockMvc.perform(get("/hopitaux/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nom").value("Hopital A"))
                .andExpect(jsonPath("$.adresse").value("Address A"));
    }
}
