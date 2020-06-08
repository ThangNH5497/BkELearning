package bk.elearning.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.text.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import bk.elearning.entity.Answer;
import bk.elearning.entity.Exam;
import bk.elearning.entity.ExamPaper;
import bk.elearning.entity.Question;
import bk.elearning.entity.Student;
import bk.elearning.entity.User;
import bk.elearning.entity.dto.CustomUserDetails;
import bk.elearning.entity.dto.ExamDTO;
import bk.elearning.entity.dto.PaginationResult;
import bk.elearning.entity.dto.StudentResultQuestionDTO;
import bk.elearning.entity.dto.TimeCoundown;
import bk.elearning.entity.relationship.ExamCourse;
import bk.elearning.entity.relationship.ExamFilter;
import bk.elearning.entity.relationship.ExamPaperQuestion;
import bk.elearning.entity.relationship.ExamPaperQuestionAnswer;
import bk.elearning.entity.relationship.ExamQuestion;
import bk.elearning.entity.relationship.StudentExam;
import bk.elearning.repository.IExamPaperRepository;
import bk.elearning.repository.IExamRepository;
import bk.elearning.service.IExamService;
import bk.elearning.utils.Constant;
import bk.elearning.utils.Util;

@Service
public class ExamServiceImpl implements IExamService {

	@Autowired
	IExamRepository examRepo;

	@Autowired
	IExamPaperRepository examPaperRepo;
	
	static HashMap<String, HttpSession> sessionProcess=new HashMap<String, HttpSession>();

