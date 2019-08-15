-- noinspection SqlNoDataSourceInspectionForFile

DROP SCHEMA IF EXISTS medical_company_db;

CREATE SCHEMA medical_company_db;

USE medical_company_db;

SET FOREIGN_KEY_CHECKS = 0;

CREATE TABLE employees
(
    id       INT         NOT NULL UNIQUE AUTO_INCREMENT PRIMARY KEY,
    name     VARCHAR(16) NOT NULL,
    surname  VARCHAR(16) NOT NULL,
    username VARCHAR(16) NOT NULL UNIQUE,
    password VARCHAR(68) NOT NULL
) ENGINE = InnoDB;

CREATE TABLE employees_roles
(
    id          INT         NOT NULL UNIQUE AUTO_INCREMENT PRIMARY KEY,
    employee_id INT DEFAULT NULL REFERENCES employees (id) ON DELETE NO ACTION ON UPDATE NO ACTION,
    role        VARCHAR(16) NOT NULl
) ENGINE = InnoDB;

CREATE TABLE patients
(
    id              INT                NOT NULL UNIQUE AUTO_INCREMENT PRIMARY KEY,
    social_security VARCHAR(16) UNIQUE NOT NULL,
    birth_date      DATE NOT NULL,
    name            VARCHAR(16)        NOT NULL,
    surname         VARCHAR(16)        NOT NULL,
    email           VARCHAR(16) DEFAULT NULL UNIQUE
) ENGINE = InnoDB;

CREATE TABLE patients_visits
(
    id          INT NOT NULL UNIQUE AUTO_INCREMENT PRIMARY KEY,
    patient_id  INT NOT NULL REFERENCES patients (id) ON DELETE NO ACTION ON UPDATE NO ACTION,
    visit_date  DATETIME NOT NULL,
    visit_type  ENUM('General', 'Internal','Cardiology', 'Neurology') NOT NULL
) ENGINE = InnoDB;

CREATE TABLE sessions
(
    id INT NOT NULL UNIQUE AUTO_INCREMENT PRIMARY KEY,
    employee_id INT NOT NULL REFERENCES employees(id) ON DELETE NO ACTION ON UPDATE  NO ACTION,
    session_token VARCHAR(128) NOT NULL UNIQUE,
    session_expiry DATETIME NOT NULL
) ENGINE = InnoDB;

SET FOREIGN_KEY_CHECKS = 1;

INSERT INTO employees (name, surname, username, password) VALUES ('Daniel', 'Bayne', 'baydan', '$2y$05$L8IQDO993A5f/G/z7VjHm.XFwg4rdPCdUkTr/oTa0HaHSMPFQf9fu');
INSERT INTO employees_roles (employee_id, role)
VALUES (1, 'ADMIN');
INSERT INTO employees_roles (employee_id, role)
VALUES (1, 'USER');

