package msspecialite.controller;

import msspecialite.model.Specialite;
import msspecialite.service.SpecialiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/specialite")
public class SpecialiteController {

    @Autowired
    private SpecialiteService specialiteService;

    @GetMapping
    public List<Specialite> getAllSpecialite() {
        return specialiteService.getAllSpecialite();
    }

    @GetMapping("/{id}")
    public Specialite getSpecialiteById(@PathVariable int id) {
        return specialiteService.getSpecialiteById(id);
    }

    @PostMapping
    public void createSpecialite(@RequestBody Specialite specialite) {
        specialiteService.createSpecialite(specialite);
    }

    @PutMapping("/{id}")
    public void updateSpecialite(@PathVariable int id, @RequestBody Specialite updatedSpecialite) {
        specialiteService.updateSpecialite(id, updatedSpecialite);
    }

    @DeleteMapping("/{id}")
    public void deleteSpecialite(@PathVariable int id) {
        specialiteService.deleteSpecialite(id);
    }
}
