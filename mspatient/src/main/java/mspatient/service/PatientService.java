package mspatient.service;

import mspatient.dao.PatientRepository;
import mspatient.exception.PatientNotFoundException;
import mspatient.model.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private PasswordEncoder bCryptPasswordEncoder;

    public boolean usernameExists(String userName) {
        return patientRepository.findByUsername(userName).isPresent();
    }

    public Patient getPatientByUserName(String userName) {
        return patientRepository.findByUsername(userName)
                .orElseThrow(() -> new PatientNotFoundException("Patient non trouvé"));
    }


    public List<Patient> getAllPatient() {
        return patientRepository.findAll();
    }

    public Patient getPatientById(int id) {
        return patientRepository.findById(id).orElse(null);
    }

    public Patient createPatient(Patient patient) {
        patient.setPassword(bCryptPasswordEncoder.encode(patient.getPassword()));
        return patientRepository.save(patient);
    }

    public void updatePatient(int id, Patient updatedPatient) {
        Patient existingPatient = patientRepository.findById(id).orElse(null);
        if (existingPatient != null) {
            existingPatient.setNom(updatedPatient.getNom());
            existingPatient.setPrenom(updatedPatient.getPrenom());
            existingPatient.setAge(updatedPatient.getAge());
            existingPatient.setSexe(updatedPatient.getSexe());
            existingPatient.setAdresse(updatedPatient.getAdresse());
            existingPatient.setNumero(updatedPatient.getNumero());
            patientRepository.save(existingPatient);
        }
    }

    public void deletePatient(int id) {
        patientRepository.deleteById(id);
    }
}
