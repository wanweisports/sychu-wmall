const app = getApp();

Page({
    data: {
        balance: 0,
        freeze: 0,
        score: 0,
        score_sign_continuous: 0,

        userInfo: {}
    },
    onShareAppMessage: function (e) {
        if (e.from === 'menu') {
            return app.onShareAppMessage({
                path: '/pages/index/index',
                title: app.getCookie("syc_appName") || "衣否",
                imgUrl: "/images/logo.jpg"
            });
        }
        else if (e.from === 'button') {
            return app.onShareAppMessage({
                path: '/pages/index/index?inviteCode=' + this.data.userInfo.inviteCode,
                title: app.getCookie("syc_appName") || "衣否",
                imgUrl: "/images/logo.jpg"
            });
        }
    },
    scanCode: function () {
        wx.scanCode({
            onlyFromCamera: true,
            success: function (res) {
                if (res.result.indexOf("/relay/openDoor") > -1) {
                    app.wxRequest(res.result, {}, function (res) {
                        if (res.code == 1) {
                            app.showToast("扫码开门成功");
                            app.redirect("/pages/user/center-access/index", "navigateTo");
                        }
                        else {
                            app.showToast(res.message || "扫码开门失败");
                        }
                    });
                }
            }
        });
    },
    getUserInfo: function () {
        let content = this;

        app.wxRequest("/user/userCenter", {}, function (res) {
            content.setData({
                userInfo : res.data
            });
        });
    },
    onShow: function () {
        this.getUserInfo();
    },
    inviteFriends : function () {
        //<button open-type="share" class="item-share">邀请好友</button>
        wx.showModal({
            content: ('您的邀请码：' + this.data.userInfo.inviteCode || "66666"),
            showCancel: false
        });
    },
    recharge: function () {
        app.redirect("/pages/user/recharge/index", "navigateTo");
    }
});
