var util = require('./util.js')

module.exports = {
  log: function (content) {
      console.log(util.formatTime(new Date()) + " - " + content)
  }
}
