package msreservation.client;

import mshopital.model.Hopital;
import mspatient.model.Patient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "MSPatient", url = "http://localhost:9001/patient")
public interface PatientClient {

    @GetMapping("/{id}")
    Patient getPatientById(@PathVariable("id") int id);

    @PutMapping("/{id}")
    void updatePatient(@PathVariable("id") int id, @RequestBody Patient patient);
}

