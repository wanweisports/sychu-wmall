//index.js
//获取应用实例
const app = getApp();
const utils = require("../../../utils/util.js");

Page({
    data: {
        sumPrice: 0,
        goodsList:[],
        wardrobeInfo: {},
        locksList: [],
        hasWardrobeInfo: false
    },

    onShareAppMessage: null,

    getWardrobeOrderLocks: function () {
        let content = this;

        app.wxRequest("/order/nowCanOpenLock", {did: content.data.wardrobeInfo.did}, function (res) {
            if (res.code == 1) {
                let data = res.data.locks;

                if (data.length > 0) {
                    content.setData({
                        locksList: data
                    });
                }
                else {
                    content.setData({
                        locksList: null
                    });
                }
            }
        });
    },

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
                    content.getWardrobeOrderLocks();
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
                app.wxRequest("/relay/openDoor", {did: 6}, function (res) {
                    if (res.code == 1) {
                        app.showToast("扫码开门成功", "success");
                        content.yesUserStatus();
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

    readCartSettle: function (callback) {
        let content = this;

        app.wxRequest("/rfid/readRfid", {did: this.data.wardrobeInfo.did}, function (res) {
            if (res.code == 1) {
                let goodsList = res.data.data.commoditys;
                if (!goodsList || goodsList.length <= 0) {
                    return callback(true);
                }
            }

            callback(false);
        }, function () {
            callback(false);
        });
    },

    leave: function () {
        let content = this;

        wx.showModal({
            title: "提 示",
            content: "开门前请将未结算的衣服放回衣柜，确认离开吗？",
            success: function (res) {
                if (res.confirm) {
                    content.readCartSettle(function (status) {
                        if (!status) {
                            return app.showToast("请将未结算的衣服放回衣柜中！");
                        }

                        app.wxRequest("/relay/closeDoor", {did: 6}, function (res) {
                            if (res.code == 1) {
                                app.showToast("开门成功", "success");
                                content.noUserStatus();

                                app.redirect("/pages/index/index", "switchTab");
                            }
                        });
                    });
                }
            }
        });
    },
    yesUserStatus: function () {
        let content = this;

        app.updateUserStatus(1, function (status) {
            if (status) {
                app.getCookie("syc_fitting", "yes");
            }
        });
    },
    noUserStatus: function () {
        let content = this;

        app.updateUserStatus(2, function (status) {
            if (status) {
                app.getCookie("syc_fitting", "no");
            }
        });
    }
});
