package bk.elearning.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Exam extends AbstractEntity {

	private String code;

	@Column(name = "exam_name")
	private String examName;

	private String descriptor;

	@Column(name="time_start")
	private Date timeStart;

	@Column(name="time_end")
	private Date timeEnd;

	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "subject_id")
	private Subject subject;

	@OneToMany(mappedBy = "exam", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<ExamPaper> examPaper;

	public Exam() {
		super();
	}

	public Exam(String code, String examName, String descriptor, Date timeStart, Date timeEnd, Subject subject,
			Set<ExamPaper> examPaper) {
		super();
		this.code = code;
		this.examName = examName;
		this.descriptor = descriptor;
		this.timeStart = timeStart;
		this.timeEnd = timeEnd;
		this.subject = subject;
		this.examPaper = examPaper;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getExamName() {
		return examName;
	}

	public void setExamName(String examName) {
		this.examName = examName;
	}

	public String getDescriptor() {
		return descriptor;
	}

	public void setDescriptor(String descriptor) {
		this.descriptor = descriptor;
	}

	
	public Date getTimeStart() {
		return timeStart;
	}

	public void setTimeStart(Date timeStart) {
		this.timeStart = timeStart;
	}

	public Date getTimeEnd() {
		return timeEnd;
	}

	public void setTimeEnd(Date timeEnd) {
		this.timeEnd = timeEnd;
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public Set<ExamPaper> getExamPaper() {
		return examPaper;
	}

	public void setExamPaper(Set<ExamPaper> examPaper) {
		this.examPaper = examPaper;
	}

}