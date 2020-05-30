package bk.elearning.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import bk.elearning.entity.relationship.ExamCourse;
import bk.elearning.entity.relationship.StudentCourse;

@Entity
@DynamicUpdate
public class Course extends AbstractEntity{

	@Column(unique = true,nullable = false)
	private String code;
	
	@Column(name = "course_name")
	private String courseName;
	
	private String descriptor;
	
	@JsonIgnore
	@OneToMany(mappedBy="course",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Set<StudentCourse> studentCourses;
	
	@JsonIgnore
	@OneToMany(mappedBy="course",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Set<ExamCourse> examCourse;
	
	@ManyToOne
	@JoinColumn(name = "subject_id",nullable = false)
	private Subject subject;

	@ManyToOne
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

	public Set<StudentCourse> getStudentCourses() {
		return studentCourses;
	}

	public void setStudentCourses(Set<StudentCourse> studentCourses) {
		this.studentCourses = studentCourses;
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public Set<ExamCourse> getExamCourse() {
		return examCourse;
	}

	public void setExamCourse(Set<ExamCourse> examCourse) {
		this.examCourse = examCourse;
	}
	

}
