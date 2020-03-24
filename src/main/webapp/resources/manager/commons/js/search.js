var key;
function searchEvents(rootUrlApi)
{
	//select type search click
	$(document).on('click', '#search-filter li', function () {
		$('#search-filter-value').attr('value',$(this).attr('name'));
		$('#search-filter-value').html($(this).children().text()+'  <i class="fas fa-caret-down"></i>');
	});
	//get key
	$(document).on('click', '#btn-search', function () {
		key=$('#key-search').val();
		var type=$('#search-filter-value').attr('value');
		rootUrlApi="teacher/search/";
		var url=rootUrlApi+'count?type='+type+'&key='+key;
		var count=obj.getData('GET',url);
		if(count>0)
		{
			
		}
		url=rootUrlApi+'?type='+type+'&key='+key+'&';
		handlePagination($('#pagination'),count,url);
	});
}