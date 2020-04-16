package bk.elearning.service;

import java.util.ArrayList;

import org.springframework.web.multipart.MultipartFile;

import bk.elearning.entity.Question;
import bk.elearning.entity.Subject;
import bk.elearning.entity.Teacher;
import bk.elearning.entity.dto.PaginationResult;

public interface IQuestionService extends IGenericService<Question> {

	String uploadFile(MultipartFile file);
	
	boolean deleteFile(String path);
	
	PaginationResult<Question> getByTeacher(int teacherId,int page,int size);

	int[] importFromFile(MultipartFile file, Subject subject, String rootUrl);

	PaginationResult<Question> searchByFilter(int teacherId, String subjectId, String type, String level, String key,
			int page, int size);

	PaginationResult<Question> getByFilter(int teacherId, String subjectId, String type, String level, int page,
			int size);

	

}
