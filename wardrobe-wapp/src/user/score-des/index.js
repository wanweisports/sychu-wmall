const app = getApp();

Page({
    data: {
        userInfo : {}

    },
    onShareAppMessage: null,
    onShow: function () {
        this.getUserInfo();
    },
    getUserInfo: function () {
        let content = this;

        app.wxRequest("/user/userCenter", {}, function (res) {
            content.setData({
                userInfo : res.data
            });
        });
    },
});
