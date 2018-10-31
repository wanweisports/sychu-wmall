var app = getApp();

Page({
    data: {
        transactions: {}
    },
    onShareAppMessage: null,
    getUserTransactionsList: function () {
        var that = this;

        app.wxRequest(
            "/transactions/index",
            {},
            function (res) {
                that.setData({
                    transactions : res.data
                });
            }
        );
    },
    onLoad: function () {
        this.getUserTransactionsList();
    }
});
