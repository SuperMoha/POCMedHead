package mshopital.service;

import mshopital.dao.HopitalRepository;
import mshopital.model.Hopital;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HopitalService {

    @Autowired
    private HopitalRepository hopitalRepository;

    public List<Hopital> getAllHopitaux() {
        return hopitalRepository.findAll();
    }

    public Hopital getHopitalById(int id) {
        return hopitalRepository.findById(id).orElse(null);
    }

    public void createHopital(Hopital hopital) {
        hopitalRepository.save(hopital);
    }

    public void updateHopital(int id, Hopital updatedHopital) {
        Hopital existingHopital = hopitalRepository.findById(id).orElse(null);
        if (existingHopital != null) {
            existingHopital.setNom(updatedHopital.getNom());
            existingHopital.setAdresse(updatedHopital.getAdresse());
            existingHopital.setSpecialite(updatedHopital.getSpecialite());
            existingHopital.setLits(updatedHopital.getLits());
            existingHopital.setLongitude(updatedHopital.getLongitude());
            existingHopital.setLatitude(updatedHopital.getLatitude());
            hopitalRepository.save(existingHopital);
        }
    }

    public void deleteHopital(int id) {
        hopitalRepository.deleteById(id);
    }
}
