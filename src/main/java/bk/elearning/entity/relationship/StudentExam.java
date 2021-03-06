package bk.elearning.entity.relationship;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import bk.elearning.entity.AbstractEntity;
import bk.elearning.entity.Exam;
import bk.elearning.entity.ExamPaper;
import bk.elearning.entity.Student;
import bk.elearning.entity.StudentExamDetailLock;
import bk.elearning.entity.User;

@Entity
@Table(name = "student_exam")
@DynamicUpdate
public class StudentExam extends AbstractEntity {
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "student_id", nullable = false)
	private Student student;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "exam_id")
	private Exam exam;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "exam_paper_id")
	private ExamPaper examPaper;

	@OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	@JoinColumn(name = "lock_id", referencedColumnName = "id",nullable = true)
	private StudentExamDetailLock lock;

	private String status;

	private float grade;

	@Column(name = "time_left")
	private int timeLeft;

	public StudentExam(int id) {
		super(id);
	}

	public StudentExam(String status, int timeLeft) {
		super();
		this.status = status;
		this.timeLeft = timeLeft;
	}

	public StudentExam(int id, String status, int timeLeft, float grade, StudentExamDetailLock lock) {
		super(id);
		this.status = status;
		this.timeLeft = timeLeft;
		this.grade = grade;
		this.lock = lock;
	}
	
	public StudentExam(int id, String status, int timeLeft, float grade,ExamPaper examPaper, StudentExamDetailLock lock) {
		super(id);
		this.status = status;
		this.timeLeft = timeLeft;
		this.grade = grade;
		this.lock = lock;
		this.examPaper=examPaper;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Exam getExam() {
		return exam;
	}

	public void setExam(Exam exam) {
		this.exam = exam;
	}

	public ExamPaper getExamPaper() {
		return examPaper;
	}

	public void setExamPaper(ExamPaper examPaper) {
		this.examPaper = examPaper;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public StudentExam() {
		super();
	}

	public int getTimeLeft() {
		return timeLeft;
	}

	public void setTimeLeft(int timeLeft) {
		this.timeLeft = timeLeft;
	}

	public float getGrade() {
		return grade;
	}

	public void setGrade(float grade) {
		this.grade = grade;
	}

	public StudentExamDetailLock getLock() {
		return lock;
	}

	public void setLock(StudentExamDetailLock lock) {
		this.lock = lock;
	}

}
