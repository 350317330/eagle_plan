CREATE TABLE `tb_service_ensure`
(
    `id`                   bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
    `plant_subject`       int DEFAULT NULL COMMENT '种植主体',
    `expert`  int DEFAULT NULL COMMENT '农技专家',
    `org` int DEFAULT NULL COMMENT '服务组织',
    `agricultural_shop`         int DEFAULT NULL COMMENT '农资经营店',
    `area_code`            varchar(20)    DEFAULT NULL COMMENT '区域编码',
    `area_name` varchar(30) DEFAULT NULL COMMENT '区域名称',
    `year`                 int(11)        DEFAULT NULL COMMENT '年份',
    `del_flag`             char(1)        DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
    `create_by`            varchar(64)    DEFAULT '' COMMENT '创建者',
    `create_time`          datetime       DEFAULT NULL COMMENT '创建时间',
    `update_by`            varchar(64)    DEFAULT '' COMMENT '更新者',
    `update_time`          datetime       DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='服务保障';


create table tb_plot_scale
(
    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
    name varchar(100),
    id_card varchar(100),
    plot_area decimal(10,2),
    rice_area decimal(10,2),
    wheat_area decimal(10,2),
    amount decimal(10,2),
    town varchar(100),
    village varchar(100),
    phone varchar(50),
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='服务保障';


SELECT T1.name,
       T1.plot_area,
       (T1.amount + T2.amount) amount,
       T1.district,
       T1.town,
       T1.village
FROM (
         SELECT sum(plot_area)                  plot_area,
                sum(rice_area)                  rice_area,
                sum(wheat_area)                 wheat_price,
                sum(amount)                     amount,
                name,
                GROUP_CONCAT(distinct district) district,
                GROUP_CONCAT(distinct town)     town,
                GROUP_CONCAT(distinct village)  village
         FROM tb_plot_info
         GROUP BY name
     ) T1
         LEFT JOIN tb_plot_info1 T2 ON T1.name = T2.name



