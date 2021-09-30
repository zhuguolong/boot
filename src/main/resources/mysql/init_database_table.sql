-- root/123456
-- 创建数据库
create database boot DEFAULT CHARSET utf8 COLLATE utf8_general_ci;

use boot;

drop table if exists t_user;
create table t_user
(
    USER_ID     BIGINT(20)   NOT NULL AUTO_INCREMENT COMMENT '用户ID',
    USER_NAME   VARCHAR(120) NOT NULL COMMENT '用户名称',
    PHONE       VARCHAR(11)  NOT NULL COMMENT '手机号码',
    CREATE_TIME TIMESTAMP    NOT NULL COMMENT '创建时间',
    PRIMARY KEY (USER_ID),
    KEY IDX_PHONE (PHONE)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1010 comment '用户基础表';