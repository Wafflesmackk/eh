

SELECT *
FROM dba_segments
WHERE owner = 'NIKOVITS' AND segment_type = 'TABLE PARTITION';

SELECT *
FROM dba_part_tables
WHERE owner = 'NIKOVITS';

CREATE OR REPLACE PROCEDURE partitionDetails IS
    var_size INTEGER;
BEGIN
    FOR var_table_name IN (SELECT * FROM dba_part_tables WHERE owner = 'NIKOVITS')
    LOOP
        dbms_output.put_line('Tablanev:' || var_table_name.table_name || ' :');
        FOR var_part_name IN (SELECT * FROM dba_tab_partitions WHERE table_owner = 'NIKOVITS' AND table_name = var_table_name.table_name)
        LOOP
            
            dbms_output.put_line(var_part_name.partition_name);
            
            FOR var_part_size IN (SELECT * FROM dba_segments WHERE owner = 'NIKOVITS' AND segment_type = 'TABLE PARTITION' AND partition_name = var_part_name.partition_name)
            LOOP
                dbms_output.put_line('Meret bytes ' || var_part_size.bytes);
            END LOOP;
          
            
        
        END LOOP;
    END LOOP;
        
END;

SET SERVEROUTPUT ON
EXECUTE partitionDetails();

SELECT *
FROM dba_segments 
WHERE owner = 'NIKOVITS' AND segment_type = 'INDEX' AND segment_name IN (SELECT )
ORDER BY segment_name;

CREATE OR REPLACE FUNCTION nt_tabs RETURN VARCHAR2 IS
    index_names VARCHAR2(20000);
BEGIN   
    FOR var_indexes IN (SELECT DISTINCT(segment_name) FROM dba_segments WHERE owner = 'NIKOVITS' AND segment_type = 'INDEX' ORDER BY segment_name)
    LOOP
        dbms_output.put_line(var_indexes.segment_name);
        index_names := index_names || ', ' || var_indexes.segment_name;
    END LOOP;
    return index_names;
END;

SET SERVEROUTPUT ON
SELECT nt_tabs() from dual;




