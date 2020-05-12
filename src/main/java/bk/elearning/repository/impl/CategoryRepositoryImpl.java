package bk.elearning.repository.impl;

import java.util.ArrayList;
import java.util.HashMap;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import bk.elearning.entity.Category;
import bk.elearning.entity.dto.PaginationResult;
import bk.elearning.repository.ICategoryRepository;

@Repository
@Transactional
public class CategoryRepositoryImpl extends AbstractGenericRepository<Category> implements ICategoryRepository {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public PaginationResult<Category> getPageBySubject(int subjectId,int userId, int start, int size) {
		// TODO Auto-generated method stub
		PaginationResult<Category> pages = new PaginationResult<Category>();
		try {
			HashMap<String, Object> constrantFields = new HashMap<String, Object>();
			constrantFields.put("subject.id",subjectId);
			constrantFields.put("user.id",userId);
			pages.setCount(super.getCount(constrantFields, null));

			pages.setData(super.search(constrantFields, null, start * size, size));
			return pages;
		} catch (Exception e) {
			// TODO: handle exception
		}

		return null;
	}

	@Override
	public PaginationResult<Category> searchPageBySubject(int subjectId,int userId, String key, int start, int size) {
		// TODO Auto-generated method stub
		PaginationResult<Category> pages = new PaginationResult<Category>();
		try {
			HashMap<String, Object> constrantFields = new HashMap<String, Object>();
			constrantFields.put("subject.id",subjectId);
			constrantFields.put("user.id",userId);
			HashMap<String, String> searchFields = new HashMap<String, String>();
			
			searchFields.put("id", key);
			searchFields.put("name", key);
			
			pages.setCount(super.getCount(constrantFields, searchFields));

			pages.setData(super.search(constrantFields, searchFields, start * size, size));
			return pages;
		} catch (Exception e) {
			// TODO: handle exception
		}

		return null;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public ArrayList<Category> getBySubject(int subjectId,int userId) {
		// TODO Auto-generated method stub
		Session session=sessionFactory.getCurrentSession();
		ArrayList<Category> categorys=null;
		try {
			String hqlQuery="FROM Category c where c.subject.id=:subjectId and c.user.id=:userId";
			Query q=session.createQuery(hqlQuery);
			q.setParameter("subjectId", subjectId);
			q.setParameter("userId", userId);
			categorys=(ArrayList<Category>) q.list();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.toString());
			session.clear();
		}
		return categorys;
	}

}
