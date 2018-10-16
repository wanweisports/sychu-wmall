var app = getApp();

Page({
    data: {
        balance: "",
        dictId: "",
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
            dictId  : data.id,
            rechargeList  : this.data.rechargeList
        });
    },
    bindBalanceBlur: function (e) {
        this.setData({
            balance : e.detail.value,
            dictId  : ""
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
        var price = this.data.balance;

        if (price == "" || price * 1 < 0) {
            wx.showModal({
                title: '错误',
                content: '请填写正确的充值金额',
                showCancel: false
            });
            return;
        }

        //wxpay.wxpay(app, price, 0, "/pages/my/index");
    }
});
