### 表格注解



### SQL语句

```sql
CREATE TABLE `operator_info` (
    `operatorId` int(11) NOT NULL AUTO_INCREMENT,
    `operatorName` varchar(32) DEFAULT NULL COMMENT '名称',
    `operatorAccount` varchar(32) DEFAULT NULL COMMENT '账号',
    `operatorPwd` varchar(32) DEFAULT NULL COMMENT '密码',
    `createTime` datetime DEFAULT NULL COMMENT '创建时间',
    `updateTime` datetime DEFAULT NULL COMMENT '修改时间',
    PRIMARY KEY (`operatorId`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='管理员信息表';
```
