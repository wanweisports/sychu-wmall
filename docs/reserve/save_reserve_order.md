### 请求地址

/order/saveReserveOrder

### 请求参数
 
* scids： 预约购物车ids、String（逗号分隔）
* reserveStartTime： 预约开始时间、String（2018-11-12 11:12）
* reserveEndTime： 预约开始时间、String（2018-11-12 11:12）
* did：试衣间ID、Integer

### 返回结果

```json
{
    "oid": "Integer 预约订单id"  
}
```