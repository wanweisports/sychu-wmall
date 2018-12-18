### 请求地址

/commodity/userShoppingCarts

### 请求参数
 
* shoppingType：1：购物车  2：预约购物车

### 返回结果

```json
{
    "sumPrice": 1937.43,       #总价格
    "lastPage": 1,
    "count": 3,
    "pageSize": 10,
    "currentPage": 1,
    "list": [{                  #购物车列表
        "resourcePath": "https://123.jpg", #图片地址
        "colorName": "紫色",                #颜色
        "commName": "123",                  #商品名称
        "size": "M",                        #尺码名称
        "scid": 2,                          #购物车id
        "price": 12,                        #商品价格
        "count": 2                          #商品数量
    }, {
        "resourcePath": "https://123.jpg",
        "colorName": "紫色",
        "commName": "商品2",
        "size": "XL",
        "scid": 3,
        "price": 900.22,
        "count": 3
    }, {
        "resourcePath": "https://123.jpg",
        "colorName": "黄色",
        "commName": "商品3",
        "size": "M",
        "scid": 4,
        "price": 100.99,
        "count": 1
    }, {
        ...
    }] 
}
```
