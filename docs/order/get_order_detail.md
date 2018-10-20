### 请求地址

/order/userOrderDetail

### 请求参数
 
* oid 订单编号、Integer

### 返回结果

```json
{
    "orderDetails": [{
        "itemColor"    : "String 商品颜色",
        "itemCount"    : "Integer 商品数量",
        "itemName"     : "String 商品名称",
        "itemImg"      : "String 商品图片地址",
        "createTime"   : "String 商品创建时间、2018-09-09 23:49:48",
        "itemPrice"    : "Float 商品单价",
        "itemSize"     : "String 商品尺码XL",
        "itemPriceSum" : "Float 商品合计价格",
        "cid"          : "Integer 商品编号"
    }, {
        ...
    }],
    "order": {
        "oid": 12,
        "uid": 19,
        "orderType": "1",
        "priceSum": 1800.44,
        "payPrice": 1800.44,
        "payStatus": "2",  #支付状态（1：已支付  2：待支付）
        "status": "1", #订单状态（1：正常 2：取消订单 3：退货申请中 4：已退货） 
        "payTime": null,  #支付时间（未支付为null）
        "expressName": "张三",
        "expressMobile": "020-81167888",
        "expressAreaId": null,
        "expressAddress": "广东省 广州市 海珠区 新港中路397号"
    }
}
```