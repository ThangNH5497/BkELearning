var key;
function searchEvents(rootUrlApi)
{
	//get key
	$(document).on('click', '#btn-search', function () {
		key=$('#key-search').val();
		var filter=$('#search-filter select').val();
		//search by code by default
		if(filter==""||filter==undefined) filter="code";
		$('#link-back-search').removeClass('hidden');
		url=rootUrlApi+'filter='+filter+'&key='+key+'&';
		handlePagination($('#pagination'),url);
	});
}