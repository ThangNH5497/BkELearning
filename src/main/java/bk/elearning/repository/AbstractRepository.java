package bk.elearning.repository;

import java.util.List;

public interface AbstractRepository<T> {

	public List<T> query(String sqlQuery);
	public List<T> queryWithParam(String sqlQuery,Object prams);
	public int saveEntity(T t);
	public int deleteEntity(Class<T> clazz,int id);
	public int updateEntity(T t);
}
