package bk.elearning.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bk.elearning.entity.Subject;
import bk.elearning.entity.Teacher;
import bk.elearning.entity.User;
import bk.elearning.entity.dto.PaginationResult;
import bk.elearning.repository.ISubjectRepository;
import bk.elearning.service.ISubjectService;

@Service
public class SubjectServiceImpl implements ISubjectService {

	@Autowired
	ISubjectRepository subjectRepository;

	@Override
	public Subject getById(int id) {
		// TODO Auto-generated method stub
		return subjectRepository.getById(id);
	}

	@Override
	public List<Subject> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int save(Subject t) {
		// TODO Auto-generated method stub
		t.setCourses(null);
		t.setExams(null);
		t.setQuestions(null);
		return subjectRepository.save(t);
	}

	@Override
	public int delete(int id) {
		// TODO Auto-generated method stub
		return subjectRepository.delete(id);
	}

	@Override
	public int update(Subject t) {
		// TODO Auto-generated method stub
		Subject subjectUpdate = subjectRepository.getById(t.getId());
		subjectUpdate.setCode(t.getCode());
		subjectUpdate.setDescriptor(t.getDescriptor());
		subjectUpdate.setSubjectName(t.getSubjectName());
		return subjectRepository.update(subjectUpdate);
	}

	@Override
	public Subject getByCode(String code) {
		// TODO Auto-generated method stub
		return subjectRepository.getByCode(code);
	}

	@Override
	public int deleteMultiple(ArrayList<Integer> ids) {
		// TODO Auto-generated method stub
		int checkErro = 0;
		for (Integer integer : ids) {
			try {
				subjectRepository.delete(integer);
			} catch (Exception e) {
				// TODO: handle exception
				checkErro++;
			}
		}
		if (checkErro == 0)
			return 1;
		return 1;
	}

	@Override
	public PaginationResult<Subject> getPage(int page, int size) {
		// TODO Auto-generated method stub
		PaginationResult<Subject> pageResult = new PaginationResult<Subject>();
		try {
			pageResult.setCount(subjectRepository.getCount());
			if (page > 0) {
				pageResult.setData(subjectRepository.getAll((page - 1) * size, size));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return pageResult;
	}

	@Override
	public PaginationResult<Subject> getSearchPage(String filter, String key, int page, int size) {
		// TODO Auto-generated method stub
		PaginationResult<Subject> pageResult = new PaginationResult<Subject>();

		try {
			HashMap<String, String> searchFields = new HashMap<String, String>();
			searchFields.put(filter, key);
			pageResult.setCount(subjectRepository.getCount(null, searchFields));
			if (page > 0) {
				pageResult.setData(subjectRepository.search(null, searchFields, (page - 1) * size, size));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return pageResult;
	}

}
