package msreservation.client;

import mshopital.model.Hopital;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "MSHospital", url = "http://localhost:9000/hopitaux")  // Replace with the actual URL of your MSHospital service
public interface HospitalClient {

    @GetMapping("/{id}")
    Hopital getHopitalById(@PathVariable("id") int id);

    @PutMapping("/{id}")
    void updateHopital(@PathVariable("id") int id, @RequestBody Hopital hopital);
}

