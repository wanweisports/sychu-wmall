const app = getApp();

Page({
    data:{
        orderId: 0,
        orderDetail: {},
        orderGoods: []
    },

    onShareAppMessage: null,
    toPayTap: function (e) {
        let orderId = e.currentTarget.dataset.id;

        app.wxPay(orderId, "/pages/user/order-list/index");
    },

    onLoad: function (e) {
        let content = this;

        this.setData({
            orderId: e.id
        });

        app.wxRequest("/order/userOrderDetail", {oid: content.data.orderId}, function (res) {
            if (res.code == 1) {
                content.setData({
                    orderDetail : res.data.order,
                    orderGoods  : res.data.orderDetails
                });
            }
            else {
                content.setData({
                    orderDetail : null,
                    orderGoods  : null
                });
            }
        });
    }
});
