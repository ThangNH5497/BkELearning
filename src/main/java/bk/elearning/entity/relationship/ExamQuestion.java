package bk.elearning.entity.relationship;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import bk.elearning.entity.AbstractEntity;
import bk.elearning.entity.Exam;
import bk.elearning.entity.Question;

@Entity
@Table(name = "exam_question")
public class ExamQuestion extends AbstractEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private float grade;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "question_id")
	private Question question;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "exam_id")
	private Exam exam;

	public ExamQuestion(int id, Exam exam, Question question) {
		super(id);
		this.question = question;
		this.exam = exam;
	}

	public ExamQuestion(int id) {
		super(id);
	}
	
	public ExamQuestion() {
		super();
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public Exam getExam() {
		return exam;
	}

	public void setExam(Exam exam) {
		this.exam = exam;
	}

	public float getGrade() {
		return grade;
	}

	public void setGrade(float grade) {
		this.grade = grade;
	}


}
