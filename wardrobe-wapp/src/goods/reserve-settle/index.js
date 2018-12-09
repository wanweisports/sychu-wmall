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
        serviceType: 2,
        cpid: 0,
        coupons: [],
        hasCoupons: false,

        ycoidList: [],
        ycoidIndex: 0,
        ycoid: 0,
        useYcoid: 0,
        hasYcoid: false,

        userInfo: {},

        did: "",
        dbids: [],

        userBalance: 0,

        payType      : 1, 
        payTypeIndex : 0,
        payTypeList  : [{
            value: 1,
            text: "微信支付"
        }, {
            value: 2,
            text: "余额支付"
        }],
        stopTimer: false
    },

    onShareAppMessage: null,
    getCartSettle: function () {
        let content = this;

        app.wxRequest("/rfid/readRfid", {did: this.data.did}, function (res) {
            if (res.code == 1) {
                let coupons = res.data.coupons;
                let ycoid = res.data.ycoid;
                let goodsList = res.data.data.commoditys;
                let couponsList = [];
                let dbids = [];

                !!coupons && coupons.forEach(function (item) {
                    if (item.status == 1) {
                        item.textShow = item.dictValue;
                        couponsList.push(item);
                    }
                });

                !!goodsList && goodsList.forEach(function (item) {
                    dbids.push(item.dbid);
                });

                content.setData({
                    dbids : dbids.join(","),
                    goodsList : goodsList,
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

                let _t = setTimeout(function () {
                    content.readCartSettle();
                    clearTimeout(_t);
                }, 5000);
            }
        });
    },

    readCartSettle: function () {
        let content = this;

        if (this.data.stopTimer) {
            return;
        }

        app.wxRequest("/rfid/readRfid", {did: this.data.did}, function (res) {
            if (res.code == 1) {
                let goodsList = res.data.data.commoditys;
                let dbids = [];

                !!goodsList && goodsList.forEach(function (item) {
                    dbids.push(item.dbid);
                });

                content.setData({
                    dbids : dbids.join(","),
                    goodsList : goodsList
                });
                content.countCartSettle();

                let _t = setTimeout(function () {
                    content.readCartSettle();
                    clearTimeout(_t);
                }, 5000);
            }
        }, null, null, {isLoading: false});
    },

    onHide: function () {
        this.setData({
            stopTimer: true
        });
    },
    onUnload: function () {
        this.setData({
            stopTimer: true
        });
    },

    countCartSettle: function () {
        let content = this;

        app.wxRequest("/commodity/settlementRfidCount", {
            dbids: content.data.dbids,
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

    bindPayTypeChange: function (e) {
        let index = e.detail.value;


        this.setData({
            payTypeIndex : index,
            payType : this.data.payTypeList[index].value
        });
    },

     bindYcoidChange: function (e) {
        let curYcoid = this.data.ycoidList[e.detail.value]; 

        this.setData({
            ycoidIndex : e.detail.value,
            serviceType: 2
        });

        this.countCartSettle();
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

    onLoad: function (e) {
        let content = this;

        content.setData({
            did : e.did
        });

        content.getCartSettle(e.did);
        content.getUserBalance();
    },

    createOrder:function () {
        let content = this;

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
            dbids: content.data.dbids,
            payType: content.data.payType
        };

        if (content.data.serviceType == 1) {
            postData.serviceType = content.data.serviceType;
            postData.cpid = content.data.cpid;
        }
        else if (content.data.serviceType == 2) {
            postData.serviceType = content.data.serviceType;
        }

        app.wxRequest("/order/saveRfidOrder", postData, function (res) {
            if (res.code == 1) {
                app.wxPay(res.data.oid, "/pages/user/center-access/index");
            }
            else {
                app.showToast("创建订单失败", "none");
            }
        }, function () {
            app.showToast("创建订单错误", "none");
        });
    }
});
