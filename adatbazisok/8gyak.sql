CREATE TABLE dolg2 AS SELECT * FROM kotroczo.dolgozo;
CREATE TABLE oszt2 AS SELECT * FROM kotroczo.osztaly;
CREATE TABLE fiz_kat2 AS SELECT * FROM kotroczo.fiz_kategoria;


--1.feladat: t�r�lj�k azokat a dolgoz�kat ahol a jutal�k az NULL
DELETE
FROM dolg2
WHERE jutalek IS NULL;
ROLLBACK;

--2. feladat: t�r�lj�k azokat a dolgoz�kat akiknek a bel�p�si d�tuma  1982 el�tti

DELETE
FROM dolg2
WHERE dolg2.belepes < TO_DATE('1982.01.01','YYYY.MM.DD');
ROLLBACK;

--3. feladat t�r�lj�k azokat a dolgoz�kat akiknek az oszt�ly�nak telephelye DALLAS

DELETE
FROM dolg2
WHERE oazon =(SELECT oazon FROM oszt2 WHERE telephely = 'DALLAS');
ROLLBACK;

--4. feladat t�r�lj�k azokat a dolgoz�kat akiknek kisebb a fizet�se mint az �tlag fizet�s
DELETE
FROM dolg2
WHERE dolg2.fizetes < (SELECT AVG(fizetes) FROM dolg2);
ROLLBACK;

--5. feladat: t�r�lj�k azokat az oszt�lyokat amiknek 2 olyan dolgoz�ja van akiknek a fizet�se a 2-es fizet�si kateg�ri�ba tartozik.
--try
SELECT *
FROM oszt2
WHERE oazon IN (
    SELECT oszt2.oazon 
    FROM oszt2, dolg2, fiz_kat2 
    WHERE dolg2.fizetes BETWEEN fiz_kat2.also AND fiz_kat2.felso AND fiz_kat2.kategoria = 2 AND oszt2.oazon = dolg2.oazon 
    GROUP BY oszt2.oazon
    HAVING COUNT(*) = 2);
--Megold�s:
DELETE
FROM oszt2
WHERE(
    SELECT COUNT(*)
    FROM dolg2 NATURAL JOIN fiz_kat2
    WHERE fizetes BETWEEN also AND felso AND kategoria = 2 AND oszt2.oazon = dolg2.oazon)= 2;
ROLLBACK;


--6.feladat Vigy�nk fel 'Kov�cs' n�vvel dolgoz�t 10-es oszt�lyba �s k�vetkez� �rt�kekkel : dkod = 1, dnev = 'Kov�cs', oazon = 10, bel�p�s aktu�lis d�tum, 
-- fizet�s 10es oszt�ly �tlag fizet�se t�bbi oszlop NULL

INSERT INTO dolg2(dkod,dnev,foglalkozas,fonoke,belepes,fizetes,jutalek,oazon) 
VALUES (1, 'Kov�cs', NULL, NULL,SYSDATE,(SELECT AVG(fizetes) FROM dolg2 WHERE oazon = 10), NULL, 10 );
COMMIT;

SELECT * 
FROM dolg2;


--7. feladat: N�velj�k meg a 20as oszt�lyon dolgoz�k fizet�set 20%-al
UPDATE dolg2
SET fizetes = fizetes * 1.2
WHERE oazon = 20;
COMMIT;

--8. feladat: N�velj�k meg azok fizet�s�t 500-al akiknek a jutal�ka NULL vagy a fizet�s�k kisebb az �tlagfizet�sn�l.
UPDATE dolg2
SET fizetes = fizetes + 500
WHERE jutalek IS NULL OR fizetes < (SELECT AVG(fizetes) FROM dolg2);
COMMIT;

--9. feladat n�velj�k meg mindenkinek a jutal�k�t a maxim�lis jutal�kkal (A NULL-t tekints�k null�nak)
UPDATE dolg2
SET jutalek = NVL(jutalek,0) + (SELECT MAX(jutalek) FROM dolg2);

COMMIT;

--10. feladat n�velj�k meg azoknak a dolgoz�knak a fizet�s�t a minim�lis fizet�ssel akiknek van beosztotja
UPDATE dolg2
SET fizetes = fizetes + (SELECT MIN(fizetes) FROM dolg2)
WHERE dkod IN (SELECT fonoke FROM dolg2);
COMMIT;


