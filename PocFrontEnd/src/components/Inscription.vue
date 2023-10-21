<template>
  <body>
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
            <h1><a rel="dofollow">Inscription</a></h1>
          </div>
          <div class="formbg-outer">
            <div class="formbg">
              <div class="formbg-inner padding-horizontal--48">
                <form id="stripe-login" @submit.prevent="submitForm">
                  <div>
                    <label for="nom">Nom</label>
                    <input required type="text" id="nom" v-model="patient.nom" />
                  </div>
                  <div>
                    <label for="prenom">Prenom</label>
                    <input required type="text" id="prenom" v-model="patient.prenom" />
                  </div>
                  <div>
                    <label for="age">Age</label>
                    <input required type="number" id="age" v-model="patient.age" />
                  </div>
                  <div>
                    <label for="sexe">Sexe</label>
                    <select id="sexe" v-model="patient.sexe">
                      <option value="Male">Male</option>
                      <option value="Female">Female</option>
                    </select>
                  </div>
                  <div>
                    <label for="adresse">Adresse</label>
                    <input required type="text" id="adresse" v-model="patient.adresse" />
                  </div>
                  <div>
                    <label for="numero">Numero</label>
                    <input required type="text" id="numero" v-model="patient.numero" />
                  </div>
                  <div>
                    <label for="username">Username</label>
                    <input required type="text" id="username" v-model="patient.username" />
                  </div>
                  <div>
                    <label for="password">Password</label>
                    <input required type="password" id="password" v-model="patient.password" />
                  </div>

                  <div class="rgpd-link" data-cy="rgpd" @click="openRGPDPopup">Consentement RGPD</div>

                  <div v-if="showRGPDPopup" class="rgpd-popup">
                    <div class="rgpd-popup-content">
                      <p>En soumettant ce formulaire, vous consentez au traitement de vos données personnelles
                        conformément à notre politique de confidentialité et aux lois en vigueur.</p>
                      <p>Vos informations seront utilisées uniquement dans le cadre des services que nous proposons et ne
                        seront pas partagées avec des tiers sans votre consentement explicite.</p>
                      <label for="rgpd-consent">J'accepte les consignes RGPD</label>
                      <input type="checkbox" id="rgpd-consent" v-model="rgpdConsent" />
                      <button @click="closeRGPDPopup">Fermer</button>
                    </div>
                  </div>
                  <label for="password">Déjà inscrit ? <router-link data-cy="connexion" to="/">Connexion</router-link></label>
                  <div class="field padding-bottom--24">
                    <input data-cy="submit" type="submit" name="submit" value="S'inscrire">
                  </div>

                </form>
              </div>
            </div>

          </div>
        </div>
      </div>
    </div>
  </body>
</template>




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
</style>


<script>
import axios from 'axios';

export default {
  data() {
    return {
      patient: {
        nom: '',
        prenom: '',
        age: null,
        sexe: 'Male',
        adresse: '',
        numero: '',
        userName: '',
        password: ''
      },
      showRGPDPopup: false,
      rgpdConsent: false
    };
  },
  methods: {
    async submitForm() {
      try {
        if (!this.rgpdConsent) {
          alert('Vous devez accepter les consignes RGPD.');
          return;
        }
        const response = await axios.post('http://localhost:9001/patient/signup', this.patient);
        if (response.data === 'Inscription réussie') {
          alert('Inscription réussie !');
          window.location.href = '/';
        } else {
          alert(response.data);
        }
      } catch (error) {
        console.error('Une erreur s\'est produite :', error);
      }
    },
    openRGPDPopup() {
      this.showRGPDPopup = true;
    },
    closeRGPDPopup() {
      this.showRGPDPopup = false;
    }
  }
};
</script>