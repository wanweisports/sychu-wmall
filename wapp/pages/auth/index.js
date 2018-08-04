var log = require('../../utils/log.js')
var app = getApp()

Page({
    data: {
        isSupported: wx.canIUse('button.open-type.getUserInfo'),
        loadingHidden: false
    },
    onLoad: function() {
        log.log('[P]授权登录页面加载')

        var content = this
        setTimeout(function () {
            content.setData({
                loadingHidden: true
            })

            wx.redirectTo({
                url: '/pages/user/complete'
            })
        }, 1500)
    },
    onGetUserInfo: function (event) {
        var userInfo = app.onGetUserInfo(event);

        if (userInfo) {
            log.log('[D]用户授权登录信息：' + JSON.stringify(app.onGetUserInfo(event)));

            this.setData({
                userInfo: app.onGetUserInfo(event)
            });
        }
        else {
            log.log('[D]用户授权登录信息：false');
        }
        
    }
})
