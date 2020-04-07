package bk.elearning.repository.impl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import bk.elearning.entity.Teacher;
import bk.elearning.repository.ITeacherRepository;

@SuppressWarnings("deprecation")
@Repository
@Transactional
public class TeacherRepositoryImpl extends UserGenericRepositoryImpl<Teacher> implements ITeacherRepository {

	@Autowired
	private SessionFactory sessionFactory;
	@Override
	public int delete(int id) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		try {
			String hqlQuery="UPDATE  Course c SET c.teacher=:teacher where c.teacher.id=:id";
			@SuppressWarnings("rawtypes")
			Query query=session.createQuery(hqlQuery);
			query.setParameter("teacher", null);
			query.setParameter("id", id);
			query.executeUpdate();
			return super.delete(id);
		} catch (Exception e) {
			System.out.println(e.toString());
			session.clear();
		}
		return 0;
	}
	

}
