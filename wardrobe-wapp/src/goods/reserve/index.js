//index.js
//获取应用实例
const app = getApp();
const utils = require("../../../utils/util.js");

Page({
    data: {
        goodsListId: [],
        goodsList:[],
        allGoodsPrice:0,

        reserveDateList: [],
        reserveDateIndex: 0,
        reserveDateValue: "",

        reserveTimeStartList: [],
        reserveTimeStartIndex: [0, 0],
        reserveTimeStartValue: "",

        reserveTimeEndList: [],
        reserveTimeEndIndex: [0, 0],
        reserveTimeEndValue: ""
    },
    onShareAppMessage: null,
    initDate: function (startTime, endTime) {
        let today = new Date();

        let reserveDateList = [];
        for (let rl = 0; rl < 14; rl++) {
            let day = new Date(today.getTime() + (rl + 1) * 24 * 60 * 60 * 1000);
            let dayStr = utils.formatDate(day);

            reserveDateList.push({
                reserveDateValue: dayStr,
                reserveDateText: dayStr,
            });
        }

        this.setData({
            reserveDateList: reserveDateList,
            reserveDateIndex: 0,
        });

        this.setData({
            reserveDateValue: this.data.reserveDateList[this.data.reserveDateIndex].reserveDateValue,
            reserveDateText: this.data.reserveDateList[this.data.reserveDateIndex].reserveDateText,
        });

        let startHours = Number(startTime.split(":")[0]);
        let endHours = Number(endTime.split(":")[0]);
        let reserveTimeHoursList = [];
        for (let i = startHours; i < endHours; i++) {
            reserveTimeHoursList.push([i > 9 ? i : "0" + i]);
        }
        this.setData({
            reserveTimeStartList: [reserveTimeHoursList, ["00", "15", "30", "45"]],
            reserveTimeStartIndex: [0, 0],
            reserveTimeEndList: [reserveTimeHoursList, ["00", "15", "30", "45"]],
            reserveTimeEndIndex: [0, 0]
        });

        this.setData({
            reserveTimeStartValue: this.data.reserveTimeStartList[0][this.data.reserveTimeStartIndex[0]] + ":" + 
                this.data.reserveTimeStartList[1][this.data.reserveTimeStartIndex[1]],
            reserveTimeEndValue: this.data.reserveTimeEndList[0][this.data.reserveTimeEndIndex[0]] + ":" + 
                this.data.reserveTimeEndList[1][this.data.reserveTimeEndIndex[1]]
        });
    },

    bindReserveDateChange: function(e) {
        this.setData({
            reserveDateIndex: e.detail.value,
            reserveDateValue: this.data.reserveDateList[e.detail.value].reserveDateValue,
            reserveDateText: this.data.reserveDateList[e.detail.value].reserveDateText,
        })
    },

    bindStartTimeChange: function (e) {
        let reserveTimeStartIndex = e.detail.value;

        this.setData({
            reserveTimeStartIndex: reserveTimeStartIndex,
            reserveTimeStartValue: this.data.reserveTimeStartList[0][reserveTimeStartIndex[0]] + ":" + 
                this.data.reserveTimeStartList[1][reserveTimeStartIndex[1]]
        })
    },

    bindEndTimeChange: function (e) {
        let reserveTimeEndIndex = e.detail.value;

        this.setData({
            reserveTimeEndIndex: reserveTimeEndIndex,
            reserveTimeEndValue: this.data.reserveTimeEndList[0][reserveTimeEndIndex[0]] + ":" + 
                this.data.reserveTimeEndList[1][reserveTimeEndIndex[1]]
        })
    },

    getWardrobeList: function () {
        let content = this;

        app.wxRequest("/device/devices", {}, function (res) {
            let date = new Date();
            let end = date.getTime() + 2 * 24 * 60 * 60 * 1000;

            if (res.code == 1) {
                let wardrobe = res.data.list[0];
                content.setData({
                    wardrobeInfo: wardrobe
                });

                content.initDate(wardrobe.startTime, wardrobe.endTime);
            }
        });
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
            allGoodsPrice += carShopBean.couPrice * carShopBean.number;
            goodsListId.push(shopList[i].scid);
        }

        content.setData({
            goodsList: shopList,
            goodsListId: goodsListId,
            allGoodsPrice: allGoodsPrice
        });

        content.getWardrobeList();
    },

    createOrder: function () {
        let content = this;
        let reserveStartTime = content.data.reserveDateValue + " " + content.data.reserveTimeStartValue + ":00";
        let reserveEndTime = content.data.reserveDateValue + " " + content.data.reserveTimeEndValue + ":00";

        if (reserveStartTime >= reserveEndTime) {
            wx.showModal({
                title: '提 示',
                content: '开始时间必须小于结束时间！！',
                showCancel: false
            });
            return;
        }

        if ((new Date(reserveStartTime.replace(/\-/g, "/"))).getTime() <= (new Date()).getTime()) {
            wx.showModal({
                title: '提 示',
                content: '开始时间不能小于当前时间！！',
                showCancel: false
            });
            return;
        }

        if ((new Date(reserveEndTime.replace(/\-/g, "/"))).getTime() - (new Date(reserveStartTime.replace(/\-/g, "/"))).getTime() > (2 * 60 * 60 * 1000)) {
            wx.showModal({
                title: '提 示',
                content: '预约时长不能超过2小时！！',
                showCancel: false
            });
            return;
        }


        let postData = {
            reserveStartTime : reserveStartTime,
            reserveEndTime : reserveEndTime,
            did: content.data.wardrobeInfo.did,
            scids: content.data.goodsListId.join(",")
        };

        app.wxRequest("/order/saveReserveOrder", postData, function (res) {
            if (res.code == 1) {
                app.clearCookie("shopOrderInfo");
                app.redirect("/pages/user/reserve/index", "navigateTo");
            }
            else {
                app.showToast(res.message || "预约失败");
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
