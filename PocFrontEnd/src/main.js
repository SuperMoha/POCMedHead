import { createApp } from 'vue'
import { createRouter, createWebHistory } from 'vue-router'
import App from './App.vue'
import Home from './components/Home.vue'
import Connexion from './components/Connexion.vue'
import Inscription from './components/Inscription.vue'
import Hopitaux from './components/Hopitaux.vue'

const router = createRouter({
    history: createWebHistory(),
    routes: [
        {
            path: '/',
            name: 'Home',
            component: Home
        },
        {
            path: '/Connexion',
            name: 'Connexion',
            component: Connexion
        },
        {
            path: '/Inscription',
            name: 'Inscription',
            component: Inscription
        },
        {
            path: '/Hopitaux',
            name: 'Hopitaux',
            component: Hopitaux
        }
    ]
})

const app = createApp(App)
app.use(router).mount('#app')
app.mount('app')
