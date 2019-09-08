CREATE TABLE room
(
    `id`        bigint AUTO_INCREMENT,
    `username`  varchar(50) ,
		`type`      VARCHAR(1),
    `room`      varchar(6) NOT NULL,
    `parking`   varchar(4) NOT NULL DEFAULT 'A0B0',
    `pmoney`    DECIMAL(8,2),
    `mmoney`    DECIMAL(8,2),
     `community_id` bigint,
    PRIMARY KEY(`id`),
    FOREIGN KEY (`username`)
        REFERENCES user (`username`)
        ON DELETE CASCADE ON UPDATE CASCADE,
 FOREIGN KEY (`community_id`)
        REFERENCES community (`id`)
        ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=INNODB DEFAULT CHARSET=UTF8MB4;

CREATE TABLE roomspace
(
    `id`        bigint AUTO_INCREMENT,
    `type`  varchar(1) NOT NULL,
`maxnum`      int NOT NULL,
`maxfloor`      int NOT NULL,
`maxroom`      int NOT NULL,    
`space`      int NOT NULL,
     `community_id` bigint,
PRIMARY KEY(`id`),
 FOREIGN KEY (`community_id`)
        REFERENCES community (`id`)
        ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=INNODB DEFAULT CHARSET=UTF8MB4;

CREATE TABLE parkplan
(
    `id`        bigint AUTO_INCREMENT,
    `type`  varchar(1) NOT NULL,
    `monthpay`      DECIMAL(8,2) NOT NULL,
     `community_id` bigint,
PRIMARY KEY(`id`),
 FOREIGN KEY (`community_id`)
        REFERENCES community (`id`)
        ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=INNODB DEFAULT CHARSET=UTF8MB4;

