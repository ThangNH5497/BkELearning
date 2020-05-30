package bk.elearning.entity.relationship;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import bk.elearning.entity.AbstractEntity;
import bk.elearning.entity.Course;
import bk.elearning.entity.Exam;

@Entity
@Table(name="exam_course")
public class ExamCourse extends AbstractEntity{

	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "exam_id",nullable=false)
	private Exam exam;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "course_id")
	private Course course;
	
	public ExamCourse() {
		super();
	}

	public ExamCourse(int id,Exam exam, Course course) {
		super(id);
		this.exam = exam;
		this.course = course;
	}

	public ExamCourse(Exam exam, Course course) {
		super();
		this.exam = exam;
		this.course = course;
	}



	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public Exam getExam() {
		return exam;
	}

	public void setExam(Exam exam) {
		this.exam = exam;
	}
	
	
}
