package bk.elearning.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.text.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import bk.elearning.entity.Answer;
import bk.elearning.entity.Question;
import bk.elearning.entity.Subject;
import bk.elearning.entity.Teacher;
import bk.elearning.entity.dto.CustomUserDetails;
import bk.elearning.entity.dto.PaginationResult;
import bk.elearning.repository.IAnswerRepository;
import bk.elearning.repository.IQuestionRepository;
import bk.elearning.repository.ISubjectRepository;
import bk.elearning.service.IQuestionService;
import bk.elearning.utils.Constant;
import bk.elearning.utils.FileUpload;
import bk.elearning.utils.QuestionMapperUtil;
import bk.elearning.utils.UserMapperUtil;

@Service
public class QuestionServiceImpl implements IQuestionService {

	@Autowired
	QuestionMapperUtil mapper;
	@Autowired
	IQuestionRepository questionRepository;
	@Autowired
	ISubjectRepository subjectRepository;
	@Autowired
	IAnswerRepository answerRepository;

	@Override

	public Question getById(int id) {
		// TODO Auto-generated method stub
		Question question = questionRepository.getById(id);
		if (question != null) {
			question.setContent(StringEscapeUtils.unescapeHtml4(question.getContent()));
			// encode answer to java string
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
		// encode html to java string
		question.setContent(StringEscapeUtils.escapeHtml4(question.getContent()));
		if (question.getSubject() != null) {
			if (question.getSubject().getId() <= 0)
				question.setSubject(null);
			else if (subjectRepository.getById(question.getSubject().getId()) == null) {
				return 0;
			}
		} else
			question.setSubject(null);
		// encode answer to java string
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
	public int update(Question q) {
		try {

			// answerRepository.deleteByQuestion(q.getId());

			Question question = questionRepository.getById(q.getId());			
			question.setContent(StringEscapeUtils.escapeHtml4(q.getContent()));
			question.setLevel(q.getLevel());
			question.setSubject(q.getSubject());
			question.setName(q.getName());
			question.setType(q.getType());
			
			question.getAnswers().clear();
			
		//	question.setAnswers(q.getAnswers());
			for (Answer answer : q.getAnswers()) {
				answer.setContent(StringEscapeUtils.escapeHtml4(answer.getContent()));
				answer.setQuestion(question);
				question.getAnswers().add(answer);
			}
			
			questionRepository.update(question);
			return 1 ;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.toString());
		}
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
		int success = 0;
		try {
			for (int i = 0; i < ids.size(); i++) {
				questionRepository.delete(ids.get(i));
				success++;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return success;
	}

	/**
	 * save file
	 * 
	 * @return: path of file upload
	 * @param 'file' : file to upload
	 */
	@Override
	public String uploadFile(MultipartFile file) {
		// TODO Auto-generated method stub
		String path = FileUpload.saveFile(file, Constant.UPLOAD_QESTION_DIR);
		return path;
	}

	/**
	 * delete file by path
	 * 
	 * @return: path of file upload
	 * @param 'file' : file to upload
	 */
	@Override
	public boolean deleteFile(String path) {
		// TODO Auto-generated method stub
		path = path.replaceAll("\"", "");
		return FileUpload.deleteFile(path);
	}

	@Override
	public PaginationResult<Question> getByTeacher(int teacherId, int page, int size) {
		// TODO Auto-generated method stub
		if (page > 0) {
			PaginationResult<Question> result = questionRepository.findByTeacher(teacherId, page - 1, size);
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

	@Override
	public int[] importFromFile(MultipartFile file, Subject subject) {
		// TODO Auto-generated method stub
		int success = 0;
		int error = 0;
		try {
			CustomUserDetails user = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication()
					.getPrincipal();

			if (file != null && user != null) {
				Teacher teacher = new Teacher();
				teacher.setId(user.getId());
				List<Question> questions = FileUpload.processFileExel(file, mapper);
				subject = subjectRepository.getById(subject.getId());
				for (Question question : questions) {
					try {
						question.setSubject(subject);
						question.setTeacher(teacher);
						questionRepository.save(question);
						success++;
					} catch (Exception e) {
						// TODO: handle exception
					}

				}
				error = questions.size() - success;
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

		return new int[] { success, error };
	}

	@Override
	public PaginationResult<Question> searchByFilter(int teacherId, String subjectId, String type, String level,
			String key, int page, int size) {
		// TODO Auto-generated method stub
		try {
			if (teacherId > 0 && page > 0) {
				PaginationResult<Question> result = questionRepository.searchByFilter(teacherId, subjectId, type, level,
						key, page - 1, size);
				for (Question question : result.getData()) {
					question.setContent(StringEscapeUtils.unescapeHtml4(question.getContent()));
					for (Answer answer : question.getAnswers()) {
						answer.setContent(StringEscapeUtils.unescapeHtml4(answer.getContent()));
					}
				}
				return result;
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	@Override
	public PaginationResult<Question> getByFilter(int teacherId, String subjectId, String type, String level, int page,
			int size) {
		try {
			if (teacherId > 0 && page > 0) {
				PaginationResult<Question> result = questionRepository.getByFilter(teacherId, subjectId, type, level,
						page - 1, size);
				for (Question question : result.getData()) {
					question.setContent(StringEscapeUtils.unescapeHtml4(question.getContent()));
					for (Answer answer : question.getAnswers()) {
						answer.setContent(StringEscapeUtils.unescapeHtml4(answer.getContent()));
					}
				}
				return result;
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
		// TODO Auto-generated method stub
		return null;
	}

}
