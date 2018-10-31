//index.js
//获取应用实例
const app = getApp();
const utils = require("../../../utils/util.js");

Page({
    data: {
        sumPrice: 0,
        goodsList:[],
        wardrobeInfo: {},
        hasWardrobeInfo: false
    },

    onShareAppMessage: null,

    getWardrobeOrder: function () {
        let content = this;

        app.wxRequest("/order/reserveOrderInfo", {}, function (res) {
            if (res.code == 1) {
                let data = res.data.list;

                if (data.length > 0) {
                    content.setData({
                        wardrobeInfo: data[0],
                        hasWardrobeInfo: true
                    });
                    content.getWardrobeOrderDetail(data[0].roid);
                }
                else {
                    content.setData({
                        hasWardrobeInfo: false
                    });
                }
            }
        });
    },

    getWardrobeOrderDetail: function (roid) {
        let content = this;

        app.wxRequest("/order/reserveOrderDetail", {roid: roid}, function (res) {
            if (res.code == 1) {
                content.setData({
                    goodsList: res.data.list,
                    sumPrice: res.data.sumPrice
                });
            }
        });
    },

    onLoad: function () {
        this.getWardrobeOrder();
    },

    scanOrder: function () {
        wx.scanCode({
            scanType: "qrCode",
            success: function (res) {
                app.wxRequest("/relay/openDoor", {}, function (res) {
                    if (res.code == 1) {
                        app.showToast("扫码开门成功", "success");
                    }
                });
            }
        });
    },

    cancelOrder: function () {
        let content = this;

        wx.showModal({
            title: '确定要取消该订单吗？',
            content: '',
            success: function (res) {
                if (res.confirm) {
                    app.wxRequest("/order/cancelReserveOrder", {roid: content.data.wardrobeInfo.roid}, function (res) {
                        if (res.code == 1) {
                            app.showToast("取消预约成功", "success");

                            content.getWardrobeOrder();
                        }
                        else {
                            app.showToast("取消预约失败", "none");
                        }
                    });
                }
            }
        });
    },

    toCartOrder: function () {
        app.redirect("/pages/goods/reserve-settle/index?did=" + this.data.wardrobeInfo.did, "navigateTo");
    },

    openLock: function (e) {
        app.wxRequest("/relay/openLock", {lockId: e.currentTarget.dataset.id}, function (res) {
            if (res.code == 1) {
                app.showToast("开柜成功", "success");
            }
        });
    },

    leave: function () {
        wx.showModal({
            title: "提 示",
            content: "您确定要离开吗？确认完后会立即开门",
            success: function (res) {
                if (res.confirm) {
                    app.wxRequest("/relay/closeDoor", {}, function (res) {
                        if (res.code == 1) {
                            app.showToast("开门成功", "success");
                        }
                    });
                }
            }
        });
    }
});
