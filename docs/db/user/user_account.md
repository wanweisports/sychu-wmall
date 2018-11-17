### 表格注解



### SQL语句

```sql
CREATE TABLE `user_account` (
    `uid` int(11) NOT NULL COMMENT '用户id',
    `balance` decimal(10,2) DEFAULT NULL COMMENT '账户余额',
    `ycoid` int(11) DEFAULT NULL COMMENT '衣橱币（闪币）',
    `rank` int(11) DEFAULT NULL COMMENT '等级',
    `score` int(11) DEFAULT NULL COMMENT '积分',
    `createTime` datetime DEFAULT NULL COMMENT '创建时间',
    `updateTime` datetime DEFAULT NULL COMMENT '修改时间',
    PRIMARY KEY (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='账户表';
```
