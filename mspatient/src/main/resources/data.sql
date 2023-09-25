CREATE TABLE  IF NOT EXISTS `patient` (
    `nom` VARCHAR(512),
    `prenom` VARCHAR(512),
                           `age` INT,
                           `sexe` VARCHAR(20),
                           `adresse` VARCHAR(512),
                           `numero` VARCHAR(20)
);

INSERT INTO patient (nom, prenom, age, sexe, adresse, numero)
VALUES ('Dupont', 'Jean', 30, 'Homme', '5 S Bank Rd, Edge Hill, Liverpool L7 9LP, United Kingdom', '1234567890');

INSERT INTO patient (nom, prenom, age, sexe, adresse, numero)
VALUES ('Martin', 'Marie', 25, 'Femme', '58 Stratford St N, Birmingham B11 1BU, United Kingdom', '065523125532');

INSERT INTO patient (nom, prenom, age, sexe, adresse, numero)
VALUES ('Lefevre', 'Paul', 35, 'Homme', '56 Hawthorn Way, Cambridge CB4 1AX, United Kingdom', '3456789012');

INSERT INTO patient (nom, prenom, age, sexe, adresse, numero)
VALUES ('Dubois', 'Sophie', 40, 'Femme', '42 Eleanor Rd, Waltham Cross EN8 7DL, United Kingdom', '4567890123');

INSERT INTO patient (nom, prenom, age, sexe, adresse, numero)
VALUES ('Moreau', 'Pierre', 28, 'Homme', '1A Bazile Rd, London N21 1HD, United Kingdom', '5678901234');

