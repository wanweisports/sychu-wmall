### 表格注解



### SQL语句

```sql
CREATE TABLE `user_order_info` (
    `oid` int(11) NOT NULL AUTO_INCREMENT COMMENT '订单id',
    `ono` varchar(32) DEFAULT NULL COMMENT '订单编号',
    `uid` int(11) DEFAULT NULL COMMENT '用户id',
    `rid` int(11) DEFAULT NULL COMMENT '预约订单id（非预约null）',
    `orderType` varchar(1) DEFAULT NULL COMMENT '订单类型',
    `priceSum` decimal(10,2) DEFAULT NULL COMMENT '订单总金额',
    `payPrice` decimal(10,2) DEFAULT NULL COMMENT '支付金额',
    `payStatus` varchar(1) DEFAULT NULL COMMENT '支付状态（查看字典表）',
    `status` varchar(1) DEFAULT NULL COMMENT '订单状态（查看字典表）',
    `payType` varchar(1) DEFAULT '1' COMMENT '1：微信支付  2：余额支付',
    `payTime` datetime DEFAULT NULL COMMENT '支付时间',
    `expressName` varchar(32) DEFAULT NULL COMMENT '收货人姓名',
    `expressMobile` varchar(32) DEFAULT NULL COMMENT '收货人电话',
    `expressAreaId` varchar(32) DEFAULT NULL COMMENT '收货区域id',
    `expressAddress` varchar(255) DEFAULT NULL COMMENT '收货人详细地址',
    `sendTime` datetime DEFAULT NULL COMMENT '发货时间',
    `freight` decimal(10,2) DEFAULT NULL COMMENT '运费',
    `cpid` int(11) DEFAULT NULL COMMENT '优惠券id',
    `ycoid` int(11) DEFAULT NULL COMMENT '使用的衣橱币',
    `createTime` datetime DEFAULT NULL COMMENT '创建时间',
    `updateTime` datetime DEFAULT NULL COMMENT '修改时间',
    PRIMARY KEY (`oid`)
) ENGINE=InnoDB AUTO_INCREMENT=79 DEFAULT CHARSET=utf8mb4 COMMENT='订单表';
```
