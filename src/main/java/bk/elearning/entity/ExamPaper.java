package bk.elearning.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import bk.elearning.entity.relationship.ExamPaperQuestion;
import bk.elearning.entity.relationship.StudentExam;

@Entity
@Table(name = "exampaper")
public class ExamPaper extends AbstractEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "code", unique = true)
	private String code;

	@Column(name = "name")
	private String name;

	@Column(name = "time")
	private Integer time;

	private String bankType;

	@Column(name = "crate_at")
	@Temporal(TemporalType.DATE)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date createAt;

	@Column(name = "update_at")
	@Temporal(TemporalType.DATE)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date updateAt;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@ManyToOne
	@JoinColumn(name = "subject_id")
	private Subject subject;

	@OneToMany(mappedBy = "examPaper", cascade = CascadeType.ALL, fetch = FetchType.EAGER, targetEntity = ExamPaperQuestion.class, orphanRemoval = true)
	@OrderBy("questionOrder ASC")
	private Set<ExamPaperQuestion> examPaperQuestions;
	
	@JsonIgnore
	@OneToMany(mappedBy="examPaper",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Set<StudentExam> studentExams;

	private String descriptor;

	public ExamPaper() {
		super();
	}

	public ExamPaper(int id, String code, String name, User user, Subject subject, String descriptor, Integer time,
			Date createAt, Date updateAt) {
		super(id);
		this.code = code;
		this.name = name;
		this.user = user;
		this.descriptor = descriptor;
		this.subject = subject;
		this.time = time;
		this.createAt = createAt;
		this.updateAt = updateAt;
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Set<ExamPaperQuestion> getExamPaperQuestions() {
		return examPaperQuestions;
	}

	public void setExamPaperQuestions(Set<ExamPaperQuestion> examPaperQuestions) {
		this.examPaperQuestions = examPaperQuestions;
	}

	public String getBankType() {
		return bankType;
	}

	public void setBankType(String bankType) {
		this.bankType = bankType;
	}

	public String getDescriptor() {
		return descriptor;
	}

	public void setDescriptor(String descriptor) {
		this.descriptor = descriptor;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public Date getUpdateAt() {
		return updateAt;
	}

	public void setUpdateAt(Date updateAt) {
		this.updateAt = updateAt;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Integer getTime() {
		return time;
	}

	public void setTime(Integer time) {
		this.time = time;
	}

	public Set<StudentExam> getStudentExams() {
		return studentExams;
	}

	public void setStudentExams(Set<StudentExam> studentExams) {
		this.studentExams = studentExams;
	}



}
