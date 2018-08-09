var app = getApp()

Page({
  data: {
  
  },
  getSMSCode: function () {
    var that = this;

    app.wxRequest(
      "/message/getCode",
      {
        mobile: "15801303167",
        type: 3
      },
      function (res) {
        console.log(res)
      },
      function (err) {
        console.log(err)
      }
    );
  },
  submitSMSCode: function () {
    var that = this;

    app.wxRequest(
      "/user/updateNewMobile",
      {
        mobile: "15801303167",
        code: "1234"
      },
      function (res) {
        console.log(res)
      },
      function (err) {
        console.log(err)
      }
    );
  }
})
