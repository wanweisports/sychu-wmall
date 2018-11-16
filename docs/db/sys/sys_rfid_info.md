### 表格注解



### SQL语句

```sql
CREATE TABLE `sys_rfid_info` (
    `rfid` int(11) NOT NULL AUTO_INCREMENT COMMENT '射频id',
    `ip` varchar(32) DEFAULT NULL COMMENT 'ip',
    `port` int(11) DEFAULT NULL COMMENT '端口',
    `type` varchar(1) DEFAULT NULL COMMENT '类型（1：商城射频  2：仓库射频）',
    `workAntenna` int(11) DEFAULT NULL COMMENT '工作天线',
    `did` int(11) DEFAULT NULL COMMENT '设备id（设备地址）',
    `name` varchar(32) DEFAULT NULL COMMENT '名称',
    PRIMARY KEY (`rfid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='射频ip表';
```
