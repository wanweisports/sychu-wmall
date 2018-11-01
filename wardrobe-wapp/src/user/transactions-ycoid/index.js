const app = getApp();

Page({
    data: {
        transactions: {}
    },
    onShareAppMessage: null,
    getUserTransactionsList: function () {
        let content = this;

        app.wxRequest("/transactions/index", {}, function (res) {
            content.setData({
                transactions : res.data
            });
        });
    },
    onShow: function () {
        this.getUserTransactionsList();
    }
});
