# POC MedHead

Le projet vise à développer un système d'intervention d'urgence en temps réel pour le consortium MedHead. Ce système a pour objectif d'améliorer la qualité des traitements d'urgence et de sauver plus de vies.
 
## Microservice : Hopital

Le microservice Hopital permet de faire la gestion des hopitaux.

- PORT : 9000

## Microservice : Patient

Le microservice Patient permet de faire la gestion des patients.

- PORT : 9001

## Microservice : Reservation

Le microservice Reservation permet d'enregistrer les réservations des patients pour les lits d'hopitaux.

- PORT : 9002

## Tests

# Test de stress

[StressTests](https://github.com/SuperMoha/POCMedHead/blob/main/jmeter/Stress%20Test%20POCMedHead.jmx)

Les tests de stress sont réalisés grâce à l'outil JMeter, ils permettent de réaliser une montée en charge de l'application et de retourner des résultats.

# Test du microservice Patient
cd mspatient

mvn test

# Test du microservice Hopital
cd mshopital

mvn test

# Test du microservice Reservation
cd msreservation

mvn test

## Build

# Build du microservice Patient
cd mspatient

mvn package

# Build du microservice Hopital
cd mshopital

mvn package

# Build du microservice Reservation
cd msreservation

mvn package

## Pipeline CI/CD

[Pipeline](https://github.com/SuperMoha/POCMedHead/blob/main/.github/workflows/maven.yml)


Ce fichier de configuration permet d'automatiser les tests, le build et le déploiement du projet selon les branches définies. Il utilise Maven pour les microservices Java et Node.js pour le frontend.

Le workflow Git retenu est structuré de manière à favoriser l'intégration continue et les déploiements réguliers. Il est recommandé de suivre ces bonnes pratiques pour assurer la stabilité et la fiabilité du projet.

## Workflow GIT

Ce projet utilise un workflow Git avec deux branches principales :

- **main** - branche contenant le code en production
- **develop** - branche pour l'intégration des nouvelles fonctionnalités
