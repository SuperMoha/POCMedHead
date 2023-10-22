package msreservation.client;

import feign.Headers;
import msreservation.config.FeignClientConfig;
import msreservation.model.Hopital;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "MSHospital", url = "http://localhost:9000/hopitaux", configuration = FeignClientConfig.class)
public interface HospitalClient {

    @GetMapping("/{id}")
    Hopital getHopitalById(@PathVariable("id") int id);


    @PutMapping("/{id}")
    @Headers("Content-Type: application/json")
    void updateHopital(@PathVariable("id") int id, @RequestBody Hopital hopital);
}

