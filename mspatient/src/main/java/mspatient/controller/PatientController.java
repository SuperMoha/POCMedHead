package mspatient.controller;

import jakarta.servlet.http.HttpSession;
import mspatient.exception.PatientNotFoundException;
import mspatient.model.Patient;
import mspatient.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/patient")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @Autowired
    private AuthenticationManager authenticationManager;


    @GetMapping("/{id}")
    public Patient getPatientById(@PathVariable int id) {
        return patientService.getPatientById(id);
    }


    @PutMapping("/{id}")
    public void updatePatient(@PathVariable int id, @RequestBody Patient updatedPatient) {
        patientService.updatePatient(id, updatedPatient);
    }

    @DeleteMapping("/{id}")
    public void deletePatient(@PathVariable int id) {
        patientService.deletePatient(id);
    }

    @PostMapping("/signup")
    public String signup(@RequestBody Patient patient) {
        if (patientService.usernameExists(patient.getUsername())) {
            return "Erreur l'utilisateur existe déjà";
        } else if (patientService.createPatient(patient) != null) {
            return "Inscription réussie";
        } else {
            return "Inscription échouée";
        }
    }

    @GetMapping
    public ResponseEntity<Patient> getAllPatients() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = auth.getName();

        try {
            Patient patient = patientService.getPatientByUserName(currentUserName);
            return new ResponseEntity<>(patient, HttpStatus.OK);
        } catch (PatientNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Patient requestPatient, HttpSession session) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(requestPatient.getUsername(), requestPatient.getPassword());
        try {
            Authentication auth = authenticationManager.authenticate(authenticationToken);

            SecurityContextHolder.getContext().setAuthentication(auth);
            session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, SecurityContextHolder.getContext());

            Patient patient = patientService.getPatientByUserName(requestPatient.getUsername());

            int patientId = patient.getId();

            session.setAttribute("patientId", patientId);

            Map<String, Object> response = new HashMap<>();
            response.put("message", "Login successful");
            response.put("patientId", patientId);
            response.put("session", session.getId());

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (BadCredentialsException e) {
            return new ResponseEntity<>("Utilisateur ou mot de passe invalide", HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            return new ResponseEntity<>("Connexion échouée", HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/deconnexion")
    public ResponseEntity<?> deconnexion(HttpSession session) {
        session.invalidate();
        return new ResponseEntity<>("Déconnexion réussie", HttpStatus.OK);
    }


}
