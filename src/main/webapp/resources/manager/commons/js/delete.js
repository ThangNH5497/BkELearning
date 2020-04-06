function deleteEvents(urlApi)
{
	//set color for row checked by add class checked
	$(document).on('click', '#table-data-body input[type="checkbox"]', function () {
		 var a=$(this).parents('tr');
         if($(this).prop("checked") == true){
             $(this).parents('tr').addClass('checked');
         }
         else if($(this).prop("checked") == false){
        	 $(this).parents('tr').removeClass('checked');
         }
     });
	 //select-all
	 $('#select-all').click(function(){
		 $('#table-data-body tr[dataId] input[type="checkbox"]').prop('checked',true);
		 $('#table-data-body tr[dataId]').addClass('checked');
     });
	 //de select-all
	 $('#deselect-all').click(function(){
		 $('#table-data-body input[type="checkbox"]').prop('checked',false);
		 $('#table-data-body tr').removeClass('checked');
     });
	// btn-delete click
	$('.btn-delete').on('click', function() {

	    if ($(this).hasClass('disabled')) {
	        return false;
	    } else {
	    	
	    	//get all input checked
	    	var items=$('#table-data-body tr input[type="checkbox"]:checked');
			
	    	//no selected
	    	if(items.length<=0)
			{
				$('#modal-delete-alert .btn-delete-alert-ok').click(this,function(){
					$('#modal-delete-alert').modal('hide');
				});
				$('#modal-delete-alert .message').text("Không Có Mục Được Chọn");
				$("#modal-delete-alert").modal('show');//show modal
			}
			else
			{
				$('#modal-delete-alert .message').text("Xóa "+items.length+" Mục Đã Chọn ?");
				$("#modal-delete-alert").modal('show');//show modal
				
				//event click ok
				$('#modal-delete-alert .btn-delete-alert-ok').click(this,function(){
					//close modal
					$('#modal-delete-alert').modal('hide');
					//list ids to delete
					var ids=[];
					var rowChecked=$('#table-data-body tr.checked');
					for(var i=0;i<rowChecked.length;i++)
					{
						ids.push($(rowChecked[i]).attr('dataId'));
					}
					$.ajax({
			        	method : "DELETE",
			            url : rootLocation+urlApi,
			            data : JSON.stringify(ids),
			            dataType : "json",
						contentType : "application/json; charset=utf-8",
			            success : function(data) {			               
			            },
			            errorr : function(err) {
			            	
			            }
			        });
					//delete row data in table
					//$(rowChecked).remove();
					 location.reload(true);
				});
			}
	    }
	});
}