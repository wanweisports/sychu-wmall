var app = getApp()

Page({
    data: {
        isSupported: wx.canIUse('button.open-type.getUserInfo'),
    },
    onLoad: function() {
    },
    onGetUserInfo: function (event) {
        if (event.type.toLowerCase() == "getuserinfo" && event.detail.errMsg.toLowerCase() == "getuserinfo:ok") {
            wx.switchTab({
                url: '/pages/index/index'
            });
        }
    }
})
