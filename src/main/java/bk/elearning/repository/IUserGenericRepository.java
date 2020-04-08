package bk.elearning.repository;

import bk.elearning.entity.dto.PaginationResult;

public interface IUserGenericRepository<T> extends IGenericRepository<T>{

	T getByUsername(String username);
	
	PaginationResult<T> getPage(int start,int size);
	
	PaginationResult<T> search(String key,int start,int size);
}
