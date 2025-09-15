
CREATE OR REPLACE PROCEDURE print_histogram(p_owner VARCHAR, p_table VARCHAR, p_col VARCHAR) AS
    TYPE cursortype IS REF CURSOR;
    var_cursor cursortype;
    sql_string VARCHAR2(1024);
    output VARCHAR2(1024);
    counter INT;
    padding VARCHAR2(1024);
    max_value INT;
    row_num INT;
    max_string_length INT;
    
    exc_invalid_id EXCEPTION;
    PRAGMA EXCEPTION_INIT(exc_invalid_id, -904);
    
    exc_table_does_not_exist EXCEPTION;
    PRAGMA EXCEPTION_INIT(exc_table_does_not_exist, -942);
    
BEGIN
    EXECUTE IMMEDIATE 'SELECT COUNT(DISTINCT ' || p_col || ') FROM ' || p_owner || '.' || p_table INTO row_num;
    IF row_num < 100 THEN
        EXECUTE IMMEDIATE 'SELECT  MAX(db) FROM (SELECT COUNT(*) db FROM ' || p_owner || '.' || p_table || ' GROUP BY ' || p_col || ')' into max_value;
        EXECUTE IMMEDIATE 'SELECT MAX(length(' || p_col ||')) FROM ' ||  p_owner || '.' || p_table INTO max_string_length;
        sql_string := 'SELECT ' || p_col || ', COUNT(*) FROM ' || p_owner || '.' || p_table || ' GROUP BY ' || p_col ||' ORDER BY ' || p_col;
        OPEN var_cursor FOR sql_string;
        LOOP
            FETCH var_cursor INTO output, counter;
            EXIT WHEN var_cursor%notfound;
            padding := lpad('*',ceil(counter / max_value * 50),'*');
            output := rpad(output, max_string_length, ' ');
            dbms_output.put_line(output|| ' --> '|| padding);
        END LOOP;
    ELSE
        dbms_output.put_line('Few or too many distinct values in column');
    END IF;
EXCEPTION    
    WHEN exc_table_does_not_exist THEN
        dbms_output.put_line('Non-existing table or column');
    WHEN exc_invalid_id THEN
        dbms_output.put_line('Non-existing table or column');
END;

set serveroutput on;
CALL print_histogram('nikovits','customers','cust_year_of_birth');



