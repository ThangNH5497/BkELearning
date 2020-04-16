//so luong data max tren 1 trang
const MAX_ITEMS = 10;
// so luong lien ket hien thi
const TOTAL_PAGE_SHOW=5;
// trang cuoi
var lastPage=1;
// trang dang hien thi truoc do,mac dinh bang 1 khi load trang
var prePage=1;
// trang duoc click
var currentPage=1;
//url api to get data
var urlApiData;
//count
var count=0;
var callBack;
var paginationId;//id pagination
var dataContainerId;//id của element chứa nội dung hiển thị dữ liệu
var rowDataContainerId;//element chứa hàng dữ liệu mẫu ban đầu để render dữ liệu


function handlePagination(paginationId,dataContainerId,rowDataContainerId,url,callBack) {
	urlApiData=url;
	currentPage=1;
	this.paginationId=paginationId;
	this.callBack=callBack;
	this.dataContainerId=dataContainerId;
	this.rowDataContainerId=rowDataContainerId;
	try {
		//lay du lieu
		var pageData=obj.ajaxCall('GET',false,urlApiData+"page="+currentPage+"&size="+MAX_ITEMS,null,null);
		
		// tinh tong so trang
		if (parseInt(pageData.count) % parseInt(MAX_ITEMS) != 0) {
			lastPage = (parseInt(pageData.count) / parseInt(MAX_ITEMS)) + 1;

		} else
			lastPage = (parseInt(pageData.count) / parseInt(MAX_ITEMS));
		lastPage = parseInt(lastPage);
		
		if(lastPage==0) lastPage=1;
		//khoi tao pagination
		var pages=$('#'+paginationId+' .page-item-number');
		$(pages).removeClass('hidden');
		$('#'+paginationId+' .active').removeClass('active');
		$(pages[0]).addClass('active');
		//danh so va loai bo nhung trang hien thi du thua neu co
		for (var i = 0; i < TOTAL_PAGE_SHOW; i++) {
			$(pages[i]).children().text(parseInt(i)+1);
			if(parseInt(i)+1>lastPage)
			{
				$(pages[i]).addClass('hidden');
			}
				
		}
		updatePagination(currentPage);
		// add events
		addEvents();
	} catch (e) {
		// TODO: handle exception
		console.log(e);
	}
	
}
function addEvents() {

	
	// click page number
	$(document).on('click', '#'+paginationId+' .page-item-number', function (e){
		currentPage=$(this).text();
		prePage=$('#'+paginationId+' .active a').text();
		if(currentPage!=prePage)
		{
			// update so cua trang hien thi phan trang
			updatePagination(currentPage);
		}
	});
	
	//prePage
	$(document).on('click', '#'+paginationId+' .prePage', function (e){
		if(currentPage>1)
		{
			currentPage=currentPage-1;
			updatePagination(currentPage);
		}
		// update du lieu trong table
		
	});
	
	//nextPage
	$(document).on('click',  '#'+paginationId+' .nextPage', function (e){
		if(currentPage<lastPage)
		{
			currentPage=parseInt(currentPage)+1;
			updatePagination(currentPage);
		}
		
		// update du lieu trong table
		
	});
}
function updatePagination(currentPage) {
	var pages=$('#'+paginationId+' .page-item-number');
	var mid=parseInt(TOTAL_PAGE_SHOW/2);
	
	//neu so trang vuot qua khoang dang hien thi
	if(lastPage>TOTAL_PAGE_SHOW)
	{
		if(currentPage>mid&&currentPage<parseInt(lastPage)-mid)
		{
			for (var i = -mid; i < parseInt(pages.length)-mid; i++) {
				$(pages[i+mid]).children().text(parseInt(currentPage)+i);
			}

		}
		else if(currentPage<=mid)
		{
			for (var i = 0; i < parseInt(TOTAL_PAGE_SHOW); i++) {
				$(pages[i]).children().text(parseInt(i)+1);
			}
		}
		else
		{
			
			for (var i = 0; i < parseInt(TOTAL_PAGE_SHOW); i++) {
				$(pages[i]).children().text(parseInt(lastPage)-parseInt(TOTAL_PAGE_SHOW)+parseInt(i)+1);
			}
		}
	}
	
	//remove class active
	$('#'+paginationId+' .active').removeClass('active');
	$('#'+paginationId+' .page-item-number a:contains('+currentPage+')').parent().addClass('active');
	
	//disable button prepage when page =1
	if(currentPage==1)
	{
		$( '#'+paginationId+' .prePage').addClass('disabled');
	}
	else $( '#'+paginationId+' .prePage').removeClass('disabled');
	//disable button nextpage when page =lastpage
	if(currentPage==lastPage)
	{
		$( '#'+paginationId+' .nextPage').addClass('disabled');
	}
	else $( '#'+paginationId+' .nextPage').removeClass('disabled');
	
	var pageData=obj.ajaxCall('GET',false,urlApiData+"page="+currentPage+"&size="+MAX_ITEMS);
	
	try {
		//update pagination-title
		var title='Hiển Thị ';
		if(currentPage==1)
		{
			title+='1-'+pageData.data.length+'/'+pageData.count;
		}
		else if(pageData.data.length==MAX_ITEMS)
		{
			title+=(parseInt((parseInt(currentPage)-1)*MAX_ITEMS)+1)+'-'+parseInt(currentPage)*MAX_ITEMS+'/'+pageData.count;
		}
		else if(pageData.data.length<MAX_ITEMS)
		{
			title+=(parseInt((parseInt(currentPage)-1)*MAX_ITEMS)+1)+'-'+(pageData.data.length+(parseInt(currentPage)-1)*MAX_ITEMS)+'/'+pageData.count;
		}
		$('#'+paginationId+' .title').text(title);
	} catch (e) {
		// TODO: handle exception
	}
	
	updateTableData(currentPage,pageData.data);
	
}
function updateTableData(currentPage,data)
{	
	//obj.initData('table-data-body','row-data-container',data);
	if(data!=""&&data!=null&&data!=undefined)
	{
		obj.initData(dataContainerId,rowDataContainerId,data);
	}
	else obj.initData(dataContainerId,rowDataContainerId,[]);
	try {
		callBack();
	} catch (e) {
		// TODO: handle exception
	}
		
}