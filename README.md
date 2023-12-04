
# POC MedHead


Le projet vise à développer un système d'intervention d'urgence en temps réel pour le consortium MedHead. Ce système a pour objectif d'améliorer la qualité des traitements d'urgence et de sauver plus de vies.


## Installation

Cloner le projet

```bash
git clone https://github.com/SuperMoha/POCMedHead.git
```
Aller dans le répertoire

```bash
cd POCMedHead
```

Construire les microservices

```bash
mvn package
```

Exécuter le frontend 

```bash
npm run dev
```   
## Screenshots

### Page de connexion
![App Screenshot](https://i.imgur.com/2wT0Vfa.png)
### Page d'inscription
![App Screenshot](https://i.imgur.com/CO0JgW4.png)
### Page de recherche d'hopitaux
![App Screenshot](https://i.imgur.com/RFNbN4o.png)
## Tests

Pour exécuter les tests côté backend

```bash
mvn test
```
Pour exécuter les tests côté frontend
```bash
npm run test:e2e
```
Les tests de stress sont réalisés grâce à l'outil JMeter, ils permettent de réaliser une montée en charge de l'application et de retourner des résultats.

[Fichier JMeter](https://github.com/SuperMoha/POCMedHead/blob/main/jmeter/Stress%20Test%20POCMedHead.jmx)

## Microservices

- Le microservice Hopital permet de faire la gestion des hopitaux.

- Le microservice Patient permet de faire la gestion des patients.

- Le microservice Reservation permet d'enregistrer les réservations des patients pour les lits d'hopitaux.



| PORT  | Microservice |
| ------------- | ------------- |
| 9000  | Hopital  |
| 9001  | Patient  |
| 9002  | Reservation  |

![App Screenshot](https://i.imgur.com/GWVY08R.png)

## Pipeline CI/CD

Ce fichier de configuration permet d'automatiser les tests, le build et le déploiement du projet selon les branches définies. Il utilise Maven pour les microservices Java et Node.js pour le frontend.

Le workflow Git retenu est structuré de manière à favoriser l'intégration continue et les déploiements réguliers. Il est recommandé de suivre ces bonnes pratiques pour assurer la stabilité et la fiabilité du projet.

[Fichier Pipeline](https://github.com/SuperMoha/POCMedHead/blob/main/.github/workflows/maven.yml)

## Workflow GIT

Ce projet utilise un workflow Git avec deux branches principales :

- **main** - branche contenant le code en production
- **develop** - branche pour l'intégration des nouvelles fonctionnalités