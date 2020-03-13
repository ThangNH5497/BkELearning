package bk.elearning.entity.relationship;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import bk.elearning.entity.AbstractEntity;
import bk.elearning.entity.Course;
import bk.elearning.entity.User;

@Entity
@Table(name="user_course")
public class UserCourse extends AbstractEntity{

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id",nullable=false)
	private User user;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "course_id")
	private Course course;
	
	public UserCourse(User user, Course course) {
		super();
		this.user = user;
		this.course = course;
	}
}
