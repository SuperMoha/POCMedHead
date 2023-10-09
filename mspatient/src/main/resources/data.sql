CREATE TABLE  IF NOT EXISTS `patient` (
    `nom` VARCHAR(512),
    `prenom` VARCHAR(512),
    `age` INT,
    `sexe` VARCHAR(20),
    `adresse` VARCHAR(512),
    `numero` VARCHAR(20),
    `username` VARCHAR(20),
    `password` VARCHAR(256)
    );

INSERT INTO patient (nom, prenom, age, sexe, adresse, numero, username, password)
VALUES ('Dupont', 'Jean', 30, 'Homme', '5 S Bank Rd, Edge Hill, Liverpool L7 9LP, United Kingdom', '1234567890','dupont','$2a$12$95XIfOuFGG.BcP.TxDgBzOyDl/tFwUl3o2LyrtSumTrUk/hixZztO');

INSERT INTO patient (nom, prenom, age, sexe, adresse, numero, username, password)
VALUES ('Martin', 'Marie', 25, 'Femme', '58 Stratford St N, Birmingham B11 1BU, United Kingdom', '065523125532','marie','$2a$12$95XIfOuFGG.BcP.TxDgBzOyDl/tFwUl3o2LyrtSumTrUk/hixZztO');

INSERT INTO patient (nom, prenom, age, sexe, adresse, numero, username, password)
VALUES ('Lefevre', 'Paul', 35, 'Homme', '56 Hawthorn Way, Cambridge CB4 1AX, United Kingdom', '3456789012','paul','$2a$12$95XIfOuFGG.BcP.TxDgBzOyDl/tFwUl3o2LyrtSumTrUk/hixZztO');

INSERT INTO patient (nom, prenom, age, sexe, adresse, numero, username, password)
VALUES ('Dubois', 'Sophie', 40, 'Femme', '42 Eleanor Rd, Waltham Cross EN8 7DL, United Kingdom', '4567890123','sophie','$2a$12$95XIfOuFGG.BcP.TxDgBzOyDl/tFwUl3o2LyrtSumTrUk/hixZztO');

INSERT INTO patient (nom, prenom, age, sexe, adresse, numero, username, password)
VALUES ('Moreau', 'Pierre', 28, 'Homme', '1A Bazile Rd, London N21 1HD, United Kingdom', '5678901234','pierre','$2a$12$95XIfOuFGG.BcP.TxDgBzOyDl/tFwUl3o2LyrtSumTrUk/hixZztO');

