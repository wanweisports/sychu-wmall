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


        var cc = {"code":"1","message":"操作成功","data":{"sumOldPrice":2279.0,"code":"1","sumPrice":1823.2,"data":{"commoditys":[{"rfidEpc":"02 12 24 20","price":799.0,"dbid":82.0,"resourcePath":"https://oss-admin.oss-cn-beijing.aliyuncs.com/img/0e27323d529446718b2cc0f953aef614.jpg","name":"柜子1号","count":1.0,"commName":"麂皮连衣裙","couPrice":799.0,"cid":336.0},{"rfidEpc":"02 12 20 50","price":1480.0,"dbid":94.0,"resourcePath":"https://oss-admin.oss-cn-beijing.aliyuncs.com/img/7ee835e407894240b248973836205d35_YS.jpg","name":"柜子4号","count":1.0,"commName":"材质优选|开叉喇叭裤","couPrice":1480.0,"cid":102.0}],"sumPrice":2279.0},"cpid":"0","freight":0.0,"discount":0.8,"message":"操作成功","useBalance":"2","coupons":[{"dictValue":"满1000元可使用","serviceType":100008,"uid":84,"dictValue2":"满1000减100","createTime":"2018-10-15 12:05:50","cpid":6,"couponPrice":100.00,"updateTime":null,"fullPrice":1000.00,"dueTime":"2018-12-14","status":"1"},{"dictValue":"满1000元可使用","serviceType":100008,"uid":84,"dictValue2":"满1000减100","createTime":"2018-10-15 12:05:50","cpid":8,"couponPrice":100.00,"updateTime":null,"fullPrice":1000.00,"dueTime":"2018-12-14","status":"1"},{"dictValue":"满2500元可使用","serviceType":100008,"uid":84,"dictValue2":"满2500减300","createTime":"2018-10-15 12:05:50","cpid":7,"couponPrice":300.00,"updateTime":null,"fullPrice":2500.00,"dueTime":"2018-12-14","status":"2"}],"ycoid":0,"couponPrice":0.0,"userDiscountSubPrice":455.8,"useYcoid":"0","sumOldDisPrice":1823.2}}
        

        app.wxRequest("/rfid/readRfid", {did: this.data.did}, function (res) {
            if (res.code == 1) {
                let coupons = res.data.coupons;
                let ycoid = res.data.ycoid;
                let goodsList = res.data.commoditys;
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
                    goodsList : res.data.commoditys,
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

    onLoad: function (e) {
        let content = this;

        content.setData({
            did : e.did
        });

        content.getCartSettle(e.did);
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
                app.wxPay(res.data.oid, "/pages/user/order-list/index");
            }
            else {
                app.showToast("创建订单失败", "none");
            }
        }, function () {
            app.showToast("创建订单错误", "none");
        });

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
    }
});
