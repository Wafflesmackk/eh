
SELECT *
FROM dba_indexes
WHERE owner = 'NIKOVITS' and table_name = 'CUSTOMERS';

SELECT *
FROM dba_segments
WHERE segment_name = 'CUSTOMERS_MARITAL_BIX' AND segment_type = 'INDEX';


CREATE OR REPLACE PROCEDURE list_indexes(p_owner VARCHAR2, p_table VARCHAR2) IS
var_bytes INTEGER := 0;

BEGIN
    FOR rec in ( SELECT * FROM dba_indexes WHERE owner = UPPER(p_owner) and table_name = UPPER(P_table)) LOOP
        SELECT bytes
        INTO var_bytes
        FROM dba_segments
        WHERE segment_name = rec.index_name AND segment_type = 'INDEX' AND owner = UPPER(p_owner);
        dbms_output.put_line(rec.index_name || ': ' || var_bytes);
    END LOOP;

END;

--SET SERVEROUTPUT ON
EXECUTE list_indexes('nikovits', 'customers');
EXECUTE list_indexes('nikovits', 'cikk_iot'); 


EXECUTE check_plsql('list_indexes(''nikovits'', ''customers'')');
EXECUTE check_plsql('list_indexes(''nikovits'', ''cikk_iot'')');

SELECT *
FROM dba_ind_expressions
WHERE (table_name, column_position) in (SELECT table_name, column_position FROM dba_ind_columns);

SELECT DISTINCT(table_name)
FROM dba_ind_columns
where descend = 'DESC';


SELECT index_owner, index_name, COUNT(*)
FROM dba_ind_columns
WHERE index_name IN (SELECT index_name FROM dba_indexes WHERE index_type LIKE 'FUNCTION_BASED%')
GROUP BY index_owner, index_name
HAVING COUNT(*) >= 2;










