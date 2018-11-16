### 表格注解



### SQL语句

```sql
CREATE TABLE `commodity_banner` (
    `cbid` int(11) NOT NULL AUTO_INCREMENT,
    `cid` int(11) DEFAULT NULL COMMENT '商品id',
    `title` varchar(36) DEFAULT NULL COMMENT '标题',
    `url` varchar(1000) DEFAULT NULL COMMENT '链接地址',
    `seqNo` int(11) DEFAULT NULL COMMENT '排序（值越大越靠前）',
    `createTime` datetime DEFAULT NULL COMMENT '创建时间',
    PRIMARY KEY (`cbid`)
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8mb4 COMMENT='商品banner表';
```
