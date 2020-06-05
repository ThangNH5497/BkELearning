package bk.elearning.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import bk.elearning.entity.relationship.StudentCourse;
import bk.elearning.entity.relationship.StudentExam;

@Entity
@DynamicUpdate
public class Student extends User{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name="class_name")
	private String className;
	
	@JsonIgnore
	@OneToMany(mappedBy="student",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Set<StudentCourse> studentCourses;
	
	@JsonIgnore
	@OneToMany(mappedBy="student",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Set<StudentExam> studentExams;

	public Student(String className, Set<StudentCourse> studentCourses) {
		super();
		this.className = className;
		this.studentCourses = studentCourses;
	}

	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Student(int id, String code, String username, String password, String fullName, String email, String addr,
			String phoneNumber, Date dateOfBirth, String image, String role) {
		super(id, code, username, password, fullName, email, addr, phoneNumber, dateOfBirth, image, role);
		// TODO Auto-generated constructor stub
	}

	public Student(String code, String username, String password, String fullName, String email, String addr,
			String phoneNumber, Date dateOfBirth, String image, String role) {
		super(code, username, password, fullName, email, addr, phoneNumber, dateOfBirth, image, role);
		// TODO Auto-generated constructor stub
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public Set<StudentCourse> getStudentCourses() {
		return studentCourses;
	}

	public void setStudentCourses(Set<StudentCourse> studentCourses) {
		this.studentCourses = studentCourses;
	}

	public Set<StudentExam> getStudentExams() {
		return studentExams;
	}

	public void setStudentExams(Set<StudentExam> studentExams) {
		this.studentExams = studentExams;
	}
	
	
}
