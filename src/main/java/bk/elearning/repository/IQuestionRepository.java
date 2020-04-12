package bk.elearning.repository;

import bk.elearning.entity.Question;
import bk.elearning.entity.dto.PaginationResult;

public interface IQuestionRepository extends IGenericRepository<Question>,ISubjectComponentRepository<Question>{

	

}
