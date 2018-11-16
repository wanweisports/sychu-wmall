### 表格注解



### SQL语句

```sql
CREATE TABLE `sys_device_control` (
    `dcid` int(11) NOT NULL AUTO_INCREMENT COMMENT '柜子id',
    `did` int(11) DEFAULT NULL COMMENT '设备id',
    `lockId` int(11) DEFAULT NULL COMMENT '锁id',
    `name` varchar(32) DEFAULT NULL COMMENT '锁名称',
    `type` varchar(1) DEFAULT NULL COMMENT '类型（1：大门 2：锁）',
    `lock` varchar(1) DEFAULT NULL COMMENT '锁（1：开启  2：锁住）',
    `status` varchar(1) DEFAULT NULL COMMENT '状态（1：正常  2：下线  3：损坏）',
    `createTime` datetime DEFAULT NULL COMMENT '创建时间',
    `updateTime` datetime DEFAULT NULL COMMENT '修改时间',
    `openTime` datetime DEFAULT NULL COMMENT '开锁时间',
    `closeTime` datetime DEFAULT NULL COMMENT '关锁时间',
    PRIMARY KEY (`dcid`)
) ENGINE=InnoDB AUTO_INCREMENT=102 DEFAULT CHARSET=utf8mb4 COMMENT='设备控制表（锁）';
```
