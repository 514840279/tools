CREATE TABLE  `jijin`  (
  `uuid` varchar(36)  NOT NULL COMMENT 'uuid',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '录入时间',
  `update_time` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '最后一次更新时间',
  `delete_flag` int(0) NOT NULL COMMENT '隐藏',
  `discription` varchar(255)  NULL DEFAULT NULL,
  `create_user` varchar(255)  NULL DEFAULT NULL,
  `update_user` varchar(255)  NULL DEFAULT NULL,
  `sort` int NULL DEFAULT NULL,
  `name` varchar(255) NULL COMMENT '基金名称',
  `code` varchar(255) NULL COMMENT '代码',
  `money` double NULL COMMENT '标准值',
  PRIMARY KEY (`uuid`)
);


CREATE TABLE  `flow`  (
  `uuid` varchar(36)  NOT NULL COMMENT 'uuid',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '录入时间',
  `update_time` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '最后一次更新时间',
  `delete_flag` int(0) NOT NULL COMMENT '隐藏',
  `discription` varchar(255)  NULL DEFAULT NULL,
  `create_user` varchar(255)  NULL DEFAULT NULL,
  `update_user` varchar(255)  NULL DEFAULT NULL,
  `sort` int NULL DEFAULT NULL,
  `ji_uuid` varchar(36) NULL COMMENT '基金id',
  `zhifu_money` double NULL COMMENT '花费金额',
  `mairu_fene` double NULL COMMENT '买入份额',
    `maichu_fene` double NULL COMMENT '卖出份额',
  `shuhui_money` double NULL COMMENT '赎回金额',
    `danweijingzhi` double NULL COMMENT '单位净值',
        `shouxufei` double NULL COMMENT '手续费',
  PRIMARY KEY (`uuid`)
);



select j.uuid,j.`name`,j.money,j.`code`,         SUM(IFNULL(f.zhifu_money,0))-sum(IFNULL(f.shuhui_money,0)) as countMoney         , SUM(IFNULL(f.mairu_fene,0))-sum(IFNULL(f.maichu_fene,0)) as countFene         ,round(abs(-SUM(IFNULL(f.zhifu_money,0))+sum(IFNULL(f.shuhui_money,0)))/(SUM(IFNULL(f.mairu_fene,0))-sum(IFNULL(f.maichu_fene,0))),4) as pinjunjingzhi         ,round(sum(IFNULL(f.shuhui_money,0))-SUM(IFNULL(f.zhifu_money,0))  ,2) as yingshou         , CASE  when sum(IFNULL(f.shuhui_money,0))>SUM(IFNULL(f.zhifu_money,0)) THEN  round(sum(IFNULL(f.shuhui_money,0))-SUM(IFNULL(f.zhifu_money,0))  ,2) else round(sum(IFNULL(f.shuhui_money,0))+j.money-SUM(IFNULL(f.zhifu_money,0))  ,2) end as lirun         from jijin j         left join flow f on j.uuid = f.ji_uuid and f.delete_flag =0 and f.create_time< now()         where j.delete_flag=0         group by j.uuid,j.`name`,j.money,j.`code`<EOL><EOL>### Cause: org.h2.jdbc.JdbcSQLSyntaxErrorException: Table "JIJIN" not found; SQL statement:<EOL>select j.uuid,j.`name`,j.money,j.`code`,<EOL>        SUM(IFNULL(f.zhifu_money,0))-sum(IFNULL(f.shuhui_money,0)) as countMoney<EOL>        , SUM(IFNULL(f.mairu_fene,0))-sum(IFNULL(f.maichu_fene,0)) as countFene<EOL>        ,round(abs(-SUM(IFNULL(f.zhifu_money,0))+sum(IFNULL(f.shuhui_money,0)))/(SUM(IFNULL(f.mairu_fene,0))-sum(IFNULL(f.maichu_fene,0))),4) as pinjunjingzhi<EOL>        ,round(sum(IFNULL(f.shuhui_money,0))-SUM(IFNULL(f.zhifu_money,0))  ,2) as yingshou<EOL>        , CASE  when sum(IFNULL(f.shuhui_money,0))>SUM(IFNULL(f.zhifu_money,0)) THEN  round(sum(IFNULL(f.shuhui_money,0))-SUM(IFNULL(f.zhifu_money,0))  ,2) else round(sum(IFNULL(f.shuhui_money,0))+j.money-SUM(IFNULL(f.zhifu_money,0))  ,2) end as lirun<EOL>        from jijin j<EOL>        left join flow f on j.uuid = f.ji_uuid and f.delete_flag =0 and f.create_time< now()<EOL>        where j.delete_flag=0<EOL>        group by j.uuid,j.`name`,j.money,j.`code` [42102-200]<EOL>; bad SQL grammar []; nested exception is org.h2.jdbc.JdbcSQLSyntaxErrorException: Table "JIJIN" not found; SQL statement:<EOL>select j.uuid,j.`name`,j.money,j.`code`,<EOL>        SUM(IFNULL(f.zhifu_money,0))-sum(IFNULL(f.shuhui_money,0)) as countMoney<EOL>        , SUM(IFNULL(f.mairu_fene,0))-sum(IFNULL(f.maichu_fene,0)) as countFene<EOL>        ,round(abs(-SUM(IFNULL(f.zhifu_money,0))+sum(IFNULL(f.shuhui_money,0)))/(SUM(IFNULL(f.mairu_fene,0))-sum(IFNULL(f.maichu_fene,0))),4) as pinjunjingzhi<EOL>        ,round(sum(IFNULL(f.shuhui_money,0))-SUM(IFNULL(f.zhifu_money,0))  ,2) as yingshou<EOL>        , CASE  when sum(IFNULL(f.shuhui_money,0))>SUM(IFNULL(f.zhifu_money,0)) THEN  round(sum(IFNULL(f.shuhui_money,0))-SUM(IFNULL(f.zhifu_money,0))  ,2) else round(sum(IFNULL(f.shuhui_money,0))+j.money-SUM(IFNULL(f.zhifu_money,0))  ,2) end as lirun<EOL>        from jijin j<EOL>        left join flow f on j.uuid = f.ji_uuid and f.delete_flag =0 and f.create_time< now()<EOL>        where j.delete_flag=0<EOL>        group by j.uuid,j.`name`,j.money,j.`code`