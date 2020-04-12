package bk.elearning.service;

import org.springframework.web.multipart.MultipartFile;

import bk.elearning.entity.Question;
import bk.elearning.entity.Teacher;
import bk.elearning.entity.dto.PaginationResult;

public interface IQuestionService extends IGenericService<Question> {

	String uploadFile(MultipartFile file);
	
	boolean deleteFile(String path);
	
	PaginationResult<Question> getByTeacher(int teacherId,int page,int size);

}
