var rootLocation;
const LOW_TO_HIGH = -1;
const  HIGH_TO_LOW= 1;
const SORT_DEFAULT=0;
var shoppingCartIds=[];// chua danh sach id san pham cua shoppingcart
var dataShoppingCart=[];// chua du lieu ve san pham trong shopping cart
class Base {
	
    constructor() {
    	var getUrl = window.location;
    	// for localhost
    	rootLocation = getUrl .protocol + "//" + getUrl.host + "/" + getUrl.pathname.split('/')[1];
    	rootLocation=rootLocation+"/";
    	// for real host
    	// rootLocation = getUrl .protocol + "//" + getUrl.host + "/";

    }

    InitEventsBase() {
    	
    }
    
    getData(ajxMethod,ajaxUrl,ajaxData,loadData) {
        var returnData = [];
        $.ajax({
            method: ajxMethod,
            url: rootLocation+ajaxUrl,
            async: false,
            data:JSON.stringify(ajaxData),
            dataType : "json",
			contentType : "application/json; charset=utf-8",
            beforeSend: function () {
               // $(loadData).show();
            },
            success: function (data) {
            	returnData=data;
            },
            error: function (data) {
            }
        });
        return returnData;
    }
    // ham lay tat ca san pham

}