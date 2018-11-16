### 表格注解



### SQL语句

```sql
CREATE TABLE `sys_device_info` (
    `did` int(11) NOT NULL AUTO_INCREMENT COMMENT '设备id',
    `name` varchar(32) DEFAULT NULL COMMENT '设备名称',
    `areaId` varchar(32) DEFAULT NULL COMMENT '设备区域id',
    `address` varchar(255) DEFAULT NULL COMMENT '设备地址',
    `status` varchar(1) DEFAULT NULL COMMENT '状态（1：正常  2：被占用 3：下线  4：损坏）',
    `startTime` varchar(32) DEFAULT NULL COMMENT '开启时间',
    `endTime` varchar(32) DEFAULT NULL COMMENT '关闭时间',
    `doorIp` varchar(64) DEFAULT NULL COMMENT '门ip',
    `doorPort` int(11) DEFAULT NULL COMMENT '门端口',
    `lockIp` varchar(64) DEFAULT NULL COMMENT '柜子锁ip',
    `lockPort` int(11) DEFAULT NULL COMMENT '柜子锁端口',
    `createTime` datetime DEFAULT NULL COMMENT '创建时间',
    `openTime` datetime DEFAULT NULL COMMENT '开门时间',
    `closeTime` datetime DEFAULT NULL COMMENT '关门时间',
    `openLockTime` datetime DEFAULT NULL,
    PRIMARY KEY (`did`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='设备表';
```
