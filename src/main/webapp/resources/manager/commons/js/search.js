var key;
function searchEvents(rootUrlApi)
{
	//get key
	$(document).on('click', '#btn-search', function () {
		key=$('#key-search').val();

		$('#link-back-search').removeClass('hidden');
		url=rootUrlApi+'q='+key+'&';
		handlePagination('pagination','table-data-body','row-data-container',url);
	});
}