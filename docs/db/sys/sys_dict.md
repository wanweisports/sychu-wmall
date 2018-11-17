### 表格注解



### SQL语句

```sql
CREATE TABLE `sys_dict` (
    `dictId` int(11) NOT NULL AUTO_INCREMENT COMMENT '字典id',
    `parentDictId` int(9) DEFAULT NULL COMMENT '父字典id',
    `dictName` varchar(32) NOT NULL COMMENT '字典Name',
    `dictKey` varchar(16) NOT NULL COMMENT '字典Key',
    `dictValue` varchar(32) NOT NULL COMMENT '字典值',
    `dictAdditional` varchar(32) DEFAULT NULL COMMENT '附加值',
    `seqNo` int(11) DEFAULT '0' COMMENT '排序',
    `createTime` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    PRIMARY KEY (`dictId`),
    KEY `AK_Key_2` (`dictName`,`dictKey`)
) ENGINE=InnoDB AUTO_INCREMENT=100032 DEFAULT CHARSET=utf8mb4 COMMENT='字典表';
```
