package bk.elearning.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Question extends AbstractEntity{

	private String code;
	
	private int level;
	
	private String content;
	
	private String image;
	
	private String fill;
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "question_category_id")
	private QuestionCategory questionCategory;
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "subject_id")
	private Subject subject;
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "exam_paper_id")
	private ExamPaper examPaper;
	
	@OneToMany(mappedBy="question",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Answer> answer;

	
	public Question() {
		super();
	}


	public Question(String code, int level, String content, String image, String fill,
			QuestionCategory questionCategory, Subject subject, ExamPaper examPaper, List<Answer> answer) {
		super();
		this.code = code;
		this.level = level;
		this.content = content;
		this.image = image;
		this.fill = fill;
		this.questionCategory = questionCategory;
		this.subject = subject;
		this.examPaper = examPaper;
		this.answer = answer;
	}


	public String getCode() {
		return code;
	}


	public void setCode(String code) {
		this.code = code;
	}


	public int getLevel() {
		return level;
	}


	public void setLevel(int level) {
		this.level = level;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	public String getImage() {
		return image;
	}


	public void setImage(String image) {
		this.image = image;
	}


	public String getFill() {
		return fill;
	}


	public void setFill(String fill) {
		this.fill = fill;
	}


	public QuestionCategory getQuestionCategory() {
		return questionCategory;
	}


	public void setQuestionCategory(QuestionCategory questionCategory) {
		this.questionCategory = questionCategory;
	}


	public Subject getSubject() {
		return subject;
	}


	public void setSubject(Subject subject) {
		this.subject = subject;
	}


	public ExamPaper getExamPaper() {
		return examPaper;
	}


	public void setExamPaper(ExamPaper examPaper) {
		this.examPaper = examPaper;
	}


	public List<Answer> getAnswer() {
		return answer;
	}


	public void setAnswer(List<Answer> answer) {
		this.answer = answer;
	}

	
}
