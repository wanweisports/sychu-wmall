//index.js
//获取应用实例
const app = getApp();
const utils = require("../../../utils/util.js");

Page({
    data: {
        goodsList:[],
        wardrobeInfo: {},
        hasWardrobeInfo: false
    },

    getWardrobeOrder: function () {
        let content = this;

        app.wxRequest("/order/reserveOrderInfo", {}, function (res) {
            if (res.code == 1 && res.data.list > 0) {
                content.setData({
                    wardrobeInfo: res.data.list[0],
                    hasWardrobeInfo: true
                });
            }
        });
    },

    getWardrobeOrderDetail: function () {
        let content = this;

        app.wxRequest("/order/reserveOrderDetail", {}, function (res) {
            if (res.code == 1) {
                content.setData({
                    goodsList: res.data.list
                });
            }
        });
    },

    onLoad: function () {
        this.getWardrobeOrder();
        this.getWardrobeOrderDetail();
    },

    scanOrder:function () {
        wx.scanCode({
            scanType: "qrCode",
            success: function (res) {
                app.wxRequest("/relay/openDoor", {}, function (res) {
                    if (res.code == 1) {

                    }
                });
            }
        });
    }
});
