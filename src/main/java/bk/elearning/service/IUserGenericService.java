package bk.elearning.service;

import org.springframework.web.multipart.MultipartFile;

import bk.elearning.entity.dto.PaginationResult;

public interface IUserGenericService<T> extends IGenericService<T>{

	public T getByUsername(String username);
	//luu voi anh
	public int save(T t,MultipartFile file);
	public int update(T t, MultipartFile file);
	public int[] saveFromFile(MultipartFile file);
	
	public PaginationResult<T> getPage(int page,int size);
	public PaginationResult<T> getSearchPage(String key, int page, int size);
}
