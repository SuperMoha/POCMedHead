package mspatient;

import mspatient.dao.PatientRepository;
import mspatient.model.Patient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Transactional
public class PatientCrudIntegrationTest {

    @Autowired
    private PatientRepository patientRepository;

    @Test
    public void testCreateAndRetrievePatient() {

        Patient patient = new Patient();
        patient.setId(1);
        patient.setNom("John");
        patient.setPrenom("Doe");
        patient.setAge(30);
        patient.setSexe("M");
        patient.setAdresse("123 Main St");
        patient.setNumero("123456789");
        patient.setUsername("testuser");


        Patient savedPatient = patientRepository.save(patient);


        Patient retrievedPatient = patientRepository.findById(savedPatient.getId()).orElse(null);


        assertNotNull(retrievedPatient);


        assertEquals(1, retrievedPatient.getId());
        assertEquals("John", retrievedPatient.getNom());
        assertEquals("Doe", retrievedPatient.getPrenom());
        assertEquals(30, retrievedPatient.getAge());
        assertEquals("M", retrievedPatient.getSexe());
        assertEquals("123 Main St", retrievedPatient.getAdresse());
        assertEquals("123456789", retrievedPatient.getNumero());
        assertEquals("testuser", retrievedPatient.getUsername());
    }
}
