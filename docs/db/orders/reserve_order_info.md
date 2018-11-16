### 表格注解



### SQL语句

```sql
CREATE TABLE `reserve_order_info` (
    `roid` int(11) NOT NULL AUTO_INCREMENT COMMENT '预约订单id',
    `rno` varchar(32) DEFAULT NULL COMMENT '预约单编号',
    `uid` int(11) DEFAULT NULL COMMENT '用户id',
    `dcid` int(11) DEFAULT NULL COMMENT '柜子id',
    `priceSum` decimal(10,2) DEFAULT NULL COMMENT '订单总金额',
    `payPrice` decimal(10,2) DEFAULT NULL COMMENT '支付金额',
    `payStatus` varchar(255) DEFAULT NULL COMMENT '支付状态（暂不使用，预约也会生普通订单支付）',
    `status` varchar(1) DEFAULT NULL COMMENT '订单状态（1：正常  2：取消）',
    `payTime` datetime DEFAULT NULL COMMENT '支付时间',
    `reserveStartTime` datetime DEFAULT NULL COMMENT '预约开始时间',
    `reserveEndTime` datetime DEFAULT NULL COMMENT '预约结束时间',
    `createTime` datetime DEFAULT NULL COMMENT '创建时间',
    `updateTime` datetime DEFAULT NULL COMMENT '修改时间',
    PRIMARY KEY (`roid`)
) ENGINE=InnoDB AUTO_INCREMENT=63 DEFAULT CHARSET=utf8mb4 COMMENT='预约订单表';
```
