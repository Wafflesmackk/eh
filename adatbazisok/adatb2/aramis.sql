SELECT * 
FROM dba_objects;

--1 feladat
SELECT owner
FROM dba_objects
WHERE object_name = 'DBA_TABLES' AND object_type = 'VIEW' OR object_name = 'DUAL' AND object_type = 'TABLE';

--2 feladat
SELECT owner
FROM dba_objects
WHERE object_name = 'DBA_TABLES' AND object_type = 'SYNONYM' OR object_name = 'DUAL' AND object_type = 'SYNONYM';


--3 feladat
SELECT DISTINCT object_type
FROM dba_objects
WHERE owner = 'ORAUSER'; 

--4 feladat
SELECT COUNT (DISTINCT object_type) AS darab
FROM dba_objects;

--6 feladat
SELECT owner
FROM dba_objects
GROUP BY owner
HAVING COUNT(object_type) > 10;

--13 feladat

SELECT *
FROM dba_tab_columns;


SELECT COUNT(*) AS darab
FROM dba_tab_columns
WHERE table_name = 'EMP' AND owner = 'NIKOVITS';

-- 15 feladat
SELECT owner, table_name
FROM dba_tab_columns
WHERE column_name LIKE 'Z%';


--18 feladat
CREATE OR REPLACE PROCEDURE table_print(p_kar VARCHAR2) AS 
    CURSOR k IS SELECT * FROM dba_tab_columns;
    rekord dba_tab_columns%rowtype;
    
BEGIN
 FOR rekord IN k LOOP
    IF SUBSTR(rekord,0, CHAR_LENGTH(p_kar)) = p_kar  THEN
        dbms_output.put_line(rekord);
    END IF;
 END LOOP;
END;

DECLARE 
    kar VARCHAR2 := 'Z' ;
BEGIN
     dbms_output.put_line(table_print(kar));
END;


SELECT *
FROM GYAK01





