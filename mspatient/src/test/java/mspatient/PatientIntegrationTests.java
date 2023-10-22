package mspatient;

import com.fasterxml.jackson.databind.ObjectMapper;
import mspatient.model.Patient;
import mspatient.service.PatientService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Base64;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class PatientIntegrationTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PatientService patientService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testCreateAndRetrievePatient() throws Exception {
        Patient newPatient = new Patient();
        newPatient.setId(1);
        newPatient.setNom("John");
        newPatient.setPrenom("Doe");
        newPatient.setAge(30);
        newPatient.setSexe("M");
        newPatient.setAdresse("123 Main St");
        newPatient.setNumero("123456789");
        newPatient.setUsername("testuser");

        // Mock the behavior of the patient service
        when(patientService.createPatient(any(Patient.class))).thenReturn(newPatient);
        when(patientService.getPatientById(1)).thenReturn(newPatient);

        // Create a new patient
        mockMvc.perform(post("/patient/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newPatient)))
                .andExpect(status().isOk())
                .andExpect(content().string("Inscription réussie"));  // Or whatever the successful signup message is

        // Retrieve and validate the created patient
        mockMvc.perform(get("/patient/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nom").value("John"))
                .andExpect(jsonPath("$.prenom").value("Doe"))
                .andExpect(jsonPath("$.age").value(30))
                .andExpect(jsonPath("$.sexe").value("M"))
                .andExpect(jsonPath("$.adresse").value("123 Main St"))
                .andExpect(jsonPath("$.numero").value("123456789"))
                .andExpect(jsonPath("$.username").value("testuser"));
    }
}
