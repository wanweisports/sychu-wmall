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

        banners: [],
        newlyGoods: [],
        hotGoods: [],

        bannerRedirect: false,
        bannerURL: "https://mp.weixin.qq.com/"
    },
    onShareAppMessage: function () {
        return app.onShareAppMessage({
            path: '/pages/index/index',
            title: app.getCookie("syc_appName") || "衣否",
            imgUrl: "/images/logo.jpg"
        });
    },
    onShow: function() {
        this.setData({
            bannerRedirect: false
        });

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
    addShopFavorite: function (e) {
        let content = this;
        let type = e.currentTarget.dataset.type;
        let index = e.currentTarget.dataset.index;
        let cid = e.currentTarget.dataset.id;

        app.wxRequest("/user/saveCollection", {cid: cid}, function (res) {
            if (res.code == 1) {
                if (type == "new") {
                    // content.data.newlyGoods[index].collection == 1;
                    // content.setData({
                    //     newlyGoods: content.data.newlyGoods
                    // });
                    content.getNewlyGoodsList();
                }
                if (type == "hot") {
                    // content.data.hotGoods[index].collection == 1;
                    // content.setData({
                    //     hotGoods: content.data.hotGoods
                    // });
                    content.getHotGoodsList();
                }
            }
        });
    },
    removeShopFavorite: function (e) {
        let content = this;
        let type = e.currentTarget.dataset.type;
        let index = e.currentTarget.dataset.index;
        let cid = e.currentTarget.dataset.id;

        app.wxRequest("/user/cancelCollection", {cid: cid}, function (res) {
            if (res.code == 1) {
                if (type == "new") {
                    content.getNewlyGoodsList();
                    // content.data.newlyGoods[index].collection == 2;
                    // content.setData({
                    //     newlyGoods: content.data.newlyGoods
                    // });
                }
                if (type == "hot") {
                    // content.data.hotGoods[index].collection == 2;
                    // content.setData({
                    //     hotGoods: content.data.hotGoods
                    // });
                    content.getHotGoodsList();
                }
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
        console.log(e);
        if (e.currentTarget.dataset.url) {
            this.setData({
                bannerRedirect: true,
                bannerURL: e.currentTarget.dataset.url
            });
        }
        else if (e.currentTarget.dataset.id > 0) {
            app.redirect("/pages/goods/details/index?id=" + e.currentTarget.dataset.id, "navigateTo");
        }
    },
    onShareAppMessage: function () {
        app.onShareAppMessage();
    }
});
