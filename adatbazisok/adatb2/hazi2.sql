CREATE OR REPLACE PROCEDURE newest_table(p_user VARCHAR2) IS
 v_table_name VARCHAR2(100);
  v_size_bytes NUMBER;
  v_created_date DATE;
BEGIN

    SELECT sg.segment_name, SUM(sg.bytes) sg_bytes, MAX(obj.created) created_at
    INTO v_table_name, v_size_bytes, v_created_date
    FROM dba_segments sg JOIN dba_objects obj ON (sg.owner = obj.owner AND sg.segment_name = obj.object_name)
    WHERE  sg.owner = UPPER(p_user)
    GROUP BY sg.owner, sg.segment_name
    ORDER BY created_at DESC
    FETCH FIRST 1 ROWS ONLY;
    dbms_output.put_line('Table_name: ' || v_table_name ||' Size: ' || v_size_bytes || ' bytes' || ' Created: ' || TO_CHAR(v_created_date, 'yyyy.mm.dd.hh24:mi'));
EXCEPTION
  WHEN NO_DATA_FOUND THEN
     dbms_output.put_line('No table for ' || p_user || ' user');
  WHEN OTHERS THEN
     dbms_output.put_line('Error: ' || SQLERRM);  --nem tudom hogy kellet volna-e exception kezelés de itt van attól
END;

--TESZT
SET SERVEROUTPUT ON
EXECUTE newest_table('NIKOVITS');

EXECUTE check_plsql('newest_table(''nikovits'')');
