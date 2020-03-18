package bk.elearning.repository.impl;

import java.io.Serializable;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import bk.elearning.repository.IGeneralRepository;

@Transactional
@Repository
public abstract class GeneralRepositoryImpl<T> implements IGeneralRepository<T> {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<T> query(String hqlQuery)
	{
		List<T> list=null;
		try {
			Session session = this.sessionFactory.getCurrentSession();

			list = session.createQuery(hqlQuery).list();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}
	

	@Override
	public List<T> getLimit(int start, int count) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<T> getAll(Class<T> clazz) {
		// TODO Auto-generated method stub
		List<T> list=null;
		try {
			Session session = this.sessionFactory.getCurrentSession();
			String className=clazz.getName();
			String hqlQuery="from "+className;
			list = session.createQuery(hqlQuery).list();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}
	@Override
	public List<T> getById(Class<T> clazz,int id)
	{
		List<T> list=null;
		try {
			Session session = this.sessionFactory.getCurrentSession();
			String className=clazz.getName();
			String hqlQuery="from "+className+" where id=:param";
			Query q=session.createQuery(hqlQuery);
			q.setParameter("param", id);
			list = q.list();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return list;
		
	}
	@Override
	public int save(T t) {
		// TODO Auto-generated method stub
		try {
			Session session = this.sessionFactory.getCurrentSession();
			session.save(t);
			return 1;
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return 0;
	}

	@Override
	public int delete(Class<T> clazz, int id) {
		try {
			Session session = this.sessionFactory.getCurrentSession();
			Serializable rid = new Integer(id);
			Object persistentInstance = session.load(clazz, id);
			if (persistentInstance != null) {
			    session.delete(persistentInstance);
			    return 1;
			}	
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return 0;
	}

	@Override
	public int update(T t) {
		// TODO Auto-generated method stub
		try {
			Session session = this.sessionFactory.getCurrentSession();
			session.update(t);
			return 1;
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return 0;
	}

}
