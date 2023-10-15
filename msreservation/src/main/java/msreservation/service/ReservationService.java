package msreservation.service;

import mshopital.dao.HopitalRepository;
import mshopital.model.Hopital;
import mshopital.service.HopitalService;
import mspatient.dao.PatientRepository;
import mspatient.model.Patient;
import msreservation.client.HospitalClient;
import msreservation.client.PatientClient;
import msreservation.dao.ReservationRepository;
import msreservation.model.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private HospitalClient hospitalClient;

    @Autowired
    private PatientClient patientClient;


    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    public Reservation getReservationById(int id) {
        return reservationRepository.findById(id).orElse(null);
    }

    public void deleteReservation(int id) {
        reservationRepository.deleteById(id);
    }


    public void createReservation(Reservation reservation) {

        reservationRepository.save(reservation);

        Patient patient = patientClient.getPatientById(reservation.getPatientId());

        Hopital hopital = hospitalClient.getHopitalById(reservation.getHopitalId());

        if (hopital == null) {
            throw new RuntimeException("L'hôpital spécifié n'existe pas.");
        }

        int litsActuels = hopital.getLits();

        if (litsActuels > 0) {
            hopital.setLits(litsActuels - 1);
            patientClient.updatePatient(patient.getId(), patient);
            hospitalClient.updateHopital(hopital.getId(), hopital);

        } else {

            reservationRepository.delete(reservation);
            throw new RuntimeException("Pas assez de lits disponibles dans cet hôpital. La réservation a été annulée.");
        }
    }

    public void updateReservation(int id, Reservation updatedReservation) {
        Reservation existingReservation = reservationRepository.findById(id).orElse(null);
        if (existingReservation != null) {
            existingReservation.setId(updatedReservation.getId());
            existingReservation.setHopitalId(updatedReservation.getHopitalId());
            existingReservation.setPatientId(updatedReservation.getPatientId());
            reservationRepository.save(existingReservation);
        }
    }
}
