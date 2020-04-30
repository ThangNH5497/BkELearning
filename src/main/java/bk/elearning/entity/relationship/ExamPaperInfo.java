package bk.elearning.entity.relationship;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import bk.elearning.entity.AbstractEntity;
import bk.elearning.entity.ExamPaper;
import bk.elearning.entity.Question;

@Entity
@Table(name="exam_paper_info")
public class ExamPaperInfo extends AbstractEntity{


	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "exam_paper_id",nullable=false)
	private ExamPaper examPaper;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "question_id")
	private Question question;
	
	@Column(name = "question_order")
	private int questionOrder;
	
	@Column(name = "question_grade")
	private float questionGrade;

	public ExamPaperInfo(ExamPaper examPaper, Question question, int questionOrder,float questionGrade) {
		super();
		this.examPaper = examPaper;
		this.question = question;
		this.questionOrder = questionOrder;
		this.questionGrade=questionGrade;
	}

	public ExamPaperInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ExamPaperInfo(int id,ExamPaper examPaper, Question question, int questionOrder,float questionGrade) {
		super(id);
		this.examPaper = examPaper;
		this.question = question;
		this.questionOrder = questionOrder;
		this.questionGrade=questionGrade;
	
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

	
	
}
