<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script type="text/javascript">
	(function($) {

		"use strict";

		var fullHeight = function() {

			$('.js-fullheight').css('height', $(window).height());
			$(window).resize(function() {
				$('.js-fullheight').css('height', $(window).height());
			});

		};
		fullHeight();
		if($('#sidebar').hasClass("active"))
		{
			
			$('#content').css('width','100%');
		}
		else
		{
			$('#content').css('width','calc(100% - 240px)');
		}
		$('#sidebarCollapse').on('click', function() {
			$('#sidebar').toggleClass('active');

			if($('#sidebar').hasClass("active"))
			{
				
				$('#content').css('width','100%');
			}
			else
			{
				$('#content').css('width','calc(100% - 240px)');
			}
		});

	})(jQuery);
</script>
</body>
</html>