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
    getStyleList: function () {
        let content = this;

        app.wxRequest("/dict/getDicts", {
            dictName: "COMM_STYLE"
        }, function (res) {
            content.setData({
                styleList: res.data.dicts
            });
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
            content.setData({
                categoryList: res.data.dicts
            });
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
            content.setData({
                materialList: res.data.dicts
            });
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
            category : categoryIds.length > 0 ? "," + categoryIds.join(",") + "," : "",
            style    : styleIds.length > 0 ? "," + styleIds.join(",") + "," : "",
            material : materialIds.length > 0 ? "," + materialIds.join(",") + "," : ""
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

    getGoodsList: function (page) {
        let content = this;
        let conditions = content.getSelectedItems();

        conditions.page = page || 1;

        app.wxRequest("/commodity/index", conditions, function (res) {
            if (res.code == 1) {
                content.setData({
                    goods: content.data.goods.concat(res.data.list),
                    lastPage: res.data.lastPage,
                    currentPage: res.data.currentPage
                });
            }
        });
    }
});