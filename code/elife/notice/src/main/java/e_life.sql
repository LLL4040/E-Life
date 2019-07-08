DROP DATABASE IF EXISTS e_life;
create database e_life;
use e_life;

/* ============================================================== */
/* Table: community                                                                                                                   */
/* ============================================================== */

CREATE TABLE community (
                           `id` BIGINT AUTO_INCREMENT,
                           `communityname` VARCHAR(50) NOT NULL,
                           `communityinfo` VARCHAR(1024) NOT NULL,
                           `manager` VARCHAR(50) NOT NULL,
                           `password` VARCHAR(50) NOT NULL,
                           `phone` VARCHAR(20) NOT NULL,
                           `email` varchar(50) NOT NULL,
                           PRIMARY KEY (`id`)
)  ENGINE=INNODB DEFAULT CHARSET=UTF8MB4;

/* ============================================================== */
/* Table: user                                                                                                                            */
/* ============================================================== */
/* role: 0:user, 1:merchant */
CREATE TABLE user
(
    `username`  varchar(50) NOT NULL,
    `password` varchar(50) NOT NULL,
    `phone` varchar(20) NOT NULL,
    `email` varchar(50) NOT NULL,
    `role` smallint NOT NULL,
    `community_id` bigint,
    PRIMARY KEY(`username`),
    FOREIGN KEY (`community_id`)
        REFERENCES community (`id`)
        ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=INNODB DEFAULT CHARSET=UTF8MB4;

/*==============================================================*/
/* Table: merchant                                                                                                                   */
/*==============================================================*/

CREATE TABLE merchant
(
    `id`        bigint AUTO_INCREMENT,
    `username`  varchar(50) NOT NULL,
    `name`      varchar(100) NOT NULL,
    `phone`     varchar(20) NOT NULL,
    `address`   varchar(200) NOT NULL,
    `detail`    varchar(1024) NOT NULL,
    `type`      varchar(100) NOT NULL,
    PRIMARY KEY(`id`),
    FOREIGN KEY (`username`)
        REFERENCES user (`username`)
        ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=INNODB DEFAULT CHARSET=UTF8MB4;

/*==============================================================*/
/* Table: manager                                                                                                                   */
/*==============================================================*/

CREATE TABLE manager
(
    `username`  varchar(50) NOT NULL,
    `password` varchar(50) NOT NULL,
    `phone` varchar(20) NOT NULL,
    `email` varchar(50) NOT NULL,
    `role` smallint NOT NULL,
    `community_id` bigint,
    PRIMARY KEY(`username`),
    FOREIGN KEY (`community_id`)
        REFERENCES community (`id`)
        ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=INNODB DEFAULT CHARSET=UTF8MB4;

/*==============================================================*/
/* Table: friend                                                                                                                        */
/*==============================================================*/

CREATE TABLE friend
(
    `id`       bigint AUTO_INCREMENT,
    `username` varchar(50) NOT NULL,
    `friend`   varchar(50) NOT NULL,
    PRIMARY KEY(`id`),
    FOREIGN KEY (`username`)
        REFERENCES user (`username`)
        ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (`friend`)
        REFERENCES user (`username`)
        ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=INNODB DEFAULT CHARSET=UTF8MB4;

/*==============================================================*/
/* Table: urgent                                                                                                                       */
/*==============================================================*/

CREATE TABLE urgent
(
    `id`          bigint AUTO_INCREMENT,
    `time`        timestamp NULL DEFAULT CURRENT_TIMESTAMP,
    `content`     varchar(1024) NOT NULL,
    `managername` varchar(50) NOT NULL,
    `status`      smallint NOT NULL,
    `community_id` bigint,
    PRIMARY KEY(`id`),
    FOREIGN KEY (`managername`)
        REFERENCES manager (`username`)
        ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (`community_id`)
        REFERENCES community (`id`)
        ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=INNODB DEFAULT CHARSET=UTF8MB4;

/*==============================================================*/
/* Table: news                                                                                                                         */
/*==============================================================*/

CREATE TABLE news
(
    `id`          bigint AUTO_INCREMENT,
    `time`        varchar(20) NOT NULL,
    `content`     varchar(1024) NOT NULL,
    `managername` varchar(50) NOT NULL,
    `title` varchar(20) NOT NULL,
    `status` SMALLINT,
    `photo` VARCHAR(50),
    `community_id` bigint,
    PRIMARY KEY(`id`),
    FOREIGN KEY (`managername`)
        REFERENCES manager (`username`)
        ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (`community_id`)
        REFERENCES community (`id`)
        ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=INNODB DEFAULT CHARSET=UTF8MB4;

/*==============================================================*/
/* Table: urgentused                                                                                                                       */
/*==============================================================*/

CREATE TABLE urgentused
(
    `id`          bigint AUTO_INCREMENT,
    `time`        timestamp NULL DEFAULT CURRENT_TIMESTAMP,
    `content`     varchar(1024) NOT NULL,
    `managername` varchar(50) NOT NULL,
    `community_id` bigint,
    PRIMARY KEY(`id`),
    FOREIGN KEY (`managername`)
        REFERENCES manager (`username`)
        ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (`community_id`)
        REFERENCES community (`id`)
        ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=INNODB DEFAULT CHARSET=UTF8MB4;

/*==============================================================*/
/* Table: newsused                                                                                                                         */
/*==============================================================*/

CREATE TABLE newsused
(
    `id`          bigint AUTO_INCREMENT,
    `time`        varchar(20) NOT NULL,
    `content`     varchar(1024) NOT NULL,
    `managername` varchar(50) NOT NULL,
    `title` varchar(20) NOT NULL,
    `photo` VARCHAR(50),
    `community_id` bigint,
    PRIMARY KEY(`id`),
    FOREIGN KEY (`managername`)
        REFERENCES manager (`username`)
        ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (`community_id`)
        REFERENCES community (`id`)
        ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=INNODB DEFAULT CHARSET=UTF8MB4;


/*==============================================================*/
/* Table: activity                                                                                                                      */
/*==============================================================*/

CREATE TABLE activity
(
    `id`          bigint AUTO_INCREMENT,
    `time`        varchar(20) NOT NULL,
    `content`     varchar(1024) NOT NULL,
    `managername` varchar(50) NOT NULL,
    `title`       varchar(50) NOT NULL,
    `community_id` bigint,
    PRIMARY KEY(`id`),
    FOREIGN KEY (`managername`)
        REFERENCES manager (`username`)
        ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (`community_id`)
        REFERENCES community (`id`)
        ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=INNODB DEFAULT CHARSET=UTF8MB4;

/*==============================================================*/
/* Table: participator                                                                                                                */
/*==============================================================*/
CREATE TABLE Participator
(
    `id`          bigint AUTO_INCREMENT,
    `aid`        bigint NOT NULL,
    `content`    VARCHAR(100),
    `username` varchar(50) NOT NULL,
    PRIMARY KEY(`id`),
    FOREIGN KEY (`username`)
        REFERENCES user (`username`)
        ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (`aid`)
        REFERENCES activity (`id`)
        ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=INNODB DEFAULT CHARSET=UTF8MB4;

/*==============================================================*/
/* Table: pay                                                                                                                         */
/*==============================================================*/

CREATE TABLE pay
(
    `id`          bigint AUTO_INCREMENT,
    `username`    varchar(20) NOT NULL,
    `bill`        DECIMAL(8,2),
    `status`      SMALLINT,
    `community_id` bigint,
    `time` timestamp NOT NULL ,
    PRIMARY KEY(`id`),
    FOREIGN KEY (`community_id`)
        REFERENCES community (`id`)
        ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=INNODB DEFAULT CHARSET=UTF8MB4;
/*==============================================================*/
/* Table: notice                                                                                                                        */
/*==============================================================*/

CREATE TABLE notice
(
    `id`          bigint AUTO_INCREMENT,
    `time`        varchar(20) NOT NULL,
    `content`     varchar(1024) NOT NULL,
    `managername` varchar(50) NOT NULL,
    `communityId` BIGINT,

    PRIMARY KEY(`id`),
    FOREIGN KEY (`managername`)
        REFERENCES manager (`username`)
        ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=INNODB DEFAULT CHARSET=UTF8MB4;

/*==============================================================*/
/* Table: noticeUser          物业通知关联的用户                                                                                                              */
/*==============================================================*/
CREATE TABLE noticeUser
(
    `id`          bigint NOT NULL,
    `username`    varchar(50) NOT NULL,

    FOREIGN KEY (`username`)
        REFERENCES user (`username`)
        ON DELETE CASCADE ON UPDATE CASCADE
)ENGINE=INNODB DEFAULT CHARSET=UTF8MB4;
/*==============================================================*/
/* Table: package                                                                                                                    */
/*==============================================================*/

CREATE TABLE package
(
    `id`          bigint AUTO_INCREMENT,
    `time`        varchar(20) NOT NULL,
    `status`      smallint NOT NULL,
    `managername` varchar(50) NOT NULL,
    `username`    varchar(50) NOT NULL,
    PRIMARY KEY(`id`),
    FOREIGN KEY (`managername`)
        REFERENCES manager (`username`)
        ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (`username`)
        REFERENCES user (`username`)
        ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=INNODB DEFAULT CHARSET=UTF8MB4;

/*==============================================================*/
/* Table: maintain                                                                                                                    */
/*==============================================================*/

CREATE TABLE maintain
(
    `id`          bigint AUTO_INCREMENT,
    `time`        varchar(20) NOT NULL,
    `status`      smallint NOT NULL,
    `content`     varchar(1024) NOT NULL,
    `maintainname` varchar(100),
    `phone`    varchar(100),
    `managername` varchar(50) NOT NULL,
    `username`    varchar(50) NOT NULL,
    PRIMARY KEY(`id`),
    FOREIGN KEY (`managername`)
        REFERENCES manager (`username`)
        ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (`username`)
        REFERENCES user (`username`)
        ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=INNODB DEFAULT CHARSET=UTF8MB4;

/*==============================================================*/
/* Table: maintain                                                                                                                    */
/*==============================================================*/

CREATE TABLE demand
(
    `id`          bigint AUTO_INCREMENT,
    `starttime`   varchar(20) NOT NULL,
    `endtime`   varchar(20) NOT NULL,
    `content`     varchar(1024) NOT NULL,
    `username`    varchar(50) NOT NULL,
    PRIMARY KEY(`id`),
    FOREIGN KEY (`username`)
        REFERENCES user (`username`)
        ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=INNODB DEFAULT CHARSET=UTF8MB4;


/*==============================================================*/
/* Table: discount                                                                                                                     */
/*==============================================================*/

CREATE TABLE discount
(
    `id`          bigint AUTO_INCREMENT,
    `starttime`   varchar(20) NOT NULL,
    `endtime`     varchar(20) NOT NULL,
    `merchantid`  bigint NOT NULL,
    `number`      int NOT NULL,
    `content`     varchar(1024) NOT NULL,
    `status`      smallint NOT NULL,
    PRIMARY KEY(`id`),
    FOREIGN KEY (`merchantid`)
        REFERENCES merchant (`id`)
        ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=INNODB DEFAULT CHARSET=UTF8MB4;

/*==============================================================*/
/* Table: bargain                                                                                                                     */
/*==============================================================*/

CREATE TABLE bargain
(
    `id`          bigint AUTO_INCREMENT,
    `starttime`   varchar(20) NOT NULL,
    `endtime`     varchar(20) NOT NULL,
    `merchantid`  bigint NOT NULL,
    `goods`       varchar(1024) NOT NULL,
    `content`     varchar(1024) NOT NULL,
    `status`      smallint NOT NULL,
    PRIMARY KEY(`id`),
    FOREIGN KEY (`merchantid`)
        REFERENCES merchant (`id`)
        ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=INNODB DEFAULT CHARSET=UTF8MB4;

/*==============================================================*/
/* Table: identify                                                                                                                     */
/*==============================================================*/
CREATE TABLE identify (
                          `phone` VARCHAR(20) NOT NULL,
                          `code` VARCHAR(10) NOT NULL,
                          `time` VARCHAR(20) NOT NULL,
                          PRIMARY KEY (`phone`)
)  ENGINE=INNODB DEFAULT CHARSET=UTF8MB4;
