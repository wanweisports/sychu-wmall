const app = getApp();

Page({
    data: {
        isSupported: wx.canIUse('button.open-type.getUserInfo')
    },
    onShareAppMessage: null,
    onLoad: function () {
        //app.toLogin();
    },
    onGetUserInfo: function (event) {
        if (event.type.toLowerCase() == "getuserinfo" && event.detail.errMsg.toLowerCase() == "getuserinfo:ok") {
            app.globalData.sessionId = "";
            app.toLogin();
        }
        else {
            app.showToast("不支持的微信版本", "none");
        }
    }
});
