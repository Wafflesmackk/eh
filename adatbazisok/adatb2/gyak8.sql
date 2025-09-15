EXPLAIN PLAN SET statement_id='ut81a'
FOR
    SELECT SUM(mennyiseg)
    FROM nikovits.szallit
    WHERE ckod = 2 AND szkod = 2;


SELECT * FROM plan_table;

EXPLAIN PLAN SET statement_id='ut81b'
FOR
    SELECT /*+ NO_INDEX(sz) */ SUM(mennyiseg)
    FROM nikovits.szallit sz
    WHERE ckod = 2 AND szkod = 2;


SELECT *
FROM dba_indexes
WHERE table_name = 'SZALLIT';

SELECT *
FROM dba_ind_columns
WHERE table_name = 'SZALLIT';

EXPLAIN PLAN SET statement_id='ut81b3'
FOR
    SELECT /*+ AND_EQUAL(sz, szt_szkod, szt_ckod) */ SUM(mennyiseg)
    FROM nikovits.szallit sz
    WHERE ckod = 2 AND szkod = 2;



EXPLAIN PLAN SET statement_id='ut82a3'
FOR
    SELECT /*+ LEADING(sz szo c) */ SUM(mennyiseg)
    FROM nikovits.szallit sz
    NATURAL JOIN nikovits.szallito szo 
    NATURAL JOIN nikovits.cikk c
    WHERE telephely = 'Pecs' and szin = 'piros';

EXPLAIN PLAN SET statement_id='ut82b1'
FOR
    SELECT /*+ ORDERED */ SUM(mennyiseg)
    FROM nikovits.szallit sz
    NATURAL JOIN nikovits.szallito szo 
    NATURAL JOIN nikovits.cikk c
    WHERE telephely = 'Pecs' and szin = 'piros';



EXPLAIN PLAN SET statement_id='ut83a'
FOR
    SELECT /*+ NO_INDEX(sz) */ SUM(mennyiseg)
    FROM nikovits.szallit sz
    WHERE ckod = 1 OR szkod = 2;



EXPLAIN PLAN SET statement_id='ut83b2'
FOR
    SELECT /*+ USE_CONCAT USE_INDEX(szt_ckod szt_szkod)  */ SUM(mennyiseg)
    FROM nikovits.szallit sz
    WHERE ckod = 1 OR szkod = 2;


EXPLAIN PLAN SET statement_id='ut84a'
FOR
    SELECT COUNT(*)
    FROM nikovits.cikk
    
EXPLAIN PLAN SET statement_id='ut84b'
FOR
    SELECT SUM(suly)
    FROM nikovits.cikk c
    WHERE ckod = 1;
    
EXPLAIN PLAN SET statement_id='ut84c1'
FOR
    SELECT /*+ USE_HASH(c p) NO_INDEX(p) */ SUM(suly)
    FROM nikovits.projekt p NATURAL JOIN nikovits.cikk c
    WHERE ckod = 1;
    
    
EXPLAIN PLAN SET statement_id='ut84e1'
FOR
    SELECT /*+ INDEX(c ckod) USE_MERGE(c sz) */SUM(mennyiseg)
    FROM nikovits.cikk c
    NATURAL JOIN nikovits.szallit sz
    WHERE szin = 'piros' ;


EXPLAIN PLAN SET statement_id='ut84f1'
FOR
SELECT /*+ ORDERED 
            NO_INDEX(p)  */ SUM(mennyiseg)
FROM nikovits.szallito szo
NATURAL JOIN nikovits.projekt p
NATURAL JOIN nikovits.szallit sz
WHERE szkod = 1;

    

SELECT * FROM plan_table;


select plan_table_output from table(dbms_xplan.display('plan_table','ut84f1','all'));


