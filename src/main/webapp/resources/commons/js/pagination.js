//so luong data max tren 1 trang
const MAX_ITEMS = 10;
// so luong lien ket hien thi
const TOTAL_PAGE_SHOW=5;
function handlePagination(paginationId,dataContainerId,rowDataContainerId,url,callBack) {
	
	var object={
			// trang cuoi
			 lastPage:1,
			// trang; dang hien thi truoc do,mac dinh bang 1 khi load trang
			 prePage:1,
			// tran g duoc click
			 currentPage:1,
			//url api to get data
			 urlApiData:url,
			//count
			 count:0,
			 callBack:callBack,
			 paginationId:paginationId,//id pagination
			 dataContainerId:dataContainerId,//id của element chứa nội dung hiển thị dữ liệu
			 rowDataContainerId:rowDataContainerId//element chứa hàng dữ liệu mẫu ban đầu để render dữ liệu
	}
	try {
		//lay du lieu
		var pageData=obj.ajaxCall('GET',false,object.urlApiData+"page="+object.currentPage+"&size="+MAX_ITEMS,null,null);
		if(pageData!=""&&pageData!=null&&pageData!=undefined)
		{
			// tinh tong so trang
			if (parseInt(pageData.count) % parseInt(MAX_ITEMS) != 0) {
				object.lastPage = (parseInt(pageData.count) / parseInt(MAX_ITEMS)) + 1;

			} else
				object.lastPage = (parseInt(pageData.count) / parseInt(MAX_ITEMS));
			object.lastPage = parseInt(object.lastPage);
			
			if(object.lastPage==0) object.lastPage=1;
			//khoi tao pagination
			var pages=$('#'+object.paginationId+' .page-item-number');
			$(pages).removeClass('hidden');
			$('#'+object.paginationId+' .active').removeClass('active');
			$(pages[0]).addClass('active');
			//danh so va loai bo nhung trang hien thi du thua neu co
			for (var i = 0; i < TOTAL_PAGE_SHOW; i++) {
				$(pages[i]).children().text(parseInt(i)+1);
				if(parseInt(i)+1>object.lastPage)
				{
					$(pages[i]).addClass('hidden');
				}
					
			}
			updatePagination(object);
			// add events
			addEvents(object);
		}
		
	} catch (e) {
		// TODO: handle exception
		console.log(e);
	}
	
}
function addEvents(object) {

	
	// click page number
	$(document).on('click', '#'+object.paginationId+' .page-item-number', function (e){
		object.currentPage=$(this).text();
		object.prePage=$('#'+object.paginationId+' .active a').text();
		if(object.currentPage!=object.prePage)
		{
			// update so cua trang hien thi phan trang
			updatePagination(object);
		}
	});
	
	//prePage
	$(document).on('click', '#'+object.paginationId+' .prePage', function (e){
		if(object.currentPage>1)
		{
			object.currentPage=object.currentPage-1;
			updatePagination(object);
		}
		// update du lieu trong table
		
	});
	
	//nextPage
	$(document).on('click',  '#'+object.paginationId+' .nextPage', function (e){
		if(object.currentPage<object.lastPage)
		{
			object.currentPage=parseInt(object.currentPage)+1;
			updatePagination(object);
		}
		
		// update du lieu trong table
		
	});
}
function updatePagination(object) {
	var pages=$('#'+object.paginationId+' .page-item-number');
	var mid=parseInt(TOTAL_PAGE_SHOW/2);
	
	//neu so trang vuot qua khoang dang hien thi
	if(object.lastPage>TOTAL_PAGE_SHOW)
	{
		if(object.currentPage>mid&&object.currentPage<parseInt(object.lastPage)-mid)
		{
			for (var i = -mid; i < parseInt(pages.length)-mid; i++) {
				$(pages[i+mid]).children().text(parseInt(object.currentPage)+i);
			}

		}
		else if(object.currentPage<=mid)
		{
			for (var i = 0; i < parseInt(TOTAL_PAGE_SHOW); i++) {
				$(pages[i]).children().text(parseInt(i)+1);
			}
		}
		else
		{
			
			for (var i = 0; i < parseInt(TOTAL_PAGE_SHOW); i++) {
				$(pages[i]).children().text(parseInt(object.lastPage)-parseInt(TOTAL_PAGE_SHOW)+parseInt(i)+1);
			}
		}
	}
	
	//remove class active
	$('#'+object.paginationId+' .active').removeClass('active');
	$('#'+object.paginationId+' .page-item-number a:contains('+object.currentPage+')').parent().addClass('active');
	
	//disable button prepage when page =1
	if(object.currentPage==1)
	{
		$( '#'+object.paginationId+' .prePage').addClass('disabled');
	}
	else $( '#'+object.paginationId+' .prePage').removeClass('disabled');
	//disable button nextpage when page =lastpage
	if(object.currentPage==object.lastPage)
	{
		$( '#'+object.paginationId+' .nextPage').addClass('disabled');
	}
	else $( '#'+object.paginationId+' .nextPage').removeClass('disabled');
	
	var pageData=obj.ajaxCall('GET',false,object.urlApiData+"page="+object.currentPage+"&size="+MAX_ITEMS);
	
	try {
		//update pagination-title
		var title='Hiển Thị ';
		if(object.currentPage==1)
		{
			title+='1-'+pageData.data.length+'/'+pageData.count;
		}
		else if(pageData.data.length==MAX_ITEMS)
		{
			title+=(parseInt((parseInt(object.currentPage)-1)*MAX_ITEMS)+1)+'-'+parseInt(object.currentPage)*MAX_ITEMS+'/'+pageData.count;
		}
		else if(pageData.data.length<MAX_ITEMS)
		{
			title+=(parseInt((parseInt(object.currentPage)-1)*MAX_ITEMS)+1)+'-'+(pageData.data.length+(parseInt(object.currentPage)-1)*MAX_ITEMS)+'/'+pageData.count;
		}
		$('#'+object.paginationId+' .title').text(title);
	} catch (e) {
		// TODO: handle exception
	}
	
	updateTableData(object,pageData.data);
	
}
function updateTableData(object,data)
{	
	//obj.initData('table-data-body','row-data-container',data);
	if(data!=""&&data!=null&&data!=undefined)
	{
		obj.initData(object.dataContainerId,object.rowDataContainerId,data);
	}
	else obj.initData(object.dataContainerId,object.rowDataContainerId,[]);
	try {
		object.callBack();
	} catch (e) {
		// TODO: handle exception
	}
		
}