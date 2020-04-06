package bk.elearning.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import org.hibernate.annotations.DynamicUpdate;
import org.springframework.stereotype.Indexed;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@DynamicUpdate
@Indexed
public class Teacher extends User{
	
	private String department;
	
	private String position;

	@JsonIgnore
	@OneToMany(mappedBy="teacher",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Set<Course> courses;

	public Teacher() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Teacher(int id, String code, String username, String password, String fullName, String email, String addr,
			String phoneNumber, Date dateOfBirth, String image, String role,String department, String position) {
		super(id, code, username, password, fullName, email, addr, phoneNumber, dateOfBirth, image, role);
		// TODO Auto-generated constructor stub
		this.department = department;
		this.position = position;
	}

	public Teacher(String code, String username, String password, String fullName, String email, String addr,
			String phoneNumber, Date dateOfBirth, String image, String role,String department, String position, Set<Course> courses) {
		super(code, username, password, fullName, email, addr, phoneNumber, dateOfBirth, image, role);
		// TODO Auto-generated constructor stub
		this.department = department;
		this.position = position;
		this.courses = courses;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public Set<Course> getCourses() {
		return courses;
	}

	public void setCourses(Set<Course> courses) {
		this.courses = courses;
	}
	
}
