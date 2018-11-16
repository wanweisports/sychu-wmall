### 表格注解



### SQL语句

```sql
CREATE TABLE `sys_area` (
    `areaId` varchar(32) NOT NULL COMMENT '地区id',
    `areaParentId` varchar(32) DEFAULT NULL COMMENT '地区父id',
    `areaRootId` varchar(32) DEFAULT NULL COMMENT '区域根标识',
    `areaCode` varchar(16) NOT NULL COMMENT '地区Code',
    `areaName` varchar(32) NOT NULL COMMENT '地区名称',
    `abbreviation` varchar(32) DEFAULT NULL COMMENT '地区简称',
    `areaNameFull` varchar(64) DEFAULT NULL COMMENT '地区全名',
    `areaPinYinFull` varchar(64) DEFAULT NULL COMMENT '地区全拼音名',
    `areaPinyinInit` varchar(16) DEFAULT NULL COMMENT '地区简称拼音名',
    `sortNo` varchar(16) DEFAULT NULL COMMENT '排序',
    `rank` varchar(16) DEFAULT NULL COMMENT '地区等级',
    `seqNo` varchar(64) DEFAULT NULL COMMENT '地区全id',
    `updateTime` datetime DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`areaId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='地区表';
```
