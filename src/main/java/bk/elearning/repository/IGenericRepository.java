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

	public List<T> getWithConstraint(HashMap<String, Object> constrantFields,int start,int size);
	//count record
	public Long getCount();

	// count by constraints and search
	Long getCount(HashMap<String, Object> constrantFields,HashMap<String, String> searchFields);

	//// count with constraints
	public List<T> search(HashMap<String, Object> constrantFields,HashMap<String, String> searchFields, int start, int size);
	
}
