### 请求地址

/order/reserveOrderInfo

### 请求参数
 
* roid：预约订单编号、Integer

### 返回结果

```json
{
    "list": [{
        "address": "新中关购物中心1层优衣库旁", 
        "sdcName": "柜子1号",
        "resDate": "2018-08-29",
        "roid": 5,
        "resStartTime": "15:00",
        "resEndTime": "16:00",
        "sdName": "新中关购物中心",
        "areaNameFull": "北京市 北京市 海淀区",
        "dcid": "1"  #柜子id(开柜子使用)
    }]
}
```