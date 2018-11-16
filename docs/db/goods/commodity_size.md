### 表格注解



### SQL语句

```sql
CREATE TABLE `commodity_size` (
    `sid` int(11) NOT NULL AUTO_INCREMENT COMMENT '商品尺码id',
    `coid` int(11) DEFAULT NULL COMMENT '商品颜色id',
    `cid` int(11) DEFAULT NULL COMMENT '商品id',
    `size` varchar(16) DEFAULT NULL COMMENT '尺码',
    `stock` int(11) DEFAULT NULL COMMENT '库存',
    `lockStock` int(11) DEFAULT NULL COMMENT '冻结库存',
    `createTime` datetime DEFAULT NULL COMMENT '创建时间',
    `updateTime` datetime DEFAULT NULL COMMENT '修改时间',
    PRIMARY KEY (`sid`)
) ENGINE=InnoDB AUTO_INCREMENT=1054 DEFAULT CHARSET=utf8mb4 COMMENT='商品尺码表';
```
