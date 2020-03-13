package bk.elearning.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

@Entity
public class Subject extends AbstractEntity{

	private String code;
	
	@Column(name="subject_name")
	private String subjectName;
	
	private String descriptor;
	
	@OneToMany(mappedBy="subject",fetch = FetchType.LAZY)
	private List<Course> courses;
	
	@OneToMany(mappedBy="subject",fetch = FetchType.LAZY)
	private List<Question> questions;
	
	@OneToMany(mappedBy="subject",fetch = FetchType.LAZY)
	private List<Exam> exams;

	public Subject() {
		super();
	}

	public Subject(String code, String subjectName, String descriptor, List<Course> courses, List<Question> questions,
			List<Exam> exams) {
		super();
		this.code = code;
		this.subjectName = subjectName;
		this.descriptor = descriptor;
		this.courses = courses;
		this.questions = questions;
		this.exams = exams;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public String getDescriptor() {
		return descriptor;
	}

	public void setDescriptor(String descriptor) {
		this.descriptor = descriptor;
	}

	public List<Course> getCourses() {
		return courses;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}

	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

	public List<Exam> getExams() {
		return exams;
	}

	public void setExams(List<Exam> exams) {
		this.exams = exams;
	}
	
	
}
