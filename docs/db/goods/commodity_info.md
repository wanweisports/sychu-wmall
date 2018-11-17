### 表格注解



### SQL语句

```sql
CREATE TABLE `commodity_info` (
    `cid` int(11) NOT NULL AUTO_INCREMENT,
    `commName` varchar(32) DEFAULT NULL COMMENT '商品名称',
    `category` varchar(100) DEFAULT NULL COMMENT '品类（存储方式：,1,2,33,）如果查询使用正则',
    `style` varchar(100) DEFAULT NULL COMMENT '风格（存储方式：,1,2,33,）如果查询使用正则',
    `material` varchar(100) DEFAULT NULL COMMENT '材质（存储方式：,1,2,33,）如果查询使用正则',
    `productDesc` text COMMENT '商品描述',
    `price` decimal(10,2) DEFAULT NULL COMMENT '价格',
    `couPrice` decimal(10,2) DEFAULT NULL COMMENT '优惠价',
    `saleCount` int(11) NOT NULL DEFAULT '0' COMMENT '售出总数量',
    `status` varchar(1) DEFAULT NULL COMMENT '状态（1：在售  2：下架  3：删除）',
    `seqNo` int(11) DEFAULT NULL COMMENT '商品排序',
    `newly` varchar(2) NOT NULL DEFAULT '2' COMMENT '最新（1：最新）',
    `hot` varchar(2) NOT NULL DEFAULT '2' COMMENT '人气（1：人气）',
    `groupId` int(11) DEFAULT NULL COMMENT '商品组id',
    `commNo` varchar(32) DEFAULT NULL COMMENT '商品编号',
    `brandName` varchar(64) DEFAULT NULL COMMENT '品牌名称',
    `clickRate` int(11) DEFAULT '0' COMMENT '点击量',
    `createTime` datetime DEFAULT NULL COMMENT '创建时间',
    `updateTime` datetime DEFAULT NULL COMMENT '修改时间',
    PRIMARY KEY (`cid`)
) ENGINE=InnoDB AUTO_INCREMENT=330 DEFAULT CHARSET=utf8mb4 COMMENT='商品表';
```
