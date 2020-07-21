<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="d-flex row mt-4 answer">
	<div class='col-2'>
		<label class="answer-index">Đáp án </label>
		<div class="form-group col-12 d-flex answer-check">
			<span>Đúng</span>
			<div class="custom-control custom-checkbox ml-3 ">
				<input type="checkbox" class="custom-control-input" id="check-">
				<label class="custom-control-label" for="check-"></label>
			</div>
		</div>
		<div class="form-group col-12 answer-weight hidden">
			<form>
				<div class="form-group">
					<label for="exampleFormControlSelect1">Trọng số điểm</label> <select
						class="form-control">
						<option value='1'>100%</option>
						<option value='0.9'>90%</option>
						<option  value='0.833333'>83.3333%</option>
						<option  value='0.8'>80%</option>
						<option value='0.75'>75%</option>
						<option value='0.7'>70%</option>
						<option value='0.666667'>66.6667%</option>
						<option value='0.6'>60%</option>
						<option value='0.5'>50%</option>
						<option value='0.4'>40%</option>
						<option value='0.333333'>33.3333%</option>
						<option value='0.3'>30%</option>
						<option value='0.25'>25%</option>
						<option value='0.2'>20%</option>
						<option value='0.166667'>16.6667%</option>
						<option value='0.142875'>14.2857%</option>
						<option value='0.125'>12.5%</option>
						<option value='0.111111'>11.1111%</option>
						<option value='0.1'>10%</option>
						<option value='0.05'>5%</option>
						<option selected value='0'>0%</option>
						<option value='-0.05'>-5%</option>
						<option value='-0.1'>-10%</option>
						<option value='-0.111111'>-11.1111%</option>
						<option value='-0.125'>-12.5%</option>
						<option value='-0.142875'>-14.2875%</option>
						<option value='-0.166667'>-16.6667%</option>
						<option value='-0.2'>-20%</option>
						<option value='-0.25'>-25%</option>
						<option value='-0.3'>-30%</option>
						<option value='-0.333333'>-33.3333%</option>
						<option value='-0.4'>-40%</option>
						<option value='-0.5'>-50%</option>
						<option value='-0.6'>-60%</option>
						<option value='-0.666667'>-66.6667%</option>
						<option value='-0.7'>-70%</option>
						<option value='-0.75'>-75%</option>
						<option value='-0.8'>-80%</option>
						<option value='-0.833333'>-83.3333%</option>
						<option value='-0.9'>-90%</option>
						<option value='-1'>-100%</option>
					</select>
				</div>
			</form>
		</div>
	</div>

	<div class="wrap-editor col-9">
		<div class="answer-editor" ></div>
	</div>
	<div class="col-1 justify-content-start">

		<button class="btn text-danger btn-control p-0 m-0 btn-remove-answer">
			<i class="fas fa-minus-square"></i>
		</button>
	</div>
</div>