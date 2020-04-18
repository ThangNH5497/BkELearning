var key;
function searchEvents(inputId,buttonId,rootUrlApi)
{
	//get key
	$(document).on('click', '#'+buttonId, function () {
		key=$('#'+inputId).val();

		$('#link-back-search').removeClass('hidden');
		url=rootUrlApi+'q='+key+'&';
		handlePagination('pagination','table-data-body','row-data-container',url);
	});
}