package bk.elearning.repository.impl;

import java.io.Serializable;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import bk.elearning.entity.Teacher;
import bk.elearning.repository.IGeneralRepository;

@SuppressWarnings("rawtypes")
@Transactional
@Repository
public abstract class GeneralRepositoryImpl<T> implements IGeneralRepository<T> {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<T> search(Class<T> clazz, String filter, String key, int start, int size) {
		// TODO Auto-generated method stub
		String hqlQuery = " FROM " + clazz.getName() + " t WHERE t." + filter + " like concat('%',:param,'%')";
		List<T> list = null;
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		try {

			Query q = session.createQuery(hqlQuery);
			q.setParameter("param", key);
			q.setFirstResult(start);
			q.setMaxResults(size);
			list = q.list();
		} catch (Exception e) {
			// TODO: handle exception
			session.clear();
		}
		return list;
	}

	@Override
	public List<T> getByIds(int[] ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long getCount(Class<T> clazz) {
		// TODO Auto-generated method stub
		Long count = 0l;
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		try {

			String hqlQuery = "select count(distinct t.id) from " + clazz.getName() + " t";

			Query query = session.createQuery(hqlQuery);
			count = (Long) query.uniqueResult();
		} catch (Exception e) {
			System.out.println(e.toString());
			session.clear();
		}
		return count;
	}

	@Override
	// tra ve tong gban ghi tim kiem
	public Long getCount(Class<T> clazz, String contraintField, String key) {
		// TODO Auto-generated method stub
		Long count = 0l;
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		try {
			String hqlQuery = "select count(distinct t.id) from " + clazz.getName() + " t where t." + contraintField
					+ " like concat('%',:param,'%')";

			Query query = session.createQuery(hqlQuery);
			query.setParameter("param", key);
			count = (Long) query.uniqueResult();
		} catch (Exception e) {
			System.out.println(e.toString());
			session.clear();
		}
		return count;
	}

	@Override
	public Query query(String hqlQuery) {
		Query<T> query = null;
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		try {

			query = session.createQuery(hqlQuery);
		} catch (Exception e) {
			// TODO: handle exception
			session.clear();
		}
		return query;
	}

	@Override
	public List<T> getLimit(int start, int count, Class<T> clazz) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		List<T> list = null;
		try {
			String hqlQuery = "from " + clazz.getName() + " t order by t.id desc";

			Query query = session.createQuery(hqlQuery);

			query.setFirstResult(start);
			query.setMaxResults(count);
			list = query.list();
		} catch (Exception e) {
			session.clear();
		}
		return list;
	}

	@Override
	public List<T> getAll(Class<T> clazz) {
		// TODO Auto-generated method stub
		List<T> list = null;
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		try {

			String className = clazz.getName();
			String hqlQuery = "from " + className;
			list = session.createQuery(hqlQuery).list();
		} catch (Exception e) {
			// TODO: handle exception
			session.clear();
		}
		return list;
	}

	@Override
	public T getById(Class<T> clazz, int id) {
		T t = null;
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		try {

			String className = clazz.getName();
			String hqlQuery = "from " + className + " where id=:param";
			Query q = session.createQuery(hqlQuery);
			q.setParameter("param", id);
			t = (T) q.list().get(0);
		} catch (Exception e) {
			// TODO: handle exception
			session.clear();
		}
		return t;

	}

	@Override
	public int save(T t) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		try {

			session.save(t);
			return 1;
		} catch (Exception e) {
			System.out.println(e.toString());
			session.clear();
		}
		return 0;
	}

	@Override
	public int delete(Class<T> clazz, int id) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		try {

			Serializable rid = new Integer(id);
			Object persistentInstance = session.load(clazz, id);
			if (persistentInstance != null) {
				session.delete(persistentInstance);
				return 1;
			}
		} catch (Exception e) {
			System.out.println(e.toString());
			session.clear();
		}
		return 0;
	}

	@Override
	public int update(T t) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		try {
			session.update(t);
			return 1;
		} catch (Exception e) {
			System.out.println(e.toString());
			session.clear();
		}
		return 0;
	}

}
