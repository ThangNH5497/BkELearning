package bk.elearning.repository.impl;

import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import bk.elearning.entity.Question;
import bk.elearning.entity.dto.CustomUserDetails;
import bk.elearning.entity.dto.PaginationResult;
import bk.elearning.entity.dto.QuestionFilter;
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
	@SuppressWarnings("rawtypes")
	public PaginationResult<Question> searchByTeacherAndFilter(String teacherId, String subjectId, String type,
			String level, String key, int start, int size) {
		// TODO Auto-generated method stub
		/*
		 * PaginationResult<Question> pages = new PaginationResult<Question>(); try {
		 * HashMap<String, Object> constrantFields = new HashMap<String, Object>(); if
		 * (!teacherId.equals("ALL")) { constrantFields.put("category.user.id",
		 * Integer.parseInt(teacherId)); }
		 * 
		 * if (!(subjectId.equals("ALL") || subjectId.equals("NONE"))) {
		 * constrantFields.put("category.subject.id", Integer.parseInt(subjectId)); }
		 * 
		 * if (!type.equals("ALL")) { constrantFields.put("type", type); }
		 * 
		 * if (!level.equals("ALL")) { constrantFields.put("level",
		 * Integer.parseInt(level)); }
		 * 
		 * HashMap<String, String> searchFields = new HashMap<String, String>();
		 * searchFields.put("id", key); searchFields.put("name", key);
		 * pages.setCount(super.getCount(constrantFields, searchFields));
		 * 
		 * pages.setData(super.search(constrantFields, searchFields, start * size,
		 * size)); return pages; } catch (Exception e) { // TODO: handle exception }
		 * 
		 * return null;
		 */
		Session session = sessionFactory.getCurrentSession();
		try {
			String hqlQuery = "FROM Question q where q.category.bankType=:bankType ";
			PaginationResult<Question> page = new PaginationResult<Question>();

			if (!teacherId.equals("ALL")) {
				hqlQuery += " AND q.category.user.id=:teacherId ";
			}

			if (!subjectId.equals("ALL")) {
				hqlQuery += " AND q.category.subject.id=:subjectId ";
			}
			if (!type.equals("ALL")) {
				hqlQuery += " AND q.type=:type ";
			}

			if (!level.equals("ALL")) {
				hqlQuery += " AND q.level=:level ";
			}

			hqlQuery += " AND (q.id like concat('%',:searchById,'%') or q.name like concat('%',:searchByName,'%'))";
			Query q = session.createQuery(hqlQuery);
			Query qCount = session.createQuery("SELECT COUNT(distinct q.id) " + hqlQuery);
			q.setParameter("bankType", Constant.BANK_TYPE_TEACHER);
			q.setParameter("searchById", key);
			q.setParameter("searchByName", key);
			qCount.setParameter("bankType", Constant.BANK_TYPE_TEACHER);
			qCount.setParameter("searchById", key);
			qCount.setParameter("searchByName", key);

			if (!teacherId.equals("ALL")) {
				q.setParameter("teacherId", Integer.parseInt(teacherId));
				qCount.setParameter("teacherId", Integer.parseInt(teacherId));
			}

			if (!subjectId.equals("ALL")) {
				q.setParameter("subjectId", Integer.parseInt(subjectId));
				qCount.setParameter("subjectId", Integer.parseInt(subjectId));
			}
			if (!type.equals("ALL")) {
				q.setParameter("type", type);
				qCount.setParameter("type", type);
			}

			if (!level.equals("ALL")) {
				q.setParameter("level", Integer.parseInt(level));
				qCount.setParameter("level", Integer.parseInt(level));
			}

			page.setCount((Long) qCount.uniqueResult());
			if (page.getCount() > 0)
				page.setData(q.list());
			return page;
		} catch (Exception e) {
			System.out.println(e.toString());
			// TODO: handle exception
			session.clear();
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
	@SuppressWarnings("rawtypes")
	@Override
	public PaginationResult<Question> getByTeacherAndFilter(String teacherId, String subjectId, String type,
			String level, int start, int size) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		try {
			String hqlQuery = "FROM Question q where q.category.bankType=:bankType ";
			PaginationResult<Question> page = new PaginationResult<Question>();

			if (!teacherId.equals("ALL")) {
				hqlQuery += " AND q.category.user.id=:teacherId ";
			}

			if (!subjectId.equals("ALL")) {
				hqlQuery += " AND q.category.subject.id=:subjectId ";
			}
			if (!type.equals("ALL")) {
				hqlQuery += " AND q.type=:type ";
			}

			if (!level.equals("ALL")) {
				hqlQuery += " AND q.level=:level ";
			}

			Query q = session.createQuery(hqlQuery);
			Query qCount = session.createQuery("SELECT COUNT(distinct q.id) " + hqlQuery);
			q.setParameter("bankType", Constant.BANK_TYPE_TEACHER);
			qCount.setParameter("bankType", Constant.BANK_TYPE_TEACHER);

			if (!teacherId.equals("ALL")) {
				q.setParameter("teacherId", Integer.parseInt(teacherId));
				qCount.setParameter("teacherId", Integer.parseInt(teacherId));
			}

			if (!subjectId.equals("ALL")) {
				q.setParameter("subjectId", Integer.parseInt(subjectId));
				qCount.setParameter("subjectId", Integer.parseInt(subjectId));
			}
			if (!type.equals("ALL")) {
				q.setParameter("type", type);
				qCount.setParameter("type", type);
			}

			if (!level.equals("ALL")) {
				q.setParameter("level", Integer.parseInt(level));
				qCount.setParameter("level", Integer.parseInt(level));
			}

			page.setCount((Long) qCount.uniqueResult());
			if (page.getCount() > 0)
				page.setData(q.list());
			return page;
		} catch (Exception e) {
			System.out.println(e.toString());
			// TODO: handle exception
			session.clear();
		}
		return null;
	}

	@Override
	@SuppressWarnings("rawtypes")
	public PaginationResult<Question> getPublicQuestion(String subjectId, String type, String level, int start,
			int size) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		try {
			String hqlQuery = "FROM Question q where q.category.bankType=:bankType ";
			PaginationResult<Question> page = new PaginationResult<Question>();

			if (!subjectId.equals("ALL")) {
				hqlQuery += " AND q.category.subject.id=:subjectId ";
			}
			if (!type.equals("ALL")) {
				hqlQuery += " AND q.type=:type ";
			}

			if (!level.equals("ALL")) {
				hqlQuery += " AND q.level=:level ";
			}

			Query q = session.createQuery(hqlQuery);
			Query qCount = session.createQuery("SELECT COUNT(distinct q.id) " + hqlQuery);
			q.setParameter("bankType", Constant.BANK_TYPE_ADMIN);
			qCount.setParameter("bankType", Constant.BANK_TYPE_ADMIN);
			if (!subjectId.equals("ALL")) {
				q.setParameter("subjectId", Integer.parseInt(subjectId));
				qCount.setParameter("subjectId", Integer.parseInt(subjectId));
			}
			if (!type.equals("ALL")) {
				q.setParameter("type", type);
				qCount.setParameter("type", type);
			}

			if (!level.equals("ALL")) {
				q.setParameter("level", Integer.parseInt(level));
				qCount.setParameter("level", Integer.parseInt(level));
			}

			page.setCount((Long) qCount.uniqueResult());
			if (page.getCount() > 0)
				page.setData(q.list());
			return page;
		} catch (Exception e) {
			System.out.println(e.toString());
			// TODO: handle exception
			session.clear();
		}
		return null;
	}

	@Override
	@SuppressWarnings("rawtypes")
	public PaginationResult<Question> searchPublicQuestion(String subjectId, String type, String level, String key,
			int start, int size) {
		Session session = sessionFactory.getCurrentSession();
		try {
			String hqlQuery = "FROM Question q where q.category.bankType=:bankType ";
			PaginationResult<Question> page = new PaginationResult<Question>();

			if (!subjectId.equals("ALL")) {
				hqlQuery += " AND q.category.subject.id=:subjectId ";
			}
			if (!type.equals("ALL")) {
				hqlQuery += " AND q.type=:type ";
			}

			if (!level.equals("ALL")) {
				hqlQuery += " AND q.level=:level ";
			}

			hqlQuery += " AND (q.id like concat('%',:searchById,'%') or q.name like concat('%',:searchByName,'%'))";
			Query q = session.createQuery(hqlQuery);
			Query qCount = session.createQuery("SELECT COUNT(distinct q.id) " + hqlQuery);
			q.setParameter("bankType", Constant.BANK_TYPE_ADMIN);
			q.setParameter("searchById", key);
			q.setParameter("searchByName", key);
			qCount.setParameter("bankType", Constant.BANK_TYPE_ADMIN);
			qCount.setParameter("searchById", key);
			qCount.setParameter("searchByName", key);
			if (!subjectId.equals("ALL")) {
				q.setParameter("subjectId", Integer.parseInt(subjectId));
				qCount.setParameter("subjectId", Integer.parseInt(subjectId));
			}
			if (!type.equals("ALL")) {
				q.setParameter("type", type);
				qCount.setParameter("type", type);
			}

			if (!level.equals("ALL")) {
				q.setParameter("level", Integer.parseInt(level));
				qCount.setParameter("level", Integer.parseInt(level));
			}

			page.setCount((Long) qCount.uniqueResult());
			if (page.getCount() > 0)
				page.setData(q.list());
			return page;
		} catch (Exception e) {
			System.out.println(e.toString());
			// TODO: handle exception
			session.clear();
		}
		return null;

	}

	@Override
	public ArrayList<Question> getRandomQuestion(QuestionFilter filter, CustomUserDetails user) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		try {
			String hqlQuery = "FROM Question q where q.category.subject.id=:subjectId ";
			if (user.getRole().equals(Constant.ROLE_ADMIN)) {
				hqlQuery += " AND q.category.bankType=:bankType ";
			}

			else if (user.getRole().equals(Constant.ROLE_TEACHER)) {
				hqlQuery += " AND q.category.user.id=:userId ";
			}
			
			if(filter.getTypes()!=null)
			{
				//for (String type : filter.getTypes()) {
					hqlQuery += " AND q.type IN (:types) ";
				//}
			}
			
			if(filter.getLevels()!=null)
			{
				hqlQuery += " AND q.level IN (:levels) ";
			}
			
			if(filter.getCategoryIds()!=null)
			{
				hqlQuery += " AND q.category.id IN (:categoryIds) ";
			}
			
			if(filter.getExistQuestionIds()!=null)
			{
				hqlQuery += " AND q.id NOT IN (:existQuestionIds) ";
			}
			hqlQuery+=" ORDER BY RAND()";
			Query q = session.createQuery(hqlQuery);
			q.setMaxResults(filter.getCount());
			q.setParameter("subjectId", filter.getSubjectId());
			if (user.getRole().equals(Constant.ROLE_ADMIN)) {
				q.setParameter("bankType", Constant.BANK_TYPE_ADMIN);
			}

			else if (user.getRole().equals(Constant.ROLE_TEACHER)) {
				q.setParameter("userId",user.getId());
			}
			
			if(filter.getTypes()!=null)
			{
				q.setParameterList("types", filter.getTypes());
			}
			
			if(filter.getLevels()!=null)
			{
				q.setParameterList("levels", filter.getLevels());
			}
			
			if(filter.getCategoryIds()!=null)
			{
				q.setParameterList("categoryIds", filter.getCategoryIds());
			}
			
			if(filter.getExistQuestionIds()!=null)
			{
				q.setParameterList("existQuestionIds", filter.getExistQuestionIds());
			}
			
			return (ArrayList<Question>) q.list();
			
		} catch (Exception e) {
			// TODO: handle exception
			session.clear();
			System.out.println("ex reponsitory : "+e.toString());
		}
		return null;
	}

	

}
