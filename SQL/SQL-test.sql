--實作練習2
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
 
--實作練習3
insert into POLICE_STATION 
values('M001','竹南分局','苗栗縣竹南鎮民族街72號','037-474796');
insert into POLICE_STATION 
values('M002','苗栗分局','苗栗縣苗栗市金鳳街109號','037-320059');
insert into POLICE_STATION 
values('M003','頭份分局','苗栗縣頭份市中興路503號','037-663004');
insert all into BUILDING_TYPE values('A001','公寓')
           into BUILDING_TYPE values('A002','大樓')select 1 from dual;
           
insert all into BUILDING_TYPE values('A003','公共設施')
           into BUILDING_TYPE values('A004','私營單位')select 1 from dual;
           
insert all into VALLEY_OFFICE values('C001','大埔里','竹南鎮公義路1035號','037-581072')
           into VALLEY_OFFICE values('C002','竹南里','竹南鎮竹南里中山路 103 號','037-472735')
           into VALLEY_OFFICE values('C003','山佳里','竹南鎮山佳里國光街 14 號','037-614186')
           into VALLEY_OFFICE values('C004','埔頂里','後龍鎮埔頂里中興路136-1號','037-724839')
           into VALLEY_OFFICE values('C005','綠苗里','苗栗市綠苗里中正路 766 號','037-333240')
           into VALLEY_OFFICE values('C006','民族里','民族里民族路96號','037-660001')
           into VALLEY_OFFICE values('C007','忠孝里','忠孝里光大街82號','037-661145')
           into VALLEY_OFFICE values('C008','信義里','信義里信義路53巷1號','037-616072')select 1 from dual;
           
insert all into BUILDING values('1','苗栗縣竹南鎮中埔街20號','100','1','A001','M001','C001')
           into BUILDING values('2','苗栗縣竹南鎮和平街79號','3142','1','A002','M001','C002')
           into BUILDING values('3','苗栗縣竹南鎮龍山路三段142號','1072','1','A002','M001','C003')
           into BUILDING values('4','苗栗縣後龍鎮中華路1498號','32','1','A003','M001','C004')
           into BUILDING values('5','苗栗縣苗栗市米市街80號','106','1','A001','M002','C005')
           into BUILDING values('6','苗栗縣苗栗市光復路117號','26','1','A001','M002','C005')
           into BUILDING values('7','苗栗縣苗栗市博愛街109號','2038','2','A002','M002','C005')
           into BUILDING values('8','苗栗縣苗栗市大同路53號','128','2','A002','M002','C005')
           into BUILDING values('9','苗栗縣頭份市民族里和平路102號','353','1','A003','M003','C006')
           into BUILDING values('10','苗栗縣頭份市忠孝忠孝一路69號','501','1','A004','M003','C007')
           into BUILDING values('11','苗栗縣頭份市信義里中正路65號','194','1','A001','M003','C008')
           into BUILDING values('12','苗栗縣頭份市信義里中正路116號','78','1','A004','M003','C008')select 1 from dual;
--實作練習4-1
select PRECINCT, PRICINCT_CALL
 from EDU_student.BUILDING BU
 left join EDU_student.POLICE_STATION PS
 on PS.SERIAL_NUMBER = BU.POLICE_SORT
 where BU.ACCOMMODATE > 1000;
 
--實作練習4-2
select PRECINCT, PRICINCT_CALL,
       count(PRECINCT)over(partition by PRECINCT)as CNT
 from EDU_student.BUILDING BU
 left join EDU_student.POLICE_STATION PS
 on PS.SERIAL_NUMBER = BU.POLICE_SORT
 where BU.ACCOMMODATE > 1000;
--實作練習4-3
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
--實作練習4-4
select VO.VALLEY, BU.BUILDING_ADDRESS, BU.ACCOMMODATE, PS.PRECINCT, PS.PRICINCT_CALL
 from EDU_student.BUILDING BU
 left join EDU_student.VALLEY_OFFICE VO
 on VO.SERIAL_NUMBER = BU.VALLEY_SORT
 left join EDU_student.POLICE_STATION PS
 on BU.POLICE_SORT = PS.SERIAL_NUMBER
 where BU.BUILDING_ADDRESS LIKE '%中%';
--實作練習4-5
select VO.VALLEY, VO.OFFICE_ADDRESS, BU.BUILDING_ADDRESS, BU.ACCOMMODATE
 from EDU_student.BUILDING BU
 left join EDU_student.BUILDING_TYPE BT
 on BU.BUILDING_SORT = BT.SERIAL_NUMBER
 left join EDU_student.VALLEY_office VO
 on BU.VALLEY_SORT = VO.SERIAL_NUMBER
 where BT.B_TYPE in ('公寓','大樓') ;
--實作練習5-1
create table BUILDING1 as select * from BUILDING;
update BUILDING1 
 set ACCOMMODATE = 5000
 where BUILDING_ADDRESS = '苗栗縣竹南鎮和平街79號';
 
--實作練習5-2
create table BUILDING2 as select * from BUILDING;
 delete from BUILDING2
 where ACCOMMODATE < 1000;
