SELECT * 
FROM dba_tables
WHERE owner = 'NIKOVITS' AND iot_type = 'IOT';

SELECT *
FROM dba_segments
WHERE segment_name IN ( SELECT index_name
                        FROM user_indexes
                        WHERE index_type LIKE('IOT%'));

SELECT *
FROM user_indexes
WHERE index_type LIKE('IOT%');

SELECT *
FROM user_objects
WHERE object_name IN (  SELECT index_name FROM user_indexes WHERE index_type LIKE('IOT%'));

drop table eladasok;
drop table eladasok2;
drop table eladasok3;
drop table eladasok4;
drop table eladasok5;

-- Particionálás tartományok alapján
CREATE TABLE eladasok (szla_szam   NUMBER(5), 
                       szla_nev    CHAR(30), 
                       mennyiseg   NUMBER(6), 
                       het         INTEGER ) 
PARTITION BY RANGE (het)  
 (PARTITION negyedev1  VALUES LESS THAN (13) SEGMENT CREATION IMMEDIATE 
    STORAGE(INITIAL 8K NEXT 8K) TABLESPACE users, 
  PARTITION negyedev2  VALUES LESS THAN (26) SEGMENT CREATION IMMEDIATE 
    STORAGE(INITIAL 8K NEXT 8K) TABLESPACE example, 
  PARTITION negyedev3  VALUES LESS THAN (39) SEGMENT CREATION IMMEDIATE  
    STORAGE(INITIAL 8K NEXT 8K) TABLESPACE users)
;

insert into eladasok values(100, 'Sport cikkek', 231, 2);
insert into eladasok values(101, 'Irodai termekek', 1200, 3);
insert into eladasok values(102, 'Eszkozok', 43, 4);
insert into eladasok values(103, 'Gepek', 21, 6);
insert into eladasok values(104, 'Butorok', 31, 7);
insert into eladasok values(105, 'Ingatlan', 3, 8);
insert into eladasok values(106, 'Szolgaltatasok', 200, 9);
insert into eladasok values(107, 'Elelmiszer', 300, 40); -- ezt már nem tudja beszúrni, 40 > 39 

-- Particionálás hash kulcs alapján
CREATE TABLE eladasok2 (szla_szam   NUMBER(5), 
                        szla_nev    CHAR(30), 
                        mennyiseg   NUMBER(6), 
                        het         INTEGER ) 
PARTITION BY HASH ( het )  
   (PARTITION part1 SEGMENT CREATION IMMEDIATE TABLESPACE users, 
    PARTITION part2 SEGMENT CREATION IMMEDIATE TABLESPACE example, 
    PARTITION part3 SEGMENT CREATION IMMEDIATE TABLESPACE users )
;
insert into eladasok2 select * from eladasok;

-- Particionálás lista alapján
CREATE TABLE eladasok3 (szla_szam   NUMBER(5), 
                        szla_nev    CHAR(30), 
                        mennyiseg   NUMBER(6), 
                        het         INTEGER ) 
PARTITION BY LIST ( het )  
   (PARTITION part1 VALUES(1,2,3,4,5)   SEGMENT CREATION IMMEDIATE 
      STORAGE(INITIAL 8K NEXT 8K) TABLESPACE users, 
    PARTITION part2 VALUES(6,7,8,9)     SEGMENT CREATION IMMEDIATE 
      STORAGE(INITIAL 8K NEXT 8K) TABLESPACE example, 
    PARTITION part3 VALUES(10,11,12,13) SEGMENT CREATION IMMEDIATE 
      STORAGE(INITIAL 8K NEXT 8K) TABLESPACE users ) -- ide sem tud beszúrni > 13-at
;
insert into eladasok3 select * from eladasok;    

CREATE TABLE eladasok4 (szla_szam   NUMBER(5), 
                        szla_nev    CHAR(30), 
                        mennyiseg   NUMBER(6), 
                        het         INTEGER ) 
PARTITION BY RANGE ( het )
SUBPARTITION BY HASH (mennyiseg)
SUBPARTITIONS 3  
   (PARTITION negyedev1  VALUES LESS THAN  ( 13 )  SEGMENT CREATION IMMEDIATE 
      STORAGE(INITIAL 8K NEXT 8K) TABLESPACE users, 
    PARTITION negyedev2  VALUES LESS THAN  ( 26 )  SEGMENT CREATION IMMEDIATE 
      STORAGE(INITIAL 8K NEXT 8K) TABLESPACE example, 
    PARTITION negyedev3  VALUES LESS THAN  ( 39 )  SEGMENT CREATION IMMEDIATE
      STORAGE(INITIAL 8K NEXT 8K) TABLESPACE users )
