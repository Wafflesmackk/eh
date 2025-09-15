ALTER SESSION SET NLS_DATE_LANGUAGE = ENGLISH;
ALTER SESSION SET NLS_DATE_FORMAT = 'DD-MON-YYYY';

CREATE TABLE Osztaly
    (OAZON             NUMBER(2) NOT NULL,
     ONEV              VARCHAR2(14),
     TELEPHELY         VARCHAR2(13),
     CONSTRAINT OSZT_PK PRIMARY KEY (OAZON));

INSERT INTO Osztaly VALUES (10,'ACCOUNTING','NEW YORK');
INSERT INTO Osztaly VALUES (20,'RESEARCH','DALLAS');
INSERT INTO Osztaly VALUES (30,'SALES','CHICAGO');
INSERT INTO Osztaly VALUES (40,'OPERATIONS','BOSTON');

CREATE TABLE Dolgozo
    (DKOD               NUMBER(4) NOT NULL,
     DNEV               VARCHAR2(10),
     FOGLALKOZAS        VARCHAR2(9),
     FONOKE             NUMBER(4) CONSTRAINT DOLG_SELF_KEY REFERENCES Dolgozo (DKOD),
     BELEPES            DATE,
     FIZETES            NUMBER(7,2),
     JUTALEK            NUMBER(7,2),
     OAZON              NUMBER(2),
     CONSTRAINT DOLG_FK FOREIGN KEY (OAZON) REFERENCES Osztaly (OAZON),
     CONSTRAINT DOLG_PK PRIMARY KEY (DKOD));

INSERT INTO Dolgozo VALUES (7839,'KING','PRESIDENT',NULL,'17-NOV-1981',5000,NULL,10);
INSERT INTO Dolgozo VALUES (7698,'BLAKE','MANAGER',7839,'1-MAY-1981',2850,NULL,30);
INSERT INTO Dolgozo VALUES (7782,'CLARK','MANAGER',7839,'9-JUN-1981',2450,NULL,10);
INSERT INTO Dolgozo VALUES (7566,'JONES','MANAGER',7839,'2-APR-1981',2975,NULL,20);
INSERT INTO Dolgozo VALUES (7654,'MARTIN','SALESMAN',7698,'28-SEP-1981',1250,1400,30);
INSERT INTO Dolgozo VALUES (7499,'ALLEN','SALESMAN',7698,'20-FEB-1981',1600,300,30);
INSERT INTO Dolgozo VALUES (7844,'TURNER','SALESMAN',7698,'8-SEP-1981',1500,0,30);
INSERT INTO Dolgozo VALUES (7900,'JAMES','CLERK',7698,'3-DEC-1981',950,NULL,30);
INSERT INTO Dolgozo VALUES (7521,'WARD','SALESMAN',7698,'22-FEB-1981',1250,500,30);
INSERT INTO Dolgozo VALUES (7902,'FORD','ANALYST',7566,'3-DEC-1981',3000,NULL,20);
INSERT INTO Dolgozo VALUES (7369,'SMITH','CLERK',7902,'17-DEC-1980',800,NULL,20);
INSERT INTO Dolgozo VALUES (7788,'SCOTT','ANALYST',7566,'09-DEC-1982',3000,NULL,20);
INSERT INTO Dolgozo VALUES (7876,'ADAMS','CLERK',7788,'12-JAN-1983',1100,NULL,20);
INSERT INTO Dolgozo VALUES (7934,'MILLER','CLERK',7782,'23-JAN-1982',1300,NULL,10);
INSERT INTO Dolgozo VALUES (7877,'LOLA','CLERK',7902,'12-JAN-1981',800,NULL,NULL);
INSERT INTO Dolgozo VALUES (7878,'BLACK',NULL,7902,'01-MAY-1987',1800,300,NULL);

CREATE TABLE Fiz_Kategoria (
 KATEGORIA          NUMBER,
 ALSO               NUMBER,
 FELSO              NUMBER);

INSERT INTO Fiz_Kategoria VALUES (1,700,1200);
INSERT INTO Fiz_Kategoria VALUES (2,1201,1400);
INSERT INTO Fiz_Kategoria VALUES (3,1401,2000);
INSERT INTO Fiz_Kategoria VALUES (4,2001,3000);
INSERT INTO Fiz_Kategoria VALUES (5,3001,9999);

