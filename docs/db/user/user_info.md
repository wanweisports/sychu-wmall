### 表格注解



### SQL语句

```sql
CREATE TABLE `user_info` (
    `uid` int(11) NOT NULL AUTO_INCREMENT,
    `openId` varchar(32) DEFAULT NULL COMMENT '小程序openId',
    `unionId` varchar(32) DEFAULT NULL COMMENT '小程序unionId',
    `nickname` varchar(255) DEFAULT NULL COMMENT '用户昵称',
    `headImg` varchar(233) DEFAULT NULL COMMENT '用户头像',
    `sex` varchar(2) DEFAULT NULL COMMENT '性别字典',
    `age` date DEFAULT NULL COMMENT '年龄',
    `dressStyle` varchar(32) DEFAULT NULL COMMENT '风格偏好（存储方式：,1,2,33,）如果查询使用正则',
    `usualSize` varchar(32) DEFAULT NULL COMMENT '尺码偏好（存储方式：,1,2,33,）如果查询使用正则',
    `mobile` varchar(32) DEFAULT NULL COMMENT '手机号',
    `inviteCode` varchar(10) DEFAULT NULL COMMENT '邀请码',
    `invitedBy` int(11) DEFAULT NULL COMMENT '邀请人id',
    `isPerfect` varchar(2) DEFAULT NULL COMMENT '是否完善了资料',
    `registerTime` datetime DEFAULT NULL COMMENT '注册时间',
    `createTime` datetime DEFAULT NULL COMMENT '创建时间',
    PRIMARY KEY (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=705 DEFAULT CHARSET=utf8mb4 COMMENT='会员表';
```
