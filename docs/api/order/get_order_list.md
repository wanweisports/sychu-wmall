### 请求地址

/order/userOrders

### 请求参数
 
* page 分页索引、Integer

### 返回结果

```json
{
    "count"       : "Integer 总条数",
    "pageSize"    : "Integer 每页条数",
    "lastPage"    : "Integer 总页数",
    "currentPage" : "Integer 当前页",
    "list"        : [{
        "freight"        : "Float 订单运费",
        "orderType"      : "Integer 订单类型",
        "oid"            : "Integer 订单编号",
        "expressMobile"  : "String 收货人手机号",
        "expressAddress" : "String 收货人地址",
        "expressName"    : "String 收货人姓名",
        "createTime"     : "String 订单创建时间、2018-09-09 23:49:48",
        "payPrice"       : "Float 订单支付金额",
        "priceSum"       : "Float 商品总价",
        "payStatus"      : "Integer 支付状态、1：已支付 2：待支付 3：取消订单",
        "status"         : "Integer 订单状态、1：正常 2：申请退款 3：退款成功 4：退款拒绝",
        "payTime"        : "String 订单支付时间、2018-09-09 23:49:48",
        "orderDetails"   : [{
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
        }]
    }, {
        ...
    }]
}
```
