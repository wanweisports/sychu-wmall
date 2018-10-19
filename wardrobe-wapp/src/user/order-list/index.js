const app = getApp();

Page({
    data: {
        lastPage: 0,
        currentPage: 1,
        orderList: null
    },

    onReachBottom: function () {
        if (this.data.currentPage >= this.data.lastPage) {
            return app.showToast("已经最后一页", "none");
        }

        this.orderList(++this.data.currentPage);
    },

    orderDetail: function (e) {
        let orderId = e.currentTarget.dataset.id;

        app.redirect("/pages/user/order-details/index?id=" + orderId, "navigateTo");
    },

    cancelOrderTap: function(e){
        let content = this;
        let orderId = e.currentTarget.dataset.id;

        wx.showModal({
            title: '确定要取消该订单吗？',
            content: '',
            success: function (res) {
                if (res.confirm) {
                    app.wxRequest("/order/cancelOrder", {oid: orderId}, function (res) {});

                    content.orderList(1);
                }
            }
        });
    },

    toPayTap: function (e) {
        let orderId = e.currentTarget.dataset.id;

        app.wxPay(orderId, "/pages/user/order-list/index");
    },

    orderList: function (page) {
        let content = this;

        page = page || 1;

        app.wxRequest("/order/userOrders", {page: page}, function (res) {
            if (res.code == 1) {
                content.setData({
                    lastPage: res.data.lastPage,
                    currentPage: res.data.currentPage,
                    orderList : res.data.list
                });
            }
            else {
                content.setData({
                    orderList : null
                });
            }
        });
    },
    onLoad: function () {
        this.orderList(1);
    }
});
