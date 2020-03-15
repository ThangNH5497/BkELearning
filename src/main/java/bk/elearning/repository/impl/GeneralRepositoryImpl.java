package bk.elearning.repository.impl;

import java.util.List;

import javax.transaction.Transactional;

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
	public int save(T t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(Class<T> clazz, int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(T t) {
		// TODO Auto-generated method stub
		return 0;
	}

}
