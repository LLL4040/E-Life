select * from maintain  where username="哪吒" and (status=0 or 1);


select count(*) from (maintain natural join (select username from user where community_id=1)as A) where status=0;
