"use strict";var app=getApp(),utils=require("../../../utils/util.js");Page({data:{goodsListId:[],goodsList:[],allGoodsPrice:0,reserveDateList:[],reserveDateIndex:0,reserveDateValue:"",reserveTimeStartList:[],reserveTimeStartIndex:[0,0],reserveTimeStartValue:"",reserveTimeEndList:[],reserveTimeEndIndex:[0,0],reserveTimeEndValue:""},onShareAppMessage:null,initDate:function(e,t){var a=new Date;a.getHours()>=17&&a.setTime(a.getTime()+864e5);for(var r=[],s=0;s<14;s++){var i=new Date;i.setTime(a.getTime()+24*(s+1)*60*60*1e3);var d=utils.formatDate(i);r.push({reserveDateValue:d,reserveDateText:d})}this.setData({reserveDateList:r,reserveDateIndex:0}),this.setData({reserveDateValue:this.data.reserveDateList[this.data.reserveDateIndex].reserveDateValue,reserveDateText:this.data.reserveDateList[this.data.reserveDateIndex].reserveDateText});for(var n=Number(e.split(":")[0]),v=Number(t.split(":")[0]),o=[],l=n;l<v;l++)o.push([l>9?l:"0"+l]);this.setData({reserveTimeStartList:[o,["00","15","30","45"]],reserveTimeStartIndex:[0,0],reserveTimeEndList:[o,["00","15","30","45"]],reserveTimeEndIndex:[0,0]}),this.setData({reserveTimeStartValue:this.data.reserveTimeStartList[0][this.data.reserveTimeStartIndex[0]]+":"+this.data.reserveTimeStartList[1][this.data.reserveTimeStartIndex[1]],reserveTimeEndValue:this.data.reserveTimeEndList[0][this.data.reserveTimeEndIndex[0]]+":"+this.data.reserveTimeEndList[1][this.data.reserveTimeEndIndex[1]]})},bindReserveDateChange:function(e){this.setData({reserveDateIndex:e.detail.value,reserveDateValue:this.data.reserveDateList[e.detail.value].reserveDateValue,reserveDateText:this.data.reserveDateList[e.detail.value].reserveDateText})},bindStartTimeChange:function(e){var t=e.detail.value;this.setData({reserveTimeStartIndex:t,reserveTimeStartValue:this.data.reserveTimeStartList[0][t[0]]+":"+this.data.reserveTimeStartList[1][t[1]]})},bindEndTimeChange:function(e){var t=e.detail.value;this.setData({reserveTimeEndIndex:t,reserveTimeEndValue:this.data.reserveTimeEndList[0][t[0]]+":"+this.data.reserveTimeEndList[1][t[1]]})},getWardrobeList:function(){var e=this;app.wxRequest("/device/devices",{},function(t){var a=new Date;a.getTime();if(1==t.code){var r=t.data.list[0];e.setData({wardrobeInfo:r}),e.initDate(r.startTime,r.endTime)}})},onLoad:function(){var e=this,t=[],a=app.getCookie("shopOrderInfo");a&&a.shopList&&(t=a.shopList.filter(function(e){return e.active}));for(var r=0,s=[],i=0;i<t.length;i++){var d=t[i];r+=d.couPrice*d.number,s.push(t[i].scid)}e.setData({goodsList:t,goodsListId:s,allGoodsPrice:r}),e.getWardrobeList()},createOrder:function(){var e=this,t=e.data.reserveDateValue+" "+e.data.reserveTimeStartValue+":00",a=e.data.reserveDateValue+" "+e.data.reserveTimeEndValue+":00";if(t>=a)return void wx.showModal({title:"提 示",content:"开始时间必须小于结束时间！！",showCancel:!1});if(new Date(t.replace(/\-/g,"/")).getTime()<=(new Date).getTime())return void wx.showModal({title:"提 示",content:"开始时间不能小于当前时间！！",showCancel:!1});if(new Date(a.replace(/\-/g,"/")).getTime()-new Date(t.replace(/\-/g,"/")).getTime()>72e5)return void wx.showModal({title:"提 示",content:"预约时长不能超过2小时！！",showCancel:!1});var r={reserveStartTime:t,reserveEndTime:a,did:e.data.wardrobeInfo.did,scids:e.data.goodsListId.join(",")};app.wxRequest("/order/saveReserveOrder",r,function(e){1==e.code?(app.clearCookie("shopOrderInfo"),app.redirect("/pages/user/reserve/index","navigateTo")):app.showToast(e.message||"预约失败")})}});