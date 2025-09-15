
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