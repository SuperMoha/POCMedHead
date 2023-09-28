package mshopital.controller;

import mshopital.model.Hopital;
import mshopital.service.HopitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/hopitaux")
public class HopitalController {

    @Autowired
    private HopitalService hopitalService;

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


    @GetMapping("/par-specialite/{specialite}")
    public List<Hopital> getHopitauxBySpecialite(@PathVariable String specialite) {
        return hopitalService.getHopitauxBySpecialite(specialite);
    }

    @GetMapping("/avec-lits-et-specialite/{specialite}")
    public List<Hopital> getHopitauxAvecLitsEtSpecialite(@PathVariable String specialite) {
        return hopitalService.getHopitauxAvecLitsEtSpecialite(specialite);
    }

    @GetMapping("/hopitaux-proches")
    public String[] getHopitauxProches(@RequestParam String adresse) {
        try {
            return hopitalService.getHopitauxProches(adresse);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