COMMIT; 

GRANT SELECT ON Osztaly TO PUBLIC; 
GRANT SELECT ON Dolgozo TO PUBLIC;
GRANT SELECT ON Fiz_Kategoria TO PUBLIC;

ALTER SESSION SET NLS_DATE_LANGUAGE = HUNGARIAN;
ALTER SESSION SET NLS_DATE_FORMAT = 'YYYY.MM.DD';

SELECT * 
FROM Osztaly;

SELECT * 
FROM Dolgozo;

SELECT * 
FROM Fiz_Kategoria;

--1 feladat
SELECT dnev
FROM dolgozo
WHERE fizetes > 2800;

--3 feladat
--A verzió
SELECT dnev
FROM dolgozo
WHERE jutalek <= 600 OR jutalek IS NULL;

--B verzió
SELECT dnev
FROM dolgozo
MINUS
SELECT dnev
FROM dolgozo
WHERE jutalek > 600;

--7. feladat
SELECT dnev, fizetes * 2
FROM dolgozo
WHERE oazon = 10;

--Kik szeretnek legelább kétféle gyümölcsöt
SELECT DISTINCT s1.nev
FROM szeret s1, szeret s2
WHERE s1.nev = s2.nev AND s1.gyumolcs <> s2.gyumolcs;

--Kik szeretnek legalább 3 féle gyümölcsöt
SELECT DISTINCT s1.nev
FROM szeret s1, szeret s2, szeret s3
WHERE s1.nev = s2.nev 
    AND s2.nev = s3.nev 
    AND s1.gyumolcs <> s2.gyumolcs 
    AND s2.gyumolcs <> s3.gyumolcs 
    AND s1.gyumolcs <> s3.gyumolcs;

--Kik szeretnek legfeljebb kétféle gyümölcsöt
SELECT nev 
FROM szeret
MINUS
SELECT DISTINCT s1.nev
FROM szeret s1, szeret s2, szeret s3
WHERE s1.nev = s2.nev 
    AND s2.nev = s3.nev 
    AND s1.gyumolcs <> s2.gyumolcs 
    AND s2.gyumolcs <> s3.gyumolcs 
    AND s1.gyumolcs <> s3.gyumolcs;
    

--Kik azok a dolgozok akiknek KING a fõnöke
-- A. verzió
SELECT dnev
FROM dolgozo
WHERE fonoke = (SELECT dkod FROM dolgozo WHERE dnev = 'KING');

--B. verzió
SELECT d1.dnev
FROM dolgozo d1, dolgozo d2
WHERE d1.fonoke = d2.dkod AND d2.dnev = 'KING';

--Adjuk meg azokat a dolgozókat akik többet keresnek a fõnöküknél
SELECT d1.dnev
FROM dolgozo d1, dolgozo d2
WHERE d1.fonoke = d2.dkod AND d2.fizetes < d1.fizetes;

--Kik azok a dolgozok akiknek a fõnökének a fõnöke a King
--A verzió
SElECT d1.dnev
FROM dolgozo d1, dolgozo d2, dolgozo d3
WHERE d1.fonoke = d2.dkod AND d2.fonoke = d3.dkod AND d3.dnev = 'KING';

--B verzió
SELECT d1.dnev
FROM dolgozo d1, dolgozo d2
WHERE d1.fonoke = d2.dkod AND d2.fonoke = (SELECT dkod FROM dolgozo WHERE dnev = 'KING');

--C verzió
SELECT dnev
FROM dolgozo
WHERE fonoke IN (
    SELECT dkod
    FROM dolgozo 
    WHERE fonoke =
        (SELECT dkod 
        FROM dolgozo 
        WHERE dnev = 'KING'));
        
        
SELECT * FROM dolgozo;
SELECT * FROM osztaly;

SELECT *
FROM dolgozo d, osztaly o
WHERE d.oazon = o.oazon;

SELECT *
FROM dolgozo NATURAL JOIN osztaly;

SELECT *
FROM dolgozo INNER JOIN osztaly ON dolgozo.oazon = osztaly.oazon;

--Kik azok a dolgozok akiknek a telephelye Dallas vagy Chicago
SELECT dnev
FROM dolgozo NATURAL JOIN osztaly
WHERE osztaly.telephely = 'DALLAS' OR osztaly.telephely = 'CHICAGO';

--



