package bk.elearning.repository.impl;

<<<<<<< HEAD
<<<<<<< HEAD
import java.awt.print.Book;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
=======
import java.util.List;

>>>>>>> parent of dd0be6d... edit delete method teacherRepository
=======
import java.util.List;

>>>>>>> parent of dd0be6d... edit delete method teacherRepository
import org.hibernate.SessionFactory;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import bk.elearning.entity.Subject;
import bk.elearning.entity.Teacher;
import bk.elearning.repository.ITeacherRepository;

@Repository
@Transactional
public class TeacherRepositoryImpl extends UserRepositoryImpl<Teacher> implements ITeacherRepository {

	@Autowired
	private SessionFactory sessionFactory;
<<<<<<< HEAD
<<<<<<< HEAD
	@Override
	public int delete(int id) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		try {
			String hqlQuery="UPDATE  Course c SET c.teacher=:teacher where c.teacher.id=:id";
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
	@Override
	public List<Teacher> test(String key) {
		// TODO Auto-generated method stub
		 Session session = sessionFactory.getCurrentSession();

		    FullTextSession fullTextSession = Search.getFullTextSession(session);

		    QueryBuilder qb = fullTextSession.getSearchFactory()
		                .buildQueryBuilder().forEntity(Teacher.class).get();
		    org.apache.lucene.search.Query query = qb
		                .keyword().onFields("code",  "fullName") // Chỉ định tìm theo cột nào
		                .matching(key)
		                .createQuery();

		    org.hibernate.Query hibQuery =
		                fullTextSession.createFullTextQuery(query, Teacher.class);

		    List<Teacher> results = hibQuery.list();
		    return results;
	}
=======

>>>>>>> parent of dd0be6d... edit delete method teacherRepository
=======

>>>>>>> parent of dd0be6d... edit delete method teacherRepository
	

}
