package bk.elearning.entity;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import bk.elearning.entity.relationship.ExamPaperInfo;

@Entity
@DynamicUpdate
public class Question extends AbstractEntity {

	private String name;

	private int level;

	private String type;
	
	private String bankType;

	@Column(name = "content", columnDefinition = "TEXT", nullable = false)
	private String content;

	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "question_category_id")
	private QuestionCategory questionCategory;

	@ManyToOne
	@JoinColumn(name = "subject_id")
	private Subject subject;

	@JsonIgnore
	@OneToMany(mappedBy="question",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Set<ExamPaperInfo> examPaperInfos;

	@ManyToOne
	@JoinColumn(name = "teacher_id")
	private Teacher teacher;

	@OneToMany(mappedBy = "question", cascade = CascadeType.ALL, fetch = FetchType.EAGER, targetEntity = Answer.class, orphanRemoval = true)
	private List<Answer> answers;
	
	@ManyToOne
	@JoinColumn(name = "category_id")
	private QuestionCategory category;

	public Question() {
		super();
	}

	public Question(String name, int level, String type,String bankType, String content, QuestionCategory questionCategory,
			Subject subject, ExamPaper examPaper, Set<ExamPaperInfo> examPaperInfos, Teacher teacher,List<Answer> answers,QuestionCategory category) {
		super();

		this.name = name;
		this.level = level;
		this.content = content;
		this.type = type;
		this.bankType=bankType;
		this.questionCategory = questionCategory;
		this.subject = subject;
		this.examPaperInfos = examPaperInfos;
		this.answers = answers;
		this.teacher = teacher;
		this.category=category;
	}

	public Question(int id, String name, int level, String type,String bankType, String content, QuestionCategory questionCategory,
			Subject subject, Set<ExamPaperInfo> examPaperInfos, List<Answer> answers, Teacher teacher,QuestionCategory category) {
		super(id);
		this.name = name;
		this.level = level;
		this.content = content;
		this.questionCategory = questionCategory;
		this.subject = subject;
		this.examPaperInfos = examPaperInfos;
		this.answers = answers;
		this.teacher = teacher;
		this.type = type;
		this.bankType=bankType;
		this.category=category;
	}

	
	public String getBankType() {
		return bankType;
	}

	public void setBankType(String bankType) {
		this.bankType = bankType;
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

	

	public Set<ExamPaperInfo> getExamPaperInfos() {
		return examPaperInfos;
	}

	public void setExamPaperInfos(Set<ExamPaperInfo> examPaperInfos) {
		this.examPaperInfos = examPaperInfos;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public QuestionCategory getCategory() {
		return category;
	}

	public void setCategory(QuestionCategory category) {
		this.category = category;
	}
	
}
