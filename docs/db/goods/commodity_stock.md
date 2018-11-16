### 表格注解



### SQL语句

```sql
CREATE TABLE `commodity_stock` (
    `skid` int(11) NOT NULL AUTO_INCREMENT,
    `sid` int(11) NOT NULL COMMENT '商品尺码id',
    `type` varchar(2) NOT NULL COMMENT '变更类型',
    `num` int(11) NOT NULL COMMENT '变更数量',
    `remark` varchar(255) DEFAULT NULL COMMENT '备注',
    `operatorId` int(11) DEFAULT NULL COMMENT '操作员id',
    `createTime` datetime DEFAULT NULL COMMENT '创建时间',
    PRIMARY KEY (`skid`)
) ENGINE=InnoDB AUTO_INCREMENT=664 DEFAULT CHARSET=utf8mb4 COMMENT='商品库存表';
```
