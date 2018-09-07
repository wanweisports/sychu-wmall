var app = getApp();

Page({
    data: {
        coupons: []
    },
    getUserCouponsList: function () {
        var that = this;

        app.wxRequest(
            "/user/userCouponList",
            {},
            function (res) {
                that.setData({
                    coupons : res.data.coupons
                });
            }
        );
    },
    onLoad: function () {
        this.getUserCouponsList();
    }
});
