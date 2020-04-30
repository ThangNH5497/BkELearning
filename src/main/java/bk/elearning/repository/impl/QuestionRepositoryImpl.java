package bk.elearning.repository.impl;

import java.util.HashMap;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import bk.elearning.entity.Question;
import bk.elearning.entity.dto.PaginationResult;
import bk.elearning.repository.IQuestionRepository;
import bk.elearning.utils.Constant;

@Repository
@Transactional
public class QuestionRepositoryImpl extends SubjectComponentRepositoryImpl<Question> implements IQuestionRepository {

	@Autowired
	private SessionFactory sessionFactory;
	/**
	 * @return danh sách câu hỏi và số lượng phân trang , lọc theo giảng viên, môn
	 *         hoc, loại, độ khó và tìm kiếm theo key
	 * @param teacherId :id giảng viên
	 * @param subjectId id môn học
	 * @param type      loại câu hỏi
	 * @param level     độ khó câu hỏi
	 * @param key       từ khóa tìm kiếm (theo trường id hoặc name)
	 * @param start     index bắt đầu lấy
	 * @param size      số lượng bản ghi tối đa lấy
	 */
	@Override
	public PaginationResult<Question> searchByTeacherAndFilter(String teacherId, String subjectId, String type, String level,
			String key, int start, int size) {
		// TODO Auto-generated method stub
		PaginationResult<Question> pages = new PaginationResult<Question>();
		try {
			HashMap<String, Object> constrantFields = new HashMap<String, Object>();
			constrantFields.put("bankType", Constant.BANK_TYPE_PRIVATE);
			if (!teacherId.equals("ALL")) {
				constrantFields.put("teacher.id", Integer.parseInt(teacherId));
			}
			
			if (!(subjectId.equals("ALL")||subjectId.equals("NONE"))) {
				constrantFields.put("subject.id", Integer.parseInt(subjectId));
			}

			if (!type.equals("ALL")) {
				constrantFields.put("type", type);
			}
			
			if (!level.equals("ALL")) {
				constrantFields.put("level", Integer.parseInt(level));
			}

			HashMap<String, String> searchFields = new HashMap<String, String>();
			searchFields.put("id", key);
			searchFields.put("name", key);
			pages.setCount(super.getCount(constrantFields, searchFields));

			pages.setData(super.search(constrantFields, searchFields, start * size, size));
			return pages;
		} catch (Exception e) {
			// TODO: handle exception
		}

		return null;
	}

	/**
	 * @return danh sách câu hỏi và số lượng phân trang , lọc theo giảng viên, môn
	 *         hoc, loại, độ khó
	 * @param teacherId :id giảng viên
	 * @param subjectId id môn học
	 * @param type      loại câu hỏi
	 * @param level     độ khó câu hỏi
	 * @param start     index bắt đầu lấy
	 * @param size      số lượng bản ghi tối đa lấy
	 */
	@Override
	public PaginationResult<Question> getByTeacherAndFilter(String teacherId, String subjectId, String type, String level, int start,
			int size) {
		// TODO Auto-generated method stub
		try {
			PaginationResult<Question> page = new PaginationResult<Question>();
			HashMap<String, Object> constrantFields = new HashMap<String, Object>();
			constrantFields.put("bankType", Constant.BANK_TYPE_PRIVATE);
			if (!teacherId.equals("ALL")) {
				constrantFields.put("teacher.id", Integer.parseInt(teacherId));
			}
			
			if (!subjectId.equals("ALL")) {
				constrantFields.put("subject.id", Integer.parseInt(subjectId));
			}

			if (!type.equals("ALL")) {
				constrantFields.put("type", type);
			}
			
			if (!level.equals("ALL")) {
				constrantFields.put("level", Integer.parseInt(level));
			}
			page.setCount(super.getCount(constrantFields, null));
			page.setData(super.getWithConstraint(constrantFields, start * size, size));

			return page;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}


	@Override
	public PaginationResult<Question> getByBankTypeAndFilter(String subjectId, String type, String level, int start,
			int size) {
		// TODO Auto-generated method stub
		try {
			PaginationResult<Question> page = new PaginationResult<Question>();
			HashMap<String, Object> constrantFields = new HashMap<String, Object>();
			constrantFields.put("bankType", Constant.BANK_TYPE_PUBLIC);
			if (!subjectId.equals("ALL")) {
				constrantFields.put("subject.id", Integer.parseInt(subjectId));
			}

			if (!type.equals("ALL")) {
				constrantFields.put("type", type);
			}
			
			if (!level.equals("ALL")) {
				constrantFields.put("level", Integer.parseInt(level));
			}
			page.setCount(super.getCount(constrantFields, null));
			page.setData(super.getWithConstraint(constrantFields, start * size, size));

			return page;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	@Override
	public PaginationResult<Question> searchByBankTypeAndFilter(String subjectId, String type, String level, String key,
			int start, int size) {
		// TODO Auto-generated method stub
		PaginationResult<Question> pages = new PaginationResult<Question>();
		try {
			HashMap<String, Object> constrantFields = new HashMap<String, Object>();
			
			constrantFields.put("bankType", Constant.BANK_TYPE_PUBLIC);
			if (!(subjectId.equals("ALL")||subjectId.equals("NONE"))) {
				constrantFields.put("subject.id", Integer.parseInt(subjectId));
			}

			if (!type.equals("ALL")) {
				constrantFields.put("type", type);
			}
			
			if (!level.equals("ALL")) {
				constrantFields.put("level", Integer.parseInt(level));
			}

			HashMap<String, String> searchFields = new HashMap<String, String>();
			searchFields.put("id", key);
			searchFields.put("name", key);
			pages.setCount(super.getCount(constrantFields, searchFields));

			pages.setData(super.search(constrantFields, searchFields, start * size, size));
			return pages;
		} catch (Exception e) {
			// TODO: handle exception
		}

		return null;
	}


}
