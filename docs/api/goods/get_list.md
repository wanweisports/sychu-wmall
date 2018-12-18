### 请求地址

/commodity/index

### 请求参数
 
* category （品类, , 此值为字典接口的dictId字段,参数格式【,10,20,】，多个逗号分隔，注意参数前后必须有逗号，为了方便数据库正则查询）
* style （风格，同上）
* material （材质，同上）
* newly （传1：则返回首页最新上架商品）
* hot   （传1：则返回首页人气热门商品）
* orderBy（排序规则：【按金额正排序传:couPrice，按金额倒排序传:couPrice DESC，中间有空格】）

### 返回结果

```json
{
    "count": 2,         #总条数
    "pageSize": 10,     #每页条数
    "lastPage": 1,      #总页数
    "currentPage": 1,   #当前页
    "list": [{          #商品列表
        "commName": "商品2",                 #商品名称 
        "brandName":"耐克",                  #商品品牌
        "resourcePath": "https://321.jpg",   #图片地址
        "price": 900,                        #商品价格
        "couPrice": 800,                     #优惠价格
        "cid": 2                             #商品id
    }, {
        "commName": "商品1",
        "brandName":"耐克",
        "resourcePath": "https://123.jpg",
        "price": 1000,
        "couPrice": 800
        "cid": 1
    }, {
        ...
    }]
}
```
