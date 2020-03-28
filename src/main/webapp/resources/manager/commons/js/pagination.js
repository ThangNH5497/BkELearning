//so luong data max tren 1 trang
const MAX_ITEMS = 1;
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
function handlePagination(paginationContainer,url) {
	urlApiData=url;
	currentPage=1;
	
	try {
		//lay du lieu
		var pageData=obj.getData('GET',urlApiData+"page="+currentPage+"&size="+MAX_ITEMS);
		
		// tinh tong so trang
		if (parseInt(pageData.count) % parseInt(MAX_ITEMS) != 0) {
			lastPage = (parseInt(pageData.count) / parseInt(MAX_ITEMS)) + 1;

		} else
			lastPage = (parseInt(pageData.count) / parseInt(MAX_ITEMS));
		lastPage = parseInt(lastPage);
		
		if(lastPage==0) lastPage=1;
		//khoi tao pagination
		var pages=$('#pagination .page-item');
		$(pages).removeClass('hidden');
		$('#pagination .active').removeClass('active');
		$(pages[0]).addClass('active');
		//danh so va loai bo nhung trang hien thi du thua neu co
		for (var i = 0; i < TOTAL_PAGE_SHOW; i++) {
			$(pages[i]).children().text(parseInt(i)+1);
			if(parseInt(i)+1>lastPage)
			{
				$(pages[i]).addClass('hidden');
			}
				
		}
		updateTableData(currentPage,pageData.data);
		// add events
		addEvents();
	} catch (e) {
		// TODO: handle exception
	}
	
}
function addEvents() {

	// click
	$('#pagination .page-item').click(this,function(){
		currentPage=$(this).text();
		prePage=$('#pagination .active a').text();
		if(currentPage!=prePage)
		{
			// update so cua trang hien thi phan trang
			updatePagination(currentPage);
		}
	});
	
	//prePage
	$('#prePage').click(this,function(){
		if(currentPage>1)
		{
			currentPage=currentPage-1;
			updatePagination(currentPage);
		}
		// update du lieu trong table
		
	});
	//nextPage
	$('#nextPage').click(this,function(){
		if(currentPage<lastPage)
		{
			currentPage=parseInt(currentPage)+1;
			updatePagination(currentPage);
		}
		
		// update du lieu trong table
		
	});
}
function updatePagination(currentPage) {
	var pages=$('#pagination .page-item');
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
	$('#pagination .active').removeClass('active');
	$('#pagination .page-item a:contains('+currentPage+')').parent().addClass('active');
	
	//disable button prepage when page =1
	if(currentPage==1)
	{
		$('#prePage').addClass('disabled');
	}
	else $('#prePage').removeClass('disabled');
	//disable button nextpage when page =lastpage
	if(currentPage==lastPage)
	{
		$('#nextPage').addClass('disabled');
	}
	else $('#nextPage').removeClass('disabled');
	
	var pageData=obj.getData('GET',urlApiData+"page="+currentPage+"&size="+MAX_ITEMS);
	updateTableData(currentPage,pageData.data);
	
}
function updateTableData(currentPage,data)
{
	try {
		//update pagination-title
		var title='';
		if(currentPage==1)
		{
			title='1-'+data.length+'/'+countItems;
		}
		else if(data.length==MAX_ITEMS)
		{
			title=(parseInt((parseInt(currentPage)-1)*MAX_ITEMS)+1)+'-'+parseInt(currentPage)*MAX_ITEMS+'/'+countItems;
		}
		else if(data.length<MAX_ITEMS)
		{
			title=(parseInt((parseInt(currentPage)-1)*MAX_ITEMS)+1)+'-'+data.length+'/'+countItems;
		}
		$('#pagination-title').text(title);
	} catch (e) {
		// TODO: handle exception
	}
	
	
	obj.initData('table-data-body','row-data-container',data);

	

	
}