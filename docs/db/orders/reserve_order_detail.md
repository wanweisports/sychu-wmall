### 表格注解



### SQL语句

```sql
CREATE TABLE `reserve_order_detail` (
    `rdid` int(11) NOT NULL AUTO_INCREMENT COMMENT '预约订单详情id',
    `roid` int(11) DEFAULT NULL COMMENT '预约订单id',
    `cid` int(11) DEFAULT NULL COMMENT '商品id',
    `sid` int(11) DEFAULT NULL COMMENT '尺码id',
    `resItemName` varchar(32) DEFAULT NULL COMMENT '商品名称',
    `resItemColor` varchar(32) DEFAULT NULL COMMENT '商品颜色',
    `resItemImg` varchar(1000) DEFAULT NULL COMMENT '商品图片地址',
    `resItemSize` varchar(10) DEFAULT NULL COMMENT '商品尺码',
    `resItemPrice` decimal(10,2) DEFAULT NULL COMMENT '商品单价',
    `resItemCount` int(11) DEFAULT NULL COMMENT '商品数量',
    `resItemPriceSum` decimal(10,2) DEFAULT NULL COMMENT '商品总价（单价*数量）',
    `createTime` datetime DEFAULT NULL COMMENT '创建时间',
    `updateTime` datetime DEFAULT NULL COMMENT '修改时间',
    PRIMARY KEY (`rdid`)
) ENGINE=InnoDB AUTO_INCREMENT=65 DEFAULT CHARSET=utf8mb4 COMMENT='预约订单详情表';
```
