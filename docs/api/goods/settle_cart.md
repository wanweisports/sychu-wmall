### 请求地址

/commodity/settlement

### 请求参数
 
* scids 购物车id  #（多个逗号分隔）

### 返回结果

```json
{
    "discount": 0.95,
    "sumPrice": 2202.22,          #最终支付金额
    "sumOldPrice": 2512.33,       #商品总金额（未减去用户折扣）【新10-17】
    "sumOldDisPrice": 2312.33,    #商品总金额（减去用户折扣）【新10-17】
    "userDiscountSubPrice":200,   #用户等级折扣减去了多少金额【新10-17】
    "couponPrice": 0,             #使用的优惠券金额【新10-17】
    "useYcoid": 0,                #使用的衣橱币数量【新10-17】
    "ycoid": 500,             #用户当前衣橱币数量
    "balance": 3000,          #用户总余额【新11-01】
    "useBalance": 1,              #是否可以使用余额支付1：是 2：否【新11-01】
    "freight": 13,   #【新10-16，运费】
    "list": [{
        "colorName": "黄色",
        "size": "M",
        "price": 100.99,
        "resourcePath": "https://oss-admin.oss-cn-beijing.aliyuncs.com/img/94a4d496b90c430496c571d36774b2fa.jpg",
        "count": 2,
        "commName": "商品3",
        "scid": 22
    }, {
        "colorName": "红色",
        "size": "M",
        "price": 1000.12,
        "resourcePath": "https://oss-admin.oss-cn-beijing.aliyuncs.com/img/94a4d496b90c430496c571d36774b2fa.jpg",
        "count": 2,
        "commName": "商品1",
        "scid": 23
    }, {
        ...
    }],
    "coupons": [{     #可用优惠券列表
        "dictValue": "满1000元可使用",
        "dictValue2": "满1000元减100",【新10-18】
        "fullPrice":1000,     #满多少可用【新10-17】
        "cpid": 1,
        "status": "1",  #1：可以使用（满减够了）  2：不能使用（金额不够满减）
        "couponPrice": 100,
        "dueTime": "2018-09-22 12:30:00" #优惠券过期时间
    }, {
        "dictValue": "满2500元可使用",
        "fullPrice":2500,
        "cpid": 2,
        "status": "1",
        "couponPrice": 300,
        "dueTime": "2018-09-22 10:50:00"
    }, {
        ...
    }]
}
```
