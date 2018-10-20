//index.js
//获取应用实例
const app = getApp();

Page({
    data: {
        goodsListId: [], //
        goodsList:[], //

        allGoodsPrice:0,
        yunPrice:0, //

        allGoodsAndYunPrice:0, // 

        youhuijine: 0, //优惠券金额
        curCoupon: null, // 当前选择使用的优惠券
        couponsIndex: 0,
        serviceType: 2,
        cpid: 0,
        coupons: [],

        ycoidList: [],
        ycoidIndex: 0,
        ycoid: 0,

        userInfo: {}
    },

    getCartSettle: function () {
        let content = this;

        app.wxRequest("/commodity/settlement", {scids: content.data.goodsListId.join(",")}, function (res) {
            if (res.code == 1) {
                let coupons = res.data.coupons;
                let ycoid = res.data.ycoid;

                !!coupons && coupons.forEach(function (item) {
                    item.textShow = "[" + item.dictValue + "]优惠" + item.couponPrice + "元";
                });

                content.setData({
                    goodsList : res.data.list,
                    coupons : coupons.length > 0 ? [{textShow: "不使用优惠券", cpid: ""}].concat(coupons) : [{textShow: "无可用优惠券", cpid: ""}],
                    discount : res.data.discount,
                    allGoodsPrice : res.data.sumPrice,
                    allGoodsAndYunPrice : res.data.sumPrice,
                    ycoid: res.data.ycoid,
                    ycoidList: ycoid > 0 ? ["不使用薏米", res.data.ycoid + "薏米"] : ["无薏米"],
                    yunPrice: res.data.freight
                });
            }
        });
    },

    countCartSettle: function () {
        let content = this;

        app.wxRequest("/commodity/settlementCount", {
            scids: content.data.goodsListId.join(","),
            serviceType: content.data.serviceType,
            cpid: content.data.cpid,
        }, function (res) {
            if (res.code == 1) {
                content.setData({
                    allGoodsAndYunPrice : res.data.sumPrice,
                    yunPrice: res.data.freight
                });
            }
        });
    },

    bindCouponsChange: function (e) {
        let curCoupon = this.data.coupons[e.detail.value]; 

        this.setData({
            couponsIndex : e.detail.value,
            curCoupon : curCoupon,
            serviceType: 1,
            cpid: curCoupon.cpid
        });

        if (!!curCoupon.cpid) {
            this.countCartSettle();
        }
    },

     bindYcoidChange: function (e) {
        let ycoid = this.data.ycoidList[e.detail.value]; 

        this.setData({
            ycoidIndex : e.detail.value,
            ycoid : ycoid,
            serviceType: 2
        });

        if (ycoid > 0) {
            this.countCartSettle();
        }
    },

    onLoad: function () {
        let content = this;

        let shopList = [];
        let shopCarInfoMem = app.getCookie('shopCartInfo');
        if (shopCarInfoMem && shopCarInfoMem.shopList) {
            shopList = shopCarInfoMem.shopList.filter(function (entity) {
                return entity.active;
            });
        }

        let goodsListId = [];
        for (let i = 0; i < shopList.length; i++) {
            goodsListId.push(shopList[i].scid);
        }

        content.setData({
            goodsListId: goodsListId
        });

        content.getCartSettle();
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
            serviceType: content.data.serviceType,
            cpid: content.data.cpid
        };

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
