package bk.elearning.repository;

import java.util.HashMap;
import java.util.List;

public interface IGenericRepository<T> {

	public List<T> getAll();
	
	public List<T> getAll(int start,int size);

	public T getById( int id);

	public T getByCode( String code);

	public int save(T t);

	public int delete( int id);
	

	public int update(T t);

	public Long getCount();
	
}
