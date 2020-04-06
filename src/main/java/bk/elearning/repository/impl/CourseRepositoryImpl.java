package bk.elearning.repository.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import bk.elearning.entity.Course;
import bk.elearning.repository.ICourseRepository;

@Transactional
@SuppressWarnings("rawtypes")
@Repository
public class CourseRepositoryImpl extends AbstractGenericRepository<Course> implements ICourseRepository {

	@Autowired
	SessionFactory sessionFactory;


	@Override
	public Long getCount(String fieldContraint, Object fieldContraintValue, String fieldSearch, String key) {
		// TODO Auto-generated method stub
		String hqlQuery = "select count(distinct c.id) FROM Course c where c." + fieldContraint
				+ "=:fieldContraintValue and c." + fieldSearch + " like concat('%',:key,'%')";
		Session session = null;
		Long count=0l;
		try {
			session = sessionFactory.getCurrentSession();
			Query query = session.createQuery(hqlQuery);
			query.setParameter("fieldContraint", fieldContraintValue);
			query.setParameter(fieldSearch, key);
			count=(Long)query.uniqueResult();
		} catch (Exception e) {
			// TODO: handle exception
			session.clear();
		}
		return null;
	}

	@Override
	public List<Course> search(String fieldContraint, Object fieldContraintValue, String fieldSearch, String key,
			int start, int size) {
		// TODO Auto-generated method stub
		String hqlQuery = "FROM Course c where c." + fieldContraint + "=:fieldContraintValue and c." + fieldSearch
				+ " like concat('%',:key,'%')";
		List<Course> list = null;
		Session session = null;
		try {
			session = sessionFactory.getCurrentSession();
			Query query = session.createQuery(hqlQuery);
			query.setParameter("fieldContraint", fieldContraintValue);
			query.setParameter(fieldSearch, key);
			list=query.list();
		} catch (Exception e) {
			session.clear();
		}
		return list;
	}

}