;
insert into eladasok4 select * from eladasok;


CREATE TABLE eladasok5 (szla_szam   NUMBER(5), 
                        szla_nev    CHAR(30), 
                        mennyiseg   NUMBER(6), 
                        het         INTEGER ) 
PARTITION BY RANGE ( mennyiseg )
SUBPARTITION BY LIST (het)
SUBPARTITION TEMPLATE
  (SUBPARTITION lista VALUES(1,2,3,4,5), SUBPARTITION other VALUES(DEFAULT))
    (PARTITION kicsi    VALUES LESS THAN  (100) SEGMENT CREATION IMMEDIATE
       STORAGE(INITIAL 8K NEXT 8K) TABLESPACE users,
     PARTITION kozepes  VALUES LESS THAN  (500) SEGMENT CREATION IMMEDIATE  
       STORAGE(INITIAL 8K NEXT 8K) TABLESPACE example, 
     PARTITION nagy     VALUES LESS THAN  (MAXVALUE) SEGMENT CREATION IMMEDIATE 
       STORAGE(INITIAL 8K NEXT 8K) TABLESPACE users )
;
insert into eladasok5 select * from eladasok;
commit;

SELECT *
FROM dba_tables
WHERE owner = 'NIKOVITS';

SELECT column_name, column_position
FROM DBA_PART_KEY_COLUMNS
WHERE name = 'ELADASOK';


drop table emp_clt;
drop table dept_clt;
drop cluster personnel_cl;
drop table cikk_hclt;
drop table szallit_hclt;
drop cluster cikk_hcl;
drop table cikk_hcl2t;
drop cluster cikk_hcl2;
drop cluster cikk_hcl3;



-- Cluster létrehozása (index cluster lesz):
CREATE CLUSTER personnel_cl (department_number NUMBER(2)) SIZE 4K;

-- Táblák létrehozása a clusteren:
CREATE TABLE emp_clt
  (empno NUMBER PRIMARY KEY, ename VARCHAR2(30), job VARCHAR2(27),
   mgr NUMBER(4), hiredate DATE, sal NUMBER(7,2), comm NUMBER(7,2), 
   deptno NUMBER(2) NOT NULL)
CLUSTER personnel_cl (deptno);
  
CREATE TABLE dept_clt
  (deptno NUMBER(2), dname VARCHAR2(42), loc VARCHAR2(39))
CLUSTER personnel_cl (deptno);

-- Index létrehozása (csak ezután lehet sorokat beszúrni):
CREATE INDEX personnel_cl_idx ON CLUSTER personnel_cl;
INSERT INTO emp_clt SELECT * FROM emp;
INSERT INTO dept_clt SELECT * FROM dept;

SELECT * FROM emp;

SELECT rowid, ename ename_dname FROM emp_clt WHERE deptno=10
 UNION
SELECT rowid, dname FROM dept_clt WHERE deptno=10;
                        

CREATE CLUSTER cikk_hcl (ckod  NUMBER ) SIZE 4K  HASHKEYS 30;
CREATE TABLE cikk_hclt(ckod NUMBER, cnev VARCHAR2(20), 
             szin VARCHAR2(15), suly FLOAT) 
CLUSTER cikk_hcl(ckod); 
INSERT INTO cikk_hclt select * from cikk;
CREATE TABLE szallit_hclt(szkod NUMBER, ckod NUMBER, pkod NUMBER, 
             mennyiseg NUMBER, datum DATE) 
CLUSTER cikk_hcl(ckod); 
INSERT INTO szallit_hclt select * from szallit;

-- Saját hash függvényt adunk meg:

CREATE CLUSTER cikk_hcl2 (ckod  NUMBER ) HASHKEYS 30 HASH IS MOD(ckod, 53);
CREATE TABLE cikk_hcl2t(ckod NUMBER, cnev VARCHAR2(20), 
             szin VARCHAR2(15), suly FLOAT) 
CLUSTER cikk_hcl2(ckod); 
INSERT INTO cikk_hcl2t select * from cikk;


CREATE CLUSTER cikk_hcl3 (ckod  NUMBER ) SINGLE TABLE HASHKEYS 30;



SELECT c.cluster_name
FROM user_clusters c left join user_tables t on c.cluster_name = t.cluster_name
WHERE t.table_name is null;

SELECT owner, cluster_name
FROM dba_clu_columns
GROUP BY owner, cluster_name
HAVING COUNT(DISTINCT clu_column_name) = 3;


