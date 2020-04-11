package bk.elearning.service;

import org.springframework.web.multipart.MultipartFile;

import bk.elearning.entity.Question;

public interface IQuestionService extends IGenericService<Question>{

	String uploadFile(MultipartFile file);
	
	boolean deleteFile(String path);

}
