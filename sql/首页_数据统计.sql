CREATE TABLE `tb_data_total`
(
    `id`          bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
    `plant_area`  decimal(10, 2) DEFAULT NULL COMMENT '区域',
    `grain_total` decimal(10, 2) DEFAULT NULL COMMENT '类型名称',
    `ration_rate` decimal(10, 2) DEFAULT NULL COMMENT '类型',
    `area_rate`   decimal(10, 2) DEFAULT NULL COMMENT '金额',
    `grain_rate`  decimal(10, 2) DEFAULT NULL COMMENT '目标金额',
    `area_code`   varchar(30)    DEFAULT NULL COMMENT '区域',
    `year`        int(11)        DEFAULT NULL COMMENT '年份',
    `del_flag`    char(1)        DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
    `create_by`   varchar(64)    DEFAULT '' COMMENT '创建者',
    `create_time` datetime       DEFAULT NULL COMMENT '创建时间',
    `update_by`   varchar(64)    DEFAULT '' COMMENT '更新者',
    `update_time` datetime       DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='政策支持';



























