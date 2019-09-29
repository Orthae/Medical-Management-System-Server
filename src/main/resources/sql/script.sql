-- noinspection SqlNoDataSourceInspectionForFile

# Schema setup

DROP SCHEMA IF EXISTS medical_company_db;

CREATE SCHEMA medical_company_db;

USE medical_company_db;

SET FOREIGN_KEY_CHECKS = 0;

CREATE TABLE employees
(
    id       INT         NOT NULL UNIQUE AUTO_INCREMENT PRIMARY KEY,
    name     VARCHAR(24) NOT NULL,
    surname  VARCHAR(24) NOT NULL,
    username VARCHAR(24) NOT NULL UNIQUE,
    email    VARCHAR(32) NOT NULL UNIQUE,
    password VARCHAR(60) NOT NULL,
    enabled  BOOLEAN     NOT NULL DEFAULT 1
) ENGINE = InnoDB;

CREATE TABLE employees_roles
(
    id          INT         NOT NULL UNIQUE AUTO_INCREMENT PRIMARY KEY,
    employee_id INT REFERENCES employees (id) ON DELETE NO ACTION ON UPDATE NO ACTION,
    role        VARCHAR(16) NOT NULl
) ENGINE = InnoDB;

CREATE TABLE employees_sessions
(
    id               INT          NOT NULL UNIQUE AUTO_INCREMENT PRIMARY KEY,
    employee_id      INT REFERENCES employees (id) ON DELETE NO ACTION ON UPDATE NO ACTION,
    ip_address       VARCHAR(39)  NOT NULL,
    session_token    VARCHAR(128) NOT NULL UNIQUE,
    session_creation DATETIME     NOT NULL,
    session_expiry   DATETIME     NOT NULL
) ENGINE = InnoDB;


CREATE TABLE patients
(
    id              INT                NOT NULL UNIQUE AUTO_INCREMENT PRIMARY KEY,
    social_security VARCHAR(16) UNIQUE NOT NULL,
    birth_date      DATE               NOT NULL,
    name            VARCHAR(16)        NOT NULL,
    surname         VARCHAR(16)        NOT NULL,
    email           VARCHAR(16) DEFAULT NULL,
    phone           VARCHAR(12) DEFAULT NULL
) ENGINE = InnoDB;

CREATE TABLE patients_visits
(
    id         INT                                                    NOT NULL UNIQUE AUTO_INCREMENT PRIMARY KEY,
    patient_id INT                                                    NOT NULL REFERENCES patients (id) ON DELETE NO ACTION ON UPDATE NO ACTION,
    visit_date DATETIME                                               NOT NULL,
    visit_type ENUM ('General', 'Internal','Cardiology', 'Neurology') NOT NULL
) ENGINE = InnoDB;



SET FOREIGN_KEY_CHECKS = 1;

