package bk.elearning.entity;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@DynamicUpdate
public class Subject extends AbstractEntity{
	@Column(name="code",nullable = false,unique = true)
	private String code;
	
	@Column(name="subject_name",nullable = false)
	private String subjectName;
	
	private String descriptor;
	
	@JsonIgnore
	@OneToMany(mappedBy="subject",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private List<Course> courses;
	
	@JsonIgnore
	@OneToMany(mappedBy="subject",fetch = FetchType.LAZY)
	private List<Exam> exams;
	
	@JsonIgnore
	@OneToMany(mappedBy="subject",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Set<Category> category;

	public Subject() {
		super();
	}

	public Subject(String code, String subjectName, String descriptor, List<Course> courses,
			List<Exam> exams) {
		super();
		this.code = code;
		this.subjectName = subjectName;
		this.descriptor = descriptor;
		this.courses = courses;
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

	public List<Exam> getExams() {
		return exams;
	}

	public void setExams(List<Exam> exams) {
		this.exams = exams;
	}

	public Set<Category> getCategory() {
		return category;
	}

	public void setCategory(Set<Category> category) {
		this.category = category;
	}
	
	
}
