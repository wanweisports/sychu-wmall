### 表格注解



### SQL语句

```sql
CREATE TABLE `user_express_info` (
    `eid` int(11) NOT NULL AUTO_INCREMENT COMMENT '快递id',
    `id` int(11) DEFAULT NULL COMMENT '用户id',
    `name` varchar(32) DEFAULT NULL COMMENT '收货人姓名',
    `mobile` varchar(32) DEFAULT NULL COMMENT '收货人电话',
    `areaId` varchar(32) DEFAULT NULL COMMENT '区域id',
    `address` varchar(255) DEFAULT NULL COMMENT '详细地址',
    `defaultFlag` varchar(1) DEFAULT NULL COMMENT '默认收货地址（1：是  2：否）',
    `createTime` varchar(20) DEFAULT NULL COMMENT '创建时间',
    `updateTime` varchar(20) DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`eid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户快递地址表';
```
