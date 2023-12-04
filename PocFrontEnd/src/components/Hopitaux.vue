<template>


  <div class="login-root">
    <div class="box-root flex-flex flex-direction--column" style="min-height: 100vh;flex-grow: 1;">
      <div class="loginbackground box-background--white padding-top--64">
       
        <div class="loginbackground-gridContainer">
          
          <div class="box-root flex-flex" style="grid-area: top / start / 8 / end;">
            
            <div class="box-root"
              style="background-image: linear-gradient(white 0%, rgb(247, 250, 252) 33%); flex-grow: 1;">
            </div>
          </div>
          
          <div class="box-root flex-flex" style="grid-area: 4 / 2 / auto / 5;">
            
            <div class="box-root box-divider--light-all-2 animationLeftRight tans3s" style="flex-grow: 1;"></div>
          </div>
          <div class="box-root flex-flex" style="grid-area: 6 / start / auto / 2;">
            <div class="box-root box-background--blue800" style="flex-grow: 1;"></div>
          </div>
          <div class="box-root flex-flex" style="grid-area: 7 / start / auto / 4;">
            <div class="box-root box-background--blue animationLeftRight" style="flex-grow: 1;"></div>
          </div>
          <div class="box-root flex-flex" style="grid-area: 8 / 4 / auto / 6;">
            <div class="box-root box-background--gray100 animationLeftRight tans3s" style="flex-grow: 1;"></div>
          </div>
          <div class="box-root flex-flex" style="grid-area: 2 / 15 / auto / end;">
            <div class="box-root box-background--cyan200 animationRightLeft tans4s" style="flex-grow: 1;"></div>
          </div>
          <div class="box-root flex-flex" style="grid-area: 3 / 14 / auto / end;">
            <div class="box-root box-background--blue animationRightLeft" style="flex-grow: 1;"></div>
          </div>
          <div class="box-root flex-flex" style="grid-area: 4 / 17 / auto / 20;">
            <div class="box-root box-background--gray100 animationRightLeft tans4s" style="flex-grow: 1;"></div>
          </div>
          <div class="box-root flex-flex" style="grid-area: 5 / 14 / auto / 17;">
            <div class="box-root box-divider--light-all-2 animationRightLeft tans3s" style="flex-grow: 1;"></div>
          </div>
        </div>
      </div>
      
      <div class="box-root padding-top--24 flex-flex flex-direction--column" style="flex-grow: 1; z-index: 9;">
    <div class="box-root padding-top--48 padding-bottom--24 flex-flex flex-justifyContent--center">
      
      <h1>Hopitaux</h1>
    </div>
    <div class="formbg-outer">
      <div class="formbg">
        <div class="formbg-inner padding-horizontal--48">


              <div class="field padding-bottom--24">
                <input id="deconnexion" @click="deconnexion" class="field button padding-bottom--24" value="Déconnexion" type="button">

                <form class="field padding-bottom--24" @submit.prevent="fetchHopitauxProches">
                  <label for="adresse">Veuillez saisir votre adresse actuelle :</label>
                  <input ref="adresse" id="adresse" type="text" required>
                  <br>
                  <label for="specialite">Spécialité voulue:</label>
                  <select ref="specialite" id="specialite" required>
                    <option v-for="specialite in specialites" :value="specialite" :key="specialite">
                      {{ specialite }}
                    </option>
                  </select>
                  <br>
                  <input class="field input button padding-bottom--24" type="submit" value="Valider">
                </form>



                <div v-if="hopitaux.length > 0">
              <h2>L'hopital le plus proche est le suivant :</h2>
              <ul>
                <li :key="hopitaux[0].id">
                  <div class="highlighted-hopital">
                    {{ hopitaux[0].nom }} situé à {{ hopitaux[0].adresse }} possédant : {{ hopitaux[0].lits }} lits
                    <button id="reserver" @click="reserverHopital(hopitaux[0])" class="field input button">Réserver</button>
                  </div>
                </li>
              </ul>
            </div>
            <div v-else>
              <p></p>
            </div>

            <input id="supp" @click="showHopitaux = !showHopitaux" class="field button " type="submit"  value="Afficher/Masquer les hopitaux supp.">

            <div v-if="showHopitaux">
              <h2>Autres hopitaux à proximité :</h2>
              <ul>
                <li v-for="(hopital, index) in hopitaux.slice(1)" :key="hopital.id">
                  <div>
                    {{ hopital.nom }} situé à {{ hopital.adresse }} possédant : {{ hopital.lits }} lits
                  </div>
                </li>
              </ul>
            </div>
