"use strict";var app=getApp();Page({data:{isGetSMSCode:!1,isTimer:!1,isTimerCount:60,smsText:"获取验证码",_t:null,mobile:"",code:""},bindMobileBlur:function(t){this.setData({mobile:t.detail.value})},bindCodeBlur:function(t){this.setData({code:t.detail.value})},sendSMSCode:function(){var t=this;t.validate("sms")&&(t.setData({isGetSMSCode:!0}),t.data.isTimer||(t.timer(),app.wxRequest("/message/getCode",{mobile:t.data.mobile,type:3},function(){},function(){})))},timer:function(){var t=this;t.setData({isTimer:!0,smsText:--t.data.isTimerCount+"s",_t:setTimeout(function(){if(0==t.data.isTimerCount)return void t.clearTimer();var e=--t.data.isTimerCount;t.setData({isTimerCount:e,smsText:e+"s",_t:setTimeout(t.timer,1e3)})},1e3)})},clearTimer:function(){this.setData({isTimer:!1,isTimerCount:60,smsText:"重新获取"}),clearTimeout(this.data._t)},validate:function(t){var e="";return"submit"==t?""==this.data.mobile?e="请输入手机号码":/^1\d{10}$/.test(this.data.mobile)?""==this.data.code&&(e="请输入验证码"):e="请输入合法的手机号":"sms"==t&&(""==this.data.mobile?e="请输入手机号码":/^1\d{10}$/.test(this.data.mobile)||(e="请输入合法的手机号")),!e||(wx.showModal({title:"提示",content:e,showCancel:!1}),!1)},submitSMSCode:function(){var t=this;t.validate("submit")&&app.wxRequest("/user/updateNewMobile",{mobile:t.data.mobile,code:t.data.code},function(t){1==t.code&&wx.switchTab({url:"/pages/user/center/index"})},function(t){console.log(t)})}});