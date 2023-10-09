import { createApp } from 'vue'
import { createRouter, createWebHistory } from 'vue-router'
import App from './App.vue'
import Connexion from './components/Connexion.vue'
import Inscription from './components/Inscription.vue'
import Hopitaux from './components/Hopitaux.vue'

const router = createRouter({
    history: createWebHistory(),
    routes: [
        {
            path: '/',
            name: 'Connexion',
            component: Connexion,
            meta: { requiresNoAuth: true }
        },
        {
            path: '/Inscription',
            name: 'Inscription',
            component: Inscription,
            meta: { requiresNoAuth: true }
        },
        {
            path: '/Hopitaux',
            name: 'Hopitaux',
            component: Hopitaux,
            meta: { requiresAuth: true }
            
        }
    ]
})

router.beforeEach((to, from, next) => {
    const requiresAuth = to.matched.some(record => record.meta.requiresAuth);
    const requiresNoAuth = to.matched.some(record => record.meta.requiresNoAuth);
    const hasSession = localStorage.getItem("session") !== null;

    if (requiresAuth && !hasSession) {
        next('/'); 
    } else if (requiresNoAuth && hasSession) {
        next('/Hopitaux'); 
    } else {
        next(); 
    }
});

const app = createApp(App)
app.use(router).mount('#app')
app.mount('app')
