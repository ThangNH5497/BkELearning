package bk.elearning.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
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
	@Temporal(TemporalType.DATE)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date dateOfBirth;
	
	@Column(columnDefinition = "resources/commons/image/default.jpg")
	
	private String image;
	

	@JsonIgnore
	@ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.MERGE) // load cac phan tu manytomany
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles;

	public User()
	{
		
	}
	public User(String code, String username, String password, String fullName, String email, String addr,
			String phoneNumber, Date dateOfBirth, String image, Set<Role> roles) {
		this.code = code;
		this.username = username;
		this.password = password;
		this.fullName = fullName;
		this.email = email;
		this.addr = addr;
		this.phoneNumber = phoneNumber;
		this.dateOfBirth = dateOfBirth;
		this.image = image;
		this.roles = roles;
	}
	
	public User(int id,String code, String username, String password, String fullName, String email, String addr,
			String phoneNumber, Date dateOfBirth, String image, Set<Role> roles) {
		super(id);
		this.code = code;
		this.username = username;
		this.password = password;
		this.fullName = fullName;
		this.email = email;
		this.addr = addr;
		this.phoneNumber = phoneNumber;
		this.dateOfBirth = dateOfBirth;
		this.image = image;
		this.roles = roles;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
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
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public Set<Role> getRoles() {
		return roles;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	
	
	
}
