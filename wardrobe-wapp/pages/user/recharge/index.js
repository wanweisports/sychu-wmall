var wxpay = require('../../utils/pay.js');
var app = getApp();

Page({
    data: {
        rechargeList: []
    },
    getUserRechargeList: function () {
        var that = this;

        app.wxRequest(
            "/user/userRecharge",
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
    onLoad: function () {},
    onShow: function () {
        this.getUserRechargeList();
    },
    bindCancel: function () {
        wx.navigateBack({});
    },
    bindSave: function (e) {
        var that = this;
        var amount = e.detail.value.amount;

        if (amount == "" || amount*1 < 0) {
            wx.showModal({
                title: '错误',
                content: '请填写正确的充值金额',
                showCancel: false
            });
            return
        }

        wxpay.wxpay(app, amount, 0, "/pages/my/index");
    }
});