</div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<style scoped>
.highlighted-hopital {
  border: 2px solid #ff5733;
  padding: 5px; 
  border-radius: 5px;
}
</style>
<style>
* {
  padding: 0;
  margin: 0;
  color: #1a1f36;
  box-sizing: border-box;
  word-wrap: break-word;
  font-family: -apple-system, BlinkMacSystemFont, Segoe UI, Roboto, Helvetica Neue, Ubuntu, sans-serif;
}

body {
  min-height: 100%;
  background-color: #ffffff;
}

h1 {
  letter-spacing: -1px;
}

a {
  color: #5469d4;
  text-decoration: unset;
}

.login-root {
  background: #fff;
  display: flex;
  width: 100%;
  min-height: 100vh;
  overflow: hidden;
}

.loginbackground {
  min-height: 692px;
  position: fixed;
  bottom: 0;
  left: 0;
  right: 0;
  top: 0;
  z-index: 0;
  overflow: hidden;
}

.flex-flex {
  display: flex;
}

.align-center {
  align-items: center;
}

.center-center {
  align-items: center;
  justify-content: center;
}

.box-root {
  box-sizing: border-box;
}

.flex-direction--column {
  -ms-flex-direction: column;
  flex-direction: column;
}

.loginbackground-gridContainer {
  display: -ms-grid;
  display: grid;
  -ms-grid-columns: [start] 1fr [left-gutter] (86.6px)[16] [left-gutter] 1fr [end];
  grid-template-columns: [start] 1fr [left-gutter] repeat(16, 86.6px) [left-gutter] 1fr [end];
  -ms-grid-rows: [top] 1fr [top-gutter] (64px)[8] [bottom-gutter] 1fr [bottom];
  grid-template-rows: [top] 1fr [top-gutter] repeat(8, 64px) [bottom-gutter] 1fr [bottom];
  justify-content: center;
  margin: 0 -2%;
  transform: rotate(-12deg) skew(-12deg);
}

.box-divider--light-all-2 {
  box-shadow: inset 0 0 0 2px #e3e8ee;
}

.box-background--blue {
  background-color: #5469d4;
}

.box-background--white {
  background-color: #ffffff;
}

.box-background--blue800 {
  background-color: #212d63;
}

.box-background--gray100 {
  background-color: #e3e8ee;
}

.box-background--cyan200 {
  background-color: #7fd3ed;
}

.padding-top--64 {
  padding-top: 64px;
}

.padding-top--24 {
  padding-top: 24px;
}

.padding-top--48 {
  padding-top: 48px;
}

.padding-bottom--24 {
  padding-bottom: 24px;
}

.padding-horizontal--48 {
  padding: 48px;
}

.padding-bottom--15 {
  padding-bottom: 15px;
}


.flex-justifyContent--center {
  -ms-flex-pack: center;
  justify-content: center;
}

.formbg {
  margin: 0px auto;
  width: 100%;
  max-width: 448px;
  background: white;
  border-radius: 4px;
  box-shadow: rgba(60, 66, 87, 0.12) 0px 7px 14px 0px, rgba(0, 0, 0, 0.12) 0px 3px 6px 0px;
}

span {
  display: block;
  font-size: 20px;
  line-height: 28px;
  color: #1a1f36;
}

label {
  margin-bottom: 10px;
}

.reset-pass a,
label {
  font-size: 14px;
  font-weight: 600;
  display: block;
}

.reset-pass>a {
  text-align: right;
  margin-bottom: 10px;
}

.grid--50-50 {
  display: grid;
  grid-template-columns: 50% 50%;
  align-items: center;
}

.field input {
  font-size: 16px;
  line-height: 28px;
  padding: 8px 16px;
  width: 100%;
  min-height: 44px;
  border: unset;
  border-radius: 4px;
  outline-color: rgb(84 105 212 / 0.5);
  background-color: rgb(255, 255, 255);
  box-shadow: rgba(0, 0, 0, 0) 0px 0px 0px 0px,
    rgba(0, 0, 0, 0) 0px 0px 0px 0px,
    rgba(0, 0, 0, 0) 0px 0px 0px 0px,
    rgba(60, 66, 87, 0.16) 0px 0px 0px 1px,
    rgba(0, 0, 0, 0) 0px 0px 0px 0px,
    rgba(0, 0, 0, 0) 0px 0px 0px 0px,
    rgba(0, 0, 0, 0) 0px 0px 0px 0px;
}

