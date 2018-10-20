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
    },

    getCartSettle: function () {
        let content = this;

        app.wxRequest("/rfid/readRfid", {did: this.data.did}, function (res) {
//             res = {
//     "code": "1",
//     "message": "操作成功",
//     "data": {
// "discount": 0.95,
//         "sumPrice": 2202.22,          
//         "sumOldPrice": 2512.33,      
//         "sumOldDisPrice": 2312.33,    
//         "userDiscountSubPrice":200,   
//         "couponPrice": 0,             
// "useYcoid": 0,                         
// "ycoid": 500,        
//         "commoditys": [{
//             "price": 178.2,
//             "resourcePath": "https://oss-admin.oss-cn-b6c5.jpg",
//             "name": "柜子2号",
//             "commName": "惠维2018秋女装 ",
//             "cid": 19,
// "dbid": 1
//         }, {
//             "price": 59,
//             "resourcePath": "https://oss157aceb3a2b7a60.jpg",
//             "name": "柜子2号",
//             "commName": "洛蔓希性感透视装女士内衣白上衣+黑裙",
//             "cid": 17,
//             "dbid": 2
//         }, {
//             "price": 100.99,
//             "resourcePath": "https://oss2fa.jpg",
//             "name": "柜子1号",
//             "commName": "商品3",
//             "cid": 14,
// "dbid": 3
//         }],
//         "coupons": [{    
//                 "dictValue": "满1000元可使用",
//                 "dictValue2": "满1000元减100",
//                 "fullPrice":1000,    
//                 "cpid": 1,
//                 "status": "1",  
//                 "couponPrice": 100,
//                 "dueTime": "2018-09-22 12:30:00" 
//             },
//             {
//                 "dictValue": "满2500元可使用",
//                 "fullPrice":2500,
//                 "cpid": 2,
//                 "status": "1",
//                 "couponPrice": 300,
//                 "dueTime": "2018-09-22 10:50:00"
//             }
//         ]
//     }
// }


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

        let postData = {
            dbids: content.data.dbids,
            serviceType: content.data.serviceType,
            cpid: content.data.cpid
        };

        app.wxRequest("/order/saveRfidOrder", postData, function (res) {
            if (res.code == 1) {
                app.wxPay(res.data.oid, "/pages/user/order-list/index");
            }

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
    }
});
