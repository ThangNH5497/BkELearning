package bk.elearning.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.text.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import bk.elearning.entity.Answer;
import bk.elearning.entity.Question;
import bk.elearning.entity.dto.PaginationResult;
import bk.elearning.repository.IQuestionRepository;
import bk.elearning.service.IQuestionService;
import bk.elearning.utils.Constant;
import bk.elearning.utils.FileUpload;

@Service
public class QuestionServiceImpl implements IQuestionService{

	@Autowired
	IQuestionRepository questionRepository;
	@Override
	public Question getById(int id) {
		// TODO Auto-generated method stub
		Question question=questionRepository.getById(id);
		if(question!=null)
		{
			question.setContent(StringEscapeUtils.unescapeHtml4(question.getContent()));
			//encode answer to java string
			for (Answer answer : question.getAnswers()) {
				answer.setContent(StringEscapeUtils.unescapeHtml4(answer.getContent()));
				answer.setQuestion(question);
			}
		}
		
		return question;
	}


	@Override
	public List<Question> getAll() {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public int save(Question question) {
		// TODO Auto-generated method stub
		//encode html to java string
		question.setContent(StringEscapeUtils.escapeHtml4(question.getContent()));
		//encode answer to java string
		for (Answer answer : question.getAnswers()) {
			answer.setContent(StringEscapeUtils.escapeHtml4(answer.getContent()));
			answer.setQuestion(question);
		}
		
		return questionRepository.save(question);
	}

	@Override
	public int delete(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Question t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Question getByCode(String code) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int deleteMultiple(ArrayList<Integer> ids) {
		// TODO Auto-generated method stub
		return 0;
	}


	/**
	 * save file
	 * @return: path of file upload
	 * @param 'file' : file to upload
	 */
	@Override
	public String uploadFile(MultipartFile file) {
		// TODO Auto-generated method stub
		String path=FileUpload.saveFile(file, Constant.UPLOAD_QESTION_DIR);
		return path;
	}

	/**
	 * delete file by path
	 * @return: path of file upload
	 * @param 'file' : file to upload
	 */
	@Override
	public boolean deleteFile(String path) {
		// TODO Auto-generated method stub
		path=path.replaceAll("\"", "");
		return FileUpload.deleteFile(path);
	}


	@Override
	public PaginationResult<Question> getByTeacher(int teacherId, int page, int size) {
		// TODO Auto-generated method stub
		if(page>0)
		{
			PaginationResult<Question> result= questionRepository.findByTeacher(teacherId,page-1,size);
			for (Question question : result.getData()) {
				question.setContent(StringEscapeUtils.unescapeHtml4(question.getContent()));
				for (Answer answer : question.getAnswers()) {
					answer.setContent(StringEscapeUtils.unescapeHtml4(answer.getContent()));
				}
			}
			return result;
		}
			
		return null;
	}

}
