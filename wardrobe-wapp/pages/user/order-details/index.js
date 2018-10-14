const app = getApp();

Page({
    data:{
        orderId: 0,
        orderDetail: {},
        orderGoods: []
    },
    onLoad: function (e) {
        this.setData({
            orderId: e.id
        });

        let content = this;

        app.wxRequest("/order/userOrderDetail", {oid: e.id}, function (res) {
            if (res.code == 1) {
                content.setData({
                    orderDetail : res.data.order,
                    orderGoods : res.data.orderDetails
                });
            }
            else {
                content.setData({
                    orderList : null
                });
            }
        });
    }
});
