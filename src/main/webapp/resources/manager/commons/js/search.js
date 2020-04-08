var key;
function searchEvents(rootUrlApi)
{
	//get key
	$(document).on('click', '#btn-search', function () {
		key=$('#key-search').val();

		$('#link-back-search').removeClass('hidden');
		url=rootUrlApi+'key='+key+'&';
		handlePagination($('#pagination'),url);
	});
}