EXPLAIN PLAN SET statement_id='ut2a'  -- 'ut1' -> az utasítás egyedi neve
FOR 
    SELECT sum(darab) 
    FROM nikovits.hivas, nikovits.kozpont, nikovits.primer
    WHERE hivas.kozp_azon_hivo=kozpont.kozp_azon AND kozpont.primer=primer.korzet
    AND primer.varos = 'Szentendre' 
    AND datum + 1 = next_day(to_date('2012.01.31', 'yyyy.mm.dd'),'hétf?');
select plan_table_output from table(dbms_xplan.display('plan_table','ut2a','all'));


EXPLAIN PLAN SET statement_id='ut2b'  -- 'ut1' -> az utasítás egyedi neve
FOR 
-- futásid?: 1 sec
    SELECT sum(darab) 
    FROM nikovits.hivas, nikovits.kozpont, nikovits.primer
    WHERE hivas.kozp_azon_hivo=kozpont.kozp_azon AND kozpont.primer=primer.korzet
    AND primer.varos = 'Szentendre' 
    AND datum = next_day(to_date('2012.01.31', 'yyyy.mm.dd'),'hétf?') - 1;
select plan_table_output from table(dbms_xplan.display('plan_table','ut2b','all'));    

SELECT * FROM dba_partitions
WHERE table_name = 'HIVAS';

EXPLAIN PLAN SET STATEMENT_ID = 'ut3g1'
FOR
SELECT /*+ USE_NL(sz c) NO_INDEX(sz) NO_INDEX(c) */ SUM(mennyiseg)
FROM nikovits.szallit sz natural JOIN nikovits.cikk c
WHERE   szin = 'piros';

select plan_table_output from table(dbms_xplan.display('plan_table','ut3g1','all'));    


















