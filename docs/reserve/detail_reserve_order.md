### 请求地址

/order/reserveOrderDetail

### 请求参数

无

### 返回结果

```json
{
    "sumPrice": 1050.24,  #总价
    "list": [{
        "roid": 5,   #预约订单id
        "resItemPrice": 1000.12,
        "resItemCount": 2,
        "resItemImg": "https://gss0.bdsxxx.jpg ",
        "resItemName": "商品1",
        "resItemColor": "红色",
        "resItemSize": "M",
        "resItemPriceSum": 2000.24,
        "did": 1,  #大门id
        "dcid": 10 #柜子id
    }, {
        "roid": 6,
        "resItemPrice": 50.12,
        "resItemCount": 1,
        "resItemImg": "https://gss0.bdsxxx.jpg ",
        "resItemName": "商品2",
        "resItemColor": "紫色",
        "resItemSize": "XXL",
        "resItemPriceSum": 50.12,
        "did": 1,  #大门id
        "dcid": 10 #柜子id
    }]
}
```