package bk.elearning.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name="role")
@DynamicUpdate
public class Role extends AbstractEntity  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name="role_name",unique = true)
	private String roleName;
	
	@ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY,cascade = CascadeType.MERGE)
	@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
	private Set<User> users;

	
	public Role() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Role(int id,Set<User> users) {
		super(id);
		this.users=users;
		// TODO Auto-generated constructor stub
	}

	public Role(String name) {
		this.roleName = name;
	}

	public String getName() {
		return roleName;
	}

	public void setName(String name) {
		this.roleName = name;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}
	
	
}
