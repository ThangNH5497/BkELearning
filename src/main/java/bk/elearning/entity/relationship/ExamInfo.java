package bk.elearning.entity.relationship;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import bk.elearning.entity.AbstractEntity;
import bk.elearning.entity.Exam;
import bk.elearning.entity.ExamPaper;

@Entity
@Table(name="exam_info")
public class ExamInfo extends AbstractEntity{


	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "exam_paper_id",nullable=false)
	private ExamPaper examPaper;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "exam_id")
	private Exam exam;

	public ExamInfo(ExamPaper examPaper, Exam exam) {
		this.examPaper = examPaper;
		this.exam = exam;
	}
	
	public ExamInfo(int id,ExamPaper examPaper, Exam exam) {
		super(id);
		this.examPaper = examPaper;
		this.exam = exam;
	}

	public ExamPaper getExamPaper() {
		return examPaper;
	}

	public void setExamPaper(ExamPaper examPaper) {
		this.examPaper = examPaper;
	}

	public Exam getExam() {
		return exam;
	}

	public void setExam(Exam exam) {
		this.exam = exam;
	}
	
	
}
