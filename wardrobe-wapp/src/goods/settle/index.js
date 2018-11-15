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

        curCoupon: null, // 当前选择使用的优惠券
        couponsIndex: 0,
        serviceType: "",
        cpid: 0,
        coupons: [],
        hasCoupons: false,

        ycoidList: [],
        ycoidIndex: 0,
        ycoid: 0,
        useYcoid: 0,
        hasYcoid: false,

        userBalance: 0,

        payType      : 1, 
        payTypeIndex : 0,
        payTypeList  : [{
            value: 1,
            text: "微信支付"
        }, {
            value: 2,
            text: "余额支付"
        }]
    },

    onShareAppMessage: null,

    getCartSettle: function () {
        let content = this;

        app.wxRequest("/commodity/settlement", {scids: content.data.goodsListId.join(",")}, function (res) {
            if (res.code == 1) {
                let coupons = res.data.coupons;
                let ycoid = res.data.ycoid;
                let couponsList = [];

                !!coupons && coupons.forEach(function (item) {
                    if (item.status == 1) {
                        item.textShow = item.dictValue2;
                        couponsList.push(item);
                    }
                });

                content.setData({
                    goodsList : res.data.list,
                    coupons : couponsList.length > 0 ? [{textShow: "不使用优惠券", cpid: ""}].concat(couponsList) : [{textShow: "无可用优惠券", cpid: ""}],
                    hasCoupons : couponsList.length > 0,
                    couponPrice : 0,
                    allGoodsPrice : res.data.sumOldDisPrice,
                    allGoodsAndYunPrice : res.data.sumPrice,
                    ycoid: res.data.ycoid,
                    useYcoid: 0,
                    ycoidList: ycoid > 0 ? [{textShow: "不使用薏米", ycoid: "0"}, {textShow: res.data.ycoid + "薏米", ycoid: res.data.ycoid}] : [{textShow: "没有薏米", ycoid: "0"}],
                    hasYcoid: ycoid > 0,
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
                    yunPrice: res.data.freight,
                    couponPrice: res.data.couponPrice,
                    useYcoid: res.data.useYcoid
                });
            }
        });
    },

    getUserBalance: function () {
        let content = this;

        app.wxRequest("/account/userAccountBalance", {}, function (res) {
            if (res.code == 1) {
                content.setData({
                    userBalance : res.data.balance
                });
            }
        });
    },

    bindPayTypeChange: function (e) {
        let index = e.detail.value;


        this.setData({
            payTypeIndex : index,
            payType : this.data.payTypeList[index].value
        });
    },

    bindCouponsChange: function (e) {
        let index = e.detail.value;

        if (index > 0) {
            let curCoupon = this.data.coupons[index];

            this.setData({
                couponsIndex : index,
                curCoupon : curCoupon,
                serviceType: 1,
                cpid: curCoupon.cpid
            });

            this.countCartSettle();

            if (this.data.ycoidIndex > 0) {
                this.setData({
                    ycoidIndex: 0
                });
            }
        }
    },

     bindYcoidChange: function (e) {
        let index = e.detail.value;

        if (index > 0) {
            let curYcoid = this.data.ycoidList[index];

            this.setData({
                ycoidIndex : index,
                serviceType: 2
            });

            this.countCartSettle();

            if (this.data.couponsIndex > 0) {
                this.setData({
                    cpid: "",
                    couponsIndex: 0
                });
            }
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

    createOrder: function () {
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

        if (content.data.payType == 2 && content.data.allGoodsAndYunPrice > content.data.userBalance) {
            wx.showModal({
                title: '提 示',
                content: '您的余额不足，请先去充值或者取消选择微信支付！',
                cancelText: "取消",
                confirmText: "去充值",
                success: function (res) {
                    if (res.confirm) {
                        app.redirect("/pages/user/recharge/index")
                    }
                }
            });
            return;
        }

        let postData = {
            expressName: content.data.curAddressData.linkMan,
            expressMobile: content.data.curAddressData.mobile,
            expressAddress: content.data.curAddressData.address,
            scids: content.data.goodsListId.join(","),
            payType: content.data.payType
        };

        if (content.data.serviceType == 1) {
            postData.serviceType = content.data.serviceType;
            postData.cpid = content.data.cpid;
        }
        else if (content.data.serviceType == 2) {
            postData.serviceType = content.data.serviceType;
        }

        app.wxRequest("/order/saveOrder", postData, function (res) {
            if (res.code == 1) {
                // 清空购物车数据
                app.clearCookie("shopCartInfo");

                app.wxPay(res.data.oid, "/pages/user/order-list/index");

                // 配置模板消息推送
                //let postJsonString = {};
                //postJsonString.keyword1 = { value: utils.formatTime(new Date()), color: '#173177' }
                //postJsonString.keyword2 = { value: content.data.allGoodsAndYunPrice + '元', color: '#173177' }
                //postJsonString.keyword3 = { value: res.data.data.orderNumber, color: '#173177' }
                //postJsonString.keyword4 = { value: '订单已关闭', color: '#173177' }
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
                }
            else {
                app.showToast("创建订单失败", "none");
            }
        }, function () {
            app.showToast("创建订单错误", "none");
        });
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
    }
});
