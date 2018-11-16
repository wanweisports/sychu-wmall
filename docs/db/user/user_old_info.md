### 表格注解



### SQL语句

```sql
CREATE TABLE `user_old_info` (
    `uoid` int(11) NOT NULL AUTO_INCREMENT,
    `mobile` varchar(32) DEFAULT NULL,
    `score` int(11) DEFAULT NULL,
    `nickname` varchar(32) DEFAULT NULL COMMENT '昵称',
    PRIMARY KEY (`uoid`),
    UNIQUE KEY `uq_mobile` (`mobile`)
) ENGINE=InnoDB AUTO_INCREMENT=385 DEFAULT CHARSET=utf8mb4 COMMENT='老用户';
```
