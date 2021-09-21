CREATE TABLE `tb_policy_support`
(
    `id`          bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
    `area_name`   varchar(30)    DEFAULT NULL COMMENT '区域',
    `type_name`   varchar(30)    DEFAULT NULL COMMENT '类型名称',
    `type`        varchar(30)    DEFAULT NULL COMMENT '类型',
    `amount`      decimal(18, 6) DEFAULT NULL COMMENT '金额',
    `goal_amount` decimal(18, 6) DEFAULT NULL COMMENT '目标金额',
    `year`        int(11)        DEFAULT NULL COMMENT '年份',
    `month`       int(11)        DEFAULT NULL COMMENT '月份',
    `unit`        varchar(10)    DEFAULT NULL COMMENT '单位',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='政策支持';






