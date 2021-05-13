create database IF NOT EXISTS evaluation_db;

use evaluation_db;

drop table if exists sys_user;

create table sys_user
(
    id          int unsigned AUTO_INCREMENT,
    name        varchar(10) NOT NULL,
    deleted     boolean     NOT NULL,
    creator     varchar(10) NOT NULL,
    updater     varchar(10) NOT NULL,
    create_time varchar(19) NOT NULL COMMENT '小项目，为了方便查数据库就知道什么时间，就不用 Timestamp 了',
    update_time varchar(19) NOT NULL,
    PRIMARY KEY (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

insert into sys_user(name, deleted, creator, updater, create_time, update_time)
values ('张三', 0, 'admin', 'admin', '2020-11-16:08:08', '2020-11-16 08:08:08');
insert into sys_user(name, deleted, creator, updater, create_time, update_time)
values ('李四', 0, 'admin', 'admin', '2020-11-16:08:08', '2020-11-16 08:08:08');
insert into sys_user(name, deleted, creator, updater, create_time, update_time)
values ('王五', 0, 'admin', 'admin', '2020-11-16:08:08', '2020-11-16 08:08:08');

drop table if exists access_record;

create table access_record
(
    id                int unsigned AUTO_INCREMENT,
    access_ip_address long,
    deleted           boolean     NOT NULL,
    creator           varchar(10) NOT NULL,
    updater           varchar(10) NOT NULL,
    create_time       varchar(19) NOT NULL COMMENT '小项目，为了方便查数据库就知道什么时间，就不用 Timestamp 了',
    update_time       varchar(19) NOT NULL,
    PRIMARY KEY (id)
);

drop table if exists evaluation;

create table evaluation
(
    id          int unsigned AUTO_INCREMENT,
    serial_num  varchar(16)  NOT NULL,
    level       int unsigned NOT Null,
    deleted     boolean      NOT NULL,
    creator     varchar(10)  NOT NULL,
    updater     varchar(10)  NOT NULL,
    create_time varchar(19)  NOT NULL COMMENT '小项目，为了方便查数据库就知道什么时间，就不用 Timestamp 了',
    update_time varchar(19)  NOT NULL,
    PRIMARY KEY (id)
);


select * from evaluation;