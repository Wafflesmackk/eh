CREATE TABLE dolg2 AS SELECT * FROM kotroczo.dolgozo;
CREATE TABLE oszt2 AS SELECT * FROM kotroczo.osztaly;
CREATE TABLE fiz_kat2 AS SELECT * FROM kotroczo.fiz_kategoria;


--1.feladat: töröljük azokat a dolgozókat ahol a jutalék az NULL
DELETE
FROM dolg2
WHERE jutalek IS NULL;
ROLLBACK;

--2. feladat: töröljük azokat a dolgozókat akiknek a belépési dátuma  1982 elõtti

DELETE
FROM dolg2
WHERE dolg2.belepes < TO_DATE('1982.01.01','YYYY.MM.DD');
ROLLBACK;

--3. feladat töröljük azokat a dolgozókat akiknek az osztályának telephelye DALLAS

DELETE
FROM dolg2
WHERE oazon =(SELECT oazon FROM oszt2 WHERE telephely = 'DALLAS');
ROLLBACK;

--4. feladat töröljük azokat a dolgozókat akiknek kisebb a fizetése mint az átlag fizetés
DELETE
FROM dolg2
WHERE dolg2.fizetes < (SELECT AVG(fizetes) FROM dolg2);
ROLLBACK;

--5. feladat: töröljük azokat az osztályokat amiknek 2 olyan dolgozója van akiknek a fizetése a 2-es fizetési kategóriába tartozik.
--try
SELECT *
FROM oszt2
WHERE oazon IN (
    SELECT oszt2.oazon 
    FROM oszt2, dolg2, fiz_kat2 
    WHERE dolg2.fizetes BETWEEN fiz_kat2.also AND fiz_kat2.felso AND fiz_kat2.kategoria = 2 AND oszt2.oazon = dolg2.oazon 
    GROUP BY oszt2.oazon
    HAVING COUNT(*) = 2);
--Megoldás:
DELETE
FROM oszt2
WHERE(
    SELECT COUNT(*)
    FROM dolg2 NATURAL JOIN fiz_kat2
    WHERE fizetes BETWEEN also AND felso AND kategoria = 2 AND oszt2.oazon = dolg2.oazon)= 2;
ROLLBACK;


--6.feladat Vigyünk fel 'Kovács' névvel dolgozót 10-es osztályba és következõ értékekkel : dkod = 1, dnev = 'Kovács', oazon = 10, belépés aktuális dátum, 
-- fizetés 10es osztály átlag fizetése többi oszlop NULL

INSERT INTO dolg2(dkod,dnev,foglalkozas,fonoke,belepes,fizetes,jutalek,oazon) 
VALUES (1, 'Kovács', NULL, NULL,SYSDATE,(SELECT AVG(fizetes) FROM dolg2 WHERE oazon = 10), NULL, 10 );
COMMIT;

SELECT * 
FROM dolg2;


--7. feladat: Növeljük meg a 20as osztályon dolgozók fizetéset 20%-al
UPDATE dolg2
SET fizetes = fizetes * 1.2
WHERE oazon = 20;
COMMIT;

--8. feladat: Növeljük meg azok fizetését 500-al akiknek a jutaléka NULL vagy a fizetésük kisebb az átlagfizetésnél.
UPDATE dolg2
SET fizetes = fizetes + 500
WHERE jutalek IS NULL OR fizetes < (SELECT AVG(fizetes) FROM dolg2);
COMMIT;

--9. feladat növeljük meg mindenkinek a jutalékát a maximális jutalékkal (A NULL-t tekintsük nullának)
UPDATE dolg2
SET jutalek = NVL(jutalek,0) + (SELECT MAX(jutalek) FROM dolg2);

COMMIT;

--10. feladat növeljük meg azoknak a dolgozóknak a fizetését a minimális fizetéssel akiknek van beosztotja
UPDATE dolg2
SET fizetes = fizetes + (SELECT MIN(fizetes) FROM dolg2)
WHERE dkod IN (SELECT fonoke FROM dolg2);
COMMIT;


