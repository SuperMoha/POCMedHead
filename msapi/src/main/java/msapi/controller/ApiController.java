package msapi.controller;

import mshopital.model.Hopital;
import mshopital.service.HopitalService;
import mspatient.model.Patient;
import mspatient.service.PatientService;
import msspecialite.model.Specialite;
import msspecialite.service.SpecialiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class ApiController {

    @Autowired
    private RestTemplate restTemplate;

    private final String HOPITAL_SERVICE_URL = "http://localhost:9000/hopitaux/";
    private final String PATIENT_SERVICE_URL = "http://localhost:9001/patient/";
    private final String SPECIALITE_SERVICE_URL = "http://localhost:9002/specialties/";

    @GetMapping("/api/hopitaux/{id}")
    public ResponseEntity<Hopital> getHospitalById(@PathVariable int id) {
        String url = HOPITAL_SERVICE_URL + id;
        ResponseEntity<Hopital> response = restTemplate.exchange(url, HttpMethod.GET, null, Hopital.class);
        return response;
    }

    @GetMapping("/api/patient/{id}")
    public ResponseEntity<Patient> getPatientById(@PathVariable int id) {
        String url = PATIENT_SERVICE_URL + id;
        ResponseEntity<Patient> response = restTemplate.exchange(url, HttpMethod.GET, null, Patient.class);
        return response;
    }

    @GetMapping("/api/specialite/{id}")
    public ResponseEntity<Specialite> getSpecialtyById(@PathVariable int id) {
        String url = SPECIALITE_SERVICE_URL + id;
        ResponseEntity<Specialite> response = restTemplate.exchange(url, HttpMethod.GET, null, Specialite.class);
        return response;
    }


}
