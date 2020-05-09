package bk.elearning.repository.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.HashMap;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import bk.elearning.repository.IGenericRepository;
import bk.elearning.utils.Constant;

@SuppressWarnings("unchecked")
@Transactional
public abstract class AbstractGenericRepository<T> implements IGenericRepository<T> {

	public final int ALL_RECORD = -1;
	@Autowired
	private SessionFactory sessionFactory;

	private final Class<T> clazz;

	public AbstractGenericRepository() {
		this.clazz = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass())
				.getActualTypeArguments()[0];
	}

	public Class<T> getClazz() {
		return clazz;
	}

	@Override
	public List<T> getAll() {
		// TODO Auto-generated method stub
		List<T> list = null;
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		try {
			String hqlQuery = "from " + clazz.getName();
			list = session.createQuery(hqlQuery).list();
		} catch (Exception e) {
			// TODO: handle exception
			session.clear();
		}
		return list;
	}

	@Override
	public List<T> getAll(int start, int size) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		String hqlQuery = "FROM " + clazz.getName();
		List<T> list = null;
		try {
			Query query = session.createQuery(hqlQuery);
			query.setFirstResult(start);
			query.setMaxResults(size);
			list = query.list();
		} catch (Exception e) {
			session.clear();
		}
		return list;
	}

	@Override
	public T getById(int id) {
		T t = null;
		String hqlQuery = "FROM " + clazz.getName() + " t where t.id=:id";
		Session session = sessionFactory.getCurrentSession();
		try {
			Query query = session.createQuery(hqlQuery);
			query.setParameter("id", id);
			t = (T) query.list().get(0);
		} catch (Exception e) {
			// TODO: handle exception
			session.clear();
		}
		return t;

	}

	@Override
	public T getByCode(String code) {
		// TODO Auto-generated method stub
		T t = null;
		String hqlQuery = "FROM " + clazz.getName() + " t where t.code=:code";
		Session session = sessionFactory.getCurrentSession();
		try {
			Query query = session.createQuery(hqlQuery);
			query.setParameter("code", code);
			t = (T) query.list().get(0);
		} catch (Exception e) {
			// TODO: handle exception
			session.clear();
		}
		return t;
	}

	@Override
	public Long getCount() {
		// TODO Auto-generated method stub
		String hqlQuery = "select count(distinct t.id) from  " + clazz.getName() + " t ";
		Long count = 0L;
		try {
			Session session = this.sessionFactory.getCurrentSession();
			Query query = session.createQuery(hqlQuery);
			count = (Long) query.uniqueResult();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return count;
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
	public int delete(int id) {
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

	
	public List<T> getWithConstraint(HashMap<String, Object> constrantFields, int start, int size) {
		// TODO Auto-generated method stub
		List<T> list = null;
		// TODO Auto-generated method stub
	
		StringBuilder hqlQuery = new StringBuilder("from " + clazz.getName() + " t ");
		try {
			Query query = null;
			query = queryBuild(constrantFields, null, hqlQuery);
			if(size!=Constant.MAX_RESULT)
			{
				query.setFirstResult(start);
				query.setMaxResults(size);
			}
			
			list = query.list();
		} catch (Exception e) {
			// TODO: handle exception
			(this.sessionFactory.getCurrentSession()).clear();
			System.out.println(e.toString());
		}
		this.sessionFactory.getCurrentSession().clear();
		return list;
	}

	
	public Long getCount(HashMap<String, Object> constrantFields, HashMap<String, String> searchFields) {
		// TODO Auto-generated method stub
		Long count = 0L;
		StringBuilder hqlQuery = new StringBuilder("select count(distinct t.id) from " + clazz.getName() + " t ");
		try {
			count = (Long) queryBuild(constrantFields, searchFields, hqlQuery).uniqueResult();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.toString());
		}
		return count;
	}

	
	public List<T> search(HashMap<String, Object> constrantFields, HashMap<String, String> searchFields, int start,
			int size) {
		StringBuilder hqlQuery = new StringBuilder("from " + clazz.getName() + " t ");
		// TODO Auto-generated method stub
		List<T> list=null;
		try {
			Query query = null;
			query = queryBuild(constrantFields, searchFields, hqlQuery);
			if(size!=Constant.MAX_RESULT)
			{
				query.setFirstResult(start);
				query.setMaxResults(size);
			}
			list = query.list();
		} catch (Exception e) {
			// TODO: handle exceptio
			System.out.println(e.toString());
		}
		return list;
	}

	Query queryBuild(HashMap<String, Object> constrantFields, HashMap<String, String> searchFields,
			StringBuilder hqlQuery) {
		Query query = null;
		Session session = this.sessionFactory.getCurrentSession();
		boolean checkConstraint = false;
		boolean checkSearch = false;
		try {
			if (constrantFields != null && constrantFields.size() > 0) {
				checkConstraint = true;
				hqlQuery.append(" where t.");
				int i = 0;
				for (String key : constrantFields.keySet()) {
					if (i > 0)
						hqlQuery.append(" and t.");
					hqlQuery.append(key + "=:constrantField" + String.valueOf(i));
					i++;
				}
			}
			if (searchFields != null && searchFields.size() > 0) {
				checkSearch = true;
				if (checkConstraint == true) {
					hqlQuery.append(" and (t.");
				} else
					hqlQuery.append(" where (t.");
				int i = 0;
				for (String key : searchFields.keySet()) {
					if (i > 0)
						hqlQuery.append(" or t.");
					hqlQuery.append(key + " like concat('%',:searchField" + String.valueOf(i) + ",'%') ");
					i++;
				}
				hqlQuery.append(")");

			}
			query = session.createQuery(hqlQuery.toString());
			if (checkConstraint == true) {
				int i = 0;
				for (String key : constrantFields.keySet()) {
					query.setParameter("constrantField" + String.valueOf(i), constrantFields.get(key));
					i++;
				}
			}
			if (checkSearch == true) {
				int i = 0;
				for (String key : searchFields.keySet()) {
					query.setParameter("searchField" + String.valueOf(i), searchFields.get(key));
					i++;
				}
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			session.clear();
			System.out.println("ex "+e.toString());
		}

		return query;
	}

}
