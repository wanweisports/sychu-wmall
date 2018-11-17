### 表格注解



### SQL语句

```sql
CREATE TABLE `sys_commodity_distribution` (
    `dbid` int(11) NOT NULL AUTO_INCREMENT COMMENT '配送id',
    `cid` int(11) DEFAULT NULL COMMENT '商品id',
    `sid` int(11) DEFAULT NULL COMMENT '商品尺码',
    `rfidEpc` varchar(35) DEFAULT NULL COMMENT '电子标签EPC唯一码（24+11）',
    `dcid` int(11) DEFAULT NULL COMMENT '柜子id',
    `dbTime` date DEFAULT NULL COMMENT '配送时间',
    `roid` int(11) DEFAULT NULL COMMENT '预约单id',
    `status` varchar(1) DEFAULT NULL COMMENT '1：正常  2：已被买走',
    `createTime` datetime DEFAULT NULL COMMENT '创建时间',
    `updateTime` datetime DEFAULT NULL COMMENT '修改时间',
    PRIMARY KEY (`dbid`)
) ENGINE=InnoDB AUTO_INCREMENT=62 DEFAULT CHARSET=utf8mb4 COMMENT='配送表';
```
