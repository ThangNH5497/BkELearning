package bk.elearning.repository.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import bk.elearning.entity.Exam;
import bk.elearning.entity.dto.ExamDTO;
import bk.elearning.entity.dto.PaginationResult;
import bk.elearning.repository.IExamRepository;
import bk.elearning.utils.Constant;

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
			if (page.getCount() > 0) {
				Query query = session.createQuery(
						"SELECT new Exam(e.id,e.code,e.time,e.grade,e.name,e.descriptor,e.status, e.timeOpen,e.timeClose,e.createAt,e.updateAt,e.user) "
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

	@Override
	public PaginationResult<Exam> getPageBySubject(int subjectId, int start, int size) {
		Session session = sessionFactory.getCurrentSession();
		PaginationResult<Exam> page = new PaginationResult<Exam>();
		try {
			String hqlQuery = "FROM Exam e Where e.subject.id=:subjectId and e.user.role=:roleAdmin ";
			Query qCount = session.createQuery("SELECT COUNT(distinct e.id)" + hqlQuery);
			qCount.setParameter("subjectId", subjectId);
			qCount.setParameter("roleAdmin", Constant.ROLE_ADMIN);
			page.setCount((Long) qCount.uniqueResult());
			if (page.getCount() > 0) {
				Query query = session.createQuery(
						"SELECT new Exam(e.id,e.code,e.time,e.grade,e.name,e.descriptor,e.status, e.timeOpen,e.timeClose,e.createAt,e.updateAt,e.user) "
								+ hqlQuery);
				query.setParameter("subjectId", subjectId);
				query.setParameter("roleAdmin", Constant.ROLE_ADMIN);
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

	@Override
	public PaginationResult<Exam> searchPageByCourse(int courseId, String key, int start, int size) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		PaginationResult<Exam> page = new PaginationResult<Exam>();
		try {
			String hqlQuery = "FROM Exam e join e.examCourses ec " + " Where ec.course.id=:courseId and "
					+ " (e.code like concat('%',:key,'%') or e.name like concat('%',:key,'%')) ";

			Query queryCount = session.createQuery("SELECT COUNT(distinct e.id)" + hqlQuery);

			queryCount.setParameter("courseId", courseId);
			queryCount.setParameter("key", key);
			page.setCount((Long) queryCount.uniqueResult());
			if (page.getCount() > 0) {
				Query query = session.createQuery(
						"SELECT new Exam(e.id,e.code,e.time,e.grade,e.name,e.descriptor,e.status, e.timeOpen,e.timeClose,e.createAt,e.updateAt,e.user) "
								+ hqlQuery);

				query.setParameter("courseId", courseId);
				query.setParameter("key", key);
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

	@Override
	public PaginationResult<Exam> searchPageBySubject(int subjectId, String key, int start, int size) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		PaginationResult<Exam> page = new PaginationResult<Exam>();
		try {
			String hqlQuery = "FROM Exam e Where e.subject.id=:subjectId and e.user.role=:roleAdmin "
					+" and (e.code like concat('%',:key,'%') or e.name like concat('%',:key,'%')) ";
			Query qCount = session.createQuery("SELECT COUNT(distinct e.id)" + hqlQuery);
			qCount.setParameter("subjectId", subjectId);
			qCount.setParameter("roleAdmin", Constant.ROLE_ADMIN);
			qCount.setParameter("key",key);
			page.setCount((Long) qCount.uniqueResult());
			if (page.getCount() > 0) {
				Query query = session.createQuery(
						"SELECT new Exam(e.id,e.code,e.time,e.grade,e.name,e.descriptor,e.status, e.timeOpen,e.timeClose,e.createAt,e.updateAt,e.user) "
								+ hqlQuery);
				query.setParameter("subjectId", subjectId);
				query.setParameter("roleAdmin", Constant.ROLE_ADMIN);
				query.setParameter("key",key);
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

	@Override
	public PaginationResult<ExamDTO> getByStudent(Integer studentId, int start, int size) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		PaginationResult<ExamDTO> page = new PaginationResult<ExamDTO>();
		try {
			String hqlQuery = "FROM Exam e JOIN e.examCourses ec where ec.course.id"
					+" IN ( select c.id FROM  Course c JOIN c.studentCourses sc where sc.student.id=:studentId)";
			Query qCount = session.createQuery("SELECT COUNT(distinct e.id)" + hqlQuery);
			qCount.setParameter("studentId", studentId);
			page.setCount((Long) qCount.uniqueResult());
			if (page.getCount() > 0) {
				Query query = session.createQuery(
						//"SELECT new Exam(e.id,e.code,e.time,e.grade,e.name,e.descriptor,e.status, e.timeOpen,e.timeClose,e.createAt,e.updateAt,e.subject) "
						"Select  new bk.elearning.entity.dto.ExamDTO(e.id,e,ec) "+		 hqlQuery);
				query.setParameter("studentId", studentId);
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
