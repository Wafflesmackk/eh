EXPLAIN PLAN SET statement_id='ut5a'
FOR

SELECT /*+ no_index(p) no_index(c) no_index(sz) use_nl(p,c,sz) */COUNT(*)
FROM nikovits.projekt p NATURAL JOIN nikovits.cikk c NATURAL JOIN nikovits.szallit sz
WHERE c.szin = 'zold' AND p.pnev = 'Irodahaz';


select plan_table_output from table(dbms_xplan.display('plan_table','ut5a','all'));

EXPLAIN PLAN SET statement_id='ut5b'
FOR

SELECT /*+ index(p) no_index(c) no_index(sz) use_hash(p,c,sz) */COUNT(*)
FROM nikovits.projekt p NATURAL JOIN nikovits.cikk c NATURAL JOIN nikovits.szallit sz
WHERE c.szin = 'zold' AND p.pnev = 'Irodahaz';


select plan_table_output from table(dbms_xplan.display('plan_table','ut5b','all'));

EXPLAIN PLAN SET statement_id='ut5c'
FOR

SELECT /*+ index(p) index(c) no_index(sz) use_merge(p,c,sz) */COUNT(*)
FROM nikovits.projekt p NATURAL JOIN nikovits.cikk c NATURAL JOIN nikovits.szallit sz
WHERE c.szin = 'zold' AND p.pnev = 'Irodahaz';

select plan_table_output from table(dbms_xplan.display('plan_table','ut5c','all'));


EXPLAIN PLAN SET statement_id='ut6a'
FOR

SELECT /*+ full(c) index(szt) use_hash(c,szt) USE_HASH_AGGREGATION */ SUM(szt.mennyiseg),c.ckod
FROM  nikovits.szallit szt, nikovits.cikk c
WHERE c.ckod = szt.ckod AND szt.mennyiseg > 2
GROUP BY c.ckod having c.ckod > 960
ORDER BY c.ckod;


select plan_table_output from table(dbms_xplan.display('plan_table','ut6a','all'));

EXPLAIN PLAN SET statement_id='ut6b'
FOR

SELECT /*+ full(szt) index(c) use_nl(c,szt) USE_HASH_AGGREGATION */ SUM(szt.mennyiseg),c.ckod
FROM  nikovits.szallit szt, nikovits.cikk c
WHERE c.ckod = szt.ckod AND szt.mennyiseg > 2
GROUP BY c.ckod having c.ckod > 960
ORDER BY c.ckod;


select plan_table_output from table(dbms_xplan.display('plan_table','ut6b','all'));





