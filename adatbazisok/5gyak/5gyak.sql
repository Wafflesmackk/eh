
--Adjuk meg azoknak az oszt�lyoknak az �tlag fizet�s�t ahol az nagyobb mint 2000.

SELECT oazon, AVG(fizetes)
FROM dolgozo
GROUP BY oazon
HAVING AVG(fizetes) > 2000;

--Adjuk meg az �tlagfizet�st azokon az oszt�lyokon ahol legal�bb 4-en dolgoznak
SELECT oazon, AVG(fizetes)
FROM dolgozo
GROUP BY oazon
HAVING COUNT(*) >= 4;

--Adjuk meg az �tlagfizet�st �S a telephelyet ahol legal�bb 4-en dolgoznak
SELECT oazon, telephely, AVG(fizetes)
FROM dolgozo NATURAL JOIN osztaly
GROUP BY oazon, telephely
HAVING COUNT(*) >= 4;

--Adjuk meg azoknak az oszt�lyoknak a nev�t �s telephely�t ahol az �tlagifizet�s nagyobb mint 2000.
SELECT telephely,onev, AVG(fizetes)
FROM dolgozo NATURAL JOIN osztaly
GROUP BY telephely,onev
HAVING AVG(fizetes) > 2000;

-- Adjuk meg azokat a fizet�si kategori�kat amelyekben pontosan 3 dolgoz� fizet�se esik
SELECT kategoria
FROM fiz_kategoria, dolgozo
WHERE fizetes BETWEEN also AND felso
GROUP BY kategoria
HAVING COUNT(*) = 3;

--Adjuk meg azon oszt�lyoknak nev�t �s telephely�t, amelyeknek van 1-es fizetesi kategoriaju dolgozoja
SELECT onev, telephely
FROM dolgozo, osztaly, fiz_kategoria
WHERE  fizetes BETWEEN also AND felso AND kategoria = 1 AND osztaly.oazon = dolgozo.oazon
GROUP BY onev, telephely;

--Kik szeretnek minden gy�m�lcs�t
SELECT nev
FROM szeret
GROUP BY nev
HAVING COUNT(*) = (
    SELECT COUNT(DISTINCT GYUMOLCS)
    FROM SZERET
);
