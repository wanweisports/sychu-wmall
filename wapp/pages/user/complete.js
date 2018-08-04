var log = require('../../utils/log.js')
var app = getApp()

Page({
    data: {
        loadingHidden: false,

        sexList: ['帅哥', '美女'],
        ageList: ['85后', '90后', '95后'],
        dressStyleList: [
            {value: "1", text: "职场"},
            {value: "2", text: "约会"},
            {value: "3", text: "休闲"},
            {value: "4", text: "度假"},
            {value: "5", text: "晚宴"}
        ],
        usualSizeList: [
            {value: "1", text: "S"},
            {value: "2", text: "M"},
            {value: "3", text: "L"},
            {value: "4", text: "XL"},
            {value: "5", text: "XXL"}
        ],

        submit: {
            sex: "",
            age: "",
            dressStyle: "",
            usualSize: "",
            mobile: "",
            inviteCode: ""
        }
    },
    onLoad: function() {
        log.log('[P]用户完善信息页加载')

        var content = this;

        content.data.dressStyleList[0].checked = true;

        setTimeout(function () {
            content.setData({
                dressStyleList: content.data.dressStyleList,
                loadingHidden: true
            })
        }, 1500)
    },
    bindSexChange: function (e) {
        console.log(e);
    },
    bindAgeChange: function (e) {
        console.log(e);
    },
    bindDressStyleTap: function (e) {
        console.log(e);
    },
    bindUsualSizeTap: function (e) {
        console.log(e);
    }
})
