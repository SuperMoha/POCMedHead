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
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class PatientControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PatientService patientService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testGetAllPatient() throws Exception {

        String credentials = Base64.getEncoder().encodeToString("dupont:jean".getBytes());

        Authentication authentication = new UsernamePasswordAuthenticationToken("dupont", "jean");

        SecurityContextHolder.getContext().setAuthentication(authentication);

        when(patientService.getAllPatient()).thenReturn(Arrays.asList(new Patient(), new Patient()));
        mockMvc.perform(get("/patient")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetPatientById() throws Exception {
        Patient patient = new Patient();
        when(patientService.getPatientById(anyInt())).thenReturn(patient);

        mockMvc.perform(get("/patient/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testCreatePatient() throws Exception {
        Patient patient = new Patient();

        when(patientService.createPatient(any(Patient.class))).thenReturn(patient);


        mockMvc.perform(post("/patient/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new Patient())))
                .andExpect(status().isOk());
    }

    @Test
    public void testUpdatePatient() throws Exception {
        Patient patient = new Patient();
        when(patientService.getPatientById(anyInt())).thenReturn(patient);
        mockMvc.perform(put("/patient/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new Patient())))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeletePatient() throws Exception {
        Patient patient = new Patient();
        when(patientService.getPatientById(anyInt())).thenReturn(patient);
        mockMvc.perform(delete("/patient/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

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


        when(patientService.createPatient(any(Patient.class))).thenReturn(newPatient);
        when(patientService.getPatientById(1)).thenReturn(newPatient);


        mockMvc.perform(post("/patient/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newPatient)))
                .andExpect(status().isOk())
                .andExpect(content().string("Inscription réussie"));


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
