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
@Transactional // Rollback the transaction after each test to keep the database in a clean state
public class PatientCrudIntegrationTest {

    @Autowired
    private PatientRepository patientRepository;

    @Test
    public void testCreateAndRetrievePatient() {
        // Create a new patient
        Patient patient = new Patient();
        patient.setId(1);
        patient.setNom("John");
        patient.setPrenom("Doe");
        patient.setAge(30);
        patient.setSexe("M");
        patient.setAdresse("123 Main St");
        patient.setNumero("123456789");
        patient.setUsername("testuser");

        // Save the patient to the database
        Patient savedPatient = patientRepository.save(patient);

        // Retrieve the patient from the database by ID
        Patient retrievedPatient = patientRepository.findById(savedPatient.getId()).orElse(null);

        // Assert that the retrieved patient is not null
        assertNotNull(retrievedPatient);

        // Add more assertions as needed to check if the data is correct
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
