EXPLAIN PLAN SET statement_id='ut171c'
   FOR 
    SELECT osztaly.onev
    FROM osztaly    
    WHERE oazon IN (SELECT oazon FROM dolgozo,fiz_kat WHERE (fizetes between fiz_kat.also AND fiz_kat.felso) AND fiz_kat.kategoria = 1);
    
CREATE INDEX dolgozo_fizetes_idx
ON dolgozo(fizetes);    
    
select plan_table_output from table(dbms_xplan.display('plan_table','ut171c','all'));