	@Override
	public Exam getById(int id) {
		// TODO Auto-generated method stub
		Exam exam = examRepo.getById(id);
		try {
			for (ExamQuestion eq : exam.getExamQuestions()) {
				Question q = eq.getQuestion();
				q.setContent(StringEscapeUtils.unescapeHtml4(q.getContent()));
				for (Answer answer : q.getAnswers()) {
					answer.setContent(StringEscapeUtils.unescapeHtml4(answer.getContent()));
				}
			}
			exam = checkStatus(exam);
			return exam;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;

	}

	@Override
	public List<Exam> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int save(Exam exam) {
		// TODO Auto-generated method stub
		CustomUserDetails user = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		if (user != null) {
			exam.setStatus(Constant.EXAM_STATUS_CLOSE);
			exam.setCreateAt(Util.getDate());
			for (ExamCourse ec : exam.getExamCourses()) {
				ec.setExam(exam);
			}
			exam.setUser(new User(user.getId()));
			return examRepo.save(exam);
		} else
			return 0;

	}

	@Override
	public int delete(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Exam exam) {
		// TODO Auto-generated method stub
		Exam examUpdate = examRepo.getById(exam.getId());
		try {
			examUpdate.setName(exam.getName());
			examUpdate.setDescriptor(exam.getDescriptor());
			examUpdate.setTime(exam.getTime());
			examUpdate.setGrade(exam.getGrade());
			examUpdate.setTimeClose(exam.getTimeClose());
			examUpdate.setTimeOpen(exam.getTimeOpen());
			examUpdate.setUpdateAt(Util.getDate());
			examUpdate.getExamFilters().clear();
			examUpdate.getExamQuestions().clear();
			for (ExamFilter ef : exam.getExamFilters()) {
				ef.setExam(examUpdate);
				examUpdate.getExamFilters().add(ef);
			}
			for (ExamQuestion eq : exam.getExamQuestions()) {
				eq.setExam(examUpdate);
				examUpdate.getExamQuestions().add(eq);
			}

			return examRepo.update(examUpdate);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return 0;
	}

	@Override
	public int updateCourses(Exam exam) {

		try {
			Exam examUpdate = examRepo.getById(exam.getId());
			examUpdate.getExamCourses().clear();
			for (ExamCourse ec : exam.getExamCourses()) {
				ec.setExam(examUpdate);
				examUpdate.getExamCourses().add(ec);
			}
			return examRepo.update(examUpdate);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return 0;
	}

	@Override
	public Exam getByCode(String code) {
		// TODO Auto-generated method stub
		return examRepo.getByCode(code);
	}

	@Override
	public int deleteMultiple(ArrayList<Integer> ids) {
		// TODO Auto-generated method stub
		return 0;
	}

	// get page by subject of admin
	@Override
	public PaginationResult<Exam> getPageByCourse(int courseId, int page, int size) {
		// TODO Auto-generated method stub
		try {
			if (page > 0) {

				// return examRepo.getPageByCourse(courseId, page - 1, size);
				PaginationResult<Exam> pages = examRepo.getPageByCourse(courseId, page - 1, size);

				for (Exam exam : pages.getData()) {
					exam = checkStatus(exam);
				}

				return pages;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		return null;
	}

	@Override
	public PaginationResult<Exam> getPageBySubject(int subjectId, int page, int size) {
		// TODO Auto-generated method stub
		try {
			if (page > 0) {
				CustomUserDetails user = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication()
						.getPrincipal();
				if (user.getRole().equals(Constant.ROLE_ADMIN)) {
					// return examRepo.getPageBySubject(subjectId, page - 1, size);
					PaginationResult<Exam> pages = examRepo.getPageBySubject(subjectId, page - 1, size);

					for (Exam exam : pages.getData()) {
						exam = checkStatus(exam);
					}

					return pages;
				}

			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		return null;
	}

	@Override
	public PaginationResult<Exam> searchPageByCourse(int courseId, String key, int page, int size) {
		// TODO Auto-generated method stub
		try {
			if (page > 0) {

				PaginationResult<Exam> pages = examRepo.searchPageByCourse(courseId, key, page - 1, size);

				for (Exam exam : pages.getData()) {
					exam = checkStatus(exam);
				}

				return pages;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		return null;
	}

	@Override
	public PaginationResult<Exam> searchPageBySubject(int subjectId, String key, int page, int size) {
		// TODO Auto-generated method stub
		try {
			if (page > 0) {

				// return examRepo.searchPageBySubject(subjectId, key, page - 1, size);
				PaginationResult<Exam> pages = examRepo.searchPageBySubject(subjectId, key, page - 1, size);

				for (Exam exam : pages.getData()) {
					exam = checkStatus(exam);
				}

				return pages;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		return null;
	}

	private Exam checkStatus(Exam exam) {
		try {
			Date currentTime = Util.getDate();
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss-SSS");
			// Output "Wed Sep 26 14:23:28 EST 2012"
			/*
			 * String time = format.format(Util.getDate()); System.out.println("time : " +
			 * time); System.out.println("open : " + format.format(exam.getTimeOpen()));
			 * System.out.println("close : " + format.format(exam.getTimeClose()));
			 * 
			 * System.out.println(currentTime.getTime());
			 * System.out.println(exam.getTimeOpen().getTime());
			 * System.out.println(exam.getTimeClose().getTime());
			 */

			if (currentTime.getTime() < exam.getTimeOpen().getTime()) {
				if (!exam.getStatus().equals(Constant.EXAM_STATUS_CLOSE)) {
					exam.setStatus(Constant.EXAM_STATUS_CLOSE);
					examRepo.updateStatus(exam);
				}

			} else if (currentTime.getTime() > exam.getTimeClose().getTime()) {
				if (!exam.getStatus().equals(Constant.EXAM_STATUS_FINISH)) {
					exam.setStatus(Constant.EXAM_STATUS_FINISH);
					examRepo.updateStatus(exam);
				}
			} else {
				if (!exam.getStatus().equals(Constant.EXAM_STATUS_OPEN)) {
					exam.setStatus(Constant.EXAM_STATUS_OPEN);
					examRepo.updateStatus(exam);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return exam;

	}

	@Override
	public PaginationResult<ExamDTO> getByStudent(Integer studentId, int page, int size) {
		// TODO Auto-generated method stub
		if (page > 0) {
			PaginationResult<ExamDTO> pages = examRepo.getByStudent(studentId, page - 1, size);

			for (ExamDTO examDTO : pages.getData()) {
				Exam exam = new Exam();
				exam.setId(examDTO.getId());
				exam.setTimeOpen(examDTO.getTimeOpen());
				exam.setTimeClose(examDTO.getTimeClose());
				exam.setStatus(examDTO.getStatus());
				exam = checkStatus(exam);
				examDTO.setStatus(exam.getStatus());
			}

			return pages;

		}
		return null;
	}

	@Override
	public ExamDTO getExamDTOById(Integer examId) {
		// TODO Auto-generated method stub
		return examRepo.getExamDTOById(examId);
	}

	@Override
	public ExamDTO doExam(Integer examId, Integer studentId,HttpServletRequest request) {
		// TODO Auto-generated method stub
		try {
			CustomUserDetails user = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication()
					.getPrincipal();
			HttpSession session=request.getSession();
			
			
			if (user != null && user.getId() == studentId) {
				ExamDTO examDTO = examRepo.getByIdAndStudent(examId, studentId);
				if (examDTO.getStudentExam() != null) {
					
					for (ExamPaperQuestion epq : examDTO.getStudentExam().getExamPaper().getExamPaperQuestions()) {
						epq.getQuestion().setContent(StringEscapeUtils.unescapeHtml4(epq.getQuestion().getContent()));

						List<ExamPaperQuestionAnswer> answerInfos = epq.getExamPaperQuestionAnswers();
						// epq.getQuestion().getAnswers();
						epq.getQuestion().getAnswers().clear();
						for (int i = 0; i < answerInfos.size(); i++) {
							// answers.set(i, epq.getExamPaperQuestionAnswers().get(i).getAnswer());
							if(answerInfos.get(i).getAnswer()!=null)
							{
								answerInfos.get(i).getAnswer().setContent(
										StringEscapeUtils.unescapeHtml4(answerInfos.get(i).getAnswer().getContent()));

								
							}
							

						}
					}
					session.setAttribute(Constant.SESSION_TIME_COUNTDOWN,new TimeCoundown(examDTO.getStudentExam().getTimeLeft(), null, examDTO.getStudentExam().getId()));
					sessionProcess.put(session.getId(), session);
					return examDTO;

				}
				// create new exam paper for student
				else {
					List<ExamQuestion> eqs = examRepo.getRandomQuestionByFilter(examDTO.getId(),
							examDTO.getExamFilters());
					eqs = shuffleArray(eqs);
					
					Exam exam = examRepo.getById(examDTO.getId());
					StudentExam studentExam = new StudentExam();
					ExamPaper examPaper = new ExamPaper();
					studentExam.setStatus(Constant.STUDENT_EXAM_STATUS_CONTINUE);
					studentExam.setStudent(new Student(user.getId()));
					examPaper.setCreateAt(Util.getDate());
					examPaper.setCode(String.valueOf(exam.getId()) + String.valueOf(user.getId()));
					examPaper.setName(exam.getName());
					examPaper.setSubject(exam.getSubject());
					examPaper.setTime(exam.getTime());
					examPaper.setUser(null);
					examPaper.setExamPaperQuestions(new HashSet<ExamPaperQuestion>());
					int i = 1;
					for (ExamQuestion examQuestion : eqs) {
						ExamPaperQuestion epq = new ExamPaperQuestion();
						
						Question question = examQuestion.getQuestion();
						question.setAnswers(shuffleArray(question.getAnswers()));
						//test
						question.setContent(StringEscapeUtils.unescapeHtml4(question.getContent()));
						
						
						epq.setExamPaperQuestionAnswers(new ArrayList<ExamPaperQuestionAnswer>());
						
						if(question.getType().equals("FILL_WORD"))
						{
							ExamPaperQuestionAnswer epqa = new ExamPaperQuestionAnswer();
							epqa.setStudentAnswer(null);
							epqa.setExamPaperQuestion(epq);
							epq.getExamPaperQuestionAnswers().add(epqa);
						}
						else
						{
							int j = 1;
							for (Answer answer : question.getAnswers()) {
								answer.setContent(StringEscapeUtils.unescapeHtml4(answer.getContent()));
								ExamPaperQuestionAnswer epqa = new ExamPaperQuestionAnswer();
								epqa.setAnswer(answer);
								epqa.setAnswerOrder(j);
								epqa.setExamPaperQuestion(epq);
								epq.getExamPaperQuestionAnswers().add(epqa);
								j++;
							}
						}
						
						question.getAnswers().clear();
						epq.setQuestion(question);
						epq.setQuestionGrade(examQuestion.getGrade());
						epq.setQuestionOrder(i);
						epq.setExamPaper(examPaper);
						examPaper.getExamPaperQuestions().add(epq);
						i++;
					}

					Set<StudentExam> studentExams = new HashSet<StudentExam>();
				
					studentExams.add(studentExam);
					studentExam.setTimeLeft(exam.getTime());
					examPaper.setStudentExams(studentExams);
					examPaperRepo.save(examPaper);
					studentExam.setExamPaper(examPaper);
					studentExam.setExam(exam);
					exam.setStudentExams(studentExams);
					examRepo.update(exam);
					
					examDTO.setStudentExam(studentExam);
					examDTO.getExamFilters().clear();
					
					//add session to process
					session.setAttribute(Constant.SESSION_TIME_COUNTDOWN,new TimeCoundown(examDTO.getStudentExam().getTimeLeft(), null, examDTO.getStudentExam().getId()));
					sessionProcess.put(session.getId(), session);
					return examDTO;

				}
			}

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
		return null;
	}
	
	@Override
	public void updateResult(StudentResultQuestionDTO studentResultQuestionDTO) {
		try {
			examRepo.updateResult(studentResultQuestionDTO);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
	

	private <T> List<T> shuffleArray(List<T> list) {
		for (int i = list.size() - 1; i > 0; i--) {
			int j = (int) (Math.random() * (i + 1));
			T temp = list.get(i);
			list.set(i, list.get(j));
			list.set(j, temp);
		}
		return list;
	}

	public static HashMap<String, HttpSession> getSessionProcess() {
		return sessionProcess;
	}

	public static void setSessionProcess(HashMap<String, HttpSession> sessionProcess) {
		ExamServiceImpl.sessionProcess = sessionProcess;
	}

	@Override
	public void updateResult(TimeCoundown tc) {
		// TODO Auto-generated method stub
		try {
			StudentExam se=examRepo.getStudentExamById(tc.getStudentExamId());
			if(se!=null)
			{
				if(!(se.getTimeLeft()==0&&se.getStatus().equals(Constant.STUDENT_EXAM_STATUS_FINISH)))
				{
					se.setTimeLeft(tc.getTimeLeft());
					if(tc.getTimeLeft()==0)
					{
						se.setStatus(Constant.STUDENT_EXAM_STATUS_FINISH);
					}
					examRepo.updateStudentExam(se);
				}
				
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}

	

}
