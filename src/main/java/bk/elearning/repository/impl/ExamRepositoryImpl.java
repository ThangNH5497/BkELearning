package bk.elearning.repository.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import bk.elearning.entity.Exam;
import bk.elearning.entity.ExamPaper;
import bk.elearning.entity.StudentExamDetailLock;
import bk.elearning.entity.dto.ExamDTO;
import bk.elearning.entity.dto.ExamPageDTO;
import bk.elearning.entity.dto.ExamResultDTO;
import bk.elearning.entity.dto.PaginationResult;
import bk.elearning.entity.dto.StudentExamDTO;
import bk.elearning.entity.dto.StudentResultQuestionDTO;
import bk.elearning.entity.relationship.ExamFilter;
import bk.elearning.entity.relationship.ExamPaperQuestion;
import bk.elearning.entity.relationship.ExamQuestion;
import bk.elearning.entity.relationship.StudentExam;
import bk.elearning.repository.IExamRepository;
import bk.elearning.repository.IStudentExamDetailLockRepository;
import bk.elearning.utils.Constant;

@Repository
public class ExamRepositoryImpl extends AbstractGenericRepository<Exam> implements IExamRepository {
	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private IStudentExamDetailLockRepository lockRepo;

	@Override
	public PaginationResult<ExamPageDTO> getPageByCourse(int courseId, int start, int size) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		PaginationResult<ExamPageDTO> page = new PaginationResult<ExamPageDTO>();
		try {
			String hqlQuery = "FROM Exam e join e.examCourses ec " + " Where ec.course.id=:courseId";
			page.setCount((Long) session.createQuery("SELECT COUNT(distinct e.id)" + hqlQuery)
					.setParameter("courseId", courseId).uniqueResult());
			if (page.getCount() > 0) {
				Query query = session.createQuery(
						"SELECT new bk.elearning.entity.dto.ExamPageDTO(e.id,e.code,e.time,e.grade,e.name,e.descriptor,e.status,e.createAt,e.updateAt, e.timeOpen,e.timeClose,e.user.role) "
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
	public PaginationResult<ExamPageDTO> getPageBySubject(int subjectId, int start, int size) {
		Session session = sessionFactory.getCurrentSession();
		PaginationResult<ExamPageDTO> page = new PaginationResult<ExamPageDTO>();
		try {
			String hqlQuery = "FROM Exam e Where e.subject.id=:subjectId and e.user.role=:roleAdmin ";
			Query qCount = session.createQuery("SELECT COUNT(distinct e.id)" + hqlQuery);
			qCount.setParameter("subjectId", subjectId);
			qCount.setParameter("roleAdmin", Constant.ROLE_ADMIN);
			page.setCount((Long) qCount.uniqueResult());
			if (page.getCount() > 0) {
				Query query = session.createQuery(
						"SELECT new bk.elearning.entity.dto.ExamPageDTO(e.id,e.code,e.time,e.grade,e.name,e.descriptor,e.status,e.createAt,e.updateAt, e.timeOpen,e.timeClose,e.user.role) "
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
	public PaginationResult<ExamPageDTO> searchPageByCourse(int courseId, String key, int start, int size) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		PaginationResult<ExamPageDTO> page = new PaginationResult<ExamPageDTO>();
		try {
			String hqlQuery = "FROM Exam e join e.examCourses ec " + " Where ec.course.id=:courseId and "
					+ " (e.code like concat('%',:key,'%') or e.name like concat('%',:key,'%')) ";

			Query queryCount = session.createQuery("SELECT COUNT(distinct e.id)" + hqlQuery);

			queryCount.setParameter("courseId", courseId);
			queryCount.setParameter("key", key);
			page.setCount((Long) queryCount.uniqueResult());
			if (page.getCount() > 0) {
				Query query = session.createQuery(
						"select new bk.elearning.entity.dto.ExamPageDTO(e.id,e.code,e.time,e.grade,e.name,e.descriptor,e.status,e.createAt,e.updateAt, e.timeOpen,e.timeClose,e.user.role) "
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
	public PaginationResult<ExamPageDTO> searchPageBySubject(int subjectId, String key, int start, int size) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		PaginationResult<ExamPageDTO> page = new PaginationResult<ExamPageDTO>();
		try {
			String hqlQuery = "FROM Exam e Where e.subject.id=:subjectId and e.user.role=:roleAdmin "
					+ " and (e.code like concat('%',:key,'%') or e.name like concat('%',:key,'%')) ";
			Query qCount = session.createQuery("SELECT COUNT(distinct e.id)" + hqlQuery);
			qCount.setParameter("subjectId", subjectId);
			qCount.setParameter("roleAdmin", Constant.ROLE_ADMIN);
			qCount.setParameter("key", key);
			page.setCount((Long) qCount.uniqueResult());
			if (page.getCount() > 0) {
				Query query = session.createQuery(
						"SELECT new bk.elearning.entity.dto.ExamPageDTO(e.id,e.code,e.time,e.grade,e.name,e.descriptor,e.status,e.createAt,e.updateAt, e.timeOpen,e.timeClose,e.user.role) "
								+ hqlQuery);
				query.setParameter("subjectId", subjectId);
				query.setParameter("roleAdmin", Constant.ROLE_ADMIN);
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
	public PaginationResult<ExamDTO> getByStudent(Integer studentId, String filter, int start, int size) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		PaginationResult<ExamDTO> page = new PaginationResult<ExamDTO>();
		try {
			String hqlQuery = "FROM Exam e JOIN e.examCourses ec where ec.course.id "
					+ " IN ( select c.id FROM  Course c JOIN c.studentCourses sc where sc.student.id=:studentId)";
			Query qCount = session.createQuery("SELECT COUNT(distinct e.id)" + hqlQuery);
			qCount.setParameter("studentId", studentId);
			page.setCount((Long) qCount.uniqueResult());
			if (page.getCount() > 0) {
				Query query = session.createQuery(
						// "SELECT new Exam(e.id,e.code,e.time,e.grade,e.name,e.descriptor,e.status,
						// e.timeOpen,e.timeClose,e.createAt,e.updateAt,e.subject) "
						"Select  new bk.elearning.entity.dto.ExamDTO(e.id,e.code,e.time,e.grade,e.name,e.descriptor,e.status,e.timeOpen,e.timeClose,ec) "
								+ hqlQuery);
				query.setParameter("studentId", studentId);
				query.setFirstResult(start);
				query.setMaxResults(size);
				page.setData(query.list());
				for (ExamDTO examDTO : page.getData()) {
					try {
						hqlQuery = "SELECT new bk.elearning.entity.relationship.StudentExam(sc.id,sc.status,sc.timeLeft,sc.grade,scl) FROM StudentExam sc LEFT JOIN sc.lock scl  where sc.student.id=:studentId and sc.exam.id=:examId";

						query = session.createQuery(hqlQuery);
						query.setParameter("studentId", studentId);
						query.setParameter("examId", examDTO.getId());
						List<StudentExam> sc = query.list();
						if (!sc.isEmpty()) {
							examDTO.setStudentExam(sc.get(0));
						}
					} catch (Exception e) {
						// TODO: handle exception
						System.out.println(e.toString());
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
			String hqlQuery = "UPDATE Exam e SET e.status=:status where e.id=:id";
			Query query = session.createQuery(hqlQuery);
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

	@Override
	public ExamDTO getByIdAndStudent(Integer examId, Integer studentId) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		ExamDTO exam = null;
		try {
			String hqlQuery = "FROM Exam e  JOIN  e.examCourses ec where e.id=:examId AND ec.course.id "
					+ " IN ( select c.id FROM  Course c JOIN c.studentCourses sc where sc.student.id=:studentId)";

			Query query = session.createQuery(
					"Select  new bk.elearning.entity.dto.ExamDTO(e.id,e.code,e.time,e.grade,e.name,e.descriptor,e.status,e.timeOpen,e.timeClose) "
							+ hqlQuery);
			query.setParameter("examId", examId);
			query.setParameter("studentId", studentId);

			exam = (ExamDTO) query.list().get(0);
			if (exam != null) {
				query = session
						.createQuery("FROM StudentExam se where  se.exam.id=:examId and se.student.id=:studentId");
				try {
					query.setParameter("examId", examId);
					query.setParameter("studentId", studentId);
					exam.setStudentExam((StudentExam) query.list().get(0));
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println("select studentexam : " + e);
				}
				if (exam.getStudentExam() == null) {
					System.out.println("student exam null");
					query = session.createQuery("FROM ExamFilter ef where  ef.exam.id=:examId");
					query.setParameter("examId", examId);

					/*
					 * for (ExamFilter examFilter : ef) {
					 * System.out.println("id :"+examFilter.getId());
					 * exam.getExamFilters().add(examFilter); }
					 */
					try {
						HashSet<ExamFilter> ef = new HashSet<ExamFilter>();
						ef.addAll(query.list());
						exam.setExamFilters(ef);
					} catch (Exception e) {
						// TODO: handle exception
					}
				}
			}

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
			session.clear();
		}
		return exam;
	}

	@Override
	public ArrayList<ExamQuestion> getRandomQuestionByFilter(int examId, Set<ExamFilter> examFilters) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		ArrayList<ExamQuestion> eqs = new ArrayList<ExamQuestion>();
		try {
			String hqlQuery = "FROM ExamQuestion eq where eq.exam.id=:examId AND eq.question.level=:level  ORDER BY RAND()";

			Query query = session.createQuery(hqlQuery);
			query.setFirstResult(0);
			query.setParameter("examId", examId);
			for (ExamFilter examFilter : examFilters) {
				query.setParameter("level", examFilter.getLevel());
				query.setMaxResults(examFilter.getNumber());

				eqs.addAll(query.list());
			}
			return eqs;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
			session.clear();
		}
		return null;
	}

	@Override
	public void updateResult(StudentResultQuestionDTO studentResultQuestionDTO) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		try {
			String hqlQuery = "UPDATE ExamPaperQuestionAnswer epqa SET epqa.studentAnswer=:studentAnswer where epqa.id=:id";

			Query query = session.createQuery(hqlQuery);
			query.setParameter("id", studentResultQuestionDTO.getExamPaperQuestionAnswerId());
			query.setParameter("studentAnswer", studentResultQuestionDTO.getStudentAnswer());
			query.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
			session.clear();
		}

	}

	@Override
	public StudentExam getStudentExamById(int studentExamId) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		StudentExam se = null;
		try {
			String hqlQuery = "FROM StudentExam se WHERE se.id=:id";

			Query query = session.createQuery(hqlQuery);
			query.setParameter("id", studentExamId);

			se = (StudentExam) query.list().get(0);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
			session.clear();
		}
		return se;
	}

	@Override
	public void updateStudentExam(StudentExam se) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		try {
			String hqlQuery = "UPDATE StudentExam se SET se.timeLeft=:timeLeft ,se.status=:status,se.grade=:grade where se.id=:id";

			Query query = session.createQuery(hqlQuery);
			query.setParameter("id", se.getId());
			query.setParameter("timeLeft", se.getTimeLeft());
			query.setParameter("status", se.getStatus());
			query.setParameter("grade", se.getGrade());
			query.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
			session.clear();
		}
	}

	@Override
	public Long getCoutExamProcess(int examId, int courseId) {
		Session session = sessionFactory.getCurrentSession();
		try {
			String hqlQuery = "SELECT COUNT(distinct se.id) FROM Exam e join e.examCourses ec join e.studentExams se Where ec.course.id=:courseId and e.id=:examId and se.status=:status";

			Query query = session.createQuery(hqlQuery);
			query.setParameter("examId", examId);
			query.setParameter("courseId", courseId);
			query.setParameter("status", Constant.STUDENT_EXAM_STATUS_WAIT_RESULT);
			return (Long) query.uniqueResult();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
			session.clear();
		}
		return 0l;
	}

	@Override
	public PaginationResult<StudentExamDTO> getStudentExamUncomplete(int examId, int courseId) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		PaginationResult<StudentExamDTO> page = new PaginationResult<StudentExamDTO>();
		try {
			String hqlQuery = " FROM Exam e join e.examCourses ec join e.studentExams se Where ec.course.id=:courseId and e.id=:examId and se.status=:status";

			Query query = session.createQuery(
					"SELECT  new bk.elearning.entity.dto.StudentExamDTO(se.id,se.examPaper.code,se.student.code,se.student.fullName) "
							+ hqlQuery);
			query.setParameter("examId", examId);
			query.setParameter("courseId", courseId);
			query.setParameter("status", Constant.STUDENT_EXAM_STATUS_WAIT_RESULT);
			page.setData(query.list());

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
			session.clear();
		}
		return page;
	}

	@Override
	public int updateStudentGrade(ExamPaperQuestion epq) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		try {
			String hqlQuery = "UPDATE ExamPaperQuestion epq SET epq.studentGrade=:studentGrade where epq.id=:id";

			Query query = session.createQuery(hqlQuery);
			query.setParameter("id", epq.getId());
			query.setParameter("studentGrade", epq.getStudentGrade());
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
	public StudentExam getStudentExamUncompleteById(int id) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		StudentExam se = null;
		try {

			String hqlQuery = "Select new StudentExam(se.id) FROM StudentExam se where se.id=:id";

			Query query = session.createQuery(hqlQuery);
			query.setParameter("id", id);
			se = (StudentExam) query.list().get(0);

			if (se != null) {
				hqlQuery = " Select new ExamPaper(ep.id) FROM ExamPaper ep JOIN ep.studentExams se  where se.id=:id";

				query = session.createQuery(hqlQuery);
				query.setParameter("id", id);
				se.setExamPaper((ExamPaper) query.list().get(0));
				if (se.getExamPaper() != null) {
					hqlQuery = " FROM ExamPaperQuestion epq  where epq.examPaper.id=:examPaperId AND epq.question.type=:type";

					query = session.createQuery(hqlQuery);
					query.setParameter("examPaperId", se.getExamPaper().getId());
					query.setParameter("type", Constant.QUESTION_FILL_WORD);
					Set<ExamPaperQuestion> data = new HashSet<ExamPaperQuestion>(query.list());
					se.getExamPaper().setExamPaperQuestions(data);
				}
			}

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
			session.clear();
		}
		return se;
	}

	@Override
	public PaginationResult<ExamResultDTO> getResultByCourse(int examId, int courseId) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		PaginationResult<ExamResultDTO> pages = new PaginationResult<ExamResultDTO>();
		try {

			String hqlQuery = "Select new bk.elearning.entity.dto.ExamResultDTO(s.id,s.code,s.fullName) FROM Student s JOIN s.studentCourses sc where sc.course.id=:courseId";

			Query query = session.createQuery(hqlQuery);
			query.setParameter("courseId", courseId);
			pages.setData(query.list());

			// select grade
			for (ExamResultDTO examResult : pages.getData()) {
				hqlQuery = "Select (se.grade * se.exam.grade) FROM StudentExam se WHERE se.student.id=:studentId AND se.exam.id=:examId";
				query = session.createQuery(hqlQuery);
				query.setParameter("studentId", examResult.getStudentId());
				query.setParameter("examId", examId);
				try {
					examResult.setStudentGrade((float) query.list().get(0));
				} catch (Exception e) {
					// TODO: handle exception
				}

			}

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
			session.clear();
		}
		return pages;
	}

	@Override
	public int createLockExamDetail(StudentExam studentexam) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		try {

			studentexam.getLock().setStudentExam(studentexam);
			studentexam.getLock().setLockAccess(true);
			lockRepo.save(studentexam.getLock());
			// String hqlQuery = "UPDATE StudentExam se SET se.lock=:lock where
			// se.id=:studentExamId"
			String hqlQuery = "UPDATE StudentExam se SET se.lock=:lock where se.id=:studentExamId";

			Query query = session.createQuery(hqlQuery);
			query.setParameter("studentExamId", studentexam.getId());
			query.setParameter("lock", studentexam.getLock());
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
	public StudentExam getResultDetailByStudentExamId(int id) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		StudentExam se = null;
		try {
			String hqlQuery = "SELECT new bk.elearning.entity.relationship.StudentExam(sc.id,sc.status,sc.timeLeft,sc.grade,sc.examPaper,scl) FROM StudentExam sc LEFT JOIN sc.lock scl WHERE sc.id=:id";

			Query query = session.createQuery(hqlQuery);
			query.setParameter("id", id);
			se = (StudentExam) query.list().get(0);

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
			session.clear();
		}
		return se;
	}

	@Override
	public Long getCoutStudentRequest(int examId, int courseId) {
		Session session = sessionFactory.getCurrentSession();
		try {
			String hqlQuery = "SELECT COUNT(distinct se.id) FROM Exam e join e.examCourses ec join e.studentExams se JOIN se.lock sel Where ec.course.id=:courseId and e.id=:examId and sel.lockAccess=:lock";

			Query query = session.createQuery(hqlQuery);
			query.setParameter("examId", examId);
			query.setParameter("courseId", courseId);
			query.setParameter("lock", true);
			return (Long) query.uniqueResult();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
			session.clear();
		}
		return 0l;
	}

	@Override
	public PaginationResult<StudentExamDTO> getStudentRequest(int examId, int courseId) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		PaginationResult<StudentExamDTO> page = new PaginationResult<StudentExamDTO>();
		try {
			String hqlQuery = " FROM Exam e join e.examCourses ec join e.studentExams se JOIN se.lock sel Where ec.course.id=:courseId and e.id=:examId and sel.lockAccess=:lock";

			Query query = session.createQuery(
					"SELECT  new bk.elearning.entity.dto.StudentExamDTO(se.id,se.examPaper.code,se.student.code,se.student.fullName,sel) "
							+ hqlQuery);
			query.setParameter("examId", examId);
			query.setParameter("courseId", courseId);
			query.setParameter("lock", true);
			page.setData(query.list());

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
			session.clear();
		}
		return page;
	}

	@Override
	public void updateLockDetail(int id) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		
		Session session = sessionFactory.getCurrentSession();
		try {
			String hqlQuery = "UPDATE StudentExamDetailLock sel SET sel.lockAccess=:lock where sel.id=:id";

			Query query = session.createQuery(hqlQuery);
			query.setParameter("id", id);
			query.setParameter("lock", false);
			query.executeUpdate();

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
			session.clear();
		}

	}

	@Override
	public void deleteLockDetail(int id) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		try {
			String hqlQuery="UPDATE StudentExam se  SET se.lock=:lock Where se.lock.id=:id";
			Query query = session.createQuery(hqlQuery);
			query.setParameter("id", id);
			query.setParameter("lock", null);
			query.executeUpdate();
			
			hqlQuery = "DELETE FROM  StudentExamDetailLock sel where sel.id=:id";

			query = session.createQuery(hqlQuery);
			query.setParameter("id", id);
			query.executeUpdate();

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
			session.clear();
		}
	}

}
