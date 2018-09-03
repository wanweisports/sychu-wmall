//index.js
//获取应用实例
var app = getApp();
var WxParse = require('../../../wxParse/wxParse.js');

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
        shopNum: 0,
        hideShopPopup: true,

        buyNumber: 0,
        buyNumMin: 1,
        buyNumMax: 0,

        propertyChildIds: "",
        propertyChildNames: "",
        canSubmit: false,
        //  选中规格尺寸时候是否允许加入购物车
        shopCarInfo: {},
        shopType: "addShopCar",
        //购物类型，加入购物车或立即购买，默认为加入购物车

        goodId: 0,
        goodDetail: {},
        goodDetailSize: {}
    },

    //事件处理函数
    swiperchange: function(e) {
        //console.log(e.detail.current)
        this.setData({
            swiperCurrent: e.detail.current
        })
    },

    getGoodsDetail: function () {
        var that = this;

        app.wxRequest("/commodity/detail", {cid: that.data.goodId}, function (res) {
            if (res.code == 1) {
                var colors = res.data.colors;
                for (var i = 0; i < colors.length; i++) {
                    res.data.colors[i].selected = false;
                    if (colors[i].cid == that.data.goodId) {
                        colors[i].selected = true;
                    }
                }

                that.setData({
                    goodsDetail: res.data
                });
            }
        });
    },
    getGoodsDetailSize: function (sid) {
        var that = this;

        app.wxRequest("/commodity/detail/selected", {cid: that.data.goodId}, function (res) {
            if (res.code == 1) {
                var sizes = res.data.sizes;
                for (var i = 0; i < sizes.length; i++) {
                    res.data.sizes[i].selected = false;
                    if (sid && sizes[i].cid == sid) {
                        sizes[i].selected = true;
                    }
                }

                that.setData({
                    goodDetailSize: res.data
                });
            }
        });
    },
    saveShopCartAdd: function (sid, num, type) {
        var that = this;

        app.wxRequest("/commodity/saveShoppingCart", {sid: sid, shoppingType: type, count: num}, function () {}, function () {});
    },
    bindColorTap: function (e) {
        wx.navigateTo({
            url: '/pages/goods/details/index?id=' + e.currentTarget.dataset.id
        });
    },

    toAddShopCart: function() {
        this.setData({
            shopType: "addShopCar"
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
    goShopCart: function() {
        wx.reLaunch({
            url: "/pages/goods/cart/index"
        });
    },
    /**
     * 加入购物车
     */
    addShopCart: function () {
        var selected = null;
        var sizes = this.data.goodDetailSize.sizes;
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
            shopNum: ++this.data.buyNumber
        });

        this.closePopupTap();
        wx.showToast({
            title: '加入购物车成功',
            icon: 'success',
            duration: 2000
        });
    },

    toAddShopOrderCart: function() {
        this.setData({
            shopType: "addShopOrderCart"
        });
        this.bindShopOrderTap();
    },
    bindShopOrderTap: function() {
        this.setData({
            hideShopOrderPopup: false
        });
    },
    closeShopOrderPopupTap: function() {
        this.setData({
            hideShopOrderPopup: true
        });
    },
    /**
     * 加入预约购物车
     */
    addShopOrderCart: function() {
        var selected = null;
        var sizes = this.data.goodDetailSize.sizes;
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

        this.saveShopCartAdd(selected.sid, this.data.buyNumber, 2);

        this.setData({
            shopNum: ++this.data.buyNumber
        });

        this.closePopupTap();
        wx.showToast({
            title: '加入试衣间成功',
            icon: 'success',
            duration: 2000
        });
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

    onLoad: function (e) {
        var that = this;

        that.setData({
            goodId: e.id
        });

        that.getGoodsDetail(e.id);

        if (e.inviter_id) {
            wx.setStorage({
                key: 'inviter_id_' + e.id,
                data: e.inviter_id
            })
        }

        // 获取购物车数据
        wx.getStorage({
            key: 'shopCarInfo',
            success: function(res) {
                that.setData({
                    shopCarInfo: res.data,
                    shopNum: res.data.shopNum
                });
            }
        });
    },
    tobuy: function() {
        this.setData({
            shopType: "tobuy"
        });
        this.bindGuiGeTap();
        
        if (this.data.goodsDetail.properties && !this.data.canSubmit) {
            this.bindGuiGeTap();
            return;
        }

        if (this.data.buyNumber < 1) {
            wx.showModal({
                title: '提示',
                content: '暂时缺货哦~',
                showCancel:false
            });
            return;
        }

        this.addShopCar();
        this.goShopCar();
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
    },
    /**
    * 立即购买
    */
    buyNow: function() {
        if (this.data.goodsDetail.properties && !this.data.canSubmit) {
            if (!this.data.canSubmit) {
                wx.showModal({
                    title: '提示',
                    content: '请选择商品规格！',
                    showCancel: false
                })
            }
            this.bindGuiGeTap();
            wx.showModal({
                title: '提示',
                content: '请先选择规格尺寸哦~',
                showCancel: false
            });
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
        //组建立即购买信息
        var buyNowInfo = this.buliduBuyNowInfo();
        // 写入本地存储
        wx.setStorage({
            key: "buyNowInfo",
            data: buyNowInfo
        });
        this.closePopupTap();

        wx.navigateTo({
            url: "/pages/to-pay-order/index?orderType=buyNow"
        })
    },
    /**
   * 组建购物车信息
   */
    bulidShopCarInfo: function() {
        // 加入购物车
        var shopCarMap = {};
        shopCarMap.goodsId = this.data.goodsDetail.basicInfo.id;
        shopCarMap.pic = this.data.goodsDetail.basicInfo.pic;
        shopCarMap.name = this.data.goodsDetail.basicInfo.name;
        // shopCarMap.label=this.data.goodsDetail.basicInfo.id; 规格尺寸 
        shopCarMap.propertyChildIds = this.data.propertyChildIds;
        shopCarMap.label = this.data.propertyChildNames;
        shopCarMap.price = this.data.selectSizePrice;
        shopCarMap.left = "";
        shopCarMap.active = true;
        shopCarMap.number = this.data.buyNumber;
        shopCarMap.logisticsType = this.data.goodsDetail.basicInfo.logisticsId;
        shopCarMap.logistics = this.data.goodsDetail.logistics;
        shopCarMap.weight = this.data.goodsDetail.basicInfo.weight;

        var shopCarInfo = this.data.shopCarInfo;
        if (!shopCarInfo.shopNum) {
            shopCarInfo.shopNum = 0;
        }
        if (!shopCarInfo.shopList) {
            shopCarInfo.shopList = [];
        }
        var hasSameGoodsIndex = -1;
        for (var i = 0; i < shopCarInfo.shopList.length; i++) {
            var tmpShopCarMap = shopCarInfo.shopList[i];
            if (tmpShopCarMap.goodsId == shopCarMap.goodsId && tmpShopCarMap.propertyChildIds == shopCarMap.propertyChildIds) {
                hasSameGoodsIndex = i;
                shopCarMap.number = shopCarMap.number + tmpShopCarMap.number;
                break;
            }
        }

        shopCarInfo.shopNum = shopCarInfo.shopNum + this.data.buyNumber;
        if (hasSameGoodsIndex > -1) {
            shopCarInfo.shopList.splice(hasSameGoodsIndex, 1, shopCarMap);
        } else {
            shopCarInfo.shopList.push(shopCarMap);
        }
        return shopCarInfo;
    },
    /**
   * 组建立即购买信息
   */
    buliduBuyNowInfo: function() {
        var shopCarMap = {};
        shopCarMap.goodsId = this.data.goodsDetail.basicInfo.id;
        shopCarMap.pic = this.data.goodsDetail.basicInfo.pic;
        shopCarMap.name = this.data.goodsDetail.basicInfo.name;
        // shopCarMap.label=this.data.goodsDetail.basicInfo.id; 规格尺寸 
        shopCarMap.propertyChildIds = this.data.propertyChildIds;
        shopCarMap.label = this.data.propertyChildNames;
        shopCarMap.price = this.data.selectSizePrice;
        shopCarMap.left = "";
        shopCarMap.active = true;
        shopCarMap.number = this.data.buyNumber;
        shopCarMap.logisticsType = this.data.goodsDetail.basicInfo.logisticsId;
        shopCarMap.logistics = this.data.goodsDetail.logistics;
        shopCarMap.weight = this.data.goodsDetail.basicInfo.weight;

        var buyNowInfo = {};
        if (!buyNowInfo.shopNum) {
            buyNowInfo.shopNum = 0;
        }
        if (!buyNowInfo.shopList) {
            buyNowInfo.shopList = [];
        }
        /*    var hasSameGoodsIndex = -1;
        for (var i = 0; i < toBuyInfo.shopList.length; i++) {
          var tmpShopCarMap = toBuyInfo.shopList[i];
          if (tmpShopCarMap.goodsId == shopCarMap.goodsId && tmpShopCarMap.propertyChildIds == shopCarMap.propertyChildIds) {
            hasSameGoodsIndex = i;
            shopCarMap.number = shopCarMap.number + tmpShopCarMap.number;
            break;
          }
        }
        toBuyInfo.shopNum = toBuyInfo.shopNum + this.data.buyNumber;
        if (hasSameGoodsIndex > -1) {
          toBuyInfo.shopList.splice(hasSameGoodsIndex, 1, shopCarMap);
        } else {
          toBuyInfo.shopList.push(shopCarMap);
        }*/

        buyNowInfo.shopList.push(shopCarMap);
        return buyNowInfo;
    },
    reputation: function(goodsId) {
        var that = this;
        wx.request({
            url: 'https://api.it120.cc/' + app.globalData.subDomain + '/shop/goods/reputation',
            data: {
                goodsId: goodsId
            },
            success: function(res) {
                if (res.data.code == 0) {
                    //console.log(res.data.data);
                    that.setData({
                        reputation: res.data.data
                    });
                }
            }
        })
    }
})
