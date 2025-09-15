DROP TABLE SZERET; 

CREATE TABLE SZERET
    (NEV         VARCHAR2(15),
     GYUMOLCS    VARCHAR2(15));

INSERT INTO SZERET VALUES ('Malacka','alma');
INSERT INTO SZERET VALUES ('Micimack�','alma');
INSERT INTO SZERET VALUES ('Malacka','k�rte');
INSERT INTO SZERET VALUES ('Micimack�','k�rte');
INSERT INTO SZERET VALUES ('Kanga','k�rte');
INSERT INTO SZERET VALUES ('Tigris','k�rte');
INSERT INTO SZERET VALUES ('Micimack�','m�lna');
INSERT INTO SZERET VALUES ('Malacka','m�lna');
INSERT INTO SZERET VALUES ('Kanga','m�lna');
INSERT INTO SZERET VALUES ('Tigris','m�lna');
INSERT INTO SZERET VALUES ('Nyuszi','eper');
INSERT INTO SZERET VALUES ('Malacka','eper');

COMMIT;


SELECT *
FROM szeret;

--1. feladat
SELECT gyumolcs
FROM szeret
WHERE nev = 'Micimack�';

--2. feladat
SELECT gyumolcs
FROM szeret
MINUS
SELECT gyumolcs
FROM szeret
WHERE nev = 'Micimack�';

--3. feladat
SELECT nev
FROM szeret
WHERE gyumolcs = 'alma';

--4. feladat
SELECT nev
FROM szeret
MINUS
SELECT nev
FROM szeret
WHERE gyumolcs = 'k�rte';









