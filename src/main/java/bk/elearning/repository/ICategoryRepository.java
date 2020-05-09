package bk.elearning.repository;

import java.util.ArrayList;

import bk.elearning.entity.Category;
import bk.elearning.entity.dto.PaginationResult;

public interface ICategoryRepository extends IGenericRepository<Category>{

	PaginationResult<Category> getPageBySubject(int subjectId,int userId, int start, int size);

	PaginationResult<Category> searchPageBySubject(int subjectId,int userId, String key, int start, int size);

	ArrayList<Category> getBySubject(int subjectId,int userId);

}
