### 请求地址

/commodity/recommendList

### 请求参数
 
* cid 商品id

### 返回结果

```json
{
    "list": [{
        "commNo": "商品编号",
        "brandName": "品牌1",
        "recommendCid": 2,  #商品id
        "price": 900.22,
        "resourcePath": "https://oss-admin.oss-cn-beijing.aliyuncs.com/img/94a4d496b90c430496c571d36774b2fa.jpg",
        "commName": "商品2",
        "couPrice": 900
    }, {
        "commNo": "商品编号1",
        "brandName": "品牌2",
        "recommendCid": 9, #商品id
        "price": 12,
        "resourcePath": "https://oss-admin.oss-cn-beijing.aliyuncs.com/img/94a4d496b90c430496c571d36774b2fa.jpg",
        "commName": "123",
        "couPrice": 12
    }, {
        ...
    }]
}
```
