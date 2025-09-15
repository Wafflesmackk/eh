--Kik azok a dolgozok akik 1982.01.01. után léptek be a cégbe
SELECT dnev
FROM dolgozo
WHERE belepes > TO_DATE('1982.01.01', 'YYYY.MM.DD');

--Adjuk meg azon dolgozokat akiknek a nevenek a második betûje A
SELECT dnev
FROM dolgozo
WHERE INSTR(dnev,'A') = 2;

--2.verzió
SELECT dnev
FROM dolgozo
WHERE SUBSTR(dnev,2,1) = 'A';

--Melyik dolgozóknak van legalább 2 L a nevében
SELECT dnev
FROM dolgozo
WHERE INSTR(dnev, 'L', 1,2) > 0;

--Listázzuk ki a dolgozók nevét és fizetését, valamint jelenítsük meg grafikusan a fizetést úgy, hogy
-- a fizetést 1000$-ra kerekítve minden 1000est egy $ jelöljön
SELECT dnev,fizetes, LPAD(' ',TRUNC(fizetes/1000) + 1, '#')
FROM dolgozo;

SELECT MAX(fizetes)
FROM dolgozo;

SELECT COUNT(*)
FROM dolgozo;

SELECT oazon, COUNT(*)
FROM dolgozo
GROUP BY oazon
HAVING COUNT(*) >= 5;

--Kik szeretnek minden gyümölcsöt
SELECT nev, COUNT(*)
FROM szeret
GROUP BY nev
HAVING COUNT(*) >= (SELECT COUNT( DISTINCT GYUMOLCS)FROM SZERET);
