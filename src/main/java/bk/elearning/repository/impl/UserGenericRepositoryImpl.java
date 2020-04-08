package bk.elearning.repository.impl;

import java.util.HashMap;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import bk.elearning.entity.dto.PaginationResult;
import bk.elearning.repository.IUserGenericRepository;

@Repository
@Transactional
public abstract class UserGenericRepositoryImpl<T> extends AbstractGenericRepository<T> implements IUserGenericRepository<T> {

	@Override
	public PaginationResult<T> getPage(int start, int size) {
		// TODO Auto-generated method stub
		PaginationResult<T> pageResult = new PaginationResult<T>();
		try {
			pageResult.setCount(super.getCount());

			pageResult.setData(super.getAll(start * size, size));

		} catch (Exception e) {
			// TODO: handle exception
		}
		return pageResult;
	}

	@Override
	public PaginationResult<T> search(String key, int start, int size) {
		PaginationResult<T> pageResult = new PaginationResult<T>();
		try {
			HashMap<String, String> searchFields = new HashMap<String, String>();
			searchFields.put("code", key);
			searchFields.put("fullName", key);
			pageResult.setCount(super.getCount(null, searchFields));

			pageResult.setData(super.search(null, searchFields, start * size, size));

		} catch (Exception e) {
			// TODO: handle exception
		}
		return pageResult;
	}

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public T getByUsername(String username) {
		// TODO Auto-generated method stub
		String hqlQuery = "FROM " + super.getClazz().getName() + " t where t.username=:username";
		T t = null;
		System.out.println("class usser name  : " + super.getClazz().getName());
		Session session = sessionFactory.getCurrentSession();
		try {
			Query query = session.createQuery(hqlQuery);
			query.setParameter("username", username);
			t = (T) query.list().get(0);
		} catch (Exception e) {
			// TODO: handle exception
			session.clear();
		}
		return t;
	}

}