INSERT INTO employees (name, surname, username, password) VALUES ('Richard', 'Morris', 'morric', '$2y$05$dDh/KjkFg7qBdiYUq.haEuH0dmnlIoGg3d7hGaZklEk.BETns0Ma6');
INSERT INTO employees (name, surname, username, password) VALUES ('Rachel', 'Amala', 'amarac', '$2y$05$TTpAEqONbl6.6BaElOrJ6OWgsKH1GwdGszL9qsBVCW24rFO7kWY7K');
INSERT INTO employees (name, surname, username, password) VALUES ('Leon', 'Meyers', 'meyleo', '$2y$05$TjdhULMjfV1bwjx67pzrjuGc7S1gAmtMQtY1pMosjoWqmslCJmPy2');
INSERT INTO employees (name, surname, username, password) VALUES ('Johnny', 'Jimenez', 'jimjoh', '$2y$05$OJuIvDb3hrNAaf3hrbVu2OGVpz/Mqfp2r0hFrY8kQybM8wIimXLC6');
INSERT INTO employees (name, surname, username, password) VALUES ('Annette', 'Jones', 'jonann', '$2y$05$YeaJjfx8I23ZIy9QJPL21uY0VNbVuX/KMPtsLdzQk5yT3qYVaZ3kq');
INSERT INTO employees (name, surname, username, password) VALUES ('Larissa', 'Maldonado', 'mallar', '$2y$05$YqhB8/Yeiwr0bbZDxxj9.e0fnAQI2ZFWXZUU0DkVBuP3wZPDr3df2');
INSERT INTO employees (name, surname, username, password) VALUES ('Caitlin', 'Salinas', 'salcai', '$2y$05$2QfAwCUZaQD7LKrSgzN1.OREG2ksBkOXGmdHVPsVUQILjCsAXYrzC');
INSERT INTO employees (name, surname, username, password) VALUES ('George', 'Santana', 'sangeo', '$2y$05$DHbFKyeuZk0m08zSqxDfPuRrUWgqiHjz5ht5Inx5vfLBa0ap/2rwC');
INSERT INTO employees (name, surname, username, password) VALUES ('Jana', 'Williamson', 'wiljan', '$2y$05$fYs1xCi72853IBiDnz/veOsqtnGa/OlDjP0zYbQMq5vfEZsaiEJGi');
INSERT INTO employees (name, surname, username, password) VALUES ('Tanisha', 'Rodgers', 'rodtan', '$2y$05$Y0gULjNYu.qlhzW.Isyfc.7HyoMThVWnGNxVqG0i8XR/Ji2gDOFBa');
INSERT INTO employees (name, surname, username, password) VALUES ('Hania', 'Black', 'blahan', '$2y$05$xqjHh3NpsRcWEYesvkiWueSLU7ARMQl2OTHR6lDOkbPCawEkVhYSy');
INSERT INTO employees (name, surname, username, password) VALUES ('Ellouise', 'Mullins', 'mulell', '$2y$05$LyGKWpyxa.j.d.AccgkM2.IBf97k5TYZK1PIjpzE5snGufg6Qh/Wa');
INSERT INTO employees (name, surname, username, password) VALUES ('Humera', 'Krause', 'krahum', '$2y$05$euwjjihROg7YaKf/A2KD9.oKaV7T9xksn41bmeSUTyWcZHAlwcNfi');
INSERT INTO employees (name, surname, username, password) VALUES ('Chelsy', 'Lacey', 'lacche', '$2y$05$MQXAEsD48oQPNiwTx1l8S.tI8sfLdeInk2eym2MnIVHzukRXdJxK2');
INSERT INTO employees (name, surname, username, password) VALUES ('Abiha', 'Bonilla', 'bonabi', '$2y$05$6gRrLaKzmr4/e9T..95sRO07E3jP/ZPXhwVnYT.6M3HpuALcJLYIy');
INSERT INTO employees (name, surname, username, password) VALUES ('Nicholas', 'Meyers', 'meynic', '$2y$05$fyNfpRs.P8C4g64E6ba9DeCvU8jXpKxycUxVEGTqB52RT0WIr6.5q');
INSERT INTO employees (name, surname, username, password) VALUES ('Danica ', 'Landry', 'landan', '$2y$05$/8w9u.KMGb1CGUfAc89gPOSVy5rCHY8h2Hss8uhP0UJvqNHr8wiym');
INSERT INTO employees (name, surname, username, password) VALUES ('Oliver', 'Weaver', 'weaoli', '$2y$05$TmdTZBHyZzLVSHJHiSRntuHGnJMBkbLjsNKR1UIlKJKZ.XWMXwiBO');
INSERT INTO employees (name, surname, username, password) VALUES ('Sannah', 'Bloggs', 'blosan', '$2y$05$D5KvH0VK9xQv11m6gALysOR3yEvenegmkd5qKbm6Z3zlh8a74u7Ly');
INSERT INTO employees (name, surname, username, password) VALUES ('Dilan', 'Bradford', 'bradil', '$2y$05$jjhXFc6IvwPbW92CcAO5geqs10KUcQ8RNF7c/JlmFge5nLqHoAwnS');
INSERT INTO employees (name, surname, username, password) VALUES ('Heather', 'Paterson', 'pathea', '$2y$05$nOV.TLjdsSgLVKjFytgm4.L9f/lem06UxTabB9B6NnBVgoox8nn/.');
INSERT INTO employees (name, surname, username, password) VALUES ('Rocco', 'Bowden', 'bowroc', '$2y$05$6dg1EmZ0as6IatdCyAJNpuIgcOdDCKcfYtc7EGkKviA4RBqX/zE06');
INSERT INTO employees (name, surname, username, password) VALUES ('Matas', 'Todd', 'todmat', '$2y$05$.VB4ZbfQYw996I.ewILeNONhTkxstJaHGk.dz0IgQPapyzWtWwOKW');
INSERT INTO employees (name, surname, username, password) VALUES ('Oliver', 'Kunal', 'kunoli', '$2y$05$AyxkcHxUFMa.1J4my92OLOQlIJDxWly1CYo8vOtK1QqT6Wa1bg5CG');

INSERT INTO medical_company_db.employees_roles (employee_id, role)
VALUES (2, 'ADMIN');
INSERT INTO medical_company_db.employees_roles (employee_id, role)
VALUES (4, 'Adm');
INSERT INTO medical_company_db.employees_roles (employee_id, role)
VALUES (4, 'TEST');




INSERT INTO patients (social_security, birth_date, name, surname, email) VALUES ('SS16', '1990-01-01', 'TEST', 'TEST', 'email@com.com')