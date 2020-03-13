package bk.elearning.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Answer extends AbstractEntity{

	private String content;
	
	@Column(name = "is_correct")
	boolean isCorrect;
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "question_id")
	private Question question;

	public Answer() {
		super();
	}

	public Answer(String content, boolean isCorrect, Question question) {
		super();
		this.content = content;
		this.isCorrect = isCorrect;
		this.question = question;
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
	
	
}
