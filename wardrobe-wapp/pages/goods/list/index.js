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
        loadingMoreHidden: true,

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
        var that = this;

        app.wxRequest("/dict/getDicts",
            {
                dictName: "COMM_STYLE"
            },
            function (res) {
                that.setData({
                    styleList: res.data.dicts
                });
            }
        );
    },
    bindStyleTap: function (e) {
        var that = this;
        var data = e.target.dataset;

        var current = that.data.styleList[data.index];

        current.selected = !current.selected;

        this.setData({
            styleIndex : data.index,
            styleValue : data.id,
            styleList  : this.data.styleList
        });
    },
    getCategoryList: function () {
        var that = this;

        app.wxRequest("/dict/getDicts",
            {
                dictName: "COMM_CATEGORY"
            },
            function (res) {
                that.setData({
                    categoryList: res.data.dicts
                });
            }
        );
    },
    bindCategoryTap: function (e) {
        var that = this;
        var data = e.target.dataset;

        var current = that.data.categoryList[data.index];

        current.selected = !current.selected;

        this.setData({
            categoryIndex : data.index,
            categoryValue : data.id,
            categoryList  : this.data.categoryList
        });
    },
    getMaterialList: function () {
        var that = this;

        app.wxRequest("/dict/getDicts",
            {
                dictName: "COMM_MATERIAL"
            },
            function (res) {
                that.setData({
                    materialList: res.data.dicts
                });
            }
        );
    },
    bindMaterialTap: function (e) {
        var that = this;
        var data = e.target.dataset;

        var current = that.data.materialList[data.index];

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
        var that = this;

        that.getGoodsList();
        that.setData({
            isShowFilter: false
        });
    },
    bindResetTap: function () {
        var that = this;

        that.clearSelectedItems();
        that.getGoodsList();
        that.setData({
            isShowFilter: false
        });
    },
    bindFilterTap: function () {
        var that = this;

        that.setData({
            isShowFilter: true
        });
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

        wx.setNavigationBarTitle({
            title: wx.getStorageSync('mallName')
        });

        that.getCategoryList();
        that.getStyleList();
        that.getMaterialList();
        that.getGoodsList();
    },

    getGoodsList: function () {
        var that = this;

        app.wxRequest("/commodity/index", that.getSelectedItems(), function (res) {
            if (res.code == 1) {
              that.setData({
                goods: res.data.list
              });
            }
          }
        );
    }
});
