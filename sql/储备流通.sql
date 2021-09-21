CREATE TABLE tb_reserve
(
    `id`          bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
    reserve       decimal(10, 2) COMMENT '总储备量',
    price         decimal(10, 2) COMMENT '稻谷市场价格',
    consume_ratio decimal(10, 2) COMMENT '库存消费比',
    net_work      int COMMENT '应急供应网点',
    `area_code`   varchar(20) DEFAULT NULL COMMENT '区域编码',
    `year`        int(11)     DEFAULT NULL COMMENT '年份',
    `del_flag`    char(1)     DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
    `create_by`   varchar(64) DEFAULT '' COMMENT '创建者',
    `create_time` datetime    DEFAULT NULL COMMENT '创建时间',
    `update_by`   varchar(64) DEFAULT '' COMMENT '更新者',
    `update_time` datetime    DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='首页-储备流通';

CREATE TABLE `cb_agriculture_reserve`
(
    `id`                   bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
    `emergency_seed`       decimal(18, 6) DEFAULT NULL COMMENT '应急储备种子数量',
    `emergency_pesticide`  decimal(18, 6) DEFAULT NULL COMMENT '应急储备农药数量',
    `emergency_fertilizer` decimal(18, 6) DEFAULT NULL COMMENT '应急储备化肥数量',
    `society_seed`         decimal(18, 6) DEFAULT NULL COMMENT '社会储备种子数量',
    `society_pesticide`    decimal(18, 6) DEFAULT NULL COMMENT '社会储备农药数量',
    `society_fertilizer`   decimal(18, 6) DEFAULT NULL COMMENT '社会储备化肥数量',
    `area_code`            varchar(20)    DEFAULT NULL COMMENT '区域编码',
    `year`                 int(11)        DEFAULT NULL COMMENT '年份',
    `del_flag`             char(1)        DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
    `create_by`            varchar(64)    DEFAULT '' COMMENT '创建者',
    `create_time`          datetime       DEFAULT NULL COMMENT '创建时间',
    `update_by`            varchar(64)    DEFAULT '' COMMENT '更新者',
    `update_time`          datetime       DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='农资储备';


CREATE TABLE `cb_grain_price`
(
    `id`          bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
    `flour_price` decimal(18, 6) DEFAULT NULL COMMENT '面粉价格',
    `wheat_price` decimal(18, 6) DEFAULT NULL COMMENT '小麦价格',
    `rice_price`  decimal(18, 6) DEFAULT NULL COMMENT '大米价格',
    `area_code`   varchar(20)    DEFAULT NULL COMMENT '区域编码',
    `year`        int(11)        DEFAULT NULL COMMENT '年份',
    `month`       int(11)        DEFAULT NULL COMMENT '月份',
    `del_flag`    char(1)        DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
    `create_by`   varchar(64)    DEFAULT '' COMMENT '创建者',
    `create_time` datetime       DEFAULT NULL COMMENT '创建时间',
    `update_by`   varchar(64)    DEFAULT '' COMMENT '更新者',
    `update_time` datetime       DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='粮食价格';

CREATE TABLE `cb_grain_reserve`
(
    `id`           bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
    `local_extent` decimal(18, 6) DEFAULT NULL COMMENT '地方储备粮规模(万吨)',
    `capacity`     decimal(18, 6) DEFAULT NULL COMMENT '粮食储备库容量(万吨)',
    `num`          int(11)        DEFAULT NULL COMMENT '粮食储备库数量',
    `order_area`   decimal(18, 6) DEFAULT NULL COMMENT '订单粮面积',
    `order_output` decimal(18, 6) DEFAULT NULL COMMENT '订单粮产量',
    `area_code`    varchar(20)    DEFAULT NULL COMMENT '区域编码',
    `year`         int(11)        DEFAULT NULL COMMENT '年份',
    `del_flag`     char(1)        DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
    `create_by`    varchar(64)    DEFAULT '' COMMENT '创建者',
    `create_time`  datetime       DEFAULT NULL COMMENT '创建时间',
    `update_by`    varchar(64)    DEFAULT '' COMMENT '更新者',
    `update_time`  datetime       DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='粮食储备';

CREATE TABLE `cb_guarantee_ability`
(
    `id`                 bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
    `grain_source`       decimal(18, 6) DEFAULT NULL COMMENT '应急粮源（万吨）',
    `produce_base`       int(11)        DEFAULT NULL COMMENT '省外粮食生产基地（个）',
    `process_enterprise` int(11)        DEFAULT NULL COMMENT '应急加工企业',
    `supply_network`     int(11)        DEFAULT NULL COMMENT '应急供应网点',
    `transport_company`  int(11)        DEFAULT NULL COMMENT '应急运输企业',
    `area_code`          varchar(20)    DEFAULT NULL COMMENT '区域编码',
    `year`               int(11)        DEFAULT NULL COMMENT '年份',
    `del_flag`           char(1)        DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
    `create_by`          varchar(64)    DEFAULT '' COMMENT '创建者',
    `create_time`        datetime       DEFAULT NULL COMMENT '创建时间',
    `update_by`          varchar(64)    DEFAULT '' COMMENT '更新者',
    `update_time`        datetime       DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='应急保障';


CREATE TABLE `cb_market_monitor`
(
    `id`          bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
    `enterprise`  int(11)        DEFAULT NULL COMMENT '涉粮企业',
    `industry`    decimal(18, 6) DEFAULT NULL COMMENT '粮食工业总产值',
    `brand`       int(11)        DEFAULT NULL COMMENT '区域公共品牌',
    `inspection`  int(11)        DEFAULT NULL COMMENT '抽检批次',
    `pass_rate`   decimal(18, 6) DEFAULT NULL COMMENT '抽检合格率',
    `area_code`   varchar(20)    DEFAULT NULL COMMENT '区域编码',
    `year`        int(11)        DEFAULT NULL COMMENT '年份',
    `del_flag`    char(1)        DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
    `create_by`   varchar(64)    DEFAULT '' COMMENT '创建者',
    `create_time` datetime       DEFAULT NULL COMMENT '创建时间',
    `update_by`   varchar(64)    DEFAULT '' COMMENT '更新者',
    `update_time` datetime       DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='市场监测';


CREATE TABLE `cb_reserve_center`
(
    `id`          bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
    `reserve`     decimal(18, 6) DEFAULT NULL COMMENT '总储备量',
    `output`      decimal(18, 6) DEFAULT NULL COMMENT '订单粮产量',
    `area`        decimal(18, 6) DEFAULT NULL COMMENT '订单粮面积',
    `area_code`   varchar(20)    DEFAULT NULL COMMENT '区域编码',
    `year`        int(11)        DEFAULT NULL COMMENT '年份',
    `del_flag`    char(1)        DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
    `create_by`   varchar(64)    DEFAULT '' COMMENT '创建者',
    `create_time` datetime       DEFAULT NULL COMMENT '创建时间',
    `update_by`   varchar(64)    DEFAULT '' COMMENT '更新者',
    `update_time` datetime       DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='储备中心';









