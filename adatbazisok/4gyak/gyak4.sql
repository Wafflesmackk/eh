--Kik azok a dolgozok akik 1982.01.01. ut�n l�ptek be a c�gbe
SELECT dnev
FROM dolgozo
WHERE belepes > TO_DATE('1982.01.01', 'YYYY.MM.DD');

--Adjuk meg azon dolgozokat akiknek a nevenek a m�sodik bet�je A
SELECT dnev
FROM dolgozo
WHERE INSTR(dnev,'A') = 2;

--2.verzi�
SELECT dnev
FROM dolgozo
WHERE SUBSTR(dnev,2,1) = 'A';

--Melyik dolgoz�knak van legal�bb 2 L a nev�ben
SELECT dnev
FROM dolgozo
WHERE INSTR(dnev, 'L', 1,2) > 0;

--List�zzuk ki a dolgoz�k nev�t �s fizet�s�t, valamint jelen�ts�k meg grafikusan a fizet�st �gy, hogy
-- a fizet�st 1000$-ra kerek�tve minden 1000est egy $ jel�lj�n
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

--Kik szeretnek minden gy�m�lcs�t
SELECT nev, COUNT(*)
FROM szeret
GROUP BY nev
HAVING COUNT(*) >= (SELECT COUNT( DISTINCT GYUMOLCS)FROM SZERET);
