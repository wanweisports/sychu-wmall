"use strict";var app=getApp(),utils=require("../../../utils/util.js");Page({data:{sumPrice:0,goodsList:[],wardrobeInfo:{},hasWardrobeInfo:!1},getWardrobeOrder:function(){var e=this;app.wxRequest("/order/reserveOrderInfo",{},function(o){if(1==o.code){var r=o.data.list;r.length>0&&(e.setData({wardrobeInfo:r[0],hasWardrobeInfo:!0}),e.getWardrobeOrderDetail(r[0].roid))}})},getWardrobeOrderDetail:function(e){var o=this;app.wxRequest("/order/reserveOrderDetail",{roid:e},function(e){1==e.code&&o.setData({goodsList:e.data.list,sumPrice:e.data.sumPrice})})},onLoad:function(){this.getWardrobeOrder()},scanOrder:function(){wx.scanCode({scanType:"qrCode",success:function(e){app.wxRequest("/relay/openDoor",{},function(e){1==e.code&&app.showToast("扫码开门成功","success")})}})},toCartOrder:function(){app.redirect("/pages/goods/reserve-settle/index?did="+this.data.wardrobeInfo.did,"navigateTo")},openLock:function(e){app.wxRequest("/relay/openLock",{lockId:e.currentTarget.dataset.id},function(e){1==e.code&&app.showToast("开柜成功","success")})},leave:function(){wx.showModal({title:"提 示",content:"您确定要离开吗？确认完后会立即开门",success:function(e){e.confirm&&app.wxRequest("/relay/closeDoor",{},function(e){1==e.code&&app.showToast("开门成功","success")})}})}});