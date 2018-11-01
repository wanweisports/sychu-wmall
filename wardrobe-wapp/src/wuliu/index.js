//index.js
//获取应用实例
const app = getApp()

Page({
    data: {
        orderId: "",
        logisticsTraces: [{
            acceptTime: "2018-11-01 15:10:12",
            acceptStation: "货物已到达 上海千阳站"
        }, {
            acceptTime: "2018-11-01 15:10:12",
            acceptStation: "货物已到达 上海千阳站"
        }, {
            acceptTime: "2018-11-01 15:10:12",
            acceptStation: "货物已到达 上海千阳站"
        }, {
            acceptTime: "2018-11-01 15:10:12",
            acceptStation: "货物已到达 上海千阳站"
        }, {
            acceptTime: "2018-11-01 15:10:12",
            acceptStation: "货物已到达 上海千阳站"
        }]
    },
    onShow: function (e) {
        this.data.orderId = e.id;
    }
});
