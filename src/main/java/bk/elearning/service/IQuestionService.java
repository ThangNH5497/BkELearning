package bk.elearning.service;

import java.util.ArrayList;

import org.springframework.web.multipart.MultipartFile;

import bk.elearning.entity.Category;
import bk.elearning.entity.Question;
import bk.elearning.entity.Subject;
import bk.elearning.entity.Teacher;
import bk.elearning.entity.dto.PaginationResult;

public interface IQuestionService extends IGenericService<Question> {

	String uploadFile(MultipartFile file);

	boolean deleteFile(String path);

	PaginationResult<Question> getByTeacher(int teacherId, int page, int size);

	PaginationResult<Question> searchByTeacherAndFilter(String teacherId, String subjectId, String type, String level,
			String key, int page, int size);

	PaginationResult<Question> getByTeacherAndFilter(String teacherId, String subjectId, String type, String level,
			int page, int size);


	public int[] importFromFile(MultipartFile file, Category category, String rootUrl);

	PaginationResult<Question> getPublicQuestion(String subjectId, String type, String level, int page, int size);

	PaginationResult<Question> searchPublicQuestion(String subjectId, String type, String level, String key, int page,
			int size);

	public int copyToPublicRepo(ArrayList<Integer> ids, Category category);

}
