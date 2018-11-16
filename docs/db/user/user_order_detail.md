### 表格注解



### SQL语句

```sql
CREATE TABLE `user_order_detail` (
    `odid` int(11) NOT NULL AUTO_INCREMENT COMMENT '订单详情id',
    `oid` int(11) DEFAULT NULL COMMENT '订单id',
    `cid` int(11) DEFAULT NULL COMMENT '商品id（或dictId）',
    `sid` int(11) DEFAULT NULL COMMENT '尺码id',
    `itemName` varchar(32) DEFAULT NULL COMMENT '商品名称',
    `itemColor` varchar(32) DEFAULT NULL COMMENT '商品颜色',
    `itemSize` varchar(10) DEFAULT NULL COMMENT '商品尺码',
    `itemImg` varchar(1000) DEFAULT NULL COMMENT '商品图片地址',
    `itemPrice` decimal(10,2) DEFAULT NULL COMMENT '商品单价',
    `itemCount` int(11) DEFAULT NULL COMMENT '商品数量',
    `itemPriceSum` decimal(10,2) DEFAULT NULL COMMENT '商品总价（单价*数量）',
    `itemType` varchar(1) DEFAULT NULL COMMENT '商品类型（1：普通  2：赠送）',
    `dbid` int(11) DEFAULT NULL COMMENT '配送id',
    `createTime` datetime DEFAULT NULL COMMENT '创建时间',
    `updateTime` datetime DEFAULT NULL COMMENT '修改时间',
    PRIMARY KEY (`odid`)
) ENGINE=InnoDB AUTO_INCREMENT=71 DEFAULT CHARSET=utf8mb4 COMMENT='订单详情表';
```
