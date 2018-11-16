### 表格注解



### SQL语句

```sql
CREATE TABLE `sys_resources` (
    `resourceId` int(11) NOT NULL AUTO_INCREMENT COMMENT '资源id',
    `resourceServiceType` varchar(2) NOT NULL COMMENT '资源业务类型',
    `resourceServiceId` int(11) NOT NULL COMMENT '资源业务id',
    `resourceServiceParentId` int(11) DEFAULT NULL COMMENT '资源业务父id',
    `resourceSeq` int(11) DEFAULT NULL COMMENT '资源排序',
    `resourceType` varchar(16) NOT NULL COMMENT '资源类型（1：图片  2：文件）',
    `resourceName` varchar(64) NOT NULL COMMENT '资源新名称',
    `resourceOriginal` varchar(64) DEFAULT NULL COMMENT '资源原始名称',
    `resourcePath` varchar(1000) DEFAULT NULL COMMENT '资源路径',
    `fileSize` bigint(20) DEFAULT NULL COMMENT '资源大小',
    `status` varchar(1) DEFAULT NULL COMMENT '资源状态',
    `createTime` datetime DEFAULT NULL COMMENT '创建时间',
    `updateTime` datetime DEFAULT NULL COMMENT '修改时间',
    PRIMARY KEY (`resourceId`)
) ENGINE=InnoDB AUTO_INCREMENT=4111 DEFAULT CHARSET=utf8mb4 COMMENT='资源、图片表';
```
