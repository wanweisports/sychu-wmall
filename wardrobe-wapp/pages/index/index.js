//index.js
//获取应用实例
var app = getApp();

Page({
    data: {
        indicatorDots: true,
        autoplay: true,
        interval: 3000,
        duration: 1000,
        loadingHidden: false,
        swiperCurrent: 0,
        goods: [],
        newlyGoods: [],
        hotGoods: []
    },
    onReady: function () {
        wx.setNavigationBarTitle({
            title: wx.getStorageSync('mallName')
        });
    },
    onLoad: function() {
        var that = this;

        that.getBannerGoodsList();
        that.getHotGoodsList();
        that.getNewlyGoodsList();
    },
    getBannerGoodsList: function () {
        var that = this;

        that.setData({
            banners: [
                {businessId: 121, picUrl: "http://images2.moonbasa.com/ProductImg/1/1602/huge/033016222-010-01-H.jpg"},
                {businessId: 121, picUrl: "http://img005.hc360.cn/hb/MTQ2MzI1NjA4NzI3NTM4MDA1Mjg3Mg==.jpg"},
                {businessId: 121, picUrl: "http://pic.qiantucdn.com/58pic/18/48/86/562887145916b_1024.jpg"}
            ]
        });
    },
    getNewlyGoodsList: function () {
        var that = this;

        app.wxRequest(
            "/commodity/index",
            {
                newly : "1"
            },
            function (res) {
                if (res.code == 1) {
                    that.setData({
                        newlyGoods: res.data.list
                    });
                }
            }
        );
    },
    getHotGoodsList: function () {
        var that = this;

        app.wxRequest(
            "/commodity/index",
            {
                hot : "1"
            },
            function (res) {
                if (res.code == 1) {
                    that.setData({
                        hotGoods: res.data.list
                    });
                }
            }
        );
    },
    //事件处理函数
    swiperchange: function (e) {
        this.setData({
            swiperCurrent: e.detail.current
        })
    },
    toDetailsTap: function (e) {
        wx.navigateTo({
            url: "/pages/goods/details/index?id=" + e.currentTarget.dataset.id
        });
    },
    tapBanner: function(e) {
        if (e.currentTarget.dataset.id != 0) {
            wx.navigateTo({
                url: "/pages/goods/details/index?id=" + e.currentTarget.dataset.id
            })
        }
    }
});
