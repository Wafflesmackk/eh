--1 feladat: Hozzunk létre egy oszt10 nevû nézetet, amely megjeleníti azon dolgozók nevét és fizetését akik a 10es osztályon dolgoznak
CREATE OR REPLACE VIEW oszt10 AS
SELECT dnev, fizetes
FROM dolgozo
WHERE oazon = 10;

SELECT * FROM oszt10;

--2 feladat: PLSQL Hello World
DECLARE
    message VARCHAR(20) := 'Hello World2';
BEGIN
    dbms_output.put_line('Hello world');
    dbms_output.put_line(message);
END;

--tipusok
DECLARE
    num1 INTEGER;
    num2 REAL;   --lebegõpontos
    num3 DOUBLE PRECISION;  --double
    num4 NUMERIC;
    
    char1 CHAR(10); --pontosan annyi karakter amennyi a szám utána
    char2 VARCHAR(20); --változó hosszú (rövidebbet is lehet neki beadni hosszab levágja)
    char3 LONG;
    
    bool BOOLEAN;
    datum DATE;
BEGIN
    NULL;
END;    

--Deklarációk
DECLARE
    a INTEGER := 10;
    b CONSTANT INTEGER := 20;
    c INTEGER;
    
BEGIN 
    c := a + b;
    dbms_output.put_line('VALUE of c:' || c);
END;

DECLARE
    my_dkod dolgozo.dkod%type := 7839;
    my_dnev dolgozo.dnev%type;
    my_fizetes dolgozo.fizetes%type;
BEGIN
    SELECT dnev,fizetes
    INTO my_dnev, my_fizetes
    FROM dolgozo
    WHERE dkod = my_dkod;
    dbms_output.put_line(my_dnev || ' ' || my_fizetes);
END;

DECLARE
    my_dkod dolgozo.dkod%type := 7839;
    my_dolgozo dolgozo%rowtype;
BEGIN
    SELECT *
    INTO my_dolgozo
    FROM dolgozo
    WHERE dkod = my_dkod;
    dbms_output.put_line( my_dolgozo.dnev || ' ' || my_dolgozo.fizetes);
END;

-- for, while
DECLARE
    i INTEGER := 0;
BEGIN
    FOR i in 0..10 LOOP
        IF MOD(i,2) = 0 THEN
             dbms_output.put_line( i || ' paros');
        ELSE     
             dbms_output.put_line( i || ' paratlan');
        END IF; 
    END LOOP;  
    
    WHILE i < 10 LOOP
    IF MOD(i,2) = 0 THEN
             dbms_output.put_line( i || ' paros');
        ELSE     
             dbms_output.put_line( i || ' paratlan');
        END IF;
        i := i + 1;
    END LOOP;  
END;

--Procedurak es fuggvenyek
CREATE OR REPLACE PROCEDURE hello AS
BEGIN
   dbms_output.put_line('Hello World');
END;

CALL hello();
EXECUTE hello();
BEGIN 
    hello();
END;    

CREATE OR REPLACE PROCEDURE osszeadas(a IN INTEGER, b IN INTEGER, c OUT INTEGER) AS
BEGIN
    c := a + b;
END;

DECLARE 
    eredmeny INTEGER;
BEGIN
    osszeadas(1,2,eredmeny);
    dbms_output.put_line(eredmeny);
END;

CREATE OR REPLACE PROCEDURE negyzet(a IN OUT INTEGER) AS
BEGIN
    a := a * a;
END;

DECLARE 
    szam INTEGER := 2;
BEGIN
    negyzet(szam);
    dbms_output.put_line(szam);
END;


CREATE OR REPLACE FUNCTION osszeadas_fun(a IN INTEGER, b IN INTEGER)
RETURN INTEGER AS
    osszeg INTEGER;
BEGIN
    osszeg := a + b;
    RETURN osszeg;
END;

DECLARE 
    eredmeny INTEGER;
BEGIN
    eredmeny := osszeadas_fun(1,2);
    dbms_output.put_line(eredmeny);
END;


--kurzorok
-- CURSOR <kurzor_neve> IS SELECT * FROM <tabla_neve>
-- OPEN <kurzor_neve>
-- FETCH <kurzor_neve> INTO <valtozo_neve>
-- CLOSE <kurzor_neve>
-- EXIT WHEN <kurzor_neve>%notfound LOOP

CREATE OR REPLACE PROCEDURE kurzorteszt1 AS
    CURSOR k IS SELECT * FROM dolgozo;
    sor dolgozo%rowtype;
BEGIN
    OPEN k;
    LOOP
        FETCH k INTO sor;
        EXIT WHEN k%notfound;
        dbms_output.put_line(sor.dnev);
    END LOOP;
END;

CREATE OR REPLACE PROCEDURE kurzorteszt2 AS
    CURSOR k IS SELECT * FROM dolgozo;
    sor dolgozo%rowtype;
BEGIN
    FOR sor in k LOOP
        dbms_output.put_line(sor.dnev);
    END LOOP;
END;

CALL kurzorteszt2();

--Prim fuggveny ami eldonti egy szamrol hogy prim-e

CREATE OR REPLACE FUNCTION prime_fun(a IN INTEGER)
RETURN INTEGER AS
    prime INTEGER := 1;
    b INTEGER;
BEGIN
    IF a < 2 THEN
        RETURN 0;
    END IF;
    b := a / 2;
    FOR i in 2..b LOOP
        IF MOD(a,i) = 0 THEN
             prime := 0;
             RETURN prime;
        END IF; 
    END LOOP;  
    RETURN prime;
END;

DECLARE 
    eredmeny INTEGER := 1 ;
BEGIN
    eredmeny := prime_fun(27);
    dbms_output.put_line(eredmeny || ' ');
END;












