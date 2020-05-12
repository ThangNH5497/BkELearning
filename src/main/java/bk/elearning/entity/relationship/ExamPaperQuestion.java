package bk.elearning.entity.relationship;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonIgnore;

import bk.elearning.entity.AbstractEntity;
import bk.elearning.entity.ExamPaper;
import bk.elearning.entity.Question;

@Entity
@Table(name="exampaper_question")
public class ExamPaperQuestion extends AbstractEntity implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "exam_paper_id",nullable=false)
	private ExamPaper examPaper;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "question_id")
	private Question question;
	
	@Column(name = "question_order")
	private int questionOrder;
	
	@Column(name = "question_grade")
	private float questionGrade;
	
	@OneToMany(mappedBy = "examPaperQuestion", cascade = CascadeType.ALL, 
			fetch = FetchType.EAGER,targetEntity = ExamPaperQuestionAnswer.class, orphanRemoval = true)
	@Fetch(value = FetchMode.SUBSELECT)
	@OrderBy("answerOrder ASC")
	private List<ExamPaperQuestionAnswer> examPaperQuestionAnswers;

	public ExamPaperQuestion(int id,ExamPaper examPaper, Question question, int questionOrder,float questionGrade,List<ExamPaperQuestionAnswer> examPaperQuestionAnswers) {
		super(id);
		this.examPaper = examPaper;
		this.question = question;
		this.questionOrder = questionOrder;
		this.questionGrade=questionGrade;
		this.examPaperQuestionAnswers=examPaperQuestionAnswers;
	}

	public ExamPaperQuestion() {
		super();
		// TODO Auto-generated constructor stub
	}


	public ExamPaper getExamPaper() {
		return examPaper;
	}

	public void setExamPaper(ExamPaper examPaper) {
		this.examPaper = examPaper;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public int getQuestionOrder() {
		return questionOrder;
	}

	public void setQuestionOrder(int questionOrder) {
		this.questionOrder = questionOrder;
	}

	public float getQuestionGrade() {
		return questionGrade;
	}

	public void setQuestionGrade(float questionGrade) {
		this.questionGrade = questionGrade;
	}

	public List<ExamPaperQuestionAnswer> getExamPaperQuestionAnswers() {
		return examPaperQuestionAnswers;
	}

	public void setExamPaperQuestionAnswers(List<ExamPaperQuestionAnswer> examPaperQuestionAnswers) {
		this.examPaperQuestionAnswers = examPaperQuestionAnswers;
	}

}
