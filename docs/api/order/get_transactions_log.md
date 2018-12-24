### 请求地址

/transactions/index

### 请求参数

* type：2,余额支付与充值 3,奖励薏米与支付薏米、整数类型

### 返回结果

```json
{
    "count": 3,        #总条数
    "pageSize": 10,    #每页条数
    "lastPage": 1,     #总页数
    "currentPage": 1,  #当前页
    "list": [{         #交易流水列表
        "serviceId": 10,  #订单id
        "price": 100,
        "serviceTypeName": "余额支付",
        "createTime": "2018-08-03 11:57:42",
        "type": 2
    }, {
        "price": 200,
        "serviceTypeName": "充值",
        "createTime": "2018-08-03 11:57:42",
        "type": 2
    }, {
        "serviceId": 10,  #订单id
        "price": 26,
        "serviceTypeName": "奖励薏米",
        "createTime": "2018-08-03 11:57:42",
        "type": 3
    }, {
        "serviceId": 11,  #订单id
        "price": 26,
        "serviceTypeName": "支付薏米",
        "createTime": "2018-08-03 11:57:42",
        "type":3
    },
    #冲抵，类型不变，返回remark不为空为冲抵
    {
        "price": 100,
        "serviceTypeName": "余额支付",
        "createTime": "2018-08-03 11:57:42",
        "remark": "冲抵余额abc"
    }, {
        "price": 200,
        "serviceTypeName": "充值",
        "createTime": "2018-08-03 11:57:42",
        "remark": "冲抵余额abcasa"
    }, {
        "price": 300,
        "serviceTypeName": "支付薏米",
        "createTime": "2018-08-03 11:57:42",
        "remark": "冲抵余额abcadsad"
    }, {
        "price": 500,
        "serviceTypeName": "奖励薏米",
        "createTime": "2018-08-03 11:57:42",
        "remark": "冲抵余额aabasdac"
    }, {
        ...
    }]
}
```
