package bk.elearning.service;

import java.util.ArrayList;
import java.util.List;

public interface IGenericService <T>{

	public T getById(int id);
	public List<T> getAll();
	public int save(T t);

	public int delete(int id);

	public int update(T t);

	public T getByCode(String code);
	
	public int deleteMultiple(ArrayList<Integer> ids);
}
