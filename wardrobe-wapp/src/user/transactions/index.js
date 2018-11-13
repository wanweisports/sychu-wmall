const app = getApp();

Page({
    data: {
        transactions: [],
        lastPage: 0,
        currentPage: 1
    },
    onShareAppMessage: null,
    onReachBottom: function () {
        if (this.data.currentPage >= this.data.lastPage) {
            return app.showToast("已经最后一页", "none");
        }

        this.getUserTransactionsList(++this.data.currentPage);
    },
    getUserTransactionsList: function (page) {
        let content = this;
        let conditions = {};
        conditions.page = page || 1;

        app.wxRequest("/transactions/index", {type: 2, page: conditions.page}, function (res) {
            let data = res.data;

            if (res.code == 1) {
                if (conditions.page == 1) {
                    content.setData({
                        transactions: data.list,
                        lastPage: data.lastPage,
                        currentPage: data.currentPage
                    });
                }
                else {
                    content.setData({
                        transactions: content.data.transactions.concat(data.list),
                        lastPage: data.lastPage,
                        currentPage: data.currentPage
                    });
                }
            }
            else {
                content.setData({
                    transactions: [],
                    lastPage: 0,
                    currentPage: 1
                });
                app.showToast(res.message || "获取交易失败");
            }
        });
    },
    onShow: function () {
        this.getUserTransactionsList(1);
    }
});
