package bk.elearning.repository;

import java.util.List;

public interface IGeneralRepository <T>{

	public List<T> query(String hqlQuery);
	public List<T> getById(int id);
	public List<T> getByIds(int ids[]);
	public List<T> getAll();
	public List<T> getLimit(int start,int count);
	public int save(T t);
	public int delete(Class<T> clazz,int id);
	public int update(T t);
}
