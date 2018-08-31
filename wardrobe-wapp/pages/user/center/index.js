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
        var that = this;

        app.wxRequest(
            "/user/userCenter",
            {},
            function (res) {
                that.setData({
                    userInfo : res.data
                });
            }
        );
    },
    onLoad: function () {},
    onShow: function () {
        this.getUserInfo();
    },
    inviteFriends : function () {
        wx.showModal({
            title: '邀请好友',
            content: '本系统基于开源小程序商城系统 https://github.com/EastWorld/wechat-app-mall 搭建，祝大家使用愉快！',
            showCancel: false
        })
    },
    recharge: function () {
        wx.navigateTo({
            url: "/pages/user/recharge/index"
        });
    },
    withdraw: function () {
        wx.navigateTo({
            url: "/pages/withdraw/index"
        })
    }
});
