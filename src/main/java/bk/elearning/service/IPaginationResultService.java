package bk.elearning.service;

import bk.elearning.entity.dto.PaginationResult;

public interface IPaginationResultService<T> {

	public PaginationResult<T> getPage(int page,int size);
	public PaginationResult<T> getSearchPage(String filter, String key, int page, int size);
}
