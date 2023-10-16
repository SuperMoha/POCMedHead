package mspatient.client;

import mspatient.model.Hopital;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "MSHospital", url = "http://localhost:9000/hopitaux")
public interface HospitalClient {

    @GetMapping("/{id}")
    Hopital getHopitalById(@PathVariable("id") int id);

    @PutMapping("/{id}")
    void updateHopital(@PathVariable("id") int id, @RequestBody Hopital hopital);
}

