
insert into `community`(id,communityname,communityinfo,manager,password,phone,email)value (1,'九天花园','九天花园小区','二郎神','123','188808','123@qq.com');



INSERT INTO `user`(`username`,`password`,`phone`,`email`,`role`,`community_id`)value ('二郎神','123','10888','10@qq.com',1,1);
INSERT INTO `user`(`username`,`password`,`phone`,`email`,`role`,`community_id`)value ('哮天犬','123','1088778','104@qq.com',2,1);

select 1 from notice where id=3 limit 1;
select count(*) from (select * from noticeUser where id=9 limit 1)as A;
delete from noticeUser  where id=25;
