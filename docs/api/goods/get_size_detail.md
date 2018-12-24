### 请求地址

/commodity/detail/selected

### 请求参数
 
* cid 商品编号

### 返回结果

```json
{
    "commName": "商品2",
    "price": 900,
    "couPrice": 800,    #优惠价格
    "cid": 2,
    "resourcePath": "http://p2.qhimxxxx.jpg",
    "sizes": [{           #尺码列表
        "sid": 4,         #尺码id
        "size": "XXL",    #尺码大小
        "stock": 2099     #尺码库存
    }, {
        "sid": 5,
        "size": "XL",
        "stock": 10001
    }, {
        "sid": 24,
        "size": "M",
        "stock": 90
    }, {
        ...
    }]
}
```
