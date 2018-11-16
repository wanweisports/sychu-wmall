### 表格注解



### SQL语句

```sql
CREATE TABLE `commodity_color` (
    `coid` int(11) NOT NULL AUTO_INCREMENT COMMENT '商品颜色id',
    `cid` int(11) DEFAULT NULL COMMENT '商品id',
    `colorName` varchar(32) DEFAULT NULL COMMENT '颜色名称',
    `createTime` datetime DEFAULT NULL COMMENT '创建时间',
    `updateTime` datetime DEFAULT NULL COMMENT '修改时间',
    PRIMARY KEY (`coid`)
) ENGINE=InnoDB AUTO_INCREMENT=689 DEFAULT CHARSET=utf8mb4 COMMENT='商品颜色表';
```
