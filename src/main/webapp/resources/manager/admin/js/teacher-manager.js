$(document).ready(function() {

	obj=new TeacherManager();
	data=obj.getAllTeachers();
	obj.initData('table-data-body','row-data-container',data);
	listennerEvents();
	
	
});
var data=[];
var obj;
function listennerEvents()
{
	$('#table-data-body [dataId]').click(this,function(){
		$('.selected').removeClass('selected');
		var id=$(this).attr("dataId");
		$(this).addClass('selected');
		
		//load anh
		$('.profile-img img').removeClass('hidden');
		if(data[parseInt(id)-1].image!="")
		{
			$('.profile-img img').attr("src",rootLocation+data[parseInt(id)-1].image);
		}
		else $('.profile-img img').attr("src",rootLocation+"resources/commons/image/user-default.jpg");
		//truyen tham so là 1 mang chua 1 phan tu user co chi so -1 do id bat dau bang 1
		obj.initData('user-detail','user-detail-row',[data[parseInt(id)-1]]);
	});
}

class TeacherManager extends Base {
	
    constructor() {
    	super();

    }
    
    
}