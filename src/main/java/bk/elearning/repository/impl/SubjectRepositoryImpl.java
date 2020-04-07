package bk.elearning.repository.impl;

import java.util.HashMap;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import bk.elearning.entity.Subject;
import bk.elearning.entity.dto.PaginationResult;
import bk.elearning.repository.ISubjectRepository;

@Transactional
@Repository
public class SubjectRepositoryImpl extends AbstractGenericRepository<Subject> implements ISubjectRepository {



	@Override
	public PaginationResult<Subject> search(String filter, String key, int start, int size) {
		// TODO Auto-generated method stub
		PaginationResult<Subject> pageResult = new PaginationResult<Subject>();
		try {
			HashMap<String, String> searchFields = new HashMap<String, String>();
			searchFields.put(filter, key);
			pageResult.setCount(super.getCount(null, searchFields));

			pageResult.setData(super.search(null, searchFields, start * size, size));

		} catch (Exception e) {
			// TODO: handle exception
		}
		return pageResult;
	}

	@Override
	public PaginationResult<Subject> getPage(int start, int size) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		PaginationResult<Subject> pageResult = new PaginationResult<Subject>();
		try {
			pageResult.setCount(super.getCount());

			pageResult.setData(super.getAll(start * size, size));

		} catch (Exception e) {
			// TODO: handle exception
		}
		return pageResult;
	}

}
