/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2019/7/2 16:19:36                            */
/*==============================================================*/


drop table if exists User;

drop table if exists antivity;

drop table if exists billrecord;

drop table if exists disgoods;

drop table if exists estate;

drop table if exists latestnew;

drop table if exists manager;

drop table if exists merchant;

drop table if exists parcel;

drop table if exists popnotice;

drop table if exists resident;

drop table if exists store;

drop table if exists urgmessage;

/*==============================================================*/
/* Table: User                                                  */
/*==============================================================*/
create table User
(
   Uderid               varchar(24),
   userpassword         varchar(24),
   userphone            varchar(12),
   useremail            varchar(48)
);

/*==============================================================*/
/* Table: antivity            活动                                  */
/*==============================================================*/
create table antivity
(
   anticityid           varchar(24),
   launchid             varchar(24),
   antivitydetail       varchar(1024)
);

/*==============================================================*/
/* Table: billrecord            每月账单                                */
/*==============================================================*/
create table billrecord
(
   recordid             varchar(24),
   managerid            varchar(24),
   recordtime           date,
   totalpay             decimal(8,2),
   status               int
);

/*==============================================================*/
/* Table: disgoods             优惠商品                                 */
/*==============================================================*/
create table disgoods
(
   goodid               varchar(24),
   goodname             varchar(24),
   goodprice            decimal(8,2)
);

/*==============================================================*/
/* Table: estate      小区                                          */
/*==============================================================*/
create table estate
(
   estateid             varchar(24),
   statename            varchar(24),
   estatelocation       varchar(1024)-- 待定
);

/*==============================================================*/
/* Table: latestnew          小区最新资讯                                   */
/*==============================================================*/
create table latestnew
(
   newid                varchar(24),
   newtime              date,
   newcontent           varchar(1024),
   managerid            varchar(24)
);

/*==============================================================*/
/* Table: manager             管理员                                  */
/*==============================================================*/
create table manager
(
   role                 int,-- 有物业通知管理员，物业缴费管理员，小区资讯管理，小区邮包管理员，小区负责人（小区创建者）
   managerid               varchar(24),
   managerpassword         varchar(24),
   managerphone            varchar(12),
   manageremail            varchar(48)
);

/*==============================================================*/
/* Table: merchant    商家                                          */
/*==============================================================*/
create table merchant
(
   storename            varchar(48),
   storephone           varchar(12),
   merchantid           varchar(24),
   merchantpassword         varchar(24),
   merchantphone            varchar(12),
   merchantemail            varchar(48)
);

/*==============================================================*/
/* Table: parcel        邮包                                        */
/*==============================================================*/
create table parcel
(
   parcelid             varchar(48),
   parcelowner          varchar(24),
   parceltime           date,
   parcelphone          varchar(12)
);

/*==============================================================*/
/* Table: popnotice           小区最新资讯                                  */
/*==============================================================*/
create table popnotice
(
   noticeid             long,
   managerid            varchar(24),
   noticetime           date,
   noticeconntent       varchar(2048)
);

/*==============================================================*/
/* Table: resident        小区居民                                      */
/*==============================================================*/
create table resident
(
   residentid               varchar(24),
   residentpassword         varchar(24),
   residentphone            varchar(12),
   residentemail            varchar(48)
);

/*==============================================================*/
/* Table: store     地图上的商店                                            */
/*==============================================================*/
create table store
(
   location             varchar(1024),-- 待定
   storeid              varchar(24)
);

/*==============================================================*/
/* Table: urgmessage      紧急通知                                      */
/*==============================================================*/
create table urgmessage
(
   urgid                varchar(24),
   urgtime              date,
   urgcontent           varchar(2048),
   managerid            varchar(24),
   status               int -- 看是否过期
);


