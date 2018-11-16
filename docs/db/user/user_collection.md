### 表格注解



### SQL语句

```sql
CREATE TABLE `user_collection` (
    `cnid` int(11) NOT NULL AUTO_INCREMENT,
    `uid` int(11) DEFAULT NULL COMMENT '用户id',
    `cid` int(11) DEFAULT NULL COMMENT '商品id',
    `createTime` datetime DEFAULT NULL COMMENT '创建时间',
    PRIMARY KEY (`cnid`)
) ENGINE=InnoDB AUTO_INCREMENT=76 DEFAULT CHARSET=utf8mb4 COMMENT='用户收藏';
```
