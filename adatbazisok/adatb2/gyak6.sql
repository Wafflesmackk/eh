  EXPLAIN PLAN SET statement_id='ut1'  -- 'ut1' -> az utasítás egyedi neve
   FOR 
   SELECT dname, job, AVG(sal) FROM nikovits.emp NATURAL JOIN nikovits.dept
   WHERE hiredate > to_date('1981.01.01', 'yyyy.mm.dd')
   GROUP BY dname, job HAVING SUM(sal) > 5000
   ORDER BY AVG(sal) DESC;
   
SELECT * FROM plan_table;

select plan_table_output from table(dbms_xplan.display('plan_table','ut1','all'));
--paraméterek: tábla_név, statement_id, részletesség (basic, typical, all, serial) 



EXPLAIN PLAN SET statement_id='ut2a'  -- 'ut1' -> az utasítás egyedi neve
FOR 
    SELECT sum(darab) 
    FROM nikovits.hivas, nikovits.kozpont, nikovits.primer
    WHERE hivas.kozp_azon_hivo=kozpont.kozp_azon AND kozpont.primer=primer.korzet
    AND primer.varos = 'Szentendre' 
    AND datum + 1 = next_day(to_date('2012.01.31', 'yyyy.mm.dd'),'hétf?');



EXPLAIN PLAN SET statement_id='ut2b'  -- 'ut1' -> az utasítás egyedi neve
FOR 
-- futásid?: 1 sec
    SELECT sum(darab) 
    FROM nikovits.hivas, nikovits.kozpont, nikovits.primer
    WHERE hivas.kozp_azon_hivo=kozpont.kozp_azon AND kozpont.primer=primer.korzet
    AND primer.varos = 'Szentendre' 
    AND datum = next_day(to_date('2012.01.31', 'yyyy.mm.dd'),'hétf?') - 1;