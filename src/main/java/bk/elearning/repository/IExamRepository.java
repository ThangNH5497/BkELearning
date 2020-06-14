package bk.elearning.repository;

import java.util.ArrayList;
import java.util.Set;

import bk.elearning.entity.Exam;
import bk.elearning.entity.dto.ExamDTO;
import bk.elearning.entity.dto.ExamPageDTO;
import bk.elearning.entity.dto.PaginationResult;
import bk.elearning.entity.dto.StudentExamDTO;
import bk.elearning.entity.dto.StudentResultQuestionDTO;
import bk.elearning.entity.relationship.ExamFilter;
import bk.elearning.entity.relationship.ExamPaperQuestion;
import bk.elearning.entity.relationship.ExamQuestion;
import bk.elearning.entity.relationship.StudentExam;

public interface IExamRepository extends IGenericRepository<Exam>{

	PaginationResult<ExamPageDTO> getPageByCourse(int courseId, int i, int size);

	PaginationResult<ExamPageDTO> getPageBySubject(int courseId, int i, int size);

	PaginationResult<ExamPageDTO> searchPageByCourse(int courseId, String key, int i, int size);

	PaginationResult<ExamPageDTO> searchPageBySubject(int subjectId, String key, int i, int size);

	PaginationResult<ExamDTO> getByStudent(Integer studentId, String filter, int page, int size);

	int  updateStatus(Exam exam);

	ExamDTO getExamDTOById(Integer examId);

	ExamDTO getByIdAndStudent(Integer examId, Integer studentId);

	ArrayList<ExamQuestion> getRandomQuestionByFilter(int examId, Set<ExamFilter> examFilters);

	void updateResult(StudentResultQuestionDTO studentResultQuestionDTO);

	StudentExam getStudentExamById(int studentExamId);

	void updateStudentExam(StudentExam se);

	public Long getCoutExamProcess(int examId,int courseId);

	PaginationResult<StudentExamDTO> getStudentExamUncomplete(int examId, int courseId, int start, int size);

	int updateStudentGrade(ExamPaperQuestion epq);

	StudentExam getStudentExamUncompleteById(int id);

}
