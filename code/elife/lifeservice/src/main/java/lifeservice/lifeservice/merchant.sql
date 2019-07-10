insert into `merchant`(id,username,name,phone,address,detail,type)value (
1,'哮天犬','沙县小区','123345','你家大门口','有饺子和包子嗷','周边餐饮');
insert into `merchant`(id,username,name,phone,address,detail,type)value (
2,'悟能','宜家超市','123345','你家大门口','限时打折嗷','超市购物');
insert into `merchant`(id,username,name,phone,address,detail,type)value (
3,'悟能','捞面馆','123345','你家大门口','有面条嗷','周边餐饮');
insert into `merchant`(id,username,name,phone,address,detail,type)value (
4,'悟能','电脑维修','123345','你家大门口','上门回收旧电脑','生活服务');
insert into `merchant`(id,username,name,phone,address,detail,type)value (
5,'悟能','悟能健身房','123345','你家大门口','健身的小伙伴有吗','休闲娱乐');
insert into `merchant`(id,username,name,phone,address,detail,type)value
    (6,'悟能','KTV','123345','你家大门口','唱歌的小伙伴有吗','休闲娱乐');
select * from (merchant natural join (select username from user where community_id=1)as A) where (type='周边餐饮'or type='超市购物'or type='休闲娱乐') ;
