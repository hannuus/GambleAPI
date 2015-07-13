/*************************************************************************************************************/
/****************************************基本通用方法***********************************************************/
/*************************************************************************************************************/
/*************************************************************************************************************/

/*基本通用方法*/
var general = {
	/*获取内页导航栏方法*/
	htmlGuidelines: function () {
	        general.endloading();
	        $("#parent").html(decodeURIComponent(general.getParameter("parentmenuname")));
	        $("#son").html(decodeURIComponent(general.getParameter("sonmenuname")));
	},
    /*取当前页面名称(不带后缀名)*/
    pageName1: function () {
        var a = location.href;
        var b = a.split("/");
        var c = b.slice(b.length - 1, b.length).toString(String).split(".");
        var d = c[0];
        return d;
    },
    /*取当前页面路径名称*/
    pageName2: function () {
        var strUrl = location.href;
        var arrUrl = strUrl.split("/");
        var strPage = arrUrl[arrUrl.length - 2];
        return strPage;
    },
    /*取当前地址栏地址*/
    pageName3: function () {
        return window.location.href;
    },
    /*获取地址栏参数的方法 变量名称 如:id*/
    getParameter: function (param) {
        var query = window.location.search;
        var iLen = param.length;
        var iStart = query.indexOf(param);
        if (iStart == -1)
            return "";
        iStart += iLen + 1;
        var iEnd = query.indexOf("&", iStart);
        if (iEnd == -1)
            return query.substring(iStart);
        return query.substring(iStart, iEnd);
    },
    /*加载效果*/
    statrloading: function () {
        $(".showbox").fadeIn();
    },
    /*结束效果*/
    endloading: function () {
        $(".showbox").fadeOut();
    },
    /*错误提示*/
    erroloading: function (state) {
    	general.endloading();
    	alert("服务器错误,错误值:" + state);
    },
    /*是否为空 返回true和false*/
    isnullorempty: function (str) {
        if (str == null) return true;
        if (str.length == 0) return true;
        if (/^\s*$/i.test(str)) return true;
        return false;
    },
    /*是否是带两位小数的数字 返回true和false*/
    isdemice: function (str) {
        return /^[0-9]*[\.][0-9]{0,2}$/.test(str);
    },
    /*验证身份证号码*/
    iscard: function (str) {
        return str.match(/^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}|[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}([0-9]|X|x)$/);
    },
    /*判断不能为数字开头*/
    istestd: function (str) {
        if (/^\d.*$/.test(str)) {
            return true;
        }
        return false;
    },
    /*判断用户名只能为_,英文字母,数字*/
    istestw: function (str) {
        if (/^\w+$/.test(str)) {
            return true;
        }
        return false;
    },
    /*判断用户名只能英文字母开头*/
    istesta_z: function (str) {
        if (!/^([a-z]|[A-Z])[0-9a-zA-Z_]+$/.test(str)) {
            return true;
        }
        return false;
    },
    /*是否是数字 返回true和false*/
    isnumber: function (str) {
        return /^[0-9]*$/.test(str);
    },
    /*int转型*/
    parseInt: function (str) {
        return parseInt(str);
    },
    /*Float转型*/
    parseFloat: function (str) {
        return parseFloat(str);
    },
    /*判断手机号码格式 返回true和false*/
    ishone: function (str) {
        return str.match(/^1[3|4|5|7|8|9][0-9]\d{4,8}$/);
    },
    /*验证邮箱格式 返回true和false*/
    isemail: function (str) {
        return str.match(/^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/);
    },
    /*验证银行卡号是否标准*/
    isbanck:function(str){
    	var strBin="10,18,30,35,37,40,41,42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,58,60,62,65,68,69,84,87,88,94,95,98,99";
    	if (strBin.indexOf(str.substring(0, 2))== -1) {
    		return true;
    	}
    	return false;
    },
    /*去除开始字符*/
    trimStart: function (trimStr) {
        if (!trimStr) { return this; }
        var temp = this;
        while (true) {
            if (temp.substr(0, trimStr.length) != trimStr) {
                break;
            }
            temp = temp.substr(trimStr.length);
        }
        return temp;
    },
    /*出去结束字符*/
    trimEnd: function (trimStr) {
        if (!trimStr) { return this; }
        var temp = this;
        while (true) {
            if (temp.substr(temp.length - trimStr.length, trimStr.length) != trimStr) {
                break;
            }
            temp = temp.substr(0, temp.length - trimStr.length);
        }
        return temp;
    },
    /*解码*/
    decode: function (str) {
        return decodeURIComponent(str);
    },
    UnixToDate: function (unixTime, isFull, timeZone) {
        /**              
             * 时间戳转换日期              
             * @param <int> unixTime    待时间戳(秒)              
             * @param <bool> isFull    返回完整时间(Y-m-d 或者 Y-m-d H:i:s)              
             * @param <int>  timeZone   时区              
             */
        if (typeof (timeZone) == 'number') {
            unixTime = parseInt(unixTime) + parseInt(timeZone) * 60 * 60;
        }
        var time = new Date(unixTime * 1000);
        var ymdhis = "";
        ymdhis += time.getUTCFullYear() + "-";
        ymdhis += (time.getUTCMonth() + 1) + "-";
        ymdhis += time.getUTCDate();
        if (isFull === true) {
            ymdhis += " " + time.getUTCHours() + ":";
            ymdhis += time.getUTCMinutes() + ":";
            ymdhis += time.getUTCSeconds();
        }
        return ymdhis;
    }
}
/*获取cookie值*/
var COM = COM || {};
COM.cookie = {
    /*获取cookie值*/
    get: function (b) {
        var reg = new RegExp("(?:^|;+|\\s+)\s*" + b + "=([^;]*)");
        var m = document.cookie.match(reg);
        return !m ? document.cookie.split('&')[2].split('=')[1] : decodeURIComponent(m[1]);
    },
    /*替换cookie值*/
    getOrigin: function (b) {
        var a = document.cookie.match(new RegExp("(^| )" + b + "=([^;]*)(;|$)"));
        return !a ? "" : (a[2])
    },
    /*存入cookie*/
    set: function (name, value, hour, path, domain) {
        if (hour) {
            var expire = new Date;
            expire.setTime(expire.getTime() + 36E5 * hour)
        }

        document.cookie = name + "=" + value + "; " + (hour ? "userid=" + expire.toGMTString() + "; " : "") + (path ? "path=" + path + "; " : "path=/; ") + (domain ? "domain=" + domain + ";" : "domain=" + document.domain + ";");
        return true
    },
    /*清除cookie*/
    del: function (a, b, c) {
        document.cookie = a + "=; userid=Mon, 26 Jul 1997 05:00:00 GMT; path=" + (c ? c : "/") + "; " + (b ? ("domain=" + b + ";") : "")
    }
};
/*浏览器收藏的功能*/
function addFavorite(title, url) {
    if (window.sidebar) { // Mozilla Firefox Bookmark
        window.sidebar.addPanel(title, url, "");
    } else if (document.all && window.external) { // IE Favorite
        try {
            window.external.AddFavorite(url, title);
        } catch (e) {
            window.external.addToFavoritesBar(url, title, 'slice');
        }
    } else if (window.opera) { // Opera 7+
        document.getElementById("addcollect").href = url;
        document.getElementById("addcollect").title = title;
        document.getElementById("addcollect").rel = "sidebar";
    } else {// others
        alert("浏览器不支持该操作，尝试快捷键 Ctrl + D !");
    }
};
/*Js实现Map对象 */
function Map() {
    var struct = function (key, value) {
        this.key = key;
        this.value = value;
    }
    var put = function (key, value) {
        for (var i = 0; i < this.arr.length; i++) {
            if (this.arr[i].key === key) {
                this.arr[i].value = value;
                return;
            }
        }
        this.arr[this.arr.length] = new struct(key, value);
    }
    var get = function (key) {
        for (var i = 0; i < this.arr.length; i++) {
            if (this.arr[i].key === key) {
                return this.arr[i].value;
            }
        }
        return null;
    }
    var remove = function (key) {
        var v;
        for (var i = 0; i < this.arr.length; i++) {
            v = this.arr.pop();
            if (v.key === key) {
                continue;
            }
            this.arr.unshift(v);
        }
    }
    var size = function () {
        return this.arr.length;
    }
    var isEmpty = function () {
        return this.arr.length <= 0;
    }
    this.arr = new Array();
    this.get = get;
    this.put = put;
    this.remove = remove;
    this.size = size;
    this.isEmpty = isEmpty;


    /* 使用实例
    var map = new Map();  
    map.put("re","redhacker");  
    map.put("do","douguoqiang");  
    map.put("gq","dougq");  
    alert("map的大小为：" + map.size())  
    alert("key为re的map中存储的对象为：" + map.get("re"));  
    map.remove("re");  
    alert("移除key为re的对象后，获取key为re的map中存储的对象为：" + map.get("re"));  
    alert("map移除一个元素后的大小为：" + map.size());  
    alert("map是否是一个空map:" + map.isEmpty());  
    
    */
}



