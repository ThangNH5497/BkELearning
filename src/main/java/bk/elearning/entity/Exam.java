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
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import bk.elearning.entity.relationship.ExamCourse;
import bk.elearning.entity.relationship.ExamFilter;
import bk.elearning.entity.relationship.ExamQuestion;
import bk.elearning.entity.relationship.StudentExam;

@Entity
@Table(name = "exam")
@DynamicUpdate
public class Exam extends AbstractEntity {

	private String code;

	private int time;

	private float grade;

	@Column(name = "name")
	private String name;

	private String descriptor;
	
	private String status;

	@Temporal(TemporalType.TIMESTAMP)

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm",timezone = "GMT+7")

	@Column(name = "time_open")
	private Date timeOpen;

	@Temporal(TemporalType.TIMESTAMP)

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm",timezone = "GMT+7")

	@Column(name = "time_close")
	private Date timeClose;

	@Column(name = "create_at")
	@Temporal(TemporalType.DATE)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date createAt;

	@Temporal(TemporalType.DATE)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	@Column(name = "update_at")
	private Date updateAt;

	@ManyToOne
	@JoinColumn(name = "subject_id")
	private Subject subject;

	@ManyToOne
	@JoinColumn(name = "create_by")
	private User user;

	@OneToMany(mappedBy = "exam", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<ExamQuestion> examQuestions;

	@OneToMany(mappedBy = "exam", cascade = CascadeType.ALL, fetch = FetchType.EAGER,orphanRemoval = true)
	private Set<ExamCourse> examCourses;
	
	@OneToMany(mappedBy = "exam", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@OrderBy("number ASC")
	private Set<ExamFilter> examFilters;
	
	@JsonIgnore
	@OneToMany(mappedBy="exam",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Set<StudentExam> studentExams;

	public Exam() {
		super();
	}

	public Exam(int id, String code, int time, float grade, String name, String descriptor,String status, Date timeOpen,
			Date timeClose, Date createAt, Date updateAt,User user) {
		super(id);
		this.code = code;
		this.time = time;
		this.grade = grade;
		this.name = name;
		this.descriptor = descriptor;
		this.timeOpen = timeOpen;
		this.timeClose = timeClose;
		this.createAt = createAt;
		this.updateAt = updateAt;
		this.status=status;
		this.user=user;
	}
	
	public Exam(int id, String code, int time, float grade, String name, String descriptor,String status, Date timeOpen,
			Date timeClose, Date createAt, Date updateAt,Subject subject) {
		super(id);
		this.code = code;
		this.time = time;
		this.grade = grade;
		this.name = name;
		this.descriptor = descriptor;
		this.timeOpen = timeOpen;
		this.timeClose = timeClose;
		this.createAt = createAt;
		this.updateAt = updateAt;
		this.status=status;
		this.subject=subject;
	
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescriptor() {
		return descriptor;
	}

	public void setDescriptor(String descriptor) {
		this.descriptor = descriptor;
	}

	public Date getTimeOpen() {
		return timeOpen;
	}

	public void setTimeOpen(Date timeOpen) {
		this.timeOpen = timeOpen;
	}

	public Date getTimeClose() {
		return timeClose;
	}

	public void setTimeClose(Date timeClose) {
		this.timeClose = timeClose;
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public Set<ExamQuestion> getExamQuestions() {
		return examQuestions;
	}

	public void setExamQuestions(Set<ExamQuestion> examQuestions) {
		this.examQuestions = examQuestions;
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

	public Set<ExamCourse> getExamCourses() {
		return examCourses;
	}

	public void setExamCourses(Set<ExamCourse> examCourses) {
		this.examCourses = examCourses;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public float getGrade() {
		return grade;
	}

	public void setGrade(float grade) {
		this.grade = grade;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Set<ExamFilter> getExamFilters() {
		return examFilters;
	}

	public void setExamFilters(Set<ExamFilter> examFilters) {
		this.examFilters = examFilters;
	}

}