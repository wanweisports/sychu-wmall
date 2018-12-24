<!-- docs/_sidebar.md -->

* 文档说明
    * [功能描述](common/api_introduce)
    * [数据字典](common/get_dicts_list)
    * [返回格式](common/api_return)

* 请求接口
    * 1、商品接口
        * [1.1、获取商品列表](goods/get_list)
        * [1.2、获取商品详情](goods/get_detail)
        * [1.3、推荐商品列表](goods/get_recommends_list)
        * [1.4、获取尺码详情](goods/get_size_detail)
        * [1.5、获取购物车](goods/get_cart)
        * [1.6、添加购物车](goods/save_cart)
        * [1.7、删除购物车](goods/delete_cart)
        * [1.8、购物车结算](goods/settle_cart)
        * [1.9、统计商品访问量](goods/stat_pv)
        * [1.10、商品收藏添加](user/save_collection)
        * [1.11、商品收藏取消](user/cancel_collection)
        * [1.12、获取首页轮播](goods/get_banners)

    * 2、用户接口
        * [2.1、完善用户资料](user/save_info)
        * [2.2、获取个人中心](user/get_center)
        * [2.3、获取用户信息](user/get_info)
        * [2.4、获取用户余额](user/get_balance)
        * [2.5、获取用户积分](user/get_score)
        * [2.5、商品收藏列表](user/list_collection)
        * [2.7、记录试衣状态](user/save_status)

    * 3、交易接口
        * [3.1、用户充值](order/user_recharge)
        * [3.2、交易明细](order/get_transactions_log)
        * [3.3、订单试算](order/count_order)
        * [3.4、创建订单](order/save_order)
        * [3.5、订单支付](order/pay_order)
        * [3.6、订单列表](order/get_order_list)
        * [3.7、订单详情](order/get_order_detail)
        * [3.8、取消订单](order/cancel_order)

    * 4、试衣接口
        * [4.1、获取试衣间列表](reserve/get_device_list)
        * [4.2、获取试衣间详情](reserve/get_device_detail)
        * [4.3、创建预约订单](reserve/save_order)
        * [4.4、取消预约订单](reserve/cancel_order)
        * [4.5、已预约订单列表](reserve/get_order_list)
        * [4.6、已预约订单试衣间](reserve/get_order_device)
        * [4.7、进试衣间大门](reserve/open_in_device)
        * [4.8、出试衣间大门](reserve/open_out_device)
        * [4.9、获取可解锁的衣柜](reserve/get_unlock_device)
        * [4.10、解锁衣柜](reserve/unlock_device)
        * [4.11、锁定衣柜](reserve/lock_device)
        * [4.12、正在试穿的商品](reserve/get_goods_fitting)
        * [4.13、试穿订单试算](reserve/count_order)
        * [4.16、创建试穿订单](reserve/save_order_fitting)

    * 5、优惠券接口
        * [5.1、获取用户优惠券](coupon/get_user_coupons)

    * 6、短信接口
        * [6.1、发送短信验证码](sms/send_mobile_code)
        * [6.2、验证旧手机号](sms/check_old_mobile)
        * [6.3、验证新手机号](sms/check_new_mobile)
