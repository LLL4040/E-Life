
insert into `community`(id,communityname,communityinfo,manager,password,phone,email,`address`)value (1,'九天花园','九天花园小区','二郎神','123','188808','123@qq.com','hahaha');


INSERT INTO `user`(`username`,`password`,`phone`,`email`,`role`,`community_id`)value ('哪吒','123','1088558','10@qq.com',1,0);
INSERT INTO `user`(`username`,`password`,`phone`,`email`,`role`,`community_id`)value ('悟能','123','1088558','10@qq.com',1,0);

INSERT INTO `user`(`username`,`password`,`phone`,`email`,`role`,`community_id`)value ('二郎神','123','10888','10@qq.com',1,1);
INSERT INTO `user`(`username`,`password`,`phone`,`email`,`role`,`community_id`)value ('哮天犬','123','1088778','104@qq.com',2,0);
INSERT INTO `manager`(`username`,`password`,`phone`,`email`,`role`,`community_id`)value ('二郎神','123','1088778','104@qq.com',1,1);

insert into `maintain`(time,status,content,maintainname,phone,managername,username,userphone)value (
    '2019',0,'我家厕所堵住了',null,null,null,'哮天犬',1088778
    );
insert into `maintain`(time,status,content,maintainname,phone,managername,username,userphone)value (
    '2019',0,'我家门锁头坏了',null,null,null,'哪吒',1088778
    );
select count(*) from (select * from noticeUser where id=9 limit 1)as A;
delete from noticeUser  where id=25;

select * from (maintain natural join (select username from user where community_id=1)as A);
insert into `notice`(id,time,content,managername,communityId)value (5,'2019/7/10','你家厕所欠费了','二郎神',1);
