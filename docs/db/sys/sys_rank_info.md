### 表格注解



### SQL语句

```sql
CREATE TABLE `sys_rank_info` (
    `rid` int(11) NOT NULL AUTO_INCREMENT COMMENT '等级id',
    `rank` int(11) DEFAULT NULL COMMENT '等级',
    `rankName` varchar(32) DEFAULT NULL COMMENT '等级名称',
    `rankScore` int(11) DEFAULT NULL COMMENT '等级积分',
    `rankDiscount` decimal(10,2) DEFAULT NULL COMMENT '等级折扣',
    PRIMARY KEY (`rid`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COMMENT='等级表';
```
