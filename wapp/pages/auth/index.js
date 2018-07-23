var log = require('../../utils/log.js')
var app = getApp()

Page({
    data: {
        isSupported: wx.canIUse('button.open-type.getUserInfo'),
        loadingHidden: false
    },
    onLoad: function() {
        log.log('The page auth load')

        var content = this
        setTimeout(function () {
                    content.setData({
                        loadingHidden: true
                    })
                }, 1500)
    },
    onGetUserInfo: function (event) {
        log.log(JSON.stringify(event));
        log.log(JSON.stringify(app.onGetUserInfo(event)));

        this.setData({
            userInfo: app.onGetUserInfo(event)
        })
    }
})
