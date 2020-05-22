package bk.elearning.service;

import bk.elearning.entity.ExamPaper;
import bk.elearning.entity.dto.PaginationResult;

public interface IExamPaperService extends IGenericService<ExamPaper>{

	PaginationResult<ExamPaper> getByUserAndSubject(String subject, int page, int size);

	PaginationResult<ExamPaper> searchByUserAndSubject(String subjectFilter, String key, int page, int size);

}
