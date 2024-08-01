--��@�m��2
create table POLICE_STATION(
 SERIAL_NUMBER VARCHAR2(10BYTE)primary key,
 PRECINCT NVARCHAR2(30),
 PRICINCT_ADDRESS NVARCHAR2(30),
 PRICINCT_CALL VARCHAR2(10 BYTE));
 
 create table VALLEY_OFFICE(
 SERIAL_NUMBER VARCHAR2(10BYTE)primary key,
 VALLEY NVARCHAR2(30),
 OFFICE_ADDRESS NVARCHAR2(30),
 OFFICE_CALL VARCHAR2(10BYTE));

create table BUILDING_TYPE(
 SERIAL_NUMBER VARCHAR2(10BYTE)primary key,
 TYPE NVARCHAR2(30));
 
create table BUILDING(
 SERIAL_NUMBER VARCHAR2(10BYTE)primary key,
 BUILDING_ADDRESS NVARCHAR2(30),
 CAPACITY NUMBER,
 BASEMENT VARCHAR2(10BYTE),
 BUILDING_SORT VARCHAR2(10BYTE)references BUILDING_TYPE(SERIAL_NUMBER),
 POLICE_SORT VARCHAR2(10BYTE)references POLICE_STATION(SERIAL_NUMBER),
 VALLEY_SORT VARCHAR2(10BYTE)references VALLEY_OFFICE(SERIAL_NUMBER));

--��@�m��3
insert into POLICE_STATION 
values('M001','�˫n����','�]�߿��˫n����ڵ�72��','037-474796');

insert into POLICE_STATION 
values('M002','�]�ߤ���','�]�߿��]�ߥ������109��','037-320059');

insert into POLICE_STATION 
values('M003','�Y������','�]�߿��Y����������503��','037-663004');

insert all into BUILDING_TYPE values('A001','���J')
           into BUILDING_TYPE values('A002','�j��')select 1 from dual;
           
insert all into BUILDING_TYPE values('A003','���@�]�I')
           into BUILDING_TYPE values('A004','�p����')select 1 from dual;
           
insert all into VALLEY_OFFICE values('C001','�j�H��','�˫n���q��1035��','037-581072')
           into VALLEY_OFFICE values('C002','�˫n��','�˫n��˫n�����s�� 103 ��','037-472735')
           into VALLEY_OFFICE values('C003','�s�Ψ�','�˫n��s�Ψ������ 14 ��','037-614186')
           into VALLEY_OFFICE values('C004','�H����','���s��H����������136-1��','037-724839')
           into VALLEY_OFFICE values('C005','��]��','�]�ߥ���]�������� 766 ��','037-333240')
           into VALLEY_OFFICE values('C006','���ڨ�','���ڨ����ڸ�96��','037-660001')
           into VALLEY_OFFICE values('C007','������','���������j��82��','037-661145')
           into VALLEY_OFFICE values('C008','�H�q��','�H�q���H�q��53��1��','037-616072')select 1 from dual;
           
insert all into BUILDING values('1','�]�߿��˫n���H��20��','100','1','A001','M001','C001')
           into BUILDING values('2','�]�߿��˫n��M����79��','3142','1','A002','M001','C002')
           into BUILDING values('3','�]�߿��˫n���s�s���T�q142��','1072','1','A002','M001','C003')
           into BUILDING values('4','�]�߿����s���ظ�1498��','32','1','A003','M001','C004')
           into BUILDING values('5','�]�߿��]�ߥ��̥���80��','106','1','A001','M002','C005')
           into BUILDING values('6','�]�߿��]�ߥ����_��117��','26','1','A001','M002','C005')
           into BUILDING values('7','�]�߿��]�ߥ��շR��109��','2038','2','A002','M002','C005')
           into BUILDING values('8','�]�߿��]�ߥ��j�P��53��','128','2','A002','M002','C005')
           into BUILDING values('9','�]�߿��Y�������ڨ��M����102��','353','1','A003','M003','C006')
           into BUILDING values('10','�]�߿��Y�������������@��69��','501','1','A004','M003','C007')
           into BUILDING values('11','�]�߿��Y�����H�q��������65��','194','1','A001','M003','C008')
           into BUILDING values('12','�]�߿��Y�����H�q��������116��','78','1','A004','M003','C008')select 1 from dual;

--��@�m��4-1
select PRECINCT,PRICINCT_CALL
from POLICE_STATION p
INNER JOIN BUILDING b
ON p.SERIAL_NUMBER = b.POLICE_SORT
WHERE b.CAPACITY > 1000;

--��@�m��4-2
select PRECINCT,PRICINCT_CALL,
       count(*)over()as CNT
from POLICE_STATION p
INNER JOIN BUILDING b
ON p.SERIAL_NUMBER = b.POLICE_SORT
WHERE b.CAPACITY > 1000;

--��@�m��4-3
select PRECINCT,PRICINCT_CALL,
       count(*)over()as CNT,
       b.BUILDING_ADDRESS,
       t.TYPE
from POLICE_STATION p
INNER JOIN BUILDING b
ON p.SERIAL_NUMBER = b.POLICE_SORT
INNER JOIN BUILDING_TYPE t
ON b.BUILDING_SORT = t.SERIAL_NUMBER
WHERE b.CAPACITY > 1000;

--��@�m��4-4
select v.VALLEY,b.BUILDING_ADDRESS,b.CAPACITY,p.PRECINCT,p.PRICINCT_CALL
from VALLEY_OFFICE v
inner join BUILDING b
on v.SERIAL_NUMBER = b.VALLEY_SORT
inner join POLICE_STATION p
on b.POLICE_SORT = p.SERIAL_NUMBER
where b.BUILDING_ADDRESS LIKE '%��%';

--��@�m��4-5
select v.VALLEY,v.OFFICE_ADDRESS,b.BUILDING_ADDRESS,b.CAPACITY
from BUILDING b
inner join BUILDING_TYPE t
on b.BUILDING_SORT = t.SERIAL_NUMBER
inner join VALLEY_office v
on b.VALLEY_SORT = v.SERIAL_NUMBER
where t.TYPE in ('���J','�j��') ;

--��@�m��5-1
create table BUILDING1 as select * from BUILDING;

update BUILDING1 
 set CAPACITY = 5000
 where BUILDING_ADDRESS = '�]�߿��˫n��M����79��';
 
--��@�m��5-2
create table BUILDING2 as select * from BUILDING;
delete from BUILDING2
 where CAPACITY < 1000;



           

