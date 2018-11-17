function formatTime(date) {
    var year = date.getFullYear();
    var month = date.getMonth() + 1;
    var day = date.getDate();

    var hour = date.getHours();
    var minute = date.getMinutes();
    var second = date.getSeconds();


    return [year, month, day].map(formatNumber).join('/') + ' ' + [hour, minute, second].map(formatNumber).join(':');
}

function formatDate(date) {
    var year = date.getFullYear();
    var month = date.getMonth() + 1;
    var day = date.getDate();

    year = "" + year;
    month = month > 9 ? "" + month : "0" + month;
    day = day > 9 ? "" + day : "0" + day;

    return [year, month, day].join("-");
}

function formatNumber(n) {
    n = n.toString();
    return n[1] ? n : '0' + n
}

module.exports = {
    formatDate: formatDate,
    formatTime: formatTime
};
