"use strict";var app=getApp();Page({data:{orderId:0,orderDetail:{},orderGoods:[]},toPayTap:function(r){var e=r.currentTarget.dataset.id;app.wxPay(e,"/pages/user/order-list/index")},onLoad:function(r){var e=this;this.setData({orderId:r.id}),app.wxRequest("/order/userOrderDetail",{oid:e.data.orderId},function(r){1==r.code?e.setData({orderDetail:r.data.order,orderGoods:r.data.orderDetails}):e.setData({orderDetail:null,orderGoods:null})})}});