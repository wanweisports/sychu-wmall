//index.js
//获取应用实例
var app = getApp()

Page({
    data: {
        goodsListId: [],
        goodsList:[],
        isNeedLogistics:0, // 是否需要物流信息
        allGoodsPrice:0,
        yunPrice:0,
        allGoodsAndYunPrice:0,
        goodsJsonStr:"",
        orderType:"", //订单类型，购物车下单或立即支付下单，默认是购物车，

        hasNoCoupons: true,
        coupons: [],
        youhuijine:0, //优惠券金额
        curCoupon:null // 当前选择使用的优惠券
    },
    onShow : function () {
        var that = this;
        var shopList = [];

        var shopCarInfoMem = wx.getStorageSync('shopCarInfo');
        if (shopCarInfoMem && shopCarInfoMem.shopList) {
            shopList = shopCarInfoMem.shopList.filter(function (entity) {
                return entity.active;
            });
        }

        console.log(shopList);

        var allGoodsPrice = 0;
        var goodsListId = [];
        for (let i = 0; i < shopList.length; i++) {
            let carShopBean = shopList[i];
            allGoodsPrice += carShopBean.price * carShopBean.number;
            goodsListId.push(shopList[i].scid);
        }

        //立即购买下单
        // if ("buyNow"==that.data.orderType){
        //     var buyNowInfoMem = wx.getStorageSync('buyNowInfo');
        //     if (buyNowInfoMem && buyNowInfoMem.shopList) {
        //         shopList = buyNowInfoMem.shopList
        //     }
        // }else{
        //     //购物车下单
        //     var shopCarInfoMem = wx.getStorageSync('shopCarInfo');
        //     if (shopCarInfoMem && shopCarInfoMem.shopList) {
        //         shopList = shopCarInfoMem.shopList.filter(entity => {
        //                 return entity.active;
        //         });
        //     }
        // }
        that.setData({
            goodsList: shopList,
            allGoodsPrice: allGoodsPrice,
            allGoodsAndYunPrice: allGoodsPrice,
            goodsListId: goodsListId
        });
    },

    onLoad: function (e) {
        var that = this;
        //显示收货地址标识
        that.setData({
            isNeedLogistics: 1//,
            //orderType: e.orderType
        });
    },

    createOrder:function (e) {
        wx.showLoading();

        var that = this;
        var remark = ""; // 备注信息

        if (!that.data.curAddressData) {
            wx.hideLoading();
            wx.showModal({
                title: '错误',
                content: '请先设置您的收货地址！',
                showCancel: false
            });
            return;
        }

        var postData = {
            expressName: that.data.curAddressData.linkMan,
            expressMobile: that.data.curAddressData.mobile,
            expressAddress: that.data.curAddressData.address,
            remark: remark,
            scids: that.data.goodsListId.join(","),
            serviceType: "",
            cpid: ""
        };
        if (that.data.curCoupon) {
            postData.serviceType = 2;
            postData.cpid = that.data.curCoupon.id;
        }
        postData.calculate = "true";

        app.wxRequest("/order/saveOrder", postData, function (res) {
            wx.hideLoading();

            // 清空购物车数据
            //wx.removeStorageSync('shopCarInfo');
            console.log(res);
            //
            // // 配置模板消息推送
            // var postJsonString = {};
            // postJsonString.keyword1 = { value: res.data.data.dateAdd, color: '#173177' }
            // postJsonString.keyword2 = { value: res.data.data.amountReal + '元', color: '#173177' }
            // postJsonString.keyword3 = { value: res.data.data.orderNumber, color: '#173177' }
            // postJsonString.keyword4 = { value: '订单已关闭', color: '#173177' }
            // postJsonString.keyword5 = { value: '您可以重新下单，请在30分钟内完成支付', color:'#173177'}
            // app.sendTempleMsg(res.data.data.id, -1,
            //     'uJQMNVoVnpjRm18Yc6HSchn_aIFfpBn1CZRntI685zY', e.detail.formId,
            //     'pages/index/index', JSON.stringify(postJsonString));
            // postJsonString = {};
            // postJsonString.keyword1 = { value: '您的订单已发货，请注意查收', color: '#173177' }
            // postJsonString.keyword2 = { value: res.data.data.orderNumber, color: '#173177' }
            // postJsonString.keyword3 = { value: res.data.data.dateAdd, color: '#173177' }
            // app.sendTempleMsg(res.data.data.id, 2,
            //     'GeZutJFGEWzavh69savy_KgtfGj4lHqlP7Zi1w8AOwo', e.detail.formId,
            //     'pages/order-details/index?id=' + res.data.data.id, JSON.stringify(postJsonString));
            // 下单成功，跳转到订单管理界面
            // wx.redirectTo({
            //     url: "/pages/order-list/index"
            // });
        })
    },
    processYunfei: function () {
        var that = this;
        var goodsList = this.data.goodsList;
        var goodsJsonStr = "[";
        var isNeedLogistics = 0;
        var allGoodsPrice = 0;

        for (let i = 0; i < goodsList.length; i++) {
            let carShopBean = goodsList[i];
            if (carShopBean.logistics) {
                isNeedLogistics = 1;
            }
            allGoodsPrice += carShopBean.price * carShopBean.number;

            var goodsJsonStrTmp = '';
            if (i > 0) {
                goodsJsonStrTmp = ",";
            }


            let inviter_id = 0;
            let inviter_id_storge = wx.getStorageSync('inviter_id_' + carShopBean.goodsId);
            if (inviter_id_storge) {
                inviter_id = inviter_id_storge;
            }


            goodsJsonStrTmp += '{"goodsId":' + carShopBean.goodsId + ',"number":' + carShopBean.number + ',"propertyChildIds":"' + carShopBean.propertyChildIds + '","logisticsType":0, "inviter_id":' + inviter_id +'}';
            goodsJsonStr += goodsJsonStrTmp;


        }
        goodsJsonStr += "]";
        //console.log(goodsJsonStr);
        that.setData({
            isNeedLogistics: isNeedLogistics,
            goodsJsonStr: goodsJsonStr
        });
        that.createOrder();
    },
    addAddress: function () {
        // wx.navigateTo({
        //     url:"/pages/address-add/index"
        // })
        var that = this;

        wx.chooseAddress({
            success: function (res) {
                that.setData({
                    curAddressData: {
                        linkMan: res.userName,
                        mobile: res.telNumber,
                        address: res.provinceName + " " + res.cityName + " " + res.countyName + " " + res.detailInfo
                    }
                });
                console.log(res.userName)
                console.log(res.postalCode)
                console.log(res.provinceName)
                console.log(res.cityName)
                console.log(res.countyName)
                console.log(res.detailInfo)
                console.log(res.nationalCode)
                console.log(res.telNumber)
            }
        });
    },
    selectAddress: function () {
        var that = this;

        wx.chooseAddress({
            success: function (res) {
                that.setData({
                    curAddressData: {
                        linkMan: res.userName,
                        mobile: res.telNumber,
                        address: res.provinceName + " " + res.cityName + " " + res.countyName + " " + res.detailInfo
                    }
                });
                console.log(res.userName)
                console.log(res.postalCode)
                console.log(res.provinceName)
                console.log(res.cityName)
                console.log(res.countyName)
                console.log(res.detailInfo)
                console.log(res.nationalCode)
                console.log(res.telNumber)
            }
        });
        // wx.navigateTo({
        //     url:"/pages/select-address/index"
        // })
    },
    getMyCoupons: function () {
        var that = this;
        wx.request({
            url: 'https://api.it120.cc/' + app.globalData.subDomain + '/discounts/my',
            data: {
                token: app.globalData.token,
                status:0
            },
            success: function (res) {
                if (res.data.code == 0) {
                    var coupons = res.data.data.filter(entity => {
                            return entity.moneyHreshold <= that.data.allGoodsAndYunPrice;
                });
                    if (coupons.length > 0) {
                        that.setData({
                            hasNoCoupons: false,
                            coupons: coupons
                        });
                    }
                }
            }
        })
    },
    bindChangeCoupon: function (e) {
        const selIndex = e.detail.value[0] - 1;
        if (selIndex == -1) {
            this.setData({
                youhuijine: 0,
                curCoupon:null
            });
            return;
        }
        //console.log("selIndex:" + selIndex);
        this.setData({
            youhuijine: this.data.coupons[selIndex].money,
            curCoupon: this.data.coupons[selIndex]
        });
    }
});
