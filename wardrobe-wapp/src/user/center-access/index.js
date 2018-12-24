const app = getApp();

Page({
    data: {
        wardrobeInfo: {},
        locksList: [],
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

                content.getWardrobeOrderLocks();
            }
        });
    },
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
                    app.redirect("/pages/user/reserve/index", "redirectTo");
                }
            }
        });
    },
    openLock: function (e) {
        app.wxRequest("/relay/openLock", {lockId: e.currentTarget.dataset.id}, function (res) {
            if (res.code == 1) {
                app.showToast("开柜成功", "success");
            }
        });
    },
    onShow: function () {
        this.getWardrobeOrder();
        
        this.getWardrobeList();
        app.setCookie('syc_leave', "/pages/user/center-access/index");

        this.yesUserStatus();
    },
    toCartOrder: function () {
        app.redirect("/pages/goods/reserve-settle/index?did=" + this.data.wardrobeInfo.did, "navigateTo");
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

    onHide: function () {
        app.clearCookie('syc_leave');
    },
    onUnload: function () {
        app.clearCookie('syc_leave');
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
