<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<nav aria-label="Page navigation">
	<ul class="pagination justify-content-end">

		<li class="page-item disabled"><a class="page-link title">Hiển
				Thị 1-10/20</a></li>
		<li class="page-item disabled prePage"><a class="page-link"
			tabindex="-1">Previous</a></li>
		<div  style="display: flex;">
			<li class="page-item page-item-number active"><a
				class="page-link rounded-0">1</a></li>
			<li class="page-item page-item-number hidden"><a
				class="page-link">2</a></li>
			<li class="page-item page-item-number hidden"><a
				class="page-link">3</a></li>

			<li class="page-item page-item-number hidden"><a
				class="page-link">4</a></li>
			<li class="page-item page-item-number hidden"><a
				class="page-link rounded-0">5</a></li>
		</div>

		<li class="page-item disabled nextPage" ><a class="page-link">Next</a></li>
	</ul>
</nav>