package bk.elearning.repository.impl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import bk.elearning.entity.Student;
import bk.elearning.entity.dto.PaginationResult;
import bk.elearning.repository.IStudentRepository;

@SuppressWarnings("deprecation")
@Repository
@Transactional
public class StudentRepositoryImpl extends UserGenericRepositoryImpl<Student> implements IStudentRepository {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public PaginationResult<Student> searchByCourse(int courseId, String key, int start, int size) {
		// TODO Auto-generated method stub
		PaginationResult<Student> pageResult = new PaginationResult<Student>();
		Session session=sessionFactory.getCurrentSession();
		try {
			String hqlQuery="from Student t JOIN t.studentCourses tc where tc.course.id=:courseId AND (t.code like concat('%',:key,'%') or t.fullName like concat('%',:key,'%'))";
			Query query=session.createQuery("select count(distinct t.id) "+hqlQuery);
			query.setParameter("key", key);
			query.setParameter("courseId", courseId);
			Long count=(Long) query.uniqueResult();
			if(count>0)
			{
				pageResult.setCount(count);
				query=session.createQuery("SELECT t "+hqlQuery);
				query.setFirstResult(start);
				query.setMaxResults(size);
				query.setParameter("key", key);
				query.setParameter("courseId", courseId);
				pageResult.setData(query.list());
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.toString());
			session.clear();
		}
		return pageResult;
	}

	@Override
	public PaginationResult<Student> getPageByCourse(int courseId, int start, int size) {
		// TODO Auto-generated method stub
		PaginationResult<Student> pageResult = new PaginationResult<Student>();
		Session session=sessionFactory.getCurrentSession();
		try {
			String hqlQuery=" from Student t JOIN t.studentCourses tc where tc.course.id=:courseId ";
			Query query=session.createQuery("select count(distinct t.id) "+hqlQuery);
			query.setParameter("courseId", courseId);
			Long count=(Long) query.uniqueResult();
			if(count>0)
			{
				pageResult.setCount(count);
				query=session.createQuery("SELECT t "+hqlQuery);
				query.setParameter("courseId", courseId);
				query.setFirstResult(start);
				query.setMaxResults(size);
				pageResult.setData(query.list());
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.toString());
			session.clear();
		}
		return pageResult;
	}

	

}
