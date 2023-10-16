package mshopital.controller;

import jakarta.servlet.http.HttpSession;
import mshopital.model.Hopital;
import mshopital.service.HopitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/hopitaux")
public class HopitalController {

    @Autowired
    private HttpSession httpSession;

    private final HopitalService hopitalService;

    public HopitalController(HopitalService hopitalService) {
        this.hopitalService = hopitalService;
    }


    @GetMapping
    public List<Hopital> getAllHopitaux() {
        return hopitalService.getAllHopitaux();
    }

    @GetMapping("/{id}")
    public Hopital getHopitalById(@PathVariable int id) {
        return hopitalService.getHopitalById(id);
    }

    @PostMapping
    public void createHopital(@RequestBody Hopital hopital) {
        hopitalService.createHopital(hopital);
    }

    @PutMapping("/{id}")
    public void updateHopital(@PathVariable int id, @RequestBody Hopital updatedHopital) {
        hopitalService.updateHopital(id, updatedHopital);
    }

    @DeleteMapping("/{id}")
    public void deleteHopital(@PathVariable int id) {
        hopitalService.deleteHopital(id);
    }

    @GetMapping("/hopitaux-proches")
    public ResponseEntity<List<Hopital>> getHopitauxProches(@RequestParam String adresse, @RequestParam String specialiteDemandee) {

        try {
            List<Hopital> hopitauxList = hopitalService.getHopitauxProches(adresse, specialiteDemandee);
            return ResponseEntity.ok(hopitauxList);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
