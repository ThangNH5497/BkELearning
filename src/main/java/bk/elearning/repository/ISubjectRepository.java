package bk.elearning.repository;

import bk.elearning.entity.Subject;
import bk.elearning.entity.dto.PaginationResult;

public interface ISubjectRepository extends IGenericRepository<Subject>{

	PaginationResult<Subject> search( String key, int start, int size);

	PaginationResult<Subject> getPage(int i, int size);

}
