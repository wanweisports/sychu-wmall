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
        // loading
        userInfo: {},
        selectCurrent: 0,
        goods: [],
        scrollTop: "0",
        loadingMoreHidden: true
    },
    toDetailsTap: function(e) {
        wx.navigateTo({
            url: "/pages/goods/details/index?id=" + e.currentTarget.dataset.id
        })
    },
    scroll: function(e) {
        console.log(e);

        var that = this;
        var scrollTop = that.data.scrollTop;
        
        that.setData({
            scrollTop: e.detail.scrollTop
        });
    },
    onLoad: function() {
        var that = this;

        that.getGoodsList();
    },

    getGoodsList: function () {
        var that = this;

        app.wxRequest("/user/userCollections", {}, function (res) {
            if (res.code == 1) {
              that.setData({
                goods: res.data.list
              });
            }
          }
        );
    }
});
