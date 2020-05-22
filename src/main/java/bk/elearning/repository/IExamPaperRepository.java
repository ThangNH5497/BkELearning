package bk.elearning.repository;

import bk.elearning.entity.ExamPaper;
import bk.elearning.entity.dto.PaginationResult;

public interface IExamPaperRepository extends IGenericRepository<ExamPaper>{

	PaginationResult<ExamPaper> getByAdminRepoAndSubject( String subject, int start, int size);
	PaginationResult<ExamPaper> searchByAdminRepoAndSubject(String subject,String key, int start, int size);
	PaginationResult<ExamPaper> getByTeacherRepoAndSubject(String teacherFilter, String subject, int start, int size);
	PaginationResult<ExamPaper> searchByTeacherRepoAndSubject(String teacherFilter, String subject,String key, int start, int size);
}