input[type="submit"] {
  background-color: rgb(84, 105, 212);
  box-shadow: rgba(0, 0, 0, 0) 0px 0px 0px 0px,
    rgba(0, 0, 0, 0) 0px 0px 0px 0px,
    rgba(0, 0, 0, 0.12) 0px 1px 1px 0px,
    rgb(84, 105, 212) 0px 0px 0px 1px,
    rgba(0, 0, 0, 0) 0px 0px 0px 0px,
    rgba(0, 0, 0, 0) 0px 0px 0px 0px,
    rgba(60, 66, 87, 0.08) 0px 2px 5px 0px;
  color: #fff;
  font-weight: 600;
  cursor: pointer;
}

.field-checkbox input {
  width: 20px;
  height: 15px;
  margin-right: 5px;
  box-shadow: unset;
  min-height: unset;
}

.field-checkbox label {
  display: flex;
  align-items: center;
  margin: 0;
}

a.ssolink {
  display: block;
  text-align: center;
  font-weight: 600;
}

.footer-link span {
  font-size: 14px;
  text-align: center;
}

.listing a {
  color: #697386;
  font-weight: 600;
  margin: 0 10px;
}

.animationRightLeft {
  animation: animationRightLeft 2s ease-in-out infinite;
}

.animationLeftRight {
  animation: animationLeftRight 2s ease-in-out infinite;
}

.tans3s {
  animation: animationLeftRight 3s ease-in-out infinite;
}

.tans4s {
  animation: animationLeftRight 4s ease-in-out infinite;
}

@keyframes animationLeftRight {
  0% {
    transform: translateX(0px);
  }

  50% {
    transform: translateX(1000px);
  }

  100% {
    transform: translateX(0px);
  }
}

@keyframes animationRightLeft {
  0% {
    transform: translateX(0px);
  }

  50% {
    transform: translateX(-1000px);
  }

  100% {
    transform: translateX(0px);
  }
}

.button {
  background-color: rgb(84, 105, 212);
  box-shadow: rgba(0, 0, 0, 0) 0px 0px 0px 0px,
    rgba(0, 0, 0, 0) 0px 0px 0px 0px,
    rgba(0, 0, 0, 0.12) 0px 1px 1px 0px,
    rgb(84, 105, 212) 0px 0px 0px 1px,
    rgba(0, 0, 0, 0) 0px 0px 0px 0px,
    rgba(0, 0, 0, 0) 0px 0px 0px 0px,
    rgba(60, 66, 87, 0.08) 0px 2px 5px 0px;
  color: rgb(84, 105, 212);
  font-weight: 600;
  cursor: pointer;
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
      hopitaux: [],
      showHopitaux: false
    };
  },
  methods: {
    fetchHopitauxProches() {
      const adresse = this.$refs.adresse.value;
      const specialite = this.$refs.specialite.value;
      const url = `http://localhost:9000/hopitaux/hopitaux-proches?adresse=${adresse}&specialiteDemandee=${specialite}`;

      fetch(url, {
        method: 'GET',
        credentials: 'include',
      })
        .then(response => response.json())
        .then(data => {
          console.log(data[0].nom);

          this.hopitaux = data;
        })
        .catch(error => console.error('Erreur:', error))
    },
    reserverHopital(hopital) {
      
      axios.post('http://localhost:9002/reservation', {
        hopitalId: hopital.id,
        patientId: localStorage.getItem("lepatient")
      }, {
        withCredentials: true
      })
        .then(response => {
          console.log('Réservation réussie !', response.data);
        })
        .catch(error => {
        });

      alert(`Lit réservé : ${hopital.nom}`);

    
    },

    deconnexion() {

      localStorage.removeItem('patientId');
      localStorage.removeItem('session');


  axios.get('http://localhost:9001/patient/deconnexion', {
    withCredentials: true
  })
  .then(response => {
    console.log('Déconnexion réussie !', response.data);
    window.location.href = '/';


  })
  .catch(error => {
    console.error('Erreur lors de la déconnexion:', error);
  });
}
  }
};
</script>