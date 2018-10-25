const app = getApp();

Page({
    data: {
        balance: 0,
        freeze: 0,
        score: 0,
        score_sign_continuous: 0,

        userInfo: {}
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
        wx.showModal({
            content: '您的邀请码：' + this.data.userInfo.inviteCode || "66666",
            showCancel: false
        });
    },
    recharge: function () {
        app.redirect("/pages/user/recharge/index", "navigateTo");
    }
});
