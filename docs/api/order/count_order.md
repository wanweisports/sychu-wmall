### 请求地址

/commodity/settlementCount

### 请求参数
 
* scids：  购物车列表（和上面一样）
* serviceType：1：使用优惠券（必传优惠券id）  2：使用衣橱币    
* cpid：优惠券id（非必填，使用优惠券传此id）

### 返回结果

```json
{
    "sumPrice": 2202.22,          #最终支付金额
    "sumOldPrice": 2512.33,       #商品总金额（未减去用户折扣）
    "sumOldDisPrice": 2312.33,    #商品总金额（减去用户折扣）
    "userDiscountSubPrice":200,   #用户等级折扣减去了多少金额
    "couponPrice": 0,             #使用的优惠券金额
    "useYcoid": 0,                #使用的衣橱币数量
    "freight": 13,                #【运费】
    "useBalance": 1               #是否可以使用余额支付1：是 2：否
}
```
