-- Création de la table donor
CREATE TABLE donor (
                       id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                       full_name VARCHAR(255) NOT NULL,
                       email VARCHAR(255) NOT NULL UNIQUE
);

-- Création de la table payment
CREATE TABLE payment (
                         id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                         payment_id VARCHAR(255) NOT NULL UNIQUE,
                         date TIMESTAMP WITHOUT TIME ZONE NOT NULL,
                         verification_status VARCHAR(10) NOT NULL,
                         amount DOUBLE PRECISION NOT NULL,
                         method VARCHAR(100) NOT NULL,
                         psp_payment VARCHAR(100) NOT NULL,
                         psp_payment_id VARCHAR(100) NOT NULL,
                         payer_email VARCHAR(50) NOT NULL
);

-- Création de la table donation
CREATE TABLE donation (
                          id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                          donor_id UUID NOT NULL,
                          date TIMESTAMP WITHOUT TIME ZONE NOT NULL,
                          amount DOUBLE PRECISION NOT NULL,
                          payment_method VARCHAR(100) NOT NULL,
                          payment_id UUID NOT NULL,
                          CONSTRAINT fk_donor FOREIGN KEY(donor_id) REFERENCES donor(id),
                          CONSTRAINT fk_payment_donation FOREIGN KEY(payment_id) REFERENCES payment(id)
);

-- Création de la table beneficiary
CREATE TABLE beneficiary (
                             id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                             full_name VARCHAR(255) NOT NULL,
                             email VARCHAR(255) NOT NULL UNIQUE
);

-- Création de la table help
CREATE TABLE help (
                      id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                      beneficiary_id UUID NOT NULL,
                      date TIMESTAMP WITHOUT TIME ZONE NOT NULL,
                      amount DOUBLE PRECISION NOT NULL,
                      accident_description TEXT NOT NULL,
                      payment_id UUID NOT NULL,
                      CONSTRAINT fk_beneficiary FOREIGN KEY(beneficiary_id) REFERENCES beneficiary(id),
                      CONSTRAINT fk_payment_help FOREIGN KEY(payment_id) REFERENCES payment(id)
);
