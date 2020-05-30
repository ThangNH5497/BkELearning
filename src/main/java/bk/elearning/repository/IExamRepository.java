package bk.elearning.repository;

import bk.elearning.entity.Exam;
import bk.elearning.entity.ExamPaper;
import bk.elearning.entity.dto.PaginationResult;

public interface IExamRepository extends IGenericRepository<Exam>{

	PaginationResult<Exam> getPageByCourse(int courseId, int i, int size);

}
