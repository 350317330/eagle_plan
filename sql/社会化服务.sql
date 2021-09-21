CREATE TABLE `sh_base`
(
    `id`                   bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
    `org` int DEFAULT NULL COMMENT '服务组织',
    `robot`         int DEFAULT NULL COMMENT '机手',
    `area_code`            varchar(20)    DEFAULT NULL COMMENT '区域编码',
    `year`                 int(11)        DEFAULT NULL COMMENT '年份',
    `del_flag`             char(1)        DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
    `create_by`            varchar(64)    DEFAULT '' COMMENT '创建者',
    `create_time`          datetime       DEFAULT NULL COMMENT '创建时间',
    `update_by`            varchar(64)    DEFAULT '' COMMENT '更新者',
    `update_time`          datetime       DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='社会化基本信息';


CREATE TABLE `sh_service_situation`
(
    `id`           bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
    `machine_type` char(1)        DEFAULT NULL COMMENT '机器类型',
    `local_work`   decimal(18, 6) DEFAULT NULL COMMENT '作业面积(本地)',
    `outside_work` decimal(18, 6) DEFAULT NULL COMMENT '作业面积(外来)',
    `machine_total` decimal(18, 6) DEFAULT NULL COMMENT '机器总量',
    `year`         int(11)        DEFAULT NULL COMMENT '年份',
    `del_flag`     char(1)        DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
    `create_by`    varchar(64)    DEFAULT '' COMMENT '创建者',
    `create_time`  datetime       DEFAULT NULL COMMENT '创建时间',
    `update_by`    varchar(64)    DEFAULT '' COMMENT '更新者',
    `update_time`  datetime       DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='社会化服务情况';


CREATE TABLE `sh_service_total`
(
    `id`           bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
    `machine_rate` decimal(10, 2)        DEFAULT NULL COMMENT '机械化率',
    `service_rate`   decimal(10, 2) DEFAULT NULL COMMENT '社会化服务覆盖率',
    `work` decimal(18, 6) DEFAULT NULL COMMENT '作业面积',
    `subsidy` decimal(18, 6) DEFAULT NULL COMMENT '农机购置补贴',
    `year`         int(11)        DEFAULT NULL COMMENT '年份',
    `del_flag`     char(1)        DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
    `create_by`    varchar(64)    DEFAULT '' COMMENT '创建者',
    `create_time`  datetime       DEFAULT NULL COMMENT '创建时间',
    `update_by`    varchar(64)    DEFAULT '' COMMENT '更新者',
    `update_time`  datetime       DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='社会化服务统计';




-- Agricultural Machinery Socialization Service Organization
CREATE TABLE sh_service_group(
    `id`          bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
    name          varchar(100) COMMENT '组织名称',
    contact       varchar(50) COMMENT '联系人',
    phone         varchar(20) COMMENT '联系电话',
    address       varchar(100) COMMENT '详情',
    lng           varchar(20) COMMENT '经度',
    lat           varchar(20) COMMENT '纬度',
    is_local      char(1) DEFAULT '0' COMMENT '本地标识:0代表本地 1代表外地',
    remark        text COMMENT '简介',
    work          decimal(18,6) COMMENT '最新一年作业面积',
    score         int COMMENT '1代表1颗星,以此类推,暂不考虑半颗星的情况',
    year          int COMMENT '年份',
    `del_flag`    char(1)     DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
    `create_by`   varchar(64) DEFAULT '' COMMENT '创建者',
    `create_time` datetime    DEFAULT NULL COMMENT '创建时间',
    `update_by`   varchar(64) DEFAULT '' COMMENT '更新者',
    `update_time` datetime    DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='服务组织';



CREATE TABLE sh_service_scope(
  `id`          bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
   service_id       bigint(20) COMMENT 'sh_service_group中的id',
   service_type varchar(20) COMMENT '服务种类',
   machine_type varchar(20) COMMENT '机具类型',
   brand varchar(50) COMMENT '品牌型号',
   daily varchar(50) COMMENT '日服务能力',
   num int COMMENT '数量 (台)',
   charges varchar(50) COMMENT '收费标准',
   year int COMMENT '年份',
   area decimal(18, 6) COMMENT '已服务面积',
   `del_flag`    char(1)     DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `create_by`   varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime    DEFAULT NULL COMMENT '创建时间',
  `update_by`   varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime    DEFAULT NULL COMMENT '更新时间',
   PRIMARY KEY (`id`)
)ENGINE = InnoDB
 DEFAULT CHARSET = utf8mb4 COMMENT ='服务范围';



CREATE TABLE sh_service_comment(
    `id`          bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
    service_id       bigint(20) COMMENT 'sh_service_group中的id',
    name    varchar(20) COMMENT '评论人姓名',
    area decimal(18,6) COMMENT '田块面积',
    lng           varchar(20) COMMENT '经度',
    lat           varchar(20) COMMENT '纬度',
    `del_flag`    char(1)     DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
    `create_by`   varchar(64) DEFAULT '' COMMENT '创建者',
    `create_time` datetime    DEFAULT NULL COMMENT '创建时间',
    `update_by`   varchar(64) DEFAULT '' COMMENT '更新者',
    `update_time` datetime    DEFAULT NULL COMMENT '更新时间',
     PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='服务评论';

CREATE TABLE `sh_service_object`
(
    `id`          bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
    `service_id`  bigint(20)  DEFAULT NULL COMMENT '关联外键',
    `service_name`  bigint(20)  DEFAULT NULL COMMENT '服务名称',
    `plot`        geometry    DEFAULT NULL COMMENT '地块信息',
    `plot_name`   varchar(20) DEFAULT NULL COMMENT '地块主人名字',
    `plot_area`   varchar(20) COMMENT '地块面积',
     district       varchar(100) COMMENT '区',
     town varchar(20) COMMENT '镇',
     village varchar(20) COMMENT '村',
     address varchar(200),
     service_type varchar(20) COMMENT '服务种类',
     machine_type varchar(20) COMMENT '机具类型',
     crop_type  char(1) COMMENT '作物各类',
     itinerary_url varchar(200) COMMENT '行程码URL',
     health_url varchar(200) COMMENT '健康码URL',
     robot_name varchar(20) COMMENT '机手姓名',
     is_comment char(1) COMMENT '是否评论',
     content text COMMENT '评论内容',
     score  int DEFAULT '0' COMMENT '1代表1颗星,以此类推,暂不考虑半颗星的情况',
    `del_flag`    char(1)     DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
    `create_by`   varchar(64) DEFAULT '' COMMENT '创建者',
    `create_time` datetime    DEFAULT NULL COMMENT '创建时间',
    `update_by`   varchar(64) DEFAULT '' COMMENT '更新者',
    `update_time` datetime    DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='服务对象';

CREATE TABLE `tb_plot`(
     `id`          bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
     name          varchar(50) COMMENT '农户姓名',
     phone          varchar(20) COMMENT '手机号码',
     id_card          varchar(50) COMMENT '身份证',
     total            varchar(50) COMMENT '地块总数',
     `area`   varchar(20) COMMENT '种植面积',
     amount decimal(18,6) COMMENT '补贴金额',
     district       varchar(100) COMMENT '区',
     town varchar(20) COMMENT '镇',
     village varchar(20) COMMENT '村',
     `del_flag`    char(1)     DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
     `create_by`   varchar(64) DEFAULT '' COMMENT '创建者',
     `create_time` datetime    DEFAULT NULL COMMENT '创建时间',
     `update_by`   varchar(64) DEFAULT '' COMMENT '更新者',
     `update_time` datetime    DEFAULT NULL COMMENT '更新时间',
     PRIMARY KEY (`id`)
)ENGINE = InnoDB
 DEFAULT CHARSET = utf8mb4 COMMENT ='种植';

CREATE TABLE `tb_insect`
(
    `id`      bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
    device_id int COMMENT '编号',
    type      int COMMENT '类型',
    name      varchar(20) COMMENT '名字',
    num       int COMMENT '数量',
    day       varchar(8) COMMENT '日期yyyyMMdd格式'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='害虫';


CREATE TABLE `tb_area`
(
    `id`      bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
    area_code int COMMENT '区域编码',
    area_name      int COMMENT '类型',
    level    int COMMENT '层级',
    p_id      varchar(20) COMMENT '父类编号',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='区域表';


0001	拖拉机	1	70	40	350	2021
0001	插秧机	2	60	120	1132.47	2021
0001	无人机	10	2500	8	24000	2021

1	拖拉机
2	插秧机
3	直播机
4	无人机
5	收割机
6	烘干机
7	育秧服务
8	配套农机具

0006	拖拉机	2	50	90	500	2021
0006	插秧机	2	60	50	600	2021
0006	直播机	1
0006	无人机	1	200	6	1500

INSERT INTO `sh_service_scope`( `service_id`, `service_type`, `num`,`daily`,  `charges`,  `area`,`year`)VALUES (6,'1', 2, 50, 90, 500, 2021);
INSERT INTO `sh_service_scope`( `service_id`, `service_type`, `num`,`daily`,  `charges`,  `area`,`year`)VALUES (6,'2', 2, 60, 50, 600, 2021);
INSERT INTO `sh_service_scope`( `service_id`, `service_type`, `num`,`daily`,  `charges`,  `area`,`year`)VALUES (6,'3', 1, 0, 0, 0, 2021);
INSERT INTO `sh_service_scope`( `service_id`, `service_type`, `num`,`daily`,  `charges`,  `area`,`year`)VALUES (6,'4', 1, 200, 6, 1500, 2021);

365	服务组织数	南湖区	2021	33.000000	个
366	服务组织数	秀洲区	2021	115.000000	个
367	服务组织数	嘉善县	2021	30.000000	个
368	服务组织数	海盐县	2021	43.000000	个
369	服务组织数	平湖市	2021	58.000000	个
370	服务组织数	海宁市	2021	22.000000	个
371	服务组织数	桐乡市	2021	33.000000	个

INSERT INTO `grain-brain`.`tb_service_ensure`(`plant_subject`, `expert`, `org`, `agricultural_shop`, `area_code`, `year`) VALUES ( 2465, 6, 33, 81, '330402', 2021);
INSERT INTO `grain-brain`.`tb_service_ensure`(`plant_subject`, `expert`, `org`, `agricultural_shop`, `area_code`, `year`) VALUES ( 2465, 11, 115, 81, '330411', 2021);
INSERT INTO `grain-brain`.`tb_service_ensure`(`plant_subject`, `expert`, `org`, `agricultural_shop`, `area_code`, `year`) VALUES ( 2465, 11, 30, 81, '330421', 2021);
INSERT INTO `grain-brain`.`tb_service_ensure`(`plant_subject`, `expert`, `org`, `agricultural_shop`, `area_code`, `year`) VALUES ( 2465, 10, 43, 81, '330424', 2021);
INSERT INTO `grain-brain`.`tb_service_ensure`(`plant_subject`, `expert`, `org`, `agricultural_shop`, `area_code`, `year`) VALUES ( 2465, 9, 58, 81, '330482', 2021);
INSERT INTO `grain-brain`.`tb_service_ensure`(`plant_subject`, `expert`, `org`, `agricultural_shop`, `area_code`, `year`) VALUES ( 2465, 14, 22, 81, '330481', 2021);
INSERT INTO `grain-brain`.`tb_service_ensure`(`plant_subject`, `expert`, `org`, `agricultural_shop`, `area_code`, `year`) VALUES ( 2466, 17, 33, 80, '330483', 2021);












