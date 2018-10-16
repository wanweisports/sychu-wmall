//index.js
//获取应用实例
const app = getApp();

Page({
    data: {
        indicatorDots: true,
        autoplay: true,
        interval: 3000,
        duration: 1000,
        loadingHidden: false,
        swiperCurrent: 0,
        goods: [],

        banners: [],
        newlyGoods: [],
        hotGoods: []
    },
    onLoad: function() {
        this.getBannerGoodsList();
        this.getHotGoodsList();
        this.getNewlyGoodsList();
    },
    getBannerGoodsList: function () {
        let content = this;

        app.wxRequest("/commodity/commodityBanners", {}, function (res) {
            if (res.code == 1) {
                content.setData({
                    banners: res.data.list
                });
            }
        });
    },
    getNewlyGoodsList: function () {
        let content = this;

        app.wxRequest("/commodity/index", {newly : "1"}, function (res) {
            if (res.code == 1) {
                content.setData({
                    newlyGoods: res.data.list
                });
            }
        });
    },
    getHotGoodsList: function () {
        let content = this;

        app.wxRequest("/commodity/index", {hot : "1"}, function (res) {
            if (res.code == 1) {
                content.setData({
                    hotGoods: res.data.list
                });
            }
        });
    },
    //事件处理函数
    swiperchange: function (e) {
        this.setData({
            swiperCurrent: e.detail.current
        })
    },
    toDetailsTap: function (e) {
        app.redirect("/pages/goods/details/index?id=" + e.currentTarget.dataset.id, "navigateTo");
    },
    tapBanner: function(e) {
        if (e.currentTarget.dataset.id != 0) {
            app.redirect("/pages/goods/details/index?id=" + e.currentTarget.dataset.id, "navigateTo");
        }
    },
    onShareAppMessage: function () {
        app.onShareAppMessage();
    }
});
