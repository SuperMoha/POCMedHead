package mshopital.service;

import mshopital.dao.HopitalRepository;
import mshopital.model.Hopital;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
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
    private List<Hopital> hopitaux = new ArrayList<>();

    public HopitalService() {
        // Initialisation factice des hôpitaux
        hopitaux.add(new Hopital(1, "Hôpital Fred Brooks", "Adresse1", new ArrayList<>(List.of("Cardiologie", "Immunologie")), 2, 1, 5));
        hopitaux.add(new Hopital(2, "Hôpital Julia Crusher", "Adresse2", new ArrayList<>(List.of("Cardiologie")), 0, 2, 6));
        hopitaux.add(new Hopital(3, "Hôpital Beverly Bashir", "Adresse3", new ArrayList<>(List.of("Immunologie", "Neuropathologie", "Diagnostic")), 5, 3, 7));
    }


    public void deleteHopital(int id) {
        hopitalRepository.deleteById(id);
    }

    public List<Hopital> getHopitauxBySpecialite(String specialite) {
        List<Hopital> hopitauxParSpecialite = new ArrayList<>();

        for (Hopital hopital : hopitalRepository.findAll()) {
            List<String> specialitesHopital = hopital.getSpecialite();

            for (String s : specialitesHopital) {
                List<String> specialites = Arrays.asList(s.split(", "));
                if (specialites.contains(specialite)) {
                    hopitauxParSpecialite.add(hopital);
                    break;
                }
            }
        }

        return hopitauxParSpecialite;
    }


    public List<Hopital> getHopitauxAvecLitsEtSpecialite(String specialite) {
        List<Hopital> hopitauxAvecLitsEtSpecialite = new ArrayList<>();

        for (Hopital hopital : hopitalRepository.findAll()) {
            List<String> specialitesHopital = hopital.getSpecialite();

            for (String s : specialitesHopital) {
                List<String> specialites = Arrays.asList(s.split(", "));

                if (specialites.contains(specialite) && hopital.getLits() > 0) {
                    hopitauxAvecLitsEtSpecialite.add(hopital);
                    break;
                }
            }
        }

        return hopitauxAvecLitsEtSpecialite;
    }


    public void reserverLits(int hopitalId, int nombreDeLitsAReserver) {
        Hopital hopital = hopitalRepository.findById(hopitalId).orElse(null);
        if (hopital != null) {
            hopital.reserverLit(nombreDeLitsAReserver);
            hopitalRepository.save(hopital);
        }
    }

}
