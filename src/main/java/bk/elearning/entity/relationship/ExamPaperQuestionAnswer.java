package bk.elearning.entity.relationship;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import bk.elearning.entity.AbstractEntity;
import bk.elearning.entity.Answer;

@Entity
@Table(name="exampaper_question_answer")
public class ExamPaperQuestionAnswer extends AbstractEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "exampaper_question_id")
	private ExamPaperQuestion examPaperQuestion;
	
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "answer_id")
	private Answer answer;
	
	
	@Column(name="answer_order")
	private int answerOrder;
	
	@Column(name="student_answer")
	private String studentAnswer;

	public ExamPaperQuestionAnswer()
	{
		
	}
	public ExamPaperQuestionAnswer(ExamPaperQuestion examPaperQuestion, Answer answer, int answerOrder) {
		super();
		this.examPaperQuestion = examPaperQuestion;
		this.answer = answer;
		this.answerOrder = answerOrder;
	}
	public ExamPaperQuestion getExamPaperQuestion() {
		return examPaperQuestion;
	}
	public void setExamPaperQuestion(ExamPaperQuestion examPaperQuestion) {
		this.examPaperQuestion = examPaperQuestion;
	}
	public Answer getAnswer() {
		return answer;
	}
	public void setAnswer(Answer answer) {
		this.answer = answer;
	}
	public int getAnswerOrder() {
		return answerOrder;
	}
	public void setAnswerOrder(int answerOrder) {
		this.answerOrder = answerOrder;
	}
	public String getStudentAnswer() {
		return studentAnswer;
	}
	public void setStudentAnswer(String studentAnswer) {
		this.studentAnswer = studentAnswer;
	}
	
	
}
