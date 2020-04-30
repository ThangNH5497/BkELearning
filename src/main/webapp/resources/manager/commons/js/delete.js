function deleteEvents(urlApi)
{
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
			            async:false,
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