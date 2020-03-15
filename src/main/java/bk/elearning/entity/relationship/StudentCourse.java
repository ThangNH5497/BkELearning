package bk.elearning.entity.relationship;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import bk.elearning.entity.AbstractEntity;
import bk.elearning.entity.Course;
import bk.elearning.entity.Student;

@Entity
@Table(name="student_course")
public class StudentCourse extends AbstractEntity{

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "student_id",nullable=false)
	private Student student;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "course_id")
	private Course course;
	
	public StudentCourse(Student student, Course course) {
		super();
		this.student = student;
		this.course = course;
	}
}
