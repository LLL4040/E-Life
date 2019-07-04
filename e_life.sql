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
  `time`        varchar(20) NOT NULL,
  `content`     varchar(1024) NOT NULL,
  `managername` varchar(50) NOT NULL,
  `status`      smallint NOT NULL,
  PRIMARY KEY(`id`),
  FOREIGN KEY (`managername`)
        REFERENCES manager (`username`)
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
  PRIMARY KEY(`id`),
  FOREIGN KEY (`managername`)
        REFERENCES manager (`username`)
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
  PRIMARY KEY(`id`),
  FOREIGN KEY (`managername`)
        REFERENCES manager (`username`)
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
