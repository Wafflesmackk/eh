
--Adjuk meg azoknak az osztályoknak az átlag fizetését ahol az nagyobb mint 2000.

SELECT oazon, AVG(fizetes)
FROM dolgozo
GROUP BY oazon
HAVING AVG(fizetes) > 2000;

--Adjuk meg az átlagfizetést azokon az osztályokon ahol legalább 4-en dolgoznak
SELECT oazon, AVG(fizetes)
FROM dolgozo
GROUP BY oazon
HAVING COUNT(*) >= 4;

--Adjuk meg az átlagfizetést ÉS a telephelyet ahol legalább 4-en dolgoznak
SELECT oazon, telephely, AVG(fizetes)
FROM dolgozo NATURAL JOIN osztaly
GROUP BY oazon, telephely
HAVING COUNT(*) >= 4;

--Adjuk meg azoknak az osztályoknak a nevét és telephelyét ahol az átlagifizetés nagyobb mint 2000.
SELECT telephely,onev, AVG(fizetes)
FROM dolgozo NATURAL JOIN osztaly
GROUP BY telephely,onev
HAVING AVG(fizetes) > 2000;

-- Adjuk meg azokat a fizetési kategoriákat amelyekben pontosan 3 dolgozó fizetése esik
SELECT kategoria
FROM fiz_kategoria, dolgozo
WHERE fizetes BETWEEN also AND felso
GROUP BY kategoria
HAVING COUNT(*) = 3;

--Adjuk meg azon osztályoknak nevét és telephelyét, amelyeknek van 1-es fizetesi kategoriaju dolgozoja
SELECT onev, telephely
FROM dolgozo, osztaly, fiz_kategoria
WHERE  fizetes BETWEEN also AND felso AND kategoria = 1 AND osztaly.oazon = dolgozo.oazon
GROUP BY onev, telephely;

--Kik szeretnek minden gyümölcsöt
SELECT nev
FROM szeret
GROUP BY nev
HAVING COUNT(*) = (
    SELECT COUNT(DISTINCT GYUMOLCS)
    FROM SZERET
);
