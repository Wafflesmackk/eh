--Olyan függvény ami számot kap és számot ad vissza és megadja az n-edik fibonacci számot

CREATE OR REPLACE FUNCTION fibonacci(n IN INTEGER)
RETURN INTEGER AS
    fibo INTEGER := 0;
    fibo2 INTEGER := 1;
    tmp INTEGER;
BEGIN
    FOR i in 1..n LOOP
        tmp := fibo;
       fibo := fibo + fibo2;
       fibo2 := tmp;
    END LOOP;  
    RETURN fibo;
END;

DECLARE 
    eredmeny INTEGER := 1 ;
BEGIN
    eredmeny := fibonacci(4);
     dbms_output.put_line(eredmeny || ' ');
END;

--Szamot kap parameterul es az 1-n kozotti paros szamok osszeget

CREATE OR REPLACE FUNCTION evenSum(n IN INTEGER)
RETURN INTEGER AS
    osszeg INTEGER := 0;
BEGIN
    FOR i in 1..n LOOP
         IF MOD(i,2) = 0 THEN
             osszeg := osszeg + i;
        END IF;  
    END LOOP;  
    RETURN osszeg;
END;

DECLARE 
    eredmeny INTEGER := 0 ;
BEGIN
    eredmeny := evenSum(10);
     dbms_output.put_line(eredmeny || ' ');
END;

--Név nélküli blokk ami bekér egy dolgozó nevét és visszaadja a fizetését
ACCEPT dolgozo_nev CHAR PROMPT 'Irj be egy dolgozo nevet: '; 
DECLARE
    fizetes dolgozo.fizetes%type;
BEGIN
    SELECT fizetes
    INTO fizetes
    FROM dolgozo
    WHERE dnev = UPPER ('&dolgozo_nev');
    
    dbms_output.put_line('A dolgozo fizetese: ' || fizetes);
EXCEPTION
    WHEN no_data_found THEN
         dbms_output.put_line('Nincs ilyen nevu dolgozo');
    WHEN others THEN
        dbms_output.put_line('Masik hiba');
END;


--Kerjuk be ember eletkorat ha <0 akkor dobjon hibat,
ACCEPT kor NUMBER PROMPT 'add meg az eletkorod: '
DECLARE
    rossz_ertek EXCEPTION;
BEGIN
    IF '&kor' < 0 OR '&kor' > 100 THEN
        RAISE rossz_ertek;
    END IF;    
EXCEPTION
    WHEN rossz_ertek THEN
         dbms_output.put_line('Hibas eletkor');
END;

DROP TABLE DOLG2;
CREATE TABLE DOLG2 AS SELECT * FROM dolgozo;


--Nev nelkulo blokk ahol azoknak a dolgozoknak a fizetese +1000 akik a 10es osztalyon dolgoznak
--1. m.o kurzor nelkul
DECLARE
BEGIN
    UPDATE dolg2
    SET fizetes = fizetes + 1000
    WHERE oazon = 10;
END;

SELECT * 
FROM dolg2

--2.mo kurzorral
 DECLARE
    CURSOR curs1 IS SELECT dkod, fizetes FROM dolg2 WHERE oazon = 10;
    rec curs1%rowtype;
 BEGIN
    FOR rec In curs1 LOOP
        UPDATE dolg2 SET fizetes = fizetes + 1000 WHERE rec.dkod = dolg2.dkod;
     END LOOP;
     COMMIT;
 END;
 
 --Irjunk egy procedurat amelyik megnoveli azokonak a  dolgozoknak a fizeteset 1-el,
 --akiknek a fizetesi kategoriajuk ugyanaz mint a procedura parametere. A procedura a modositas
 --utan irja ki a modositott (uj) fizetesek atlagat ket tizedesjegyre kerekitve
 
 
CREATE OR REPLACE PROCEDURE proc1(fizkat IN INTEGER) AS
    CURSOR curs IS SELECT * FROM fiz_kategoria, dolg2 WHERE fizetes BETWEEN also AND felso AND kategoria = fizkat;
    sor curs%rowtype;
    s INTEGER := 0;
    c INTEGER := 0;
    res FLOAT := 0;
BEGIN
    FOR sor in curs LOOP
        UPDATE dolg2 SET fizetes = fizetes + 1 WHERE sor.dkod = dolg2.dkod;
        s := sor.fizetes + s;
        c := c + 1 ;
    END LOOP;
    COMMIT;
   res := s / c; 
   dbms_output.put_line(res);
END;

DECLARE 
    szam INTEGER := 1;
BEGIN
    proc1(szam);
END;









