var app = getApp();

Page({
    data: {
        isSupported: wx.canIUse('button.open-type.getUserInfo')
    },
    onGetUserInfo: function (event) {
        console.log(event);
        if (event.type.toLowerCase() == "getuserinfo" && event.detail.errMsg.toLowerCase() == "getuserinfo:ok") {
            // wx.switchTab({
            //     url: '/pages/index/index'
            // });

            app.toLogin();
        }
    }
});
