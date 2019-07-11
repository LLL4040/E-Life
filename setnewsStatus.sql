
DELIMITER//
DROP PROCEDURE if EXISTS cnStatus;
create PROCEDURE cnStatus(in cid int)
begin
DECLARE done INT DEFAULT 0;
 DECLARE id_temp int DEFAULT 0;
 DECLARE status_temp int DEFAULT 0;
 DECLARE num int DEFAULT 0;
 DECLARE temp CURSOR for select id,status from news where community_id=cid;
DECLARE CONTINUE HANDLER FOR NOT FOUND SET done=1;
select count(*) into num from news where `community_id`=cid and `status`=0;
OPEN temp;
REPEAT
        FETCH temp INTO id_temp,status_temp;
        IF NOT done THEN
						IF num = 5 then
							IF status_temp=0 then
								update news set status =1 where id=id_temp;
								set done=1;
		END IF;
end if;
end if;
    UNTIL done END REPEAT;
 
    CLOSE temp;
end;
//
DELIMITER;