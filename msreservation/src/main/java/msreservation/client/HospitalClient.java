package msreservation.client;

import feign.Headers;
import mshopital.model.Hopital;
import msreservation.config.FeignClientConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "MSHospital", url = "http://localhost:9000/hopitaux", configuration = FeignClientConfig.class)
public interface HospitalClient {

    @GetMapping("/{id}")
    Hopital getHopitalById(@PathVariable("id") int id);


    @PutMapping("/{id}")
    @Headers("Content-Type: application/json")
    void updateHopital(@PathVariable("id") int id, @RequestBody Hopital hopital);
}

