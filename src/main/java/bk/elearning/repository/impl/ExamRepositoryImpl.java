package bk.elearning.repository.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import bk.elearning.entity.Exam;
import bk.elearning.entity.dto.PaginationResult;
import bk.elearning.repository.IExamRepository;

@Repository
public class ExamRepositoryImpl extends AbstractGenericRepository<Exam> implements IExamRepository {
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public PaginationResult<Exam> getPageByCourse(int courseId, int start, int size) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		PaginationResult<Exam> page = new PaginationResult<Exam>();
		try {
			String hqlQuery = "FROM Exam e join e.examCourses ec " + " Where ec.course.id=:courseId";
			page.setCount((Long) session.createQuery("SELECT COUNT(distinct e.id)" + hqlQuery)
					.setParameter("courseId", courseId).uniqueResult());
			if (page.getCount() > 0)
			{
				Query query=session.createQuery(
						"SELECT new Exam(e.id,e.code,e.time,e.grade,e.name,e.descriptor,e.status, e.timeOpen,e.timeClose,e.createAt,e.updateAt) "
								+ hqlQuery)
						.setParameter("courseId", courseId);
				query.setFirstResult(start);
				query.setMaxResults(size);
				page.setData(query.list());
			}
			return page;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

}
