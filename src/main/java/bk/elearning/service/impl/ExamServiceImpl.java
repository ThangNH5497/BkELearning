package bk.elearning.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.text.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import bk.elearning.entity.Answer;
import bk.elearning.entity.Exam;
import bk.elearning.entity.Question;
import bk.elearning.entity.User;
import bk.elearning.entity.dto.CustomUserDetails;
import bk.elearning.entity.dto.ExamDTO;
import bk.elearning.entity.dto.PaginationResult;
import bk.elearning.entity.relationship.ExamCourse;
import bk.elearning.entity.relationship.ExamFilter;
import bk.elearning.entity.relationship.ExamQuestion;
import bk.elearning.repository.IExamRepository;
import bk.elearning.service.IExamService;
import bk.elearning.utils.Constant;
import bk.elearning.utils.Util;

@Service
public class ExamServiceImpl implements IExamService {

	@Autowired
	IExamRepository examRepo;

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
			for (ExamFilter ef : exam.getExamFilters()) {
				ef.setExam(examUpdate);
			}
			for (ExamQuestion eq : exam.getExamQuestions()) {
				eq.setExam(examUpdate);
			}
			examUpdate.setExamFilters(exam.getExamFilters());
			examUpdate.setExamQuestions(exam.getExamQuestions());
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
			String time = format.format(Util.getDate());
			System.out.println("time : " + time);
			System.out.println("open : " + format.format(exam.getTimeOpen()));
			System.out.println("close : " + format.format(exam.getTimeClose()));

			System.out.println(currentTime.getTime());
			System.out.println(exam.getTimeOpen().getTime());
			System.out.println(exam.getTimeClose().getTime());

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
	public PaginationResult<ExamDTO> getByStudent(Integer studentId,int page,int size) {
		// TODO Auto-generated method stub
		if(page>0)
		{
			PaginationResult<ExamDTO> pages = examRepo.getByStudent(studentId,page-1,size);

			for (ExamDTO examDTO : pages.getData()) {
				Exam exam = new Exam();
				exam.setId(examDTO.getId());
				exam.setTimeOpen(examDTO.getTimeOpen());
				exam.setTimeClose(examDTO.getTimeClose());
				exam.setStatus(examDTO.getStatus());
				exam=checkStatus(exam);
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

}
