### 表格注解



### SQL语句

```sql
CREATE TABLE `commodity_recommend` (
    `crid` int(11) NOT NULL AUTO_INCREMENT,
    `cid` int(11) DEFAULT NULL COMMENT '商品id',
    `recommendCid` int(11) DEFAULT NULL COMMENT '推荐商品id',
    `seqNo` int(11) DEFAULT NULL COMMENT '排序',
    `createTime` datetime DEFAULT NULL COMMENT '创建时间',
    `updateTime` datetime DEFAULT NULL COMMENT '修改时间',
    PRIMARY KEY (`crid`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COMMENT='商品推荐表';
```
