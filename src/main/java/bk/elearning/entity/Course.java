package bk.elearning.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import bk.elearning.entity.relationship.StudentCourse;

@Entity
public class Course extends AbstractEntity{

	private String code;
	
	@Column(name = "course_name")
	private String courseName;
	
	private String descriptor;
	
	@OneToMany(mappedBy="course",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Set<StudentCourse> studentCourses;
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "subject_id")
	private Subject subject;

	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "teacher_id")
	private Teacher teacher;
	
	public Course() {
		super();
	}



	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getDescriptor() {
		return descriptor;
	}

	public void setDescriptor(String descriptor) {
		this.descriptor = descriptor;
	}


	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}
	
	
}
