package bk.elearning.repository.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import bk.elearning.repository.IUserRepository;

@Repository
@Transactional
public abstract class UserRepositoryImpl<T> extends AbstractGenericRepository<T> implements IUserRepository<T> {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public T getByUsername(String username) {
		// TODO Auto-generated method stub
		String hqlQuery = "FROM " + super.getClazz().getName() + " t where t.username=:username";
		T t = null;
		System.out.println("class usser name  : "+super.getClazz().getName());
		Session session = sessionFactory.getCurrentSession();
		try {
			Query query = session.createQuery(hqlQuery);
			query.setParameter("username", username);
			t=(T) query.list().get(0);
		} catch (Exception e) {
			// TODO: handle exception
			session.clear();
		}
		return t;
	}

}
