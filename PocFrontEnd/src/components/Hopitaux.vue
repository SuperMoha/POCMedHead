<template>
    <div>
      <h1>Hopitaux</h1>

      <form @submit.prevent="fetchHopitauxProches">
  <label for="adresse">Veuillez saisir votre adresse:</label>
  <input ref="adresse" type="text" required>
  <br>
  <label for="specialite">Spécialité :</label>
  <select ref="specialite" required>
    <option v-for="specialite in specialites" :value="specialite" :key="specialite">
          {{ specialite }}
        </option>
  </select>
  <br>
  <button type="submit">Valider</button>
</form>



    <div v-if="hopitaux.length > 0">
      <h2>Résultats:</h2>
      <ul>
        <li v-for="(hopital, index) in hopitaux" :key="hopital.id">
          <div :class="{ 'highlighted-hopital': index === 0 }">
          {{ hopital.nom}} - {{ hopital.adresse}} - Nombre de lits disponibles : {{ hopital.lits}}
          <button v-if="index === 0" @click="reserverHopital(hopital)">Réserver</button>
        </div>
        </li>
      </ul>
    </div>
    <div v-else>
      <p></p>
    </div>
  </div>
    
  </template>
  
  <style>
  .highlighted-hopital {
    border: 2px solid #1b48aa; 
    padding: 10px; 
    background-color: #033aaf; 
  }
  </style>

  <script>
  import axios from 'axios';

  export default {
    data() {
      return {
        specialites: [
  "Anesthésie",
  "Soins intensifs",
  "Oncologie clinique",
  "Spécialités dentaires supplémentaires",
  "Radiologie dentaire et maxillo-faciale",
  "Endodontie",
  "Chirurgie buccale et maxillo-faciale",
  "Pathologie buccale et maxillo-faciale",
  "Médecine buccale",
  "Chirurgie buccale",
  "Orthodontie",
  "Dentisterie pédiatrique",
  "Parodontie",
  "Prosthodontie",
  "Dentisterie restauratrice",
  "Dentisterie de soins spéciaux",
  "Médecine d'urgence",
  "Médecine interne de soins aigus",
  "Allergie",
  "Médecine audiovestibulaire",
  "Cardiologie",
  "Génétique clinique",
  "Neurophysiologie clinique",
  "Pharmacologie clinique et thérapeutique",
  "Dermatologie",
  "Endocrinologie et diabète sucré",
  "Gastroentérologie",
  "Médecine générale (interne)",
  "Médecine générale",
  "Médecine générale (GP) 6 mois",
  "Médecine génito-urinaire",
  "Médecine gériatrique",
  "Maladies infectieuses",
  "Oncologie médicale",
  "Ophtalmologie médicale",
  "Neurologie",
  "Médecine du travail",
  "Autre",
  "Médecine palliative",
  "Médecine de réadaptation",
  "Médecine rénale",
  "Médecine respiratoire",
  "Rhumatologie",
  "Médecine du sport et de l'exercice",
  "Santé publique sexuelle et procréative",
  "Cardiologie pédiatrique",
  "Pédiatrie",
  "Pathologie chimique",
  "Neuropathologie diagnostique",
  "Histopathologie médico-légale",
  "Pathologie générale",
  "Hématologie",
  "Histopathologie",
  "Immunologie",
  "Microbiologie médicale",
  "Pathologie pédiatriqueet périnatale",
  "Virologie",
  "Service de santé communautaire dentaire",
  "Service de santé communautaire médicale",
  "Santé publique dentaire",
  "Pratique de l'art dentaire",
  "Santé publique",
  "Psychiatrie infantile et adolescente",
  "Psychiatrie légale",
  "Psychiatrie générale",
  "Psychiatrie de la vieillessse",
  "Psychiatrie des troubles d'apprentissage",
  "Psychothérapie",
  "Radiologie clinique",
  "Médecine nucléaire",
  "Chirurgie cardiothoracique",
  "Chirurgie générale",
  "Neurochirurgie",
  "Ophtalmologie",
  "Otolaryngologie",
  "Chirurgie pédiatrique",
  "Chirurgie plastique",
  "Traumatologie et chrirurgie orthopédique",
  "Urologie",
  "Chirurgie vasculaire"
],
        hopitaux: []
      };
    },
    methods: {
      fetchHopitauxProches() {
        const adresse = this.$refs.adresse.value; 
        const specialite = this.$refs.specialite.value;
        const url = `http://localhost:9000/hopitaux/hopitaux-proches?adresse=${adresse}&specialiteDemandee=${specialite}`;

        fetch(url)
          .then(response => response.json())
          .then(data => {
            console.log(data[0].nom);

            this.hopitaux = data;
          })
          .catch(error => console.error('Erreur:', error))
      },
      reserverHopital(hopital) {
        
        const patientId = 2; // Remplacez par l'ID du patient approprié

axios.post('http://localhost:9002/reservation', {
  hopitalId: hopital.id,
  patientId: patientId
})
.then(response => {
  console.log('Réservation réussie !', response.data);
})
.catch(error => {
  console.error('Erreur lors de la réservation :', error);
});

      console.log(`Hôpital réservé : ${hopital.nom}`);
    }
    }
  };
  </script>