--��@�m��2
create table POLICE_STATION(
 SERIAL_NUMBER VARCHAR2(10BYTE) primary key,
 PRECINCT NVARCHAR2(30),
 PRICINCT_ADDRESS NVARCHAR2(30),
 PRICINCT_ADDRESS NVARCHAR2(50),
 PRICINCT_CALL VARCHAR2(10 BYTE));

 create table VALLEY_OFFICE(
 SERIAL_NUMBER VARCHAR2(10BYTE) primary key,
 VALLEY NVARCHAR2(30),
 OFFICE_ADDRESS NVARCHAR2(30),
 OFFICE_ADDRESS NVARCHAR2(50),
 OFFICE_CALL VARCHAR2(10BYTE));

create table BUILDING_TYPE(
 SERIAL_NUMBER VARCHAR2(10BYTE) primary key,
 TYPE NVARCHAR2(30));
 
 alter table BUILDING_TYPE rename column TYPE to B_TYPE; 

create table BUILDING(
 SERIAL_NUMBER NUMBER(2,0) primary key,
 BUILDING_ADDRESS NVARCHAR2(30),
 BUILDING_ADDRESS NVARCHAR2(50),
 CAPACITY NUMBER(2,0),
 BASEMENT NUMBER(2,0),
 BUILDING_SORT VARCHAR2(10BYTE) references BUILDING_TYPE(SERIAL_NUMBER),
 POLICE_SORT VARCHAR2(10BYTE) references POLICE_STATION(SERIAL_NUMBER),
 VALLEY_SORT VARCHAR2(10BYTE) references VALLEY_OFFICE(SERIAL_NUMBER));
 
 alter table BUILDING rename column CAPACITY to ACCOMMODATE; 
 
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
select PRECINCT, PRICINCT_CALL
 from EDU_student.BUILDING BU
 left join EDU_student.POLICE_STATION PS
 on PS.SERIAL_NUMBER = BU.POLICE_SORT
 where BU.ACCOMMODATE > 1000;
 
--��@�m��4-2
select PRECINCT, PRICINCT_CALL,
       count(PRECINCT)over(partition by PRECINCT)as CNT
 from EDU_student.BUILDING BU
 left join EDU_student.POLICE_STATION PS
 on PS.SERIAL_NUMBER = BU.POLICE_SORT
 where BU.ACCOMMODATE > 1000;
--��@�m��4-3
select PRECINCT, PRICINCT_CALL,
       count(PRECINCT)over(partition by PRECINCT)as CNT,
       BU.BUILDING_ADDRESS,
       BT.B_TYPE
 from EDU_student.BUILDING BU
 left join EDU_student.POLICE_STATION PS
 on PS.SERIAL_NUMBER = BU.POLICE_SORT
 left join EDU_student.BUILDING_TYPE BT
 on BU.BUILDING_SORT = BT.SERIAL_NUMBER
 where BU.ACCOMMODATE > 1000;
--��@�m��4-4
select VO.VALLEY, BU.BUILDING_ADDRESS, BU.ACCOMMODATE, PS.PRECINCT, PS.PRICINCT_CALL
 from EDU_student.BUILDING BU
 left join EDU_student.VALLEY_OFFICE VO
 on VO.SERIAL_NUMBER = BU.VALLEY_SORT
 left join EDU_student.POLICE_STATION PS
 on BU.POLICE_SORT = PS.SERIAL_NUMBER
 where BU.BUILDING_ADDRESS LIKE '%��%';
--��@�m��4-5
select VO.VALLEY, VO.OFFICE_ADDRESS, BU.BUILDING_ADDRESS, BU.ACCOMMODATE
 from EDU_student.BUILDING BU
 left join EDU_student.BUILDING_TYPE BT
 on BU.BUILDING_SORT = BT.SERIAL_NUMBER
 left join EDU_student.VALLEY_office VO
 on BU.VALLEY_SORT = VO.SERIAL_NUMBER
 where BT.B_TYPE in ('���J','�j��') ;
--��@�m��5-1
create table BUILDING1 as select * from BUILDING;
update BUILDING1 
 set ACCOMMODATE = 5000
 where BUILDING_ADDRESS = '�]�߿��˫n��M����79��';
 
--��@�m��5-2
create table BUILDING2 as select * from BUILDING;
 delete from BUILDING2
 where ACCOMMODATE < 1000;
