package mshopital.service;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;
import mshopital.dao.HopitalRepository;
import mshopital.model.Hopital;
import msreservation.model.Reservation;
import msreservation.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;

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



    @Value("${google.maps.apikey}")
    private String apiKey;
    public List<Hopital> getHopitauxProches(String adressePatient, String specialiteDemandee) throws Exception {
        GeoApiContext context = new GeoApiContext.Builder()
                .apiKey(apiKey)
                .build();

        GeocodingResult[] results = GeocodingApi.newRequest(context)
                .address(adressePatient)
                .await();

        if (results.length > 0) {
            double latitudePatient = results[0].geometry.location.lat;
            double longitudePatient = results[0].geometry.location.lng;

            String latitudeStr = String.valueOf(latitudePatient);
            String longitudeStr = String.valueOf(longitudePatient);

            System.out.println("Latitude : " + latitudeStr + ", Longitude : " + longitudeStr);


            List<Hopital> hopitaux = obtenirHopitaux();

            TreeMap<Double, Hopital> distances = new TreeMap<>();
            for (Hopital hopital : hopitaux) {
                if (hopital.getLits() > 0 && specialiteContient(hopital.getSpecialite(), specialiteDemandee)) {
                    double distance = calculerDistance(
                            latitudePatient, longitudePatient,
                            hopital.getLatitude(), hopital.getLongitude()
                    );
                    distances.put(distance, hopital);
                }
            }

            List<Hopital> hopitauxOrdres = new ArrayList<>();
            for (Hopital hopital : distances.values()) {
                hopitauxOrdres.add(hopital);
            }

            return hopitauxOrdres;
        } else {
            throw new Exception("Impossible d'obtenir les coordonnées pour l'adresse fournie.");
        }
    }

    private boolean specialiteContient(List<String> specialitesHopital, String specialiteDemandee) {
        for (String specialite : specialitesHopital) {
            if (specialite.toLowerCase().contains(specialiteDemandee.toLowerCase())) {
                return true;
            }
        }
        return false;
    }

    private List<Hopital> obtenirHopitaux() {
        return getAllHopitaux();

    }

    private double calculerDistance(double lat1, double lon1, double lat2, double lon2) {
        final int R = 6371;

        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);

        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return R * c;
    }





}
