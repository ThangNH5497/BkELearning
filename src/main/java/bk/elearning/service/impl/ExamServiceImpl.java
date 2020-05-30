package bk.elearning.service.impl;

import java.util.ArrayList;
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
				Question q=eq.getQuestion();
				q.setContent(StringEscapeUtils.unescapeHtml4(q.getContent()));
				for (Answer answer : q.getAnswers()) {
					answer.setContent(StringEscapeUtils.unescapeHtml4(answer.getContent()));
				}
			}
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
	public Exam getByCode(String code) {
		// TODO Auto-generated method stub
		return examRepo.getByCode(code);
	}

	@Override
	public int deleteMultiple(ArrayList<Integer> ids) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public PaginationResult<Exam> getPageByCourse(int courseId, int page, int size) {
		// TODO Auto-generated method stub
		if (page > 0) {

			return examRepo.getPageByCourse(courseId, page - 1, size);
		}

		return null;
	}

}
