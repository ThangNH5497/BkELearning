package bk.elearning.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Question extends AbstractEntity {

	private String code;

	private String name;

	private int level;

	@Column(name = "content", columnDefinition = "TEXT", nullable = false)
	private String content;

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

	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "teacher_id")
	private Teacher teacher;

	@OneToMany(mappedBy = "question", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Answer> answers;

	public Question() {
		super();
	}

	public Question(String code, String name, int level, String content, QuestionCategory questionCategory,
			Subject subject, ExamPaper examPaper, List<Answer> answers, Teacher teacher) {
		super();
		this.code = code;
		this.name = name;
		this.level = level;
		this.content = content;
		this.questionCategory = questionCategory;
		this.subject = subject;
		this.examPaper = examPaper;
		this.answers = answers;
		this.teacher = teacher;
	}

	public Question(int id, String code, String name, int level, String content, QuestionCategory questionCategory,
			Subject subject, ExamPaper examPaper, List<Answer> answers, Teacher teacher) {
		super(id);
		this.code = code;
		this.name = name;
		this.level = level;
		this.content = content;
		this.questionCategory = questionCategory;
		this.subject = subject;
		this.examPaper = examPaper;
		this.answers = answers;
		this.teacher = teacher;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public List<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

}
