package bk.elearning.repository;

import java.util.List;

public interface IGeneralRepository <T>{

	public List<T> query(String hqlQuery);
	public T getById(Class<T> clazz,int id);
	public List<T> getByIds(int ids[]);
	public List<T> getAll(Class<T> clazz);
	public int save(T t);
	public int delete(Class<T> clazz,int id);
	public int update(T t);
	public Long getCount(Class<T> clazz);
	List<T> getLimit(int start, int count, Class<T> clazz);
}
