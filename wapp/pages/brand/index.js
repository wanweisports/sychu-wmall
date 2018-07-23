var app = getApp()
Page({
    data: {
        
    },
    onLoad: function(options) {

        var that = this
        
        that.setData({
            brandList: {
                brand: [{
                    "id": "00001",
                    "typeid": "0000001",
                    "pic": "../../wemall/goods/images/yifu001.jpg",
                    "logo": "../../wemall/goods/images/yifu001.jpg",
                    "chinesename": "中国名字",
                    "brief": "定时达大所大大所大所多撒大所多",
                    "minprice": "12.00"
                }, {
                    "id": "00001",
                    "typeid": "0000001",
                    "pic": "../../wemall/goods/images/yifu001.jpg",
                    "logo": "../../wemall/slider/images/yifu001.jpg",
                    "chinesename": "中国名字",
                    "brief": "定时达大所大大所大所多撒大所多",
                    "minprice": "12.00"
                }, {
                    "id": "00001",
                    "typeid": "0000001",
                    "pic": "../../wemall/goods/images/yifu001.jpg",
                    "logo": "../../wemall/goods/images/yifu001.jpg",
                    "chinesename": "中国名字",
                    "brief": "定时达大所大大所大所多撒大所多",
                    "minprice": "12.00"
                }, {
                    "id": "00001",
                    "typeid": "0000001",
                    "pic": "../../wemall/goods/images/yifu001.jpg",
                    "logo": "../../wemall/goods/images/yifu001.jpg",
                    "chinesename": "中国名字",
                    "brief": "定时达大所大大所大所多撒大所多",
                    "minprice": "12.00"
                }]
            }
        });
        // wx.request({
        //     url: '/wemall/venues/getBrandAndType?id=' + options.id,
        //     method: 'GET',
        //     data: {},
        //     header: {
        //         'Accept': 'application/json'
        //     },
        //     success: function(res) {
        //         that.setData({
        //             brandList: res.data.data
        //         });
        //     }
        // })
    }

})
