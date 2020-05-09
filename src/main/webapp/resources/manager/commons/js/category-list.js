var obj;
$(document).ready(function() {

	obj=new Category();
	$('#sidebar .active').removeClass('active');
	$('#menu-item-question').addClass('active');
	$('#modal-select-subject ul.pagination').addClass('pagination-sm');
	$('#pageSubmenu').collapse('show');
	$('#category-list').addClass('text-primary');
	
	init();
	
	tableDataEvents();
	
	//lay du lieu trang va phan trang tim kiem mon hoc cho filter
	handlePagination('pagination-subject','table-data-body-subject','row-data-container-subject','api/subjects/page?');
	
	
	searchSubject();
	
	
	deleteEvents("manager/api/categorys/multiple");
	
	createCategoryEvents();
	
	editEvents();
});
function init()
{
	
	var subject=obj.getSubjectById(obj.getParam('subject'));
	if(subject!=undefined&&subject!=null&&subject!="")
	{
		$('#filter-subject input').val(subject.code+'-'+subject.subjectName);
		$('#filter-subject input').attr('subjectId',subject.id);
		showCategorys(subject.id);
		$('#modal-select-subject').modal('hide');
		$('#main-card').removeClass('hidden');
		
		
	}
	else
	{
		$('#main-card').addClass('hidden');
	}
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
function tableDataEvents() {
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
	//add btn click
	$(document).on('click', '.btn-add', function () {
		$('#modal-add-new').modal('show');
	});
	//edit btn click
	$(document).on('click', '.btn-edit', function () {
		
	});
	//select suject btn click
	$(document).on('click', '.btn-select-subject', function () {
		$('#modal-select-subject').modal('show');
	});
	
	//select subject
	$(document).on('click', '#modal-select-subject .btn-submit', function (e) {
		if(!($(this).hasClass( "disabled" )))
		{
			try {
				var subjectId=$('#modal-select-subject .selected').attr('dataId');
				var getUrl = window.location;
				var urlRedirect = getUrl.protocol + "//" + getUrl.host + "/" + getUrl.pathname.split('/')[1]+"/"+getUrl.pathname.split('/')[2];
				window.location.href = urlRedirect+'/ql-danh-muc?subject='+subjectId;
			} catch (e) {
				// TODO: handle exception
				$('#main-card').addClass('hidden');
				alert('Có Lỗi Xảy Ra . Xin Thử Lại Sau!');
			}
			
		}
	});

}
function showCategorys(subjectId)
{
	handlePagination('pagination','table-data-body','row-data-container','manager/api/categorys/page/subjects/'+subjectId+'?');
	searchEvents('key-search','btn-search','manager/api/categorys/search/subjects/'+subjectId+'?');
	//for xs
	searchEvents('key-search-xs','btn-search-xs','manager/api/categorys/search/subjects/'+subjectId+'?');
}
function createCategoryEvents()
{
	$(document).on('click', '#modal-add-new .btn-submit', function () {
		try {
			if($('#modal-add-new input').val()=="")
			{
				$('#modal-add-new input').addClass('border-danger');
				$('#modal-add-new input').addClass('border-danger');
				$('#modal-add-new label[name=name-error]').removeClass('hidden');
				
			}
			else {
				$('#modal-add-new').modal('hide');
				$('#modal-add-new input').removeClass('border-danger');
				$('#modal-add-new label[name=name-error]').addClass('hidden');
				
				var category={
						name:$('#modal-add-new input').val(),
						subject:{
							id: $('#filter-subject input').attr('subjectId')
						}
				}
				var message=obj.ajaxCall('POST',false,'manager/api/categorys',category,null);
				alert(message.msg);
				window.location.reload(true);
			}
		} catch (e) {
			// TODO: handle exception
			alert('Có Lỗi Xảy Ra . Vui Lòng Thử Lại !');
		}
		
	});
}
function editEvents()
{
	var category={};
	//open modal edit
	$(document).on('click', '.btn-edit', function(e) {
		if(!($(this).hasClass( "disabled" )))
		{
			$('#modal-edit').modal('show');
		}
		
	});
	$(document).on('show.bs.modal', '#modal-edit', function(e) {
		category = {
				id:$('#table-data-body .selected[dataId]').attr('dataId'),
				name:$('#table-data-body .selected[dataId] [field=name]').text()
		}
		$('#modal-edit input').val(category.name);
	});
	// event submit form
	$('#modal-edit .btn-submit').click(this,function() {
		try {
			if($('#modal-edit input').val()=="")
			{
				$('#modal-edit input').addClass('border-danger');
				$('#modal-edit input').addClass('border-danger');
				$('#modal-edit label[name=name-error]').removeClass('hidden');
				
			}
			else {
				$('#modal-edit').modal('hide');
				$('#modal-edit input').removeClass('border-danger');
				$('#modal-edit label[name=name-error]').addClass('hidden');
				
				category.name=$('#modal-edit input').val();
									
				var message=obj.ajaxCall('PUT',false,'manager/api/categorys',category,null);
				alert(message.msg);
				window.location.reload(true);
			}
		} catch (e) {
			// TODO: handle exception
		}
		
		
	});
}
class Category extends Base {
	
    constructor() {
    	super();
    } 
    
}
