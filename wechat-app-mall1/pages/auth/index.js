var app = getApp()

Page({
    data: {
        isSupported: wx.canIUse('button.open-type.getUserInfo'),
        loadingHidden: false
    },
    onLoad: function() {
        var content = this
        setTimeout(function () {
            content.setData({
                loadingHidden: true
            })

            wx.redirectTo({
                url: '/pages/index/index'
            })
        }, 1500)
    },
    onGetUserInfo: function (event) {

    }
})
