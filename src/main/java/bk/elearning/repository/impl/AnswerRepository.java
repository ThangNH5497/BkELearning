package bk.elearning.repository.impl;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import bk.elearning.entity.Answer;
import bk.elearning.repository.IAnswerRepository;

@Repository
@Transactional
public class AnswerRepository extends AbstractGenericRepository<Answer> implements IAnswerRepository{

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public int deleteByQuestion(int questionId)
	{
		Session session=sessionFactory.getCurrentSession();
		try {
			
			String hqlQuery="DELETE FROM Answer a where a.question.id=:questionId";
			Query query=session.createQuery(hqlQuery);
			query.setParameter("questionId", questionId);
			query.executeUpdate();
			return 1;
		} catch (Exception e) {
			// TODO: handle exception
			session.clear();
		}
		return 0;
	}
}
