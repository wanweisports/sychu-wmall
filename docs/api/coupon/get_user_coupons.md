### 请求地址

/user/userCouponList

### 请求参数
 
无

### 返回结果

```json
{
    "coupons": [{
        "dictValue": "满1000元可使用",
        "dictValue2": "满1000元减100",
        "cpid": 2,            #优惠券id
        "couponPrice": 100,  #优惠券金额
        "fullPrice":1000,     #满多少可用
        "dueTime": "2018-08-31 12:20:21" #优惠券过期时间
    }, {
        "dictValue": "满2500元可使用",
        "cpid": 1,
        "couponPrice": 300,
        "fullPrice":2500,
        "dueTime": "2018-09-01 12:20:21"
    }, {
        ...
    }]
}
```
