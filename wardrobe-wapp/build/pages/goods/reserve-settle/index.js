"use strict";var app=getApp();Page({data:{goodsListId:[],goodsList:[],allGoodsPrice:0,yunPrice:0,allGoodsAndYunPrice:0,curCoupon:null,couponsIndex:0,serviceType:2,cpid:0,coupons:[],hasCoupons:!1,ycoidList:[],ycoidIndex:0,ycoid:0,useYcoid:0,hasYcoid:!1,userInfo:{},did:"",dbids:[],userBalance:0,payType:1,payTypeIndex:0,payTypeList:[{value:1,text:"微信支付"},{value:2,text:"余额支付"}],stopTimer:!1},onShareAppMessage:null,getCartSettle:function(){var e=this;app.wxRequest("/rfid/readRfid",{did:this.data.did},function(a){if(1==a.code){var t=a.data.coupons,i=a.data.ycoid,d=a.data.data.commoditys,o=[],c=[];!!t&&t.forEach(function(e){1==e.status&&(e.textShow=e.dictValue,o.push(e))}),!!d&&d.forEach(function(e){c.push(e.dbid)}),e.setData({dbids:c.join(","),goodsList:d,coupons:o.length>0?[{textShow:"不使用优惠券",cpid:""}].concat(o):[{textShow:"无可用优惠券",cpid:""}],hasCoupons:o.length>0,couponPrice:0,allGoodsPrice:a.data.sumOldDisPrice,allGoodsAndYunPrice:a.data.sumPrice,ycoid:a.data.ycoid,useYcoid:0,ycoidList:i>0?[{textShow:"不使用薏米",ycoid:"0"},{textShow:a.data.ycoid+"薏米",ycoid:a.data.ycoid}]:[{textShow:"没有薏米",ycoid:"0"}],hasYcoid:i>0,yunPrice:a.data.freight});var s=setTimeout(function(){e.readCartSettle(),clearTimeout(s)},5e3)}})},readCartSettle:function(){var e=this;this.data.stopTimer||app.wxRequest("/rfid/readRfid",{did:this.data.did},function(a){if(1==a.code){var t=a.data.data.commoditys,i=[];!!t&&t.forEach(function(e){i.push(e.dbid)}),e.setData({dbids:i.join(","),goodsList:t}),e.countCartSettle();var d=setTimeout(function(){e.readCartSettle(),clearTimeout(d)},5e3)}},null,null,{isLoading:!1})},onHide:function(){this.setData({stopTimer:!0}),app.clearCookie("syc_leave")},onUnload:function(){this.setData({stopTimer:!0}),app.clearCookie("syc_leave")},countCartSettle:function(){var e=this;app.wxRequest("/commodity/settlementRfidCount",{dbids:e.data.dbids,serviceType:e.data.serviceType,cpid:e.data.cpid},function(a){1==a.code&&e.setData({allGoodsAndYunPrice:a.data.sumPrice,yunPrice:a.data.freight,couponPrice:a.data.couponPrice,useYcoid:a.data.useYcoid})})},bindCouponsChange:function(e){var a=this.data.coupons[e.detail.value];this.setData({couponsIndex:e.detail.value,curCoupon:a,serviceType:1,cpid:a.cpid}),a.cpid&&this.countCartSettle()},bindPayTypeChange:function(e){var a=e.detail.value;this.setData({payTypeIndex:a,payType:this.data.payTypeList[a].value})},bindYcoidChange:function(e){this.data.ycoidList[e.detail.value];this.setData({ycoidIndex:e.detail.value,serviceType:2}),this.countCartSettle()},getUserBalance:function(){var e=this;app.wxRequest("/account/userAccountBalance",{},function(a){1==a.code&&e.setData({userBalance:a.data.balance})})},onLoad:function(e){var a=this;a.setData({did:e.did}),a.getCartSettle(e.did),a.getUserBalance()},onShow:function(){app.setCookie("syc_leave","/pages/user/reserve/index")},createOrder:function(){var e=this;if(2==e.data.payType&&e.data.allGoodsAndYunPrice>e.data.userBalance)return void wx.showModal({title:"提 示",content:"您的余额不足，请先去充值或者取消选择微信支付！",cancelText:"取消",confirmText:"去充值",success:function(e){e.confirm&&app.redirect("/pages/user/recharge/index")}});var a={dbids:e.data.dbids,payType:e.data.payType};1==e.data.serviceType?(a.serviceType=e.data.serviceType,a.cpid=e.data.cpid):2==e.data.serviceType&&(a.serviceType=e.data.serviceType),app.wxRequest("/order/saveRfidOrder",a,function(e){1==e.code?app.wxPay(e.data.oid,"/pages/user/center-access/index"):app.showToast("创建订单失败","none")},function(){app.showToast("创建订单错误","none")})}});