package bk.elearning.service;

import java.util.ArrayList;

import bk.elearning.entity.Category;
import bk.elearning.entity.dto.PaginationResult;

public interface ICategoryService extends IGenericService<Category>{

	PaginationResult<Category> getBySubject(int subjectId, int page, int size);

	PaginationResult<Category> searchBySubject(int subjectId, String key, int page, int size);

	ArrayList<Category> getBySubject(int subjectId);

}
