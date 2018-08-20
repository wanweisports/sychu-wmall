$.fn.serializeObject = function(){
    var data = {};
    this.serializeArray().map(function(x){data[x.name] = x.value;});
    return data;
}

$.fn.serializeJson=function(){
    var serializeObj={};
    var array=this.serializeArray();
    $(array).each(function(){
        if(serializeObj[this.name]){
            if($.isArray(serializeObj[this.name])){
                serializeObj[this.name].push(this.value);
            }else{
                serializeObj[this.name]=[serializeObj[this.name],this.value];
            }
        }else{
            serializeObj[this.name]=this.value;
        }
    });
    return serializeObj;
};

$.fn.serializeListMap = function(){
    var arrs = [], data = {}, firstName, array = this.serializeArray();
    array.map(function(x, index) {
        if(index == 0) {
            firstName = x.name;
        }
        if(firstName == x.name){
            if(index == 0) {
                arrs.push(data);
            }else{
                data = {};
                arrs.push(data);
            }
        }
        data[x.name] = x.value;
    });
    return arrs;
};

$.fn.serializeJsonStr= function () {
    return JSON.stringify(this.serializeJson()).replace(new RegExp("\"",'gm'),'\'')
};

$.fn.serializeListMap = function(){
    var arrs = [], data = {}, firstName, array = this.serializeArray();
    array.map(function(x, index) {
        if(index == 0) {
            firstName = x.name;
        }
        if(firstName == x.name){
            if(index == 0) {
                arrs.push(data);
            }else{
                data = {};
                arrs.push(data);
            }
        }
        data[x.name] = x.value;
    });
    return arrs;
};