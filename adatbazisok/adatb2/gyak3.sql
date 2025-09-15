--1 feladat
SELECT blocks
FROM dba_segments
WHERE owner = 'NIKOVITS' AND segment_name = 'CIKK';

--2 feladat
SELECT rowid, c.*, dbms_rowid.rowid_block_number(rowid)
FROM nikovits.cikk c;

SELECT COUNT(DISTINCT dbms_rowid.rowid_block_number(rowid)) as block
FROM nikovits.cikk c;

SELECT dbms_rowid.rowid_relative_fno(rowid) as file_id, count(rowid) as darab, dbms_rowid.rowid_block_number(rowid) as block_num
FROM nikovits.cikk 
GROUP BY dbms_rowid.rowid_block_number(rowid),dbms_rowid.rowid_relative_fno(rowid);
--5 feladat
SELECT *
FROM dba_extents
WHERE owner = 'NIKOVITS' and segment_name = 'TABLA_123';


SELECT COUNT(*)
FROM nikovits.tabla_123
WHERE dbms_rowid.rowid_block_number(rowid) = 24192;


CREATE OR REPLACE PROCEDURE num_of_rows IS 
    var_row_count INTEGER;
BEGIN 
    FOR var_ext_rec in (SELECT * FROM dba_extents WHERE owner = 'NIKOVITS' and segment_name = 'TABLA_123' ORDER BY relative_fno, block_id)
    LOOP
        FOR var_block_id in var_ext_rec.block_id..(var_ext_rec.block_id + var_ext_rec.blocks - 1)
        LOOP
            SELECT COUNT(*) 
            INTO var_row_count
            FROM nikovits.tabla_123
            WHERE dbms_rowid.rowid_block_number(rowid) = var_block_id and dbms_rowid.rowid_relative_fno(rowid) = var_ext_rec.relative_fno;
            dbms_output.put_line(var_ext_rec.relative_fno || ';' || var_block_id || '->' || var_row_count);
        END LOOP;
    END LOOP;
END;


SET SERVEROUTPUT ON
EXECUTE num_of_rows();

EXECUTE check_plsql('num_of_rows()');
