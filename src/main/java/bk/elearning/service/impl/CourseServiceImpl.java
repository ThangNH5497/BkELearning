package bk.elearning.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bk.elearning.entity.Course;
import bk.elearning.entity.dto.PaginationResult;
import bk.elearning.repository.ICourseRepository;
import bk.elearning.repository.ISubjectRepository;
import bk.elearning.service.ICourseService;

@Service
public class CourseServiceImpl implements ICourseService {

	@Autowired
	ICourseRepository courseRepository;
	
	@Autowired
	ISubjectRepository subjectRepository;

	@Override
	public Course getById(int id) {
		// TODO Auto-generated method stub
		return courseRepository.getById( id);
	}

	@Override
	public List<Course> getAll() {
		// TODO Auto-generated method stub
		return  courseRepository.getAll();
	}

	@Override
	public int save(Course t) {
		// TODO Auto-generated method stub

		return courseRepository.save(t);
	}

	@Override
	public int delete(int id) {
		// TODO Auto-generated method stub
		return courseRepository.delete( id);
	}

	@Override
	public int update(Course t) {
		// TODO Auto-generated method stub
		Course courseUpdate=courseRepository.getById(t.getId());
		courseUpdate.setCode(t.getCode());
		courseUpdate.setDescriptor(t.getDescriptor());
		courseUpdate.setCourseName(t.getCourseName());
		courseUpdate.setTeacher(t.getTeacher());
		return courseRepository.update(courseUpdate);
	}

	@Override
	public Course getByCode(String code) {
		// TODO Auto-generated method stub
		return courseRepository.getByCode( code);
	}

	@Override
	public int deleteMultiple(ArrayList<Integer> ids) {
		// TODO Auto-generated method stub
		int checkErro = 0;
		for (Integer integer : ids) {
			try {
				courseRepository.delete( integer);
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
	public PaginationResult<Course> getPage(int page, int size) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PaginationResult<Course> getSearchPage(String filter, String key, int page, int size) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PaginationResult<Course> getPageBySubject(int subjectId, int page, int size) {
		// TODO Auto-generated method stub
		PaginationResult<Course> pageResult = new PaginationResult<Course>();
		try {
			HashMap<String, Object> constrantFields=new HashMap<String, Object>();
			constrantFields.put("subject.id", subjectId);
			pageResult.setCount(courseRepository.getCount(constrantFields,null));
			if (page > 0) {
				pageResult.setData(courseRepository.getWithConstraint(constrantFields, (page - 1) * size, size));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return pageResult;
	}


	@Override
	public PaginationResult<Course> getPageByTeacher(int teacherId, int page, int size) {
		// TODO Auto-generated method stub
		PaginationResult<Course> pageResult = new PaginationResult<Course>();
		try {
			HashMap<String, Object> constrantFields=new HashMap<String, Object>();
			constrantFields.put("teacher.id", teacherId);
		//	HashMap<String, String> searchFields=new HashMap<String, String>();
			//searchFields.put(filter, key);
			pageResult.setCount(courseRepository.getCount(constrantFields,null));
			if (page > 0) {
				pageResult.setData(courseRepository.getWithConstraint(constrantFields, (page - 1) * size, size));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return pageResult;
	}

	@Override
	public PaginationResult<Course> searchBySubject(int subjectId, String filter, String key, int page, int size) {
		// TODO Auto-generated method stub
		PaginationResult<Course> pageResult = new PaginationResult<Course>();
		try {
			HashMap<String, Object> constrantFields=new HashMap<String, Object>();
			constrantFields.put("subject.id", subjectId);
			HashMap<String, String> searchFields=new HashMap<String, String>();
			searchFields.put(filter, key);
			pageResult.setCount(courseRepository.getCount(constrantFields,searchFields));
			if (page > 0) {
				pageResult.setData(courseRepository.search(constrantFields,searchFields, (page - 1) * size, size));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return pageResult;
	}

	@Override
	public PaginationResult<Course> searchByTeacher(int teacherId, String filter, String key, int page, int size) {
		// TODO Auto-generated method stub
		PaginationResult<Course> pageResult = new PaginationResult<Course>();
		try {
			HashMap<String, Object> constrantFields=new HashMap<String, Object>();
			constrantFields.put("teacher.id", teacherId);
			HashMap<String, String> searchFields=new HashMap<String, String>();
			searchFields.put(filter, key);
			pageResult.setCount(courseRepository.getCount(constrantFields,searchFields));
			if (page > 0) {
				pageResult.setData(courseRepository.search(constrantFields,searchFields, (page - 1) * size, size));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return pageResult;
	}


}
