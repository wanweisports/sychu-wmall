const app = getApp();

Page({
    data: {
        coupons: [],
        hasCoupons: false
    },
    getUserCouponsList: function () {
        let content = this;

        app.wxRequest("/user/userCouponList", {}, function (res) {
            if (res.code == 1 && res.data.coupons && res.data.coupons.length > 0) {
                content.setData({
                    coupons : res.data.coupons,
                    hasCoupons : true
                });
            }
            else {
                content.setData({
                    coupons : [],
                    hasCoupons : false
                });
            }
        });
    },
    onLoad: function () {
        this.getUserCouponsList();
    }
});
