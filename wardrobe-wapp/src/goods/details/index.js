//index.js
//获取应用实例
const app = getApp();

Page({
    data: {
        autoplay: true,
        interval: 3000,
        duration: 1000,
        goodsDetail: {},
        swiperCurrent: 0,
        hasMoreSelect: false,
        selectSize: "选择：",
        selectSizePrice: 0,

        shopCartNum: 0,
        shopOrderNum: 0,

        hideShopPopup: true,
        hideShopOrderPopup: true,

        isFavorite: false,

        buyNumber: 0,
        buyNumMin: 1,
        buyNumMax: 0,

        propertyChildIds: "",
        propertyChildNames: "",
        canSubmit: false,

        shopCartInfo: {},
        shopOrderInfo: {},
        shopType: "addShopCart", // "addShopOrder"

        goodId: 0,
        goodDetail: {},
        goodDetailSize: {}
    },

    //事件处理函数
    swiperchange: function(e) {
        this.setData({
            swiperCurrent: e.detail.current
        })
    },
    bindColorTap: function (e) {
        app.redirect('/pages/goods/details/index?id=' + e.currentTarget.dataset.id, "navigateTo");
    },
    getGoodsDetail: function () {
        let content = this;

        app.wxRequest("/commodity/detail", {cid: content.data.goodId}, function (res) {
            if (res.code == 1) {
                let colors = res.data.colors;
                for (var i = 0; i < colors.length; i++) {
                    res.data.colors[i].selected = false;
                    if (colors[i].cid == content.data.goodId) {
                        colors[i].selected = true;
                    }
                }

                res.data.desc = res.data.desc.split(/\n/);

                content.setData({
                    goodsDetail: res.data,
                    isFavorite: res.data.collection == 1
                });
            }
            else {
                app.showToast(res.message || "获取商品详情失败");
            }
        });
    },
    goShopCart: function() {
        app.redirect("/pages/goods/cart/index", "reLaunch");
    },
    goShopOrderCart: function () {
        app.redirect("/pages/goods/room/index", "reLaunch");
    },
    addShopFavorite: function () {
        let content = this;

        app.wxRequest("/user/saveCollection", {cid: content.data.goodId}, function (res) {
            if (res.code == 1) {
                content.setData({
                    isFavorite: true
                });
            }
            else {
                app.showToast(res.message || "收藏商品失败");
            }
        });
    },
    removeShopFavorite: function () {
        let content = this;

        app.wxRequest("/user/cancelCollection", {cid: content.data.goodId}, function (res) {
            if (res.code == 1) {
                content.setData({
                    isFavorite: false
                });
            }
            else {
                app.showToast(res.message || "移除收藏商品失败");
            }
        });
    },
    onLoad: function (e) {
        let content = this;
        let goodId = e.id;
        let shopCartNum = app.getCookie("shopCartNum") || "";
        let shopOrderNum = app.getCookie("shopOrderNum") || "";

        content.setData({
            goodId: goodId,
            shopCartNum: shopCartNum,
            shopOrderNum: shopOrderNum
        });

        content.getGoodsDetail();
    },

    getGoodsDetailSize: function (sid) {
        let content = this;

        app.wxRequest("/commodity/detail/selected", {cid: content.data.goodId}, function (res) {
            if (res.code == 1) {
                let sizes = res.data.sizes;
                for (var i = 0; i < sizes.length; i++) {
                    res.data.sizes[i].selected = false;
                    if (sid && sizes[i].cid == sid) {
                        sizes[i].selected = true;
                    }
                }

                content.setData({
                    goodDetailSize: res.data
                });
            }
            else {
                app.showToast(res.message || "获取商品详情尺码失败");
            }
        });
    },
    saveShopCartAdd: function (sid, num, type) {
        app.wxRequest("/commodity/saveShoppingCart", {sid: sid, shoppingType: type, count: num}, function (res) {
            if (res.code != 1) {
                app.showToast(res.message || "添加购物车失败");
            }
        });
    },

    /***** 加购物车 *****/
    toAddShopCart: function() {
        this.setData({
            shopType: "addShopCart"
        });
        this.bindGuiGeTap();
        this.getGoodsDetailSize();
    },
    bindGuiGeTap: function() {
        this.setData({
            hideShopPopup: false
        })
    },
    closePopupTap: function() {
        this.setData({
            hideShopPopup: true
        })
    },
    addShopCart: function () {
        let selected = null;
        let sizes = this.data.goodDetailSize.sizes;
        for (var i = 0; i < sizes.length; i++) {
            if (sizes[i].selected) {
                selected = sizes[i];
            }
        }
        if (!selected) {
            wx.showModal({
                title: '提示',
                content: '请选择商品规格！',
                showCancel: false
            });

            this.bindGuiGeTap();
            return;
        }
        if (this.data.buyNumber < 1) {
            wx.showModal({
                title: '提示',
                content: '购买数量不能为0！',
                showCancel: false
            });
            return;
        }

        this.saveShopCartAdd(selected.sid, this.data.buyNumber, 1);

        this.setData({
            shopCartNum: Number(this.data.shopCartNum) + this.data.buyNumber
        });

        this.closePopupTap();

        app.showToast("已加入购物车", "success");
    },

    /***** 加闪衣橱 *****/
    toAddShopOrderCart: function () {
        this.setData({
            shopType: "addShopOrderCart"
        });
        this.bindShopOrderTap();
        this.getGoodsDetailSize();
    },
    bindShopOrderTap: function() {
        this.setData({
            hideShopOrderPopup: false
        });
    },
    closeShopOrderPopupTap: function () {
        this.setData({
            hideShopOrderPopup: true
        });
    },
    selectProductSize: function (e) {
        this.labelItemTap(e);
    },
    addProductCount: function (e) {
        this.numJiaTap(e);
    },
    minusProductCount: function (e) {
        this.numJianTap(e);
    },
    addShopOrderCart: function() {
        let selected = null;
        let sizes = this.data.goodDetailSize.sizes;
        for (var i = 0; i < sizes.length; i++) {
            if (sizes[i].selected) {
                selected = sizes[i];
            }
        }
        if (!selected) {
            wx.showModal({
                title: '提示',
                content: '请选择商品规格！',
                showCancel: false
            });

            this.bindShopOrderTap();
            return;
        }
        if (this.data.buyNumber < 1) {
            wx.showModal({
                title: '提示',
                content: '购买数量不能为0！',
                showCancel: false
            });
            return;
        }

        this.saveShopCartAdd(selected.sid, this.data.buyNumber, 2);

        this.setData({
            shopOrderNum: Number(this.data.shopOrderNum) + this.data.buyNumber
        });

        this.closeShopOrderPopupTap();
        app.showToast("已加入配衣间", "success");
    },

    /**
     * 选择商品规格
     * @param {Object} e
     */
    labelItemTap: function (e) {
        var that = this;
        var data = e.currentTarget.dataset;

        var sizes = that.data.goodDetailSize.sizes;
        for (var i = 0; i < sizes.length; i++) {
            sizes[i].selected = false;
        }
        sizes[data.index].selected = true;
        that.data.goodDetailSize.sizes = sizes;

        that.setData({
            buyNumMax: sizes[data.index].stock,
            buyNumber: (sizes[data.index].stock > 0) ? 1 : 0,
            goodDetailSize: that.data.goodDetailSize
        });
    },
    numJianTap: function() {
        if (this.data.buyNumber > this.data.buyNumMin) {
            var currentNum = this.data.buyNumber;
            currentNum--;
            this.setData({
                buyNumber: currentNum
            })
        }
    },
    numJiaTap: function() {
        if (this.data.buyNumber < this.data.buyNumMax) {
            var currentNum = this.data.buyNumber;
            currentNum++;
            this.setData({
                buyNumber: currentNum
            })
        }
    }
});
