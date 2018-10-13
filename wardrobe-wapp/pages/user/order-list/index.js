const app = getApp();

Page({
    data:{
        statusType: ["待付款", "待发货", "待收货", "待评价", "已完成"],
        currentType:0,
        tabClass: ["", "", "", "", ""]
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
                    content.onShow();
                }
            }
        });
    },
    toPayTap: function (e) {
        let orderId = e.currentTarget.dataset.id;

        app.wxPay(orderId, "/pages/user/order-list/index");
    },
    onLoad: function () {
        let content = this;

        app.wxRequest("/order/userOrders", {}, function (res) {
            if (res.code == 1) {
                content.setData({
                    orderList : res.data.list
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
