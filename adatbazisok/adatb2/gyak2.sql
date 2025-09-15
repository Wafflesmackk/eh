SELECT *
FROM sz1;

SELECT *
FROM dba_objects
WHERE object_name = 'SZ1';

SELECT *
FROM dba_synonyms
WHERE synonym_name = 'SZ1';

SELECT *
FROM dba_objects
WHERE object_name = (SELECT table_name FROM dba_synonyms WHERE synonym_name = 'SZ1') AND  owner = 'NIKOVITS';

SELECT *
FROM dba_views
WHERE view_name = 'V1';


CREATE TABLE osztaly AS SELECT * FROM nikovits.osztaly;
CREATE TABLE dolgozo as SELECT * FROM nikovits.dolgozo;

SELECT * FROM osztaly;
SELECT * FROM dolgozo;

CREATE SEQUENCE osztaly_azonosito_seq
    START WITH 10
    INCREMENT BY 10;

INSERT INTO osztaly (oazon, onev)
VALUES (osztaly_azonosito_seq.NEXTVAL, 'Osztály 1');

INSERT INTO osztaly (oazon, onev)
VALUES (osztaly_azonosito_seq.NEXTVAL, 'Osztály 2');

INSERT INTO osztaly (oazon, onev)
VALUES (osztaly_azonosito_seq.NEXTVAL, 'Osztály 3');

INSERT INTO dolgozo (dnev, oazon)
VALUES ('Dolgozó 1', osztaly_azonosito_seq.CURRVAL);

INSERT INTO dolgozo (dnev, oazon)
VALUES ('Dolgozó 2', osztaly_azonosito_seq.CURRVAL);

INSERT INTO dolgozo (dnev, oazon)
VALUES ('Dolgozó 3', osztaly_azonosito_seq.CURRVAL);

UPDATE osztaly
SET oazon = osztaly_azonosito_seq.NEXTVAL
WHERE onev = 'Osztály 1';

UPDATE dolgozo
SET oazon = osztaly_azonosito_seq.CURRVAL
WHERE oazon = 10;


--1 feladat
SELECT file_name, bytes
FROM dba_data_files
UNION ALL
SELECT file_name, bytes
FROM dba_temp_files
ORDER BY bytes DESC;


SELECT * FROM dba_tablespaces;

--4 feladat
SELECT tablespace_name
FROM dba_tablespaces
MINUS
SELECT tablespace_name
FROM dba_data_files;

--5 feladat
SELECT *
FROM DBA_EXTENTS;

SELECT owner,segment_name, extents
FROM dba_segments
WHERE segment_type = 'TABLE'
ORDER BY bytes DESC;


