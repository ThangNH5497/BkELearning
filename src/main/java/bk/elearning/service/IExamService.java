package bk.elearning.service;

import javax.servlet.http.HttpServletRequest;

import bk.elearning.entity.Exam;
import bk.elearning.entity.dto.ExamDTO;
import bk.elearning.entity.dto.ExamPageDTO;
import bk.elearning.entity.dto.PaginationResult;
import bk.elearning.entity.dto.StudentExamDTO;
import bk.elearning.entity.dto.StudentResultQuestionDTO;
import bk.elearning.entity.dto.TimeCoundown;
import bk.elearning.entity.relationship.StudentExam;

public interface IExamService extends IGenericService<Exam> {

	PaginationResult<ExamPageDTO> getPageByCourse(int courseId, int page, int size);

	PaginationResult<ExamPageDTO> getPageBySubject(int subjectId, int page, int size);

	public int updateCourses(Exam exam);

	PaginationResult<ExamPageDTO> searchPageByCourse(int courseId, String key, int page, int size);

	PaginationResult<ExamPageDTO> searchPageBySubject(int subjectId, String key, int page, int size);


	ExamDTO getExamDTOById(Integer examId);

	ExamDTO doExam(Integer examId, Integer studentId,HttpServletRequest request);

	void updateResult(StudentResultQuestionDTO studentResultQuestionDTO);

	void updateResult(TimeCoundown tc);

	PaginationResult<ExamDTO> getByStudent(Integer studentId, String filter, int page, int size);

	PaginationResult<StudentExamDTO> getStudentExamUncomplete(int examId, int courseId, int page, int size);


	int updateStudentGrade(StudentExam studentExam);

	StudentExam getStudentExamUncompleteById(int id);

}
