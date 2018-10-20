//index.js
//获取应用实例
const app = getApp();

Page({
    data: {
        lastPage: 0,
        currentPage: 1,
        goods: null
    },
    toDetailsTap: function (e) {
        let goodId = e.currentTarget.dataset.id;

        app.redirect("/pages/goods/details/index?id=" + goodId, "navigateTo");
    },
     onReachBottom: function () {
        if (this.data.currentPage >= this.data.lastPage) {
            return app.showToast("已经最后一页", "none");
        }

        this.getGoodsList(++this.data.currentPage);
    },
    onLoad: function() {
        this.getGoodsList(1);
    },

    getGoodsList: function (page) {
        let content = this;

        page = page || 1;

        app.wxRequest("/user/userCollections", {page: page}, function (res) {
            if (res.code == 1) {
                content.setData({
                    lastPage: res.data.lastPage,
                    currentPage: res.data.currentPage,
                    goods : res.data.list
                });
            }
            else {
                content.setData({
                    goods : null
                });
            }
        });
    }
});
