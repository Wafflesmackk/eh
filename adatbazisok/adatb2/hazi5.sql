CREATE OR REPLACE PROCEDURE empty_blocks(p_owner VARCHAR2, p_table VARCHAR2) IS
    var_reserved_blocks INTEGER;
    var_query_string VARCHAR(1000);
    var_filled_blocks INTEGER;

BEGIN
    
    SELECT sum(blocks)
    INTO var_reserved_blocks
    FROM dba_segments
    WHERE segment_name = UPPER(p_table) and owner = UPPER(p_owner);
    
    var_query_string := 'SELECT COUNT( DISTINCT dbms_rowid.rowid_block_number(rowid)) ' || ' FROM ' || p_owner || '.' || p_table;
    
    EXECUTE IMMEDIATE var_query_string INTO var_filled_blocks;
    
    dbms_output.put_line('Empty Blocks: ' || to_char(var_reserved_blocks - var_filled_blocks) );
    
    
END;

SET SERVEROUTPUT ON
EXECUTE empty_blocks('nikovits','customers');
EXECUTE empty_blocks('nikovits','eladasok');

EXECUTE check_plsql('empty_blocks(''nikovits'', ''customers'')');
EXECUTE check_plsql('empty_blocks(''nikovits'', ''eladasok'')');