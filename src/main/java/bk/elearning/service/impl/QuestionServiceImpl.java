package bk.elearning.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.text.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import bk.elearning.entity.Answer;
import bk.elearning.entity.Category;
import bk.elearning.entity.Question;
import bk.elearning.entity.User;
import bk.elearning.entity.dto.CustomUserDetails;
import bk.elearning.entity.dto.PaginationResult;
import bk.elearning.entity.dto.QuestionFilter;
import bk.elearning.repository.IAnswerRepository;
import bk.elearning.repository.IQuestionRepository;
import bk.elearning.repository.ISubjectRepository;
import bk.elearning.service.IQuestionService;
import bk.elearning.utils.Constant;
import bk.elearning.utils.FileUpload;
import bk.elearning.utils.QuestionMapperUtil;

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
		CustomUserDetails user = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		if (user != null&&question.getCategory()!=null) {
			
			question.getCategory().setUser(new User(user.getId()));
			question.setContent(StringEscapeUtils.escapeHtml4(question.getContent()));
			for (

			Answer answer : question.getAnswers()) {
				answer.setContent(StringEscapeUtils.escapeHtml4(answer.getContent()));
				answer.setQuestion(question);
			}

			return questionRepository.save(question);
		}
		return 0;

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
			question.setCategory(q.getCategory());
			question.setName(q.getName());
			question.setType(q.getType());
			question.setCategory(q.getCategory());
			question.getAnswers().clear();

			// question.setAnswers(q.getAnswers());
			for (Answer answer : q.getAnswers()) {
				answer.setContent(StringEscapeUtils.escapeHtml4(answer.getContent()));
				answer.setQuestion(question);
				question.getAnswers().add(answer);
			}

			questionRepository.update(question);
			return 1;
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
	 * @param file : file to upload
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
	public int[] importFromFile(MultipartFile file, Category category, String rootUrl) {
		// TODO Auto-generated method stub
		int success = 0;
		int error = 0;
		try {
			CustomUserDetails user = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication()
					.getPrincipal();

			if (file != null && category != null && user != null) {
				mapper.setRootUrl(rootUrl);
				List<Question> questions = FileUpload.processFileExel(file, mapper);
				for (Question question : questions) {
					try {
						question.setCategory(category);
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
	public PaginationResult<Question> searchByTeacherAndFilter(String teacherId, String subjectId, String type,
			String level, String key, int page, int size) {
		// TODO Auto-generated method stub
		try {
			if (page > 0) {
				PaginationResult<Question> result = questionRepository.searchByTeacherAndFilter(teacherId, subjectId,
						type, level, key, page - 1, size);
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
	public PaginationResult<Question> getByTeacherAndFilter(String teacherId, String subjectId, String type,
			String level, int page, int size) {
		try {
			if (page > 0) {
				PaginationResult<Question> result = questionRepository.getByTeacherAndFilter(teacherId, subjectId, type,
						level, page - 1, size);
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

	@Override
	public PaginationResult<Question> getPublicQuestion(String subjectId, String type, String level, int page,
			int size) {
		// TODO Auto-generated method stub
		try {
			if (page > 0) {
				PaginationResult<Question> result = questionRepository.getPublicQuestion(subjectId, type, level,
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

	@Override
	public PaginationResult<Question> searchPublicQuestion(String subjectId, String type, String level, String key,
			int page, int size) {
		// TODO Auto-generated method stub
		try {
			if (page > 0) {
				PaginationResult<Question> result = questionRepository.searchPublicQuestion(subjectId, type, level, key,
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
		return null;
	}

	@Override
	public int copyToPublicRepo(ArrayList<Integer> ids, Category category) {
		// TODO Auto-generated method stub
		int success = 0;
		try {
			for (Integer integer : ids) {
				Question question = questionRepository.getById(integer);
				if (question != null) {
					Question qCopy = new Question();
					qCopy.setName(question.getName());
					qCopy.setLevel(question.getLevel());
					qCopy.setCategory(category);
					qCopy.setContent(question.getContent());
					qCopy.setType(question.getType());
					qCopy.setAnswers(new ArrayList<Answer>());
					for (Answer answer : question.getAnswers()) {
						Answer aCopy = new Answer();
						aCopy.setContent(answer.getContent());
						aCopy.setWeight(answer.getWeight());
						aCopy.setCorrect(answer.isCorrect());
						aCopy.setQuestion(qCopy);
						qCopy.getAnswers().add(aCopy);
					}
					questionRepository.save(qCopy);
				}
				success++;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		return success;
	}

	@Override
	public ArrayList<Question> getRandomQuestion(QuestionFilter filter) {
		// TODO Auto-generated method stub
		CustomUserDetails user = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		if(filter!=null&&user!=null) {
			ArrayList<Question> questions= questionRepository.getRandomQuestion(filter,user);
			for (Question question : questions) {
				question.setContent(StringEscapeUtils.unescapeHtml4(question.getContent()));
				for (Answer answer : question.getAnswers()) {
					answer.setContent(StringEscapeUtils.unescapeHtml4(answer.getContent()));
				}
			}
			return questions;
		}
		return null;
		
	}

	

}
