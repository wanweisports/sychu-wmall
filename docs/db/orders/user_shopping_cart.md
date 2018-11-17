### 表格注解



### SQL语句

```sql
CREATE TABLE `user_shopping_cart` (
    `scid` int(11) NOT NULL AUTO_INCREMENT,
    `uid` int(11) DEFAULT NULL COMMENT '用户id',
    `cid` int(11) DEFAULT NULL COMMENT '商品id',
    `coid` int(11) DEFAULT NULL COMMENT '商品颜色id',
    `sid` int(11) DEFAULT NULL COMMENT '商品尺码id',
    `count` int(11) DEFAULT NULL COMMENT '数量',
    `shoppingType` varchar(2) DEFAULT NULL COMMENT '1：购物车  2：预约购物车',
    `createTime` datetime DEFAULT NULL COMMENT '创建时间',
    `updateTime` datetime DEFAULT NULL COMMENT '修改时间',
    PRIMARY KEY (`scid`)
) ENGINE=InnoDB AUTO_INCREMENT=445 DEFAULT CHARSET=utf8mb4 COMMENT='用户购物车表';
```
