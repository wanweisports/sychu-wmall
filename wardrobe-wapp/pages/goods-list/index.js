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
            url: "/pages/goods-details/index?id=" + e.currentTarget.dataset.id
        })
    },
    scroll: function(e) {
        //  console.log(e) ;
        var that = this,
        scrollTop = that.data.scrollTop;
        
        that.setData({
            scrollTop: e.detail.scrollTop
        })
        // console.log('e.detail.scrollTop:'+e.detail.scrollTop) ;
        // console.log('scrollTop:'+scrollTop)
    },
    onLoad: function() {
        var that = this;

        wx.setNavigationBarTitle({
            title: wx.getStorageSync('mallName')
        });

        that.getGoodsList(0);
    },

    getGoodsList: function (categoryId) {
        if (categoryId == 0) {
            categoryId = "";
        }

        console.log(categoryId);
        var that = this;

        wx.request({
            url: app.config.getApiHost() + '/' + app.globalData.subDomain + '/shop/goods/list',
            data: {
                categoryId: categoryId
            },
            success: function(res) {
                that.setData({
                    goods: [],
                    loadingMoreHidden: true
                });
                var goods = [];
                if (res.data.code != 0 || res.data.data.length == 0) {
                    that.setData({
                        loadingMoreHidden: false,
                    });
                    return;
                }
                for (var i = 0; i < res.data.data.length; i++) {
                    goods.push(res.data.data[i]);
                }
                that.setData({
                    goods: goods,
                });
            }
        })
    },
    onShareAppMessage: function() {
        return {
            title: wx.getStorageSync('mallName') + '——' + app.globalData.shareProfile,
            path: '/pages/index/index',
            success: function(res) {
                // 转发成功
            },
            fail: function(res) {
                // 转发失败
            }
        }
    }
})
