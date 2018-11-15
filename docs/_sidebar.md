<!-- docs/_sidebar.md -->

* 基础接口
    * [获取字典列表](api/common/get_dicts_list)

* 商品接口
    * [获取商品列表](api/goods/get_list)
    * [获取商品详情](api/goods/get_detail)
    * [获取尺码详情](api/goods/get_size_detail)
    * [获取购物车](api/goods/get_cart)
    * [添加购物车](api/goods/save_cart)
    * [删除购物车](api/goods/delete_cart)
    * [购物车结算](api/goods/settle_cart)
    * [统计商品访问量](api/goods/stat_pv)
    * [商品收藏添加](api/user/save_collection)
    * [商品收藏取消](api/user/cancel_collection)
    * [获取首页轮播](api/goods/get_banners)

* 用户接口
    * [完善用户资料](api/user/save_info)
    * [获取个人中心](api/user/get_center)
    * [获取用户信息](api/user/get_info)
    * [获取用户余额](api/user/get_balance)
    * [获取用户积分](api/user/get_score)
    * [商品收藏列表](api/user/list_collection)

* 交易接口
    * [用户充值](api/order/user_recharge)
    * [交易明细](api/order/get_transactions_log)
    * [订单试算](api/goods/count_order)
    * [创建订单](api/order/save_order)
    * [订单支付](api/order/pay_order)
    * [订单列表](api/order/get_order_list)
    * [订单详情](api/order/get_order_detail)
    * [取消订单](api/order/cancel_order)

* 试衣接口
    * [获取试衣间列表](api/reserve/get_device_list)
    * [获取试衣间详情](api/reserve/get_device_detail)
    * [创建预约订单](api/reserve/save_order)
    * [取消预约订单](api/reserve/cancel_order)
    * [已预约订单列表](api/reserve/get_order_list)
    * [已预约订单试衣间](api/reserve/get_order_device)
    * [进试衣间大门](api/reserve/open_in_device)
    * [出试衣间大门](api/reserve/open_out_device)
    * [获取可解锁的衣柜](api/reserve/get_unlock_device)
    * [解锁衣柜](api/reserve/unlock_device)
    * [锁定衣柜](api/reserve/lock_device)
    * [正在试穿的商品](api/reserve/get_goods_fitting)
    * [试穿订单试算](api/reserve/count_order)
    * [创建试穿订单](api/reserve/save_order_fitting)

* 优惠券接口
    * [获取用户优惠券](api/coupon/get_user_coupons)

* 短信接口
    * [发送短信验证码](api/sms/send_mobile_code)
    * [验证旧手机号](api/sms/check_old_mobile)
    * [验证新手机号](api/sms/check_new_mobile)
