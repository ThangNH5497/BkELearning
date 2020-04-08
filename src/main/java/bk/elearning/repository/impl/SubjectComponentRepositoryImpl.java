package bk.elearning.repository.impl;

import java.util.HashMap;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import bk.elearning.entity.dto.PaginationResult;
import bk.elearning.repository.ISubjectComponentRepository;

public class SubjectComponentRepositoryImpl<T> extends AbstractGenericRepository<T> implements ISubjectComponentRepository<T> {
	@Autowired
	SessionFactory sessionFactory;

	@Override
	public PaginationResult<T> findByTeacher(int teacherId, int start, int size) {
		// TODO Auto-generated method stub
		PaginationResult<T> page = new PaginationResult<T>();
		HashMap<String, Object> constrantFields = new HashMap<String, Object>();
		constrantFields.put("teacher.id", teacherId);
		page.setCount(super.getCount(constrantFields, null));
		page.setData(super.getWithConstraint(constrantFields, start * size, size));

		return page;
	}

	@Override
	public PaginationResult<T> findBySubject(int subjectId, int start, int size) {
		// TODO Auto-generated method stub
		PaginationResult<T> page = new PaginationResult<T>();
		HashMap<String, Object> constrantFields = new HashMap<String, Object>();
		constrantFields.put("subject.id", subjectId);
		page.setCount(super.getCount(constrantFields, null));
		page.setData(super.getWithConstraint(constrantFields, start * size, size));

		return page;
	}

	@Override
	public PaginationResult<T> searchByTeacher(int teacherId, String key, int start, int size) {
		// TODO Auto-generated method stub
		PaginationResult<T> pageResult = new PaginationResult<T>();
		try {
			HashMap<String, Object> constrantFields = new HashMap<String, Object>();
			constrantFields.put("teacher.id", teacherId);
			HashMap<String, String> searchFields = new HashMap<String, String>();
			searchFields.put("code", key);
			searchFields.put("courseName", key);
			pageResult.setCount(super.getCount(constrantFields, searchFields));

			pageResult.setData(super.search(constrantFields, searchFields, start * size, size));

		} catch (Exception e) {
			// TODO: handle exception
		}
		return pageResult;
	}

	@Override
	public PaginationResult<T> searchBySubject(int subjectId, String key, int start, int size) {
		// TODO Auto-generated method stub
		PaginationResult<T> pageResult = new PaginationResult<T>();
		try {
			HashMap<String, Object> constrantFields = new HashMap<String, Object>();
			constrantFields.put("subject.id", subjectId);
			HashMap<String, String> searchFields = new HashMap<String, String>();
			searchFields.put("code", key);
			searchFields.put("courseName", key);
			pageResult.setCount(super.getCount(constrantFields, searchFields));

			pageResult.setData(super.search(constrantFields, searchFields, start * size, size));

		} catch (Exception e) {
			// TODO: handle exception
		}
		return pageResult;
	}

	

}
