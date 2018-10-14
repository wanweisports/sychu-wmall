//index.js
//获取应用实例
const app = getApp();
const utils = require("../../../utils/util.js");

Page({
    data: {
        goodsListId: [],
        goodsList:[],
        allGoodsPrice:0,

        userInfo: {},

        reserveDate: [],
        reserveDateValue: "",
        reserveTime: [],
        reserveTimeStart: "",
        reserveTimeEnd: ""
    },

    getWardrobeList: function () {
        let content = this;

        app.wxRequest("/device/devices", {}, function (res) {
            let date = new Date();
            let end = date.getTime() + 7 * 24 * 60 * 60 * 1000;

            if (res.code == 1) {
                let wardrobe = res.data.list[0];
                content.setData({
                    wardrobeInfo: wardrobe,
                    reserveDate: [utils.formatDate(date), utils.formatDate(new Date(end))],
                    reserveTime: [wardrobe.startTime, wardrobe.endTime],
                    reserveDateValue: utils.formatDate(date),
                    reserveTimeStart: wardrobe.startTime + ":00",
                    reserveTimeEnd: wardrobe.startTime + ":00"
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

    bindDateChange: function (e) {
        this.setData({
            reserveDateValue: e.detail.value
        })
    },

    bindStartTimeChange: function (e) {
        this.setData({
            reserveTimeStart: e.detail.value
        })
    },

    bindEndTimeChange: function (e) {
        this.setData({
            reserveTimeEnd: e.detail.value
        })
    },

    onLoad: function () {
        let content = this;

        let shopList = [];
        let shopCarInfoMem = app.getCookie('shopOrderInfo');
        if (shopCarInfoMem && shopCarInfoMem.shopList) {
            shopList = shopCarInfoMem.shopList.filter(function (entity) {
                return entity.active;
            });
        }

        let allGoodsPrice = 0;
        let goodsListId = [];
        for (let i = 0; i < shopList.length; i++) {
            let carShopBean = shopList[i];
            allGoodsPrice += carShopBean.price * carShopBean.number;
            goodsListId.push(shopList[i].scid);
        }

        content.setData({
            goodsList: shopList,
            goodsListId: goodsListId,
            allGoodsPrice: allGoodsPrice
        });

        content.getUserInfo();
        content.getWardrobeList();
    },

    createOrder:function () {
        let content = this;
        let postData = {
            reserveStartTime : content.data.reserveDateValue + " " + content.data.reserveTimeStart + ":00",
            reserveEndTime : content.data.reserveDateValue + " " + content.data.reserveTimeEnd + ":00",
            did: content.data.wardrobeInfo.did,
            scids: content.data.goodsListId.join(",")
        };

        app.wxRequest("/order/saveReserveOrder", postData, function (res) {
            if (res.code == 1) {
                app.clearCookie("shopOrderInfo");
                app.redirect("/page/user/reserve/index");
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
