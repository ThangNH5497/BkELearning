package bk.elearning.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import bk.elearning.entity.relationship.UserCourse;

@Entity
public class User  extends AbstractEntity{

	@Column(name="code",nullable = false,unique = true)
	private String code;
	
	@Column(name="username",nullable = false,unique = true)
	private String username;
	
	@Column(name="password",nullable = false,unique = true)
	private String password;
	
	@Column(name="full_name",nullable = false)
	private String fullName;
	
	@Column(name="email")
	private String email;
	
	@Column(name="addr")
	private String addr;
	
	@Column(name="phone_number")
	private String phoneNumber;
	
	@Column(name="date_of_birth")
	private Date dateOfBirth;
	
	
	
	@ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.MERGE) // load cac phan tu manytomany
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
	private Set<Role> roles;
	/*
	@ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.MERGE) // load cac phan tu manytomany
	@JoinTable(name = "student_course", joinColumns = @JoinColumn(name = "student_id"), inverseJoinColumns = @JoinColumn(name = "course_id"))
	@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
	private Set<Course> courses;*/
	
	@OneToMany(mappedBy="user",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Set<UserCourse> userCourse;

	public User()
	{
		
	}


	public Set<UserCourse> getUserCourse() {
		return userCourse;
	}

	public void setUserCourse(Set<UserCourse> userCourse) {
		this.userCourse = userCourse;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}



	public Date getDateOfBirth() {
		return dateOfBirth;
	}


	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}


	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}


	
	
	
	
}
