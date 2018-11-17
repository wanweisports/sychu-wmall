### 表格注解



### SQL语句

```sql
CREATE TABLE `sys_coupon_rule` (
    `crid` int(11) NOT NULL COMMENT 'id',
    `couponPrice` decimal(10,2) DEFAULT NULL COMMENT '优惠券面值',
    `fullPrice` decimal(10,2) DEFAULT NULL COMMENT '满多少可用',
    `dueNum` int(11) DEFAULT NULL COMMENT '到期天数',
    `rule` varchar(2) DEFAULT NULL COMMENT '规则类型（1：到期时间算 2：每年某月有效  3：无限制）',
    `serviceType` int(11) DEFAULT NULL COMMENT '优惠券类型',
    `crType` varchar(1) DEFAULT NULL COMMENT '赠送类型（1：注册）',
    `crTime` datetime DEFAULT NULL COMMENT '送券有效时间（null表示无限）',
    `createTime` datetime DEFAULT NULL COMMENT '创建时间',
    `updateTime` datetime DEFAULT NULL COMMENT '修改时间',
    PRIMARY KEY (`crid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='优惠券规则表';
```
