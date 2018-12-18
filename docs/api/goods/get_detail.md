### 请求地址

/commodity/detail

### 请求参数
 
* cid 商品编号

### 返回结果

```json
{
    "resources": [           #图片数组
        "https://gss0.bdstatic.com/6LZ1dD3d1sgCo2Kml5_Y_D3/sys/portrait/item/37f5e69d80e4b984e58aab2e68",
        "https://gss0.bdstatic.com/6LZ1dD3d1sgCo2Kml5_Y_D3/sys/portrait/item/3f8be88396e587a0e9a39ecfd2",
        ...
    ],
    "commName": "商品2",      #商品名称
    "price": 900,            #商品价格
    "couPrice": 800,         #优惠价格
    "brandName":"耐克",       #商品品牌
    "desc":"商品描述123"      #商品描述
    "collection": "1",       #用户是否收藏（1：是  2：否）
    "sizes": [               #商品尺码
        "L",
        "XXL",
        "XL",
        ...
    ],
    "colors": [{               #可选颜色数组
        "colorName": "红色",    #颜色名称
        "cid": 1               #商品id（切换尺码使用）
    }, {
        "colorName": "紫色",
        "cid": 2
    }, {
        ...
    }]
}
```
