
DELIMITER//
DROP PROCEDURE if EXISTS cStatus;
create PROCEDURE cStatus(in cid int)
begin
DECLARE done INT DEFAULT 0;
 DECLARE id_temp int DEFAULT 0;
 DECLARE status_temp int DEFAULT 0;
DECLARE temp CURSOR for select id,status from urgent where community_id=cid;
DECLARE CONTINUE HANDLER FOR NOT FOUND SET done=1;
OPEN temp;
REPEAT
        FETCH temp INTO id_temp,status_temp;
        IF NOT done THEN
							IF status_temp=0 then
								update urgent set status =1 where id=id_temp;
		END IF;
end if;
    UNTIL done END REPEAT;
 
    CLOSE temp;
end;
//
DELIMITER;