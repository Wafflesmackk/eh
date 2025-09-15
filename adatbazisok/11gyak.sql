--Írjunk meg egy procedúrát amely kiírja azon dolgozók számát és átlagfizetését
--akiknek a belépési dátuma a paraméterként megadott nevû napon volt



CREATE OR REPLACE PROCEDURE atlagDatum(datum IN VARCHAR) AS
    CURSOR curs IS SELECT * FROM dolgozo;
    sor curs%rowtype;
    atlag FLOAT := 0;
    szam INTEGER := 0;
BEGIN
     FOR sor in curs LOOP
        IF TRIM (TO_CHAR(sor.belepes, 'DAY')) = UPPER(datum) THEN
            atlag := atlag + sor.fizetes;
            szam := szam + 1;
        END IF;
    END LOOP;
    atlag := atlag/szam;
    dbms_output.put_line('Atlag ' || atlag || 'dolg szam: ' || szam);
EXCEPTION
    WHEN ZERO_DIVIDE THEN
     dbms_output.put_line('Hibas adat');
END;


--Írjunk egy procedurát amelyik veszi a paramérerül kapott osztály dolgozóit
--abc szerinti sorrendben és kiírja a foglalkozásaikat egy karakterláncban kifûzve

CREATE OR REPLACE PROCEDURE foglalkoz(oszt IN NUMBER) AS
    CURSOR curs IS SELECT * FROM dolgozo ORDER BY dolgozo.dnev ASC;
     sor curs%rowtype;
    output VARCHAR(100);
    
BEGIN
 FOR sor in curs LOOP
        IF oszt = sor.oazon THEN
            output := output || '-' || sor.foglalkozas;  
        END IF;
    END LOOP;
    dbms_output.put_line(output);
END;

DECLARE 
    szam INTEGER := 20;
BEGIN
    foglalkoz(szam);
END;

CREATE TABLE vagyonok AS SELECT * FROM nikovits.vagyonok;
SELECT * FROM vagyonok;

/* Írjunk egy procedúrát amelyik a vagyonok tábla alaõján kiírja azoknak a személyeknek a nevét,
vagyonát, valamint leszármazottak átlag vagyona akikre igaz az, hogy a leszármazottak átlagvagyona
nagyobb mint az illetõ vagyona, A program 3 adatot ír ki név, vagyon, leszármazottak átlagvagyona 
*/

CREATE OR REPLACE PROCEDURE atlagVagyon AS
    CURSOR curs IS SELECT * FROM vagyonok;
    CURSOR curs2 IS SELECT * FROM vagyonok;
     sor curs%rowtype;
     sor2 curs%rowtype;
    atlag FLOAT := 0;
    szam INTEGER := 0;
    output VARCHAR(100) := '';
    nev VARCHAR(100) := '';
BEGIN 
    FOR sor in curs LOOP
        nev := sor.nev;
        FOR sor2 in curs2 LOOP
            IF nev = sor2.apja THEN
            atlag := atlag + sor2.vagyon;
            szam := szam + 1;
            END IF;
        END LOOP;
       
        IF atlag != 0 THEN
         atlag := atlag / szam;
            IF sor.vagyon < atlag THEN
            output := sor.nev || ' vagyon: ' || sor.vagyon || ' atlagvagyon leszarmazott: ' || atlag;
            dbms_output.put_line(output);
            END IF;
         END IF;
         atlag := 0;
         szam := 0;
    END LOOP;
    
END;

BEGIN
    atlagvagyon;
END;





