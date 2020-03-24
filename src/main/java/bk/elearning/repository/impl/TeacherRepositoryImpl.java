package bk.elearning.repository.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import bk.elearning.entity.Teacher;
import bk.elearning.repository.ITeacherRepository;

@Repository
@Transactional
public class TeacherRepositoryImpl extends GeneralRepositoryImpl<Teacher> implements ITeacherRepository {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Teacher> getByIds(int[] ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Teacher getByUsername(String username) {
		// TODO Auto-generated method stub
		String hqlQuery = "from Teacher t where t.username=:param";
		Teacher teacher = null;
		try {
			Session session = this.sessionFactory.getCurrentSession();
			Query q = session.createQuery(hqlQuery);
			q.setParameter("param", username);
			teacher = (Teacher) q.list().get(0);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("ex: "+e.toString());
		}
		return teacher;
	}

	@Override
	public Teacher getByCode(String code) {

		String hqlQuery = "from Teacher t where t.code=:param";
		Teacher teacher = null;
		try {
			Session session = this.sessionFactory.getCurrentSession();
			Query q = session.createQuery(hqlQuery);
			q.setParameter("param", code);
			teacher = (Teacher) q.list().get(0);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return teacher;
	}

	@Override
	public List<Teacher> searchTeachers(String type, String key, int start, int size) {
		// TODO Auto-generated method stub
		String hqlQuery =  " FROM Teacher t WHERE t."+type+" like concat('%',:param,'%')";
		List<Teacher> teachers = null;
		try {
			Session session = this.sessionFactory.getCurrentSession();
			Query q=session.createQuery(hqlQuery);
			q.setParameter("param", key);
			q.setFirstResult(start);
			q.setMaxResults(size);
			teachers=q.list();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return teachers;
	}

	@Override
	public Long searchCountTeachers(String type, String key) {
		// TODO Auto-generated method stub
		String hqlQuery= "select count(distinct t.id) FROM Teacher t WHERE t."+type+" like concat('%',:param,'%')";
		Long count=0l;
		try {
			Session session = this.sessionFactory.getCurrentSession();
			Query q=session.createQuery(hqlQuery);
			q.setParameter("param", key);
			count=(Long) q.uniqueResult();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return count;
	}

}
