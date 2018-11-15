### 请求地址

/transactions/index

### 请求参数

* type：2,余额支付与充值 3,奖励薏米与支付薏米、整数类型

### 返回结果

```json
{
    "count": 3,       #总条数
    "pageSize": 10,    #每页条数
    "lastPage": 1,     #总页数
    "currentPage": 1,  #当前页
    "list": [{          #交易流水列表
        "serviceId": 10,  #订单id【新2018-11-06】
        "price": 100,
        "serviceTypeName": "余额支付",
        "createTime": "2018-08-03 11:57:42",
        "type": 2 【新2：余额与充值】
    }, {
        "price": 200,
        "serviceTypeName": "充值",
        "createTime": "2018-08-03 11:57:42",
        "type": 2【新2：余额与充值】
    }, {
        "serviceId": 10,  #订单id【新2018-11-06】
        "price": 26,
        "serviceTypeName": "奖励薏米",
        "createTime": "2018-08-03 11:57:42",
        "type":3【新3：薏米】
    }, {
        "serviceId": 11,  #订单id【新2018-11-06】
        "price": 26,
        "serviceTypeName": "支付薏米",
        "createTime": "2018-08-03 11:57:42",
        "type":3【新3：薏米】
    }, {
        ...
    }]
}
```
