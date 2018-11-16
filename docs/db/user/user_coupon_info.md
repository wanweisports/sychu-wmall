### 表格注解



### SQL语句

```sql
CREATE TABLE `user_coupon_info` (
    `cpid` int(11) NOT NULL AUTO_INCREMENT COMMENT '优惠券id',
    `uid` int(11) DEFAULT NULL COMMENT '用户id',
    `serviceType` int(11) DEFAULT NULL COMMENT '优惠券类型',
    `couponPrice` decimal(10,2) DEFAULT NULL COMMENT '优惠券面值',
    `fullPrice` decimal(10,2) DEFAULT NULL COMMENT '满多少可用',
    `status` varchar(1) DEFAULT NULL COMMENT '优惠券状态（1：已使用  2：未使用）',
    `dueTime` datetime DEFAULT NULL COMMENT '到期时间',
    `createTime` datetime DEFAULT NULL COMMENT '创建时间',
    `updateTime` datetime DEFAULT NULL COMMENT '修改时间',
    PRIMARY KEY (`cpid`)
) ENGINE=InnoDB AUTO_INCREMENT=1872 DEFAULT CHARSET=utf8mb4 COMMENT='优惠券表';
```
