package bk.elearning.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="exam_paper")
public class ExamPaper extends AbstractEntity{

	private String code;
	
	@Column(name="exam_paper_name")
	private String examPaperName;
	
	//thoi gian
	private int time;
	
	//diem toi da
	@Column(name="max_grade")
	private int maxGrade;
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "exam_id")
	private Exam exam;
	
	@OneToMany(mappedBy="examPaper",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Set<Question> question;

	public ExamPaper() {
		super();
	}

	public ExamPaper(String code, String examPaperName, int time, int maxGrade, Exam exam, Set<Question> question) {
		super();
		this.code = code;
		this.examPaperName = examPaperName;
		this.time = time;
		this.maxGrade = maxGrade;
		this.exam = exam;
		this.question = question;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getExamPaperName() {
		return examPaperName;
	}

	public void setExamPaperName(String examPaperName) {
		this.examPaperName = examPaperName;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public int getMaxGrade() {
		return maxGrade;
	}

	public void setMaxGrade(int maxGrade) {
		this.maxGrade = maxGrade;
	}

	public Exam getExam() {
		return exam;
	}

	public void setExam(Exam exam) {
		this.exam = exam;
	}

	public Set<Question> getQuestion() {
		return question;
	}

	public void setQuestion(Set<Question> question) {
		this.question = question;
	}
	
	
}