# Employees inserts
INSERT INTO employees (name, surname, username, email, password, enabled)
VALUES ('Daniel', 'Bayne', 'baydan', 'daniel.bayne@company.com', '$2y$05$L8IQDO993A5f/G/z7VjHm.XFwg4rdPCdUkTr/oTa0HaHSMPFQf9fu', 1);
INSERT INTO employees (name, surname, username, email, password, enabled)
VALUES ('Richard', 'Morris', 'morric', 'richard.morris@company.com', '$2y$05$dDh/KjkFg7qBdiYUq.haEuH0dmnlIoGg3d7hGaZklEk.BETns0Ma6', 1);
INSERT INTO employees (name, surname, username, email, password, enabled)
VALUES ('Rachel', 'Amala', 'amarac', 'rachel.amala@company.com', '$2y$05$TTpAEqONbl6.6BaElOrJ6OWgsKH1GwdGszL9qsBVCW24rFO7kWY7K', 1);
INSERT INTO employees (name, surname, username, email, password, enabled)
VALUES ('Leon', 'Meyers', 'meyleo', 'leon.meyers@company.com', '$2y$05$TjdhULMjfV1bwjx67pzrjuGc7S1gAmtMQtY1pMosjoWqmslCJmPy2', 1);
INSERT INTO employees (name, surname, username, email, password, enabled)
VALUES ('Johnny', 'Jimenez', 'jimjoh', 'johnny.jimenez@company.com', '$2y$05$OJuIvDb3hrNAaf3hrbVu2OGVpz/Mqfp2r0hFrY8kQybM8wIimXLC6', 1);
INSERT INTO employees (name, surname, username, email, password, enabled)
VALUES ('Annette', 'Jones', 'jonann', 'annette.jones@company.com', '$2y$05$YeaJjfx8I23ZIy9QJPL21uY0VNbVuX/KMPtsLdzQk5yT3qYVaZ3kq', 0);
INSERT INTO employees (name, surname, username, email, password, enabled)
VALUES ('Larissa', 'Maldonado', 'mallar', 'larissa.maldonado@company.com', '$2y$05$YqhB8/Yeiwr0bbZDxxj9.e0fnAQI2ZFWXZUU0DkVBuP3wZPDr3df2', 0);
INSERT INTO employees (name, surname, username, email, password, enabled)
VALUES ('Caitlin', 'Salinas', 'salcai', 'caitlin.salinas@company.com', '$2y$05$2QfAwCUZaQD7LKrSgzN1.OREG2ksBkOXGmdHVPsVUQILjCsAXYrzC', 0);
INSERT INTO employees (name, surname, username, email, password, enabled)
VALUES ('George', 'Santana', 'sangeo', 'george.santana@company.com', '$2y$05$DHbFKyeuZk0m08zSqxDfPuRrUWgqiHjz5ht5Inx5vfLBa0ap/2rwC', 1);
INSERT INTO employees (name, surname, username, email, password, enabled)
VALUES ('Jana', 'Williamson', 'wiljan', 'jana.williamson@company.com', '$2y$05$fYs1xCi72853IBiDnz/veOsqtnGa/OlDjP0zYbQMq5vfEZsaiEJGi', 0);
INSERT INTO employees (name, surname, username, email, password, enabled)
VALUES ('Tanisha', 'Rodgers', 'rodtan', 'tanisha.rodgers@company.com', '$2y$05$Y0gULjNYu.qlhzW.Isyfc.7HyoMThVWnGNxVqG0i8XR/Ji2gDOFBa', 1);
INSERT INTO employees (name, surname, username, email, password, enabled)
VALUES ('Hania', 'Black', 'blahan', 'hania.black@company.com', '$2y$05$xqjHh3NpsRcWEYesvkiWueSLU7ARMQl2OTHR6lDOkbPCawEkVhYSy', 1);
INSERT INTO employees (name, surname, username, email, password, enabled)
VALUES ('Ellouise', 'Mullins', 'mulell', 'ellouise.mullins@company.com', '$2y$05$LyGKWpyxa.j.d.AccgkM2.IBf97k5TYZK1PIjpzE5snGufg6Qh/Wa', 1);
INSERT INTO employees (name, surname, username, email, password, enabled)
VALUES ('Humera', 'Krause', 'krahum', 'humera.krause@company.com', '$2y$05$euwjjihROg7YaKf/A2KD9.oKaV7T9xksn41bmeSUTyWcZHAlwcNfi', 1);
INSERT INTO employees (name, surname, username, email, password, enabled)
VALUES ('Chelsy', 'Lacey', 'lacche', 'chelsy.lacey@company.com', '$2y$05$MQXAEsD48oQPNiwTx1l8S.tI8sfLdeInk2eym2MnIVHzukRXdJxK2', 1);
INSERT INTO employees (name, surname, username, email, password, enabled)
VALUES ('Abiha', 'Bonilla', 'bonabi', 'abiha.bonilla@company.com', '$2y$05$6gRrLaKzmr4/e9T..95sRO07E3jP/ZPXhwVnYT.6M3HpuALcJLYIy', 1);
INSERT INTO employees (name, surname, username, email, password, enabled)
VALUES ('Nicholas', 'Meyers', 'meynic', 'nicholas.meyers@company.com', '$2y$05$fyNfpRs.P8C4g64E6ba9DeCvU8jXpKxycUxVEGTqB52RT0WIr6.5q', 1);
INSERT INTO employees (name, surname, username, email, password, enabled)
VALUES ('Danica ', 'Landry', 'landan', 'danica.landry@company.com', '$2y$05$/8w9u.KMGb1CGUfAc89gPOSVy5rCHY8h2Hss8uhP0UJvqNHr8wiym', 1);
INSERT INTO employees (name, surname, username, email, password, enabled)
VALUES ('Oliver', 'Weaver', 'weaoli', 'oliver.weaver@company.com', '$2y$05$TmdTZBHyZzLVSHJHiSRntuHGnJMBkbLjsNKR1UIlKJKZ.XWMXwiBO', 1);
INSERT INTO employees (name, surname, username, email, password, enabled)
VALUES ('Sannah', 'Bloggs', 'blosan', 'sannah.bloggs@company.com', '$2y$05$D5KvH0VK9xQv11m6gALysOR3yEvenegmkd5qKbm6Z3zlh8a74u7Ly', 1);
INSERT INTO employees (name, surname, username, email, password, enabled)
VALUES ('Dilan', 'Bradford', 'bradil', 'dilan.bradford@company.com', '$2y$05$jjhXFc6IvwPbW92CcAO5geqs10KUcQ8RNF7c/JlmFge5nLqHoAwnS', 1);
INSERT INTO employees (name, surname, username, email, password, enabled)
VALUES ('Heather', 'Paterson', 'pathea', 'heather.paterson@company.com', '$2y$05$nOV.TLjdsSgLVKjFytgm4.L9f/lem06UxTabB9B6NnBVgoox8nn/.', 1);
INSERT INTO employees (name, surname, username, email, password, enabled)
VALUES ('Rocco', 'Bowden', 'bowroc', 'rocco.bowden@company.com', '$2y$05$6dg1EmZ0as6IatdCyAJNpuIgcOdDCKcfYtc7EGkKviA4RBqX/zE06', 1);
INSERT INTO employees (name, surname, username, email, password, enabled)
VALUES ('Heather', 'Paterson', 'patheat', 'paterson.heather@company.com', '$2y$05$4LV6DNJtKoQ2LU4Tolw76OA2TH1rXc3nkUbQbBtSsM8jKvrESi9Oa', 0);
INSERT INTO employees (name, surname, username, email, password, enabled)
VALUES ('Oliver', 'Kunal', 'kunoli', 'oliver.kunal@company.com', '$2y$05$AyxkcHxUFMa.1J4my92OLOQlIJDxWly1CYo8vOtK1QqT6Wa1bg5CG', 1);

# Roles inserts

INSERT INTO employees_roles (employee_id, role)
VALUES (1, 'MANAGEMENT');

INSERT INTO patients (social_security, birth_date, name, surname, email)
VALUES ('SS16', '1990-01-01', 'TEST', 'TEST', 'email@com.com')