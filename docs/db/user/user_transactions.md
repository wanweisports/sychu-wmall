### 表格注解



### SQL语句

```sql
CREATE TABLE `user_transactions` (
    `tid` int(11) NOT NULL AUTO_INCREMENT COMMENT '交易流水id',
    `uid` int(1) DEFAULT NULL COMMENT '用户id',
    `serviceType` varchar(2) DEFAULT NULL COMMENT '交易业务类型（1：购物 2：充值  3：退款）',
    `serviceId` int(11) DEFAULT NULL COMMENT '交易业务id',
    `type` varchar(1) DEFAULT NULL COMMENT '类型（1：微信支付  2：余额支付  3：薏米）',
    `price` decimal(10,2) DEFAULT NULL COMMENT '交易金额',
    `remark` varchar(255) DEFAULT NULL COMMENT '备注',
    `createTime` datetime DEFAULT NULL COMMENT '创建时间',
    `updateTime` datetime DEFAULT NULL COMMENT '修改时间',
    PRIMARY KEY (`tid`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COMMENT='交易流水表';
```
