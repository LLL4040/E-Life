
DELIMITER//
DROP PROCEDURE if EXISTS movenews;
create PROCEDURE movenews(in nonused int)
begin
DECLARE done INT DEFAULT 0;
 DECLARE id_temp int DEFAULT 0;
 DECLARE status_temp int DEFAULT 0;
DECLARE time_temp timestamp;
DECLARE content_temp VARCHAR(1024);
DECLARE title_temp VARCHAR(50);
DEclare photo_temp varchar(100);
DECLARE managername_temp VARCHAR(50);
DECLARE cid_temp int DEFAULT 0;
DECLARE temp CURSOR for select id,time,content,managername,title,status,photo,community_id from news;
DECLARE CONTINUE HANDLER FOR NOT FOUND SET done=1;
OPEN temp;
REPEAT
        FETCH temp INTO id_temp,time_temp,content_temp,managername_temp,title_temp,status_temp,photo_temp,cid_temp;
        IF NOT done THEN
							IF status_temp=1 then
								INSERT into newsused VALUES(id_temp,time_temp,content_temp,managername_temp,title_temp,photo_temp,cid_temp);
								delete from news where id=id_temp;
		END IF;
end if;
    UNTIL done END REPEAT;
 
    CLOSE temp;
end;
//
DELIMITER;