### 请求地址

/rfid/readRfid

### 请求参数
 
* did（设备大门id）

### 返回结果

```json
 {
    "discount": 0.95,
    "sumPrice": 2202.22,          #最终支付金额
    "sumOldPrice": 2512.33,       #商品总金额（未减去用户折扣）
    "sumOldDisPrice": 2312.33,    #商品总金额（减去用户折扣）
    "userDiscountSubPrice":200,   #用户等级折扣减去了多少金额
    "couponPrice": 0,             #使用的优惠券金额
    "useYcoid": 0,                #使用的衣橱币数量                "ycoid": 500,              #用户当前衣橱币数量
    "useBalance": 1,          #是否可以使用余额支付1：是 2：否
    "commoditys": [{
        "price": 178.2,
        "resourcePath": "https://oss-admin.oss-cn-b6c5.jpg",
        "name": "柜子2号",
        "commName": "惠维2018秋女装 ",
        "cid": 19,
        "dbid": 1
    }, {
        "price": 59,
        "resourcePath": "https://oss157aceb3a2b7a60.jpg",
        "name": "柜子2号",
        "commName": "洛蔓希性感透视装女士内衣白上衣+黑裙",
        "cid": 17,
        "dbid": 2
    }, {
        "price": 100.99,
        "resourcePath": "https://oss2fa.jpg",
        "name": "柜子1号",
        "commName": "商品3",
        "cid": 14,
        "dbid": 3
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
