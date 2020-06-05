package bk.elearning.repository.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import bk.elearning.entity.Exam;
import bk.elearning.entity.dto.ExamDTO;
import bk.elearning.entity.dto.PaginationResult;
import bk.elearning.entity.relationship.StudentExam;
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
			System.out.println(e);
			session.clear();
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
			System.out.println(e);
			session.clear();
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
			System.out.println(e);
			session.clear();
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
			System.out.println(e);
			session.clear();
		}
		return null;
	}

	@Override
	public PaginationResult<ExamDTO> getByStudent(Integer studentId, int start, int size) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		PaginationResult<ExamDTO> page = new PaginationResult<ExamDTO>();
		try {
			String hqlQuery = "FROM Exam e JOIN e.examCourses ec where ec.course.id "
					+" IN ( select c.id FROM  Course c JOIN c.studentCourses sc where sc.student.id=:studentId)";
			Query qCount = session.createQuery("SELECT COUNT(distinct e.id)" + hqlQuery);
			qCount.setParameter("studentId", studentId);
			page.setCount((Long) qCount.uniqueResult());
			if (page.getCount() > 0) {
				Query query = session.createQuery(
						//"SELECT new Exam(e.id,e.code,e.time,e.grade,e.name,e.descriptor,e.status, e.timeOpen,e.timeClose,e.createAt,e.updateAt,e.subject) "
						"Select  new bk.elearning.entity.dto.ExamDTO(e.id,e.code,e.time,e.grade,e.name,e.descriptor,e.status,e.timeOpen,e.timeClose,ec) "+hqlQuery);
				query.setParameter("studentId", studentId);
				query.setFirstResult(start);
				query.setMaxResults(size);
				page.setData(query.list());
				for (ExamDTO examDTO : page.getData()) {
					hqlQuery="FROM StudentExam sc where sc.student.id=:studentId and sc.exam.id=:examId";
					
					query=session.createQuery(hqlQuery);
					query.setParameter("studentId", studentId);
					query.setParameter("examId", examDTO.getId());
					List<StudentExam> sc=query.list();
					if(!sc.isEmpty())
					{
						examDTO.setStudentExam(sc.get(0));
					}
					
				}
			}
			return page;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
			session.clear();
		}
		return null;
	}

	@Override
	public int updateStatus(Exam exam) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		try {
			String hqlQuery="UPDATE Exam e SET e.status=:status where e.id=:id";
			Query query=session.createQuery(hqlQuery);
			query.setParameter("status", exam.getStatus());
			query.setParameter("id", exam.getId());
			query.executeUpdate();
			return 1;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
			session.clear();
		}
		return 0;
	}

	@Override
	public ExamDTO getExamDTOById(Integer examId) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		try {

			Query query = session.createQuery(
					"Select  new bk.elearning.entity.dto.ExamDTO(e.id,e.code,e.time,e.grade,e.name,e.descriptor,e.status,e.timeOpen,e.timeClose) FROM Exam e Where e.id=:examId");
			query.setParameter("examId", examId);
			return (ExamDTO) query.list().get(0);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
			session.clear();
		}
		return null;
	}

}
