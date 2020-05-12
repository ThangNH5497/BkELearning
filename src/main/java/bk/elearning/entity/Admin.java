package bk.elearning.entity;

import javax.persistence.Entity;

import org.hibernate.annotations.DynamicUpdate;

@Entity
@DynamicUpdate
public class Admin extends User{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
