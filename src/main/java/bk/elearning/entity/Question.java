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
import javax.persistence.OrderBy;

import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import bk.elearning.entity.relationship.ExamPaperQuestion;
import bk.elearning.entity.relationship.ExamQuestion;

@Entity
@DynamicUpdate
public class Question extends AbstractEntity {

	private String name;

	private int level;

	private String type;


	@Column(name = "content", columnDefinition = "TEXT", nullable = false)
	private String content;

	@JsonIgnore
	@OneToMany(mappedBy = "question", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<ExamQuestion> examQuestions;
	
	@JsonIgnore
	@OneToMany(mappedBy = "question", cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = ExamPaperQuestion.class, orphanRemoval = true)
	private Set<ExamPaperQuestion> examPaperQuestions;

	@OneToMany(mappedBy = "question", cascade = CascadeType.ALL, fetch = FetchType.EAGER, targetEntity = Answer.class, orphanRemoval = true)
	private List<Answer> answers;

	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "category_id")
	private Category category;

	public Question() {
		super();
	}
	
	public Question(int id, String name, int level, String type,String content) {
		super(id);
		this.name = name;
		this.level = level;
		this.content = content;
		this.type = type;
	}

	public Question(int id, String name, int level, String type,String content,
			Set<ExamQuestion> examQuestions, List<Answer> answers,Category category) {
		super(id);
		this.name = name;
		this.level = level;
		this.content = content;
		this.examQuestions = examQuestions;
		this.answers = answers;
		this.type = type;
		this.category = category;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}


	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Set<ExamQuestion> getExamQuestions() {
		return examQuestions;
	}

	public void setExamQuestions(Set<ExamQuestion> examQuestions) {
		this.examQuestions = examQuestions;
	}

	public List<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Set<ExamPaperQuestion> getExamPaperQuestions() {
		return examPaperQuestions;
	}

	public void setExamPaperQuestions(Set<ExamPaperQuestion> examPaperQuestions) {
		this.examPaperQuestions = examPaperQuestions;
	}

	
}
