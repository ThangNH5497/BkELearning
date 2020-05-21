package bk.elearning.repository.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import bk.elearning.entity.Exam;
import bk.elearning.entity.ExamPaper;
import bk.elearning.entity.relationship.ExamPaperQuestion;
import bk.elearning.repository.IExamPaperRepository;

@Repository
public class ExamPaperRepositoryImpl extends AbstractGenericRepository<ExamPaper> implements IExamPaperRepository {
	@Autowired
	private SessionFactory sessionFactory;
	@Override
	public int update(ExamPaper t) {
		// TODO Auto-generated method stub
		Session session=sessionFactory.getCurrentSession();
		try {
			
		//	String	hqlQuery="delete from ExamPaperQuestionAnswer epqa join epqa.examPaperQuestion epq where epq.examPaper.id=:examPaperId";
			String hqlQuery="delete from ExamPaperQuestionAnswer epqa WHERE epqa.examPaperQuestion.id " +
					 " IN (SELECT epq.id FROM ExamPaperQuestion epq where epq.examPaper.id=:examPaperId)";
			//String	hqlQuery="delete from ExamPaperQuestionAnswer epqa where epqa.examPaperQuestion.examPaper.id=:examPaperId";
			session.createQuery(hqlQuery).setParameter("examPaperId", t.getId()).executeUpdate();
			return super.update(t);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.toString());
			session.clear();
		}
		return 0;
	}
/*
	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("rawtypes")
	@Override
	public ExamPaper getById(int id) {
		Session session=sessionFactory.getCurrentSession();
		ExamPaper examPaper=null;
		try {
			//select exampaper
			String hqlQuery = "select new ExamPaper(ep.id,ep.code,ep.name,ep.descriptor,ep.createAt,ep.updateAt) "
					+ "FROM ExamPaper ep where ep.id=:examPaperId";
			Query query=session.createQuery(hqlQuery);
			query.setParameter("examPaperId", id);
			examPaper=(ExamPaper) query.list().get(0);
			//select question
			hqlQuery="select new Question(q.id,q.name,q.level,q.type,q.content) "
					+ "FROM Question q JOIN q.examPaperQuestions qex where qex.examPaper.id=:examPaperId";
			query=session.createQuery(hqlQuery);
			query.setParameter("examPaperId", id);
			
		} catch (Exception e) {
		}
		return examPaper;
	}
*/
	
}
