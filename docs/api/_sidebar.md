<!-- docs/_sidebar.md -->

* 文档说明
    * [功能描述](common/api_introduce)
    * [数据字典](common/get_dicts_list)
    * [返回格式](common/api_return)

* 请求接口
    * 商品接口
        * [获取商品列表](goods/get_list)
        * [获取商品详情](goods/get_detail)
        * [推荐商品列表](goods/get_recommends_list)
        * [获取尺码详情](goods/get_size_detail)
        * [获取购物车](goods/get_cart)
        * [添加购物车](goods/save_cart)
        * [删除购物车](goods/delete_cart)
        * [购物车结算](goods/settle_cart)
        * [统计商品访问量](goods/stat_pv)
        * [商品收藏添加](user/save_collection)
        * [商品收藏取消](user/cancel_collection)
        * [获取首页轮播](goods/get_banners)

    * 用户接口
        * [完善用户资料](user/save_info)
        * [获取个人中心](user/get_center)
        * [获取用户信息](user/get_info)
        * [获取用户余额](user/get_balance)
        * [获取用户积分](user/get_score)
        * [商品收藏列表](user/list_collection)

    * 交易接口
        * [用户充值](order/user_recharge)
        * [交易明细](order/get_transactions_log)
        * [订单试算](goods/count_order)
        * [创建订单](order/save_order)
        * [订单支付](order/pay_order)
        * [订单列表](order/get_order_list)
        * [订单详情](order/get_order_detail)
        * [取消订单](order/cancel_order)

    * 试衣接口
        * [获取试衣间列表](reserve/get_device_list)
        * [获取试衣间详情](reserve/get_device_detail)
        * [创建预约订单](reserve/save_order)
        * [取消预约订单](reserve/cancel_order)
        * [已预约订单列表](reserve/get_order_list)
        * [已预约订单试衣间](reserve/get_order_device)
        * [进试衣间大门](reserve/open_in_device)
        * [出试衣间大门](reserve/open_out_device)
        * [获取可解锁的衣柜](reserve/get_unlock_device)
        * [解锁衣柜](reserve/unlock_device)
        * [锁定衣柜](reserve/lock_device)
        * [正在试穿的商品](reserve/get_goods_fitting)
        * [试穿订单试算](reserve/count_order)
        * [创建试穿订单](reserve/save_order_fitting)

    * 优惠券接口
        * [获取用户优惠券](coupon/get_user_coupons)

    * 短信接口
        * [发送短信验证码](sms/send_mobile_code)
        * [验证旧手机号](sms/check_old_mobile)
        * [验证新手机号](sms/check_new_mobile)
