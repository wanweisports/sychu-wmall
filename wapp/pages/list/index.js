var app = getApp()
Page({
    data: {
        
    },
    onLoad: function(options) {

        var that = this
        
        that.setData({
            list: [{
                "id": "00001",
                "goodspics": "../../wemall/goods/images/yifu001.jpg",
                "bigname": "杭州",
                "country": "中国",
                "title": "定时达大所大大所大所多撒大所多",
                "ourprice": "12",
                "marketprice": "14"
            }, {
                "id": "00001",
                "goodspics": "../../wemall/goods/images/yifu001.jpg",
                "bigname": "杭州",
                "country": "中国",
                "title": "定时达大所大大所大所多撒大所多",
                "ourprice": "12",
                "marketprice": "14"
            }, {
                "id": "00001",
                "goodspics": "../../wemall/goods/images/yifu001.jpg",
                "bigname": "杭州",
                "country": "中国",
                "title": "定时达大所大大所大所多撒大所多",
                "ourprice": "12",
                "marketprice": "14"
            }, {
                "id": "00001",
                "goodspics": "../../wemall/goods/images/yifu001.jpg",
                "bigname": "杭州",
                "country": "中国",
                "title": "定时达大所大大所大所多撒大所多",
                "ourprice": "12",
                "marketprice": "14"
            }, {
                "id": "00001",
                "goodspics": "../../wemall/goods/images/yifu001.jpg",
                "bigname": "杭州",
                "country": "中国",
                "title": "定时达大所大大所大所多撒大所多",
                "ourprice": "12",
                "marketprice": "14"
            }]
        });
        // wx.request({
        //     url: '/wemall/goods/inqGoodsByTypeBrand?brand=' + options.brand + "&typeid=" + options.typeid,
        //     method: 'GET',
        //     data: {},
        //     header: {
        //         'Accept': 'application/json'
        //     },
        //     success: function(res) {
        //         that.setData({
        //             list: res.data.data
        //         });
        //     }
        // })
    }

})
