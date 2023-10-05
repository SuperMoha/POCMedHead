<template>
    <div>
      <h1>Sign Up as Patient</h1>
      <form @submit.prevent="submitForm">
        <div>
          <label for="userName">Nom d'utilisateur</label>
          <input type="text" id="userName" v-model="patient.userName" />
        </div>
        <div>
          <label for="password">Mot de passe</label>
          <input type="password" id="password" v-model="patient.password" />
        </div>
        <button type="submit">Connexion</button>
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