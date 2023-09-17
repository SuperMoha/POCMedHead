package msspecialite.service;

import msspecialite.dao.SpecialiteRepository;
import msspecialite.model.Specialite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpecialiteService {

    @Autowired
    private SpecialiteRepository specialiteRepository;

    public List<Specialite> getAllSpecialite() {
        return specialiteRepository.findAll();
    }

    public Specialite getSpecialiteById(int id) {
        return specialiteRepository.findById(id).orElse(null);
    }

    public void createSpecialite(Specialite specialite) {
        specialiteRepository.save(specialite);
    }

    public void updateSpecialite(int id, Specialite updatedSpecialite) {
        Specialite existingSpecialite = specialiteRepository.findById(id).orElse(null);
        if (existingSpecialite != null) {
            existingSpecialite.setNom(updatedSpecialite.getNom());
            existingSpecialite.setDescription(updatedSpecialite.getDescription());
            specialiteRepository.save(existingSpecialite);
        }
    }

    public void deleteSpecialite(int id) {
        specialiteRepository.deleteById(id);
    }
}
