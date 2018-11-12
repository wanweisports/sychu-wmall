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
        // loading
        userInfo: {},
        selectCurrent: 0,
        goods: [],
        scrollTop: "0",
        loadingMoreHidden: true,

        lastPage: 0,
        currentPage: 1,

        // 风格
        styleValue     : "",
        styleIndex     : 0,
        styleList      : [],

        // 品类
        categoryValue  : "",
        categoryIndex  : 0,
        categoryList   : [],

        // 材质
        materialValue  : "",
        materialIndex  : 0,
        materialList   : [],

        // 显示
        isShowFilter   : false
    },
    onShareAppMessage: function () {
        return app.onShareAppMessage({
            path: '/pages/index/index',
            title: app.getCookie("syc_appName") || "衣否",
            imgUrl: "/images/logo.jpg"
        });
    },
    getStyleList: function () {
        let content = this;

        app.wxRequest("/dict/getDicts", {
            dictName: "COMM_STYLE"
        }, function (res) {
            if (res.code == 1) {
                content.setData({
                    styleList: res.data.dicts
                });
            }
            else {
                app.showToast(res.message || "获取字典失败");
            }
        });
    },
    bindStyleTap: function (e) {
        let data = e.currentTarget.dataset;
        let current = this.data.styleList[data.index];

        current.selected = !current.selected;

        this.setData({
            styleIndex : data.index,
            styleValue : data.id,
            styleList  : this.data.styleList
        });
    },
    getCategoryList: function () {
        let content = this;

        app.wxRequest("/dict/getDicts", {
            dictName: "COMM_CATEGORY"
        }, function (res) {
            if (res.code == 1) {
                content.setData({
                    categoryList: res.data.dicts
                });
            }
            else {
                app.showToast(res.message || "获取字典失败");
            }
        });
    },
    bindCategoryTap: function (e) {
        let data = e.currentTarget.dataset;

        let current = this.data.categoryList[data.index];

        current.selected = !current.selected;

        this.setData({
            categoryIndex : data.index,
            categoryValue : data.id,
            categoryList  : this.data.categoryList
        });
    },
    getMaterialList: function () {
        let content = this;

        app.wxRequest("/dict/getDicts", {
            dictName: "COMM_MATERIAL"
        }, function (res) {
            if (res.code == 1) {
                content.setData({
                    materialList: res.data.dicts
                });
            }
            else {
                app.showToast(res.message || "获取字典失败");
            }
        });
    },
    bindMaterialTap: function (e) {
        let data = e.currentTarget.dataset;

        let current = this.data.materialList[data.index];

        current.selected = !current.selected;

        this.setData({
            materialIndex : data.index,
            materialValue : data.id,
            materialList  : this.data.materialList
        });
    },
    getSelectedItems: function () {
        var styleIds = [];
        for (var sIndex = 0; sIndex < this.data.styleList.length; sIndex++) {
            if (this.data.styleList[sIndex].selected) {
                styleIds.push(this.data.styleList[sIndex].dictId);
            }
        }

        var categoryIds = [];
        for (var cIndex = 0; cIndex < this.data.categoryList.length; cIndex++) {
            if (this.data.categoryList[cIndex].selected) {
                categoryIds.push(this.data.categoryList[cIndex].dictId);
            }
        }
        var materialIds = [];
        for (var mIndex = 0; mIndex < this.data.materialList.length; mIndex++) {
            if (this.data.materialList[mIndex].selected) {
                materialIds.push(this.data.materialList[mIndex].dictId);
            }
        }

        return {
            category : categoryIds.length > 0 ? "," + categoryIds.join(",|,") + "," : "",
            style    : styleIds.length > 0 ? "," + styleIds.join(",|,") + "," : "",
            material : materialIds.length > 0 ? "," + materialIds.join(",|,") + "," : ""
        };
    },
    clearSelectedItems: function () {
        for (var sIndex = 0; sIndex < this.data.styleList.length; sIndex++) {
            this.data.styleList[sIndex].selected = false;
        }

        for (var cIndex = 0; cIndex < this.data.categoryList.length; cIndex++) {
            this.data.categoryList[cIndex].selected = false;
        }

        for (var mIndex = 0; mIndex < this.data.materialList.length; mIndex++) {
            this.data.materialList[mIndex].selected = false;
        }

        this.setData({
            styleList    : this.data.styleList,
            categoryList : this.data.categoryList,
            materialList : this.data.materialList
        });
    },

    bindConfirmTap: function () {
        this.getGoodsList(1);
        this.setData({
            isShowFilter: false
        });
    },
    bindResetTap: function () {
        this.clearSelectedItems();
        this.getGoodsList(1);
        this.setData({
            isShowFilter: false
        });
    },
    bindFilterTap: function () {
        this.setData({
            isShowFilter: true
        });
    },

    toDetailsTap: function(e) {
        app.redirect("/pages/goods/details/index?id=" + e.currentTarget.dataset.id, "navigateTo");
    },
    onReachBottom: function () {
        if (this.data.currentPage >= this.data.lastPage) {
            return app.showToast("已经最后一页", "none");
        }

        this.getGoodsList(++this.data.currentPage);
    },
    onLoad: function () {
        this.getCategoryList();
        this.getStyleList();
        this.getMaterialList();
        this.getGoodsList(1);
    },

    addShopFavorite: function (e) {
        let content = this;
        let cid = e.currentTarget.dataset.id;

        app.wxRequest("/user/saveCollection", {cid: cid}, function (res) {
            if (res.code == 1) {
                content.data.goods.forEach(function (item) {
                    if (item.cid == cid) {
                        item.collection = 1;
                    }
                });
                content.setData({
                    goods: content.data.goods
                });
            }
            else {
                app.showToast(res.message || "添加收藏失败");
            }
        });
    },
    removeShopFavorite: function (e) {
        let content = this;
        let cid = e.currentTarget.dataset.id;

        app.wxRequest("/user/cancelCollection", {cid: cid}, function (res) {
            if (res.code == 1) {
                content.data.goods.forEach(function (item) {
                    if (item.cid == cid) {
                        item.collection = 2;
                    }
                });
                content.setData({
                    goods: content.data.goods
                });
            }
            else {
                app.showToast(res.message || "移除收藏失败");
            }
        });
    },

    getGoodsList: function (page) {
        let content = this;
        let conditions = content.getSelectedItems();

        conditions.page = page || 1;

        app.wxRequest("/commodity/index", conditions, function (res) {
            let data = res.data;

            if (res.code == 1) {
                if (conditions.page == 1) {
                    content.setData({
                        goods: data.list,
                        lastPage: data.lastPage,
                        currentPage: data.currentPage
                    });
                }
                else {
                    content.setData({
                        goods: content.data.goods.concat(data.list),
                        lastPage: data.lastPage,
                        currentPage: data.currentPage
                    });
                }
            }
            else {
                content.setData({
                    goods: [],
                    lastPage: 0,
                    currentPage: 1
                });
                app.showToast(res.message || "获取商品列表失败");
            }
        });
    }
});
