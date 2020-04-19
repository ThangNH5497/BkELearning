$(document).ready(function() {

	obj=new Home();
	$('#sidebar .active').removeClass('active');
	$('#menu-item-home').addClass('active');
	$('#search-form').remove();
	$('#search-xs').remove();
});


class Home extends Base {
	
    constructor() {
    	super();
    } 
    
}