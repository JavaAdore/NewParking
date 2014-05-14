
create needed sequenses

 CREATE SEQUENCE  "TEMP"."DAILY_HISTORY_SEQ"  MINVALUE 1 MAXVALUE 999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 100 NOORDER  NOCYCLE ;
 CREATE SEQUENCE  "TEMP"."MONTHLY_HISTORY_SEQ"  MINVALUE 1 MAXVALUE 999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 100 NOORDER  NOCYCLE ;
 CREATE SEQUENCE  "TEMP"."YEARLY_HISTORY_SEQ"  MINVALUE 1 MAXVALUE 999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 100 NOORDER  NOCYCLE ;


create needed stored procedures 


create or replace
PROCEDURE DAILY_HISTORY_UPDATOR
IS
BEGIN
FOR TEMP_REC IN (SELECT * FROM GARAGESTATUS) LOOP
insert into  DAILYHISTORY  values(DAILY_HISTORY_SEQ.nextval,TEMP_REC.CONSUMEDhOURS,sysdate,TEMP_REC.SLOTID);
IF(TEMP_REC.STATUS=0) THEN
UPDATE GARAGESTATUS 
SET 
CONSUMEDhOURS = 0
TEMP_REC.ARRIVALTIME=SYSDATE
WHERE SLOTID = TEMP_REC.SLOTID;
END IF;


END LOOP;
END ;



create or replace
PROCEDURE MONTHLY_HISTORY_UPDATOR
IS
BEGIN
FOR TEMP_REC IN (SELECT slotid_slotid , sum(hours) as monthlyhours FROM DAILYHISTORY group by slotid_slotid) LOOP
insert into  monthlyhistory  values(Monthly_HISTORY_SEQ.nextval,TEMP_REC.monthlyhours,sysdate,TEMP_REC.slotid_slotid);
END LOOP;
delete from dailyhistory;
END ;




create or replace
PROCEDURE YEARLY_HISTORY_UPDATOR
IS
BEGIN
FOR TEMP_REC IN (SELECT slotid_slotid , sum(hours) as YEARLYHOURS FROM MONTHLYHISTORY group by slotid_slotid) LOOP
insert into  YEARLYHISTORY  values(YEARLY_HISTORY_SEQ.nextval,TEMP_REC.YEARLYHOURS,sysdate,TEMP_REC.slotid_slotid);
END LOOP;
delete from MONTHLYHISTORY;
END ;



BEGIN
  DBMS_SCHEDULER.CREATE_JOB (
   job_name           =>  'daily_update',
   job_type           =>  'STORED_PROCEDURE',
   job_action         =>  'DAILY_HISTORY_UPDATOR',
   start_date         =>  null,
   repeat_interval    =>  'FREQ=DAILY;INTERVAL=1', /* every other day */
   end_date           =>  null,
   auto_drop          =>   FALSE,
   enabled          =>  true,
   comments           =>  'insert data into daily history to generate reports');
END;
/


BEGIN
  DBMS_SCHEDULER.CREATE_JOB (
   job_name           =>  'monthly_update',
   job_type           =>  'STORED_PROCEDURE',
   job_action         =>  'MONTHLY_HISTORY_UPDATOR',
   start_date         =>  null,
   repeat_interval    =>  'FREQ=MONTHLY;INTERVAL=1', /* every other day */
   end_date           =>  null,
   auto_drop          =>   FALSE,
   enabled          =>  true,
   comments           =>  'insert data into monthly history to generate reports');
END;
/


BEGIN
  DBMS_SCHEDULER.CREATE_JOB (

   job_name           =>  'yearly_update',
   job_type           =>  'STORED_PROCEDURE',
   job_action         =>  'YEARLY_HISTORY_UPDATOR',
   start_date         =>  null,
   repeat_interval    =>  'FREQ=yearly;INTERVAL=1', /* every other day */
   end_date           =>  null,
   auto_drop          =>   FALSE,
   enabled          =>  true,
   comments           =>  'insert data into yearly history to generate reports');
END;
/


create or replace
procedure update_consumed_hours(v_slotid  number)
is
temp_minutes number;
begin
 update garageStatus
 
   set consumedhours = nvl(consumedhours,0) +(extract( hour from ( systimestamp - arrivaltime)))+(extract( minute from ( systimestamp - arrivaltime))/60)  
        where 
         slotid = v_slotid;
end;