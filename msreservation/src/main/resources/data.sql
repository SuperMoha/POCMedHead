CREATE TABLE if not exists `reservation` (
    `id` SERIAL PRIMARY KEY,
    `patient_id` INT NOT NULL,
    `hopital_id` INT NOT NULL,
    CONSTRAINT unique_reservation UNIQUE (patient_id, hopital_id)
    FOREIGN KEY (patient_id) REFERENCES patients(id),
    FOREIGN KEY (hopital_id) REFERENCES hopitaux(id)


);