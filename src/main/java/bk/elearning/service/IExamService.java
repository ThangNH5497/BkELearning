package bk.elearning.service;

import bk.elearning.entity.Exam;
import bk.elearning.entity.ExamPaper;
import bk.elearning.entity.dto.PaginationResult;

public interface IExamService extends IGenericService<Exam>{

	

	PaginationResult<Exam> getPageByCourse(int courseId, int page, int size);

}
