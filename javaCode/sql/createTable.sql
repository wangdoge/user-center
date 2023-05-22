-- auto-generated definition
create table user
(
    id           bigint auto_increment
        primary key,
    username     varchar(256)                       null comment '用户昵称',
    avatarUrl    varchar(1024)                      null comment '头像',
    userAccount  varchar(1024)                      null comment '账号',
    gender       tinyint                            null comment '性别',
    userPassword varchar(512)                       null comment '密码',
    phone        varchar(128)                       null comment '电话',
    email        varchar(512)                       null,
    userStatus   int      default 0                 null comment '状态(0=正常)',
    createTime   datetime default CURRENT_TIMESTAMP null comment '创建时间',
    updateTime   datetime default CURRENT_TIMESTAMP null comment '更新时间',
    isDelete     tinyint  default 0                 null comment '是否删除',
    userRole     int      default 0                 null comment '用户角色 0-普通 1-管理员',
    planetCode   varchar(512)                       null comment '编号'
);

