var log = require('../../utils/log.js')
var app = getApp()

Page({
    data: {
        indicatorDots: true,
        vertical: false,
        autoplay: true,
        interval: 3000,
        duration: 1000,
        loadingHidden: false
    },
    //事件处理函数
    swiperchange: function(e) {
        //console.log(e.detail.current)
    },
    onLoad: function() {
        log.log('The page index load')

        var content = this
        //调用应用实例的方法获取全局数据
        app.getUserInfo(function(userInfo) {
            //更新数据
            content.setData({
                userInfo: userInfo
            })
        })

        //sliderList
        content.setData({
            images: [{
                "picurl": "../../wemall/slider/images/banner001.jpg"
            }, {
                "picurl": "../../wemall/slider/images/banner002.jpg"
            }, {
                "picurl": "../../wemall/slider/images/banner003.jpg"
            }]
        })
        // wx.request({
        //     url: 'wemall/slider/list',
        //     method: 'GET',
        //     data: {},
        //     header: {
        //         'Accept': 'application/json'
        //     },
        //     success: function(res) {
        //         that.setData({
        //             images: res.data
        //         })
        //     }
        // })

        //venuesList
        content.setData({
            venuesItems: [{
                "id": "000001",
                "smallpic": "../../wemall/goods/images/yifu001.jpg"
            }, {
                "id": "000002",
                "smallpic": "../../wemall/goods/images/yifu002.jpg"
            }, {
                "id": "000003",
                "smallpic": "../../wemall/goods/images/yifu003.jpg"
            }, {
                "id": "000004",
                "smallpic": "../../wemall/goods/images/yifu002.jpg"
            }, {
                "id": "000005",
                "smallpic": "../../wemall/goods/images/yifu001.jpg"
            }, {
                "id": "000006",
                "smallpic": "../../wemall/goods/images/yifu003.jpg"
            }]
        })
        setTimeout(function () {
            content.setData({
                loadingHidden: true
            })
        }, 1500)
        // wx.request({
        //     url: 'wemall/venues/venuesList',
        //     method: 'GET',
        //     data: {},
        //     header: {
        //         'Accept': 'application/json'
        //     },
        //     success: function(res) {
        //         that.setData({
        //             venuesItems: res.data.data
        //         })
        //         setTimeout(function () {
        //             that.setData({
        //                 loadingHidden: true
        //             })
        //         }, 1500)
        //     }
        // })

        //choiceList
        content.setData({
            choiceItems: [{
                "id": "000001",
                "goodspics": "../../wemall/goods/images/yifu001.jpg",
                "title": "美女"
            }, {
                "id": "000002",
                "goodspics": "../../wemall/goods/images/yifu002.jpg",
                "title": "美女"
            }, {
                "id": "000003",
                "goodspics": "../../wemall/goods/images/yifu003.jpg",
                "title": "美女"
            }, {
                "id": "000004",
                "goodspics": "../../wemall/goods/images/yifu002.jpg",
                "title": "美女"
            }, {
                "id": "000005",
                "goodspics": "../../wemall/goods/images/yifu003.jpg",
                "title": "美女"
            }, {
                "id": "000006",
                "goodspics": "../../wemall/goods/images/yifu001.jpg",
                "title": "美女"
            }]
        })
        setTimeout(function () {
            content.setData({
                loadingHidden: true
            })
        }, 1500)
        // wx.request({
        //     url: 'http://huanqiuxiaozhen.com/wemall/goods/choiceList',
        //     method: 'GET',
        //     data: {},
        //     header: {
        //         'Accept': 'application/json'
        //     },
        //     success: function(res) {
        //         that.setData({
        //             choiceItems: res.data.data.dataList
        //         })
        //         setTimeout(function () {
        //             that.setData({
        //                 loadingHidden: true
        //             })
        //         }, 1500)
        //     }
        // })

    }
})
