var wxpay = require('../../../utils/pay.js');
var app = getApp();

Page({
    data: {
        balance: "",
        rechargeList  : []
    },
    getUserRechargeList: function () {
        var that = this;

        app.wxRequest(
            "/dict/getDicts",
            {
                dictName: "RECHARGE_TYPE"
            },
            function (res) {
                that.setData({
                    rechargeList : res.data.dicts
                });
            }
        );
    },
    bindRechargeTap: function (e) {
        var that = this;
        var data = e.currentTarget.dataset;

        this.setData({
            balance : data.balance,
            rechargeList  : this.data.rechargeList
        });
    },
    bindBalanceBlur: function (e) {
        this.setData({
            balance: e.detail.value
        });
    },
    onLoad: function () {},
    onShow: function () {
        this.getUserRechargeList();
    },
    bindCancel: function () {
        wx.navigateBack({});
    },
    bindSave: function (e) {
        var amount = this.data.balance;

        if (amount == "" || amount * 1 < 0) {
            wx.showModal({
                title: '错误',
                content: '请填写正确的充值金额',
                showCancel: false
            });
            return;
        }

        wxpay.wxpay(app, amount, 0, "/pages/my/index");
    }
});
