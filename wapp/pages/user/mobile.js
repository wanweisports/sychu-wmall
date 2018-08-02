var log = require('../../utils/log.js')
var app = getApp()

Page({
    data: {
        loadingHidden: false
    },
    onLoad: function() {
        log.log('The page auth load')

        var content = this
        //调用应用实例的方法获取全局数据
        app.getUserInfo(function(userInfo) {
            if (userInfo != null) {
                wx.reLaunch({     //跳转至指定页面并关闭其他打开的所有页面（这个最好用在返回至首页的的时候）
                    url:'/pages/index/index'
                })
            } else {
                //更新数据
                setTimeout(function () {
                    content.setData({
                        loadingHidden: true
                    })
                }, 1500)
            }
        })
    }
})
