$(document).ready(function() {
	obj=new ExamPaperManagement();
	//use small pagination for modal
	$('#modal-select-subject ul.pagination').addClass('pagination-sm');
	$('#sidebar .active').removeClass('active');
	$('#menu-item-exampaper').addClass('active');
	$('#submenu-exampaper').collapse('show');
	$('#exampaper-list').addClass('text-primary');
	init();
	//search events
	searchSubject();
	handleFilter();
	tableDataEvents();
});
var filterSubject="";
function init()
{
	filterSubject=obj.getParam('subject');
	try {
		if(filterSubject=='ALL')
		{
			$('#filter-subject input').val("Tất Cả");
		}
		else
		{
			var sub=obj.getSubjectById(filterSubject);
			$('#filter-subject input').val(sub.code+'-'+sub.subjectName);
		}
		
		rootApiGet='manager/api/exampapers/page/subjects/'+filterSubject+'?';
		rootApiSearch='manager/api/exampapers/search/subjects/'+filterSubject+'?';
		//lay du lieu trang va phan trang
		handlePagination('pagination','table-data-body','row-data-container',rootApiGet,null);
		//lay du lieu trang va phan trang tim kiem mon hoc cho filter
		handlePagination('pagination-subject','table-data-body-subject','row-data-container-subject','api/subjects/page?');
		searchEvents('key-search','btn-search',rootApiSearch);
		//for xs
		searchEvents('key-search-xs','btn-search-xs',rootApiSearch);
		deleteEvents("manager/api/exampapers/multiple");
	} catch (e) {
		// TODO: handle exception
		alert('Xảy Ra Lỗi Khi Lấy Dữ Liệu. Vui Lòng Thử Lại Sau .');
	}
}

function handleFilter()
{
	//stop a link of filter
	$(document).on('click', '#filter a.dropdown-item', function (e) {
		e.preventDefault();
		event.stopPropagation();
	});
	//filter subject
	$(document).on('click', '#filter-subject a', function (e) {
		var value=$(this).attr('value');
		if(value=="ALL")
		{
			$('#filter-subject input').attr('val','ALL');

			filterSubject='ALL';
			window.location.href = rootLocation+'teacher/ql-de-thi/danh-sach?subject='+filterSubject;
		}
		else
		{
			$('#modal-select-subject').modal('show');
		}
	
		
	});
	
	$(document).on('click', '#modal-select-subject .btn-submit', function (e) {
		if(!($(this).hasClass( "disabled" )))
		{
			filterSubject=$('#modal-select-subject .selected').attr('dataId');
			$('#filter-subject input').attr('val',filterSubject);
			if(filterSubject==""||filterSubject==null||filterSubject==undefined) filterSubject='ALL';
			var type=$('#filter-type input').attr('val');
			var level=$('#filter-level input').attr('val');
			window.location.href = rootLocation+'teacher/ql-de-thi/danh-sach?subject='+filterSubject;
		}
	});
}

function tableDataEvents() {
	
	var dataId;
	// btn refresh
	$(document).on('click', '.btn-refresh', function() {
		location.reload();
	});
	// click table
	$(document).on('click', '#table-data-body [dataId]', function() {
		$('.btn-edit').removeClass('disabled');
		$('#table-data-body .selected').removeClass('selected');
		var id = $(this).attr("dataId");
		$(this).addClass('selected');

	});
	// table select subject click
	$(document).on('click', '#table-data-body-subject [dataId]', function() {
		$('#table-data-body-subject .selected').removeClass('selected');
		var id = $(this).attr("dataId");
		$(this).addClass('selected');
		$('#modal-select-subject .btn-submit').removeClass('disabled');
	});
	
	//edit btn click
	$(document).on('click', '.btn-edit', function () {
		var epId=$(this).parents('[dataId]').attr('dataId');
		window.location.href = rootLocation+'teacher/ql-de-thi/cap-nhat?id='+epId;
	});
	
	//btn-download
	$(document).on('click', '.btn-download', function (e) {
		dataId=$(this).parents('[dataId]').attr('dataId');
		
		$('#modal-download').modal('show');
		
		//window.location.href = rootLocation+'manager/downloadexampaper?id='+epId;
	});
	
	$(document).on('click', '#modal-download .btn-submit', function (e) {
		
		$('#modal-download').modal('hide');
		var exampapertype=$('#modal-download .exampaper-type-choosed');
		var type=$(exampapertype).attr('type');
		var border=$(exampapertype).attr('border');
		window.open(rootLocation+'manager/downloadexampaper?id='+dataId+'&type='+type+'&border='+border);
		//window.location.href = rootLocation+'manager/downloadexampaper?id='+epId;
	});
	
	$(document).on('click', '#modal-download .exampaper-type', function (e) {
		$('#modal-download .bg-success').removeClass('bg-success');
		$('#modal-download .exampaper-type-choosed').removeClass('bg-success');
		$(this).addClass('bg-success');
		$(this).addClass('exampaper-type-choosed');
		$('#modal-download .btn-submit').removeClass('disabled');
		//window.location.href = rootLocation+'manager/downloadexampaper?id='+epId;
	});
	$(document).on('click', '.btn-add-exampaper', function (e) {
		
		window.location.href = rootLocation+'teacher/ql-de-thi/them-moi';
	});

}
function searchSubject() {
	// get key
	$(document).on(
			'click',
			'#btn-search-subject',
			function() {
				var key = $('#key-search-subject').val();

				url = 'api/subjects/search?q=' + key + '&';
				handlePagination('pagination-subject',
						'table-data-body-subject',
						'row-data-container-subject', url);
			});
}
class ExamPaperManagement extends Base {
	
    constructor() {
    	super();
    }
 
}