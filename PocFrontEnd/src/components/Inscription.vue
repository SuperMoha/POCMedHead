<template>
  <div>
    <h1>Sign Up as Patient</h1>
    <form @submit.prevent="submitForm">
      <div>
        <label for="nom">Nom</label>
        <input type="text" id="nom" v-model="patient.nom" />
      </div>
      <div>
        <label for="prenom">Prenom</label>
        <input type="text" id="prenom" v-model="patient.prenom" />
      </div>
      <div>
        <label for="age">Age</label>
        <input type="number" id="age" v-model="patient.age" />
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
        <input type="text" id="adresse" v-model="patient.adresse" />
      </div>
      <div>
        <label for="numero">Numero</label>
        <input type="text" id="numero" v-model="patient.numero" />
      </div>
      <div>
        <label for="userName">Username</label>
        <input type="text" id="userName" v-model="patient.userName" />
      </div>
      <div>
        <label for="password">Password</label>
        <input type="password" id="password" v-model="patient.password" />
      </div>
      <button type="submit">Inscription</button>
    </form>
  </div>
</template>

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
      }
    };
  },
  methods: {
    async submitForm() {
      try {
        const response = await axios.post('http://localhost:9001/patient/signup', this.patient);
        if (response.data === 'Signup successful') {
          alert('Successfully signed up!');
          // You may navigate the user to a login page or dashboard here
        } else {
          alert('Signup failed: ' + response.data);
        }
      } catch (error) {
        console.error('An error occurred:', error);
      }
    }
  }
};
</script>