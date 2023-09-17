package mspatient.controller;

import mspatient.model.Patient;
import mspatient.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/patient")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @GetMapping
    public List<Patient> getAllPatient() {
        return patientService.getAllPatient();
    }

    @GetMapping("/{id}")
    public Patient getPatientById(@PathVariable int id) {
        return patientService.getPatientById(id);
    }

    @PostMapping
    public void createPatient(@RequestBody Patient patient) {
        patientService.createPatient(patient);
    }

    @PutMapping("/{id}")
    public void updatePatient(@PathVariable int id, @RequestBody Patient updatedPatient) {
        patientService.updatePatient(id, updatedPatient);
    }

    @DeleteMapping("/{id}")
    public void deletePatient(@PathVariable int id) {
        patientService.deletePatient(id);
    }
}
