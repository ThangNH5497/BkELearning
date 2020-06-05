package bk.elearning.service;

import bk.elearning.entity.Exam;
import bk.elearning.entity.dto.ExamDTO;
import bk.elearning.entity.dto.PaginationResult;

public interface IExamService extends IGenericService<Exam> {

	PaginationResult<Exam> getPageByCourse(int courseId, int page, int size);

	PaginationResult<Exam> getPageBySubject(int subjectId, int page, int size);

	public int updateCourses(Exam exam);

	PaginationResult<Exam> searchPageByCourse(int courseId, String key, int page, int size);

	PaginationResult<Exam> searchPageBySubject(int subjectId, String key, int page, int size);

	PaginationResult<ExamDTO> getByStudent(Integer studentId,int pgae,int size);

	ExamDTO getExamDTOById(Integer examId);

}
