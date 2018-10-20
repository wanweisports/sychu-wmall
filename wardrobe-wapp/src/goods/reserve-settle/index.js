//index.js
//获取应用实例
const app = getApp();

Page({
    data: {
        goodsListId: [],
        goodsList:[],
        isNeedLogistics:0, // 是否需要物流信息
        allGoodsPrice:0,
        yunPrice:0,
        allGoodsAndYunPrice:0,
        goodsJsonStr:"",
        orderType:"", //订单类型，购物车下单或立即支付下单，默认是购物车，

        youhuijine:0, //优惠券金额
        curCoupon:null, // 当前选择使用的优惠券

        hasNoCoupons: true,
        coupons: [],

        hasNoPoint: true,
        point: 0,

        userInfo: {}
    },

    getUserCouponsList: function () {
        let content = this;

        app.wxRequest("/user/userCouponList", {}, function (res) {
            if (res.code == 1) {
                content.setData({
                    coupons : res.data.coupons
                });
            }
        });
    },

    getUserInfo: function () {
        let content = this;

        app.wxRequest("/user/userCenter", {}, function (res) {
            content.setData({
                userInfo : res.data
            });

            if (content.data.userInfo.couponCount > 0) {
                content.setData({
                    hasNoCoupons: false,
                    couponCount: content.data.userInfo.couponCount
                });

                content.getUserCouponsList();
            }

            if (content.data.userInfo.point > 0) {
                content.setData({
                    hasNoPoint: false,
                    point: content.data.userInfo.point
                });
            }
        });
    },

    getGoodsList: function (did) {
        app.wxRequest("/rfid/readRfid", {did: did}, function (res) {
            if (res.code == 1) {
                content.setData({
                    goodsList: res.code.commoditys,
                    sumPrice: res.code.sumPrice
                });
            }
        });
    },

    onLoad: function (e) {
        let content = this;

        content.getGoodsList(e.did);
        content.getUserInfo();
    },

    createOrder:function () {
        let content = this;
        let remark = ""; // 备注信息

        if (!content.data.curAddressData) {
            wx.showModal({
                title: '提 示',
                content: '请先设置您的收货地址！',
                showCancel: false
            });
            return;
        }

        let postData = {
            expressName: content.data.curAddressData.linkMan,
            expressMobile: content.data.curAddressData.mobile,
            expressAddress: content.data.curAddressData.address,
            remark: remark,
            scids: content.data.goodsListId.join(","),
            serviceType: "",
            cpid: ""
        };
        if (content.data.curCoupon) {
            postData.serviceType = 2;
            postData.cpid = content.data.curCoupon.id;
        }
        postData.calculate = "true";

        app.wxRequest("/order/saveOrder", postData, function (res) {
            // 清空购物车数据
            app.clearCookie("shopCartInfo");

            app.wxPay(res.data.oid, "/pages/user/order-list/index");

            // 配置模板消息推送
            // var postJsonString = {};
            // postJsonString.keyword1 = { value: res.data.data.dateAdd, color: '#173177' }
            // postJsonString.keyword2 = { value: res.data.data.amountReal + '元', color: '#173177' }
            // postJsonString.keyword3 = { value: res.data.data.orderNumber, color: '#173177' }
            // postJsonString.keyword4 = { value: '订单已关闭', color: '#173177' }
            // postJsonString.keyword5 = { value: '您可以重新下单，请在30分钟内完成支付', color:'#173177'}
            // app.sendTempleMsg(res.data.data.id, -1,
            //     'uJQMNVoVnpjRm18Yc6HSchn_aIFfpBn1CZRntI685zY', e.detail.formId,
            //     'pages/index/index', JSON.stringify(postJsonString));
            // postJsonString = {};
            // postJsonString.keyword1 = { value: '您的订单已发货，请注意查收', color: '#173177' }
            // postJsonString.keyword2 = { value: res.data.data.orderNumber, color: '#173177' }
            // postJsonString.keyword3 = { value: res.data.data.dateAdd, color: '#173177' }
            // app.sendTempleMsg(res.data.data.id, 2,
            //     'GeZutJFGEWzavh69savy_KgtfGj4lHqlP7Zi1w8AOwo', e.detail.formId,
            //     'pages/order-details/index?id=' + res.data.data.id, JSON.stringify(postJsonString));
        })
    },
    addAddress: function () {
        let content = this;

        wx.chooseAddress({
            success: function (res) {
                content.setData({
                    curAddressData: {
                        linkMan: res.userName,
                        mobile: res.telNumber,
                        address: res.provinceName + " " + res.cityName + " " + res.countyName + " " + res.detailInfo
                    }
                });
            }
        });
    },
    selectAddress: function () {
        let content = this;

        wx.chooseAddress({
            success: function (res) {
                content.setData({
                    curAddressData: {
                        linkMan: res.userName,
                        mobile: res.telNumber,
                        address: res.provinceName + " " + res.cityName + " " + res.countyName + " " + res.detailInfo
                    }
                });
            }
        });
    },
    bindChangeCoupon: function (e) {
        const selIndex = e.detail.value[0] - 1;

        if (selIndex == -1) {
            this.setData({
                youhuijine: 0,
                curCoupon: null
            });
            return;
        }

        this.setData({
            youhuijine: this.data.coupons[selIndex].couponPrice,
            curCoupon: this.data.coupons[selIndex]
        });
    }
});
