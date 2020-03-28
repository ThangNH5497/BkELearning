package bk.elearning.repository;

import java.util.List;

import org.hibernate.query.Query;

import bk.elearning.entity.Teacher;

public interface IGeneralRepository<T> {

	public Query query(String hqlQuery);

	public T getById(Class<T> clazz, int id);

	public List<T> getByIds(int ids[]);

	public List<T> getAll(Class<T> clazz);

	public int save(T t);

	public int delete(Class<T> clazz, int id);

	public int update(T t);

	public Long getCount(Class<T> clazz);

	List<T> getLimit(int start, int count, Class<T> clazz);

	// tong ban ghi tim kiem
	public Long getCount(Class<T> clazz, String contraintField, String key);

	// searchs
	List<T> search(Class<T> clazz, String contraintField, String key, int start, int size);
}
