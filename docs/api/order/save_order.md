### 请求地址

/order/saveOrder

### 请求参数
 
* expressName  #'收货人姓名',
* expressMobile  #'收货人电话',
* expressAddress  #收货人详细地址',
* scids 购物车id  #（多个逗号分隔）
* serviceType  #（1：使用优惠券 2：使用薏米）
* cpid：#如果使用优惠券，则传优惠券id
* payType  #支付方式（1：微信支付  2：余额支付）

### 返回结果

```json
{
    "oid": 1   #订单id
}
```
