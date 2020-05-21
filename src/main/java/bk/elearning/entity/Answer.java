package bk.elearning.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import bk.elearning.entity.relationship.ExamPaperQuestionAnswer;

@Entity
public class Answer extends AbstractEntity {

	private String content;

	@Column(name = "is_correct")
	boolean isCorrect;

	private float weight;

	@ManyToOne(cascade = CascadeType.MERGE)
	@JsonIgnore
	@JoinColumn(name = "question_id")
	private Question question;

	@JsonIgnore
	@OneToMany(mappedBy = "answer", cascade = CascadeType.ALL, 
			fetch = FetchType.LAZY,orphanRemoval = true)
	private Set<ExamPaperQuestionAnswer> examPaperQuestionAnswers;
	


	public Answer() {
		super();
	}

	public Answer(int id, String content, float weight, boolean isCorrect, Question question) {
		super(id);
		this.content = content;
		this.isCorrect = isCorrect;
		this.question = question;
		this.weight = weight;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public boolean isCorrect() {
		return isCorrect;
	}

	public void setCorrect(boolean isCorrect) {
		this.isCorrect = isCorrect;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public float getWeight() {
		return weight;
	}

	public void setWeight(float weight) {
		this.weight = weight;
	}

	public Set<ExamPaperQuestionAnswer> getExamPaperQuestionAnswers() {
		return examPaperQuestionAnswers;
	}

	public void setExamPaperQuestionAnswers(Set<ExamPaperQuestionAnswer> examPaperQuestionAnswers) {
		this.examPaperQuestionAnswers = examPaperQuestionAnswers;
	}

	/*
	 * public ExamPaperQuestionAnswer getExamPaperQuestionAnswer() { return
	 * examPaperQuestionAnswer; }
	 * 
	 * public void setExamPaperQuestionAnswer(ExamPaperQuestionAnswer
	 * examPaperQuestionAnswer) { this.examPaperQuestionAnswer =
	 * examPaperQuestionAnswer; }
	 */

}
