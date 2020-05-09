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


	@Column(name = "content", columnDefinition = "TEXT", nullable = false)
	private String content;

	@JsonIgnore
	@OneToMany(mappedBy = "question", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<ExamPaperInfo> examPaperInfos;

	@OneToMany(mappedBy = "question", cascade = CascadeType.ALL, fetch = FetchType.EAGER, targetEntity = Answer.class, orphanRemoval = true)
	private List<Answer> answers;

	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "category_id")
	private Category category;

	public Question() {
		super();
	}

	public Question(String name, int level, String type, String content, ExamPaper examPaper,
			Set<ExamPaperInfo> examPaperInfos, List<Answer> answers,Category category) {
		super();
		this.name = name;
		this.level = level;
		this.content = content;
		this.type = type;
		this.examPaperInfos = examPaperInfos;
		this.answers = answers;
		this.category = category;
	}

	public Question(int id, String name, int level, String type,String content,
			Set<ExamPaperInfo> examPaperInfos, List<Answer> answers,Category category) {
		super(id);
		this.name = name;
		this.level = level;
		this.content = content;
		this.examPaperInfos = examPaperInfos;
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

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	
}
