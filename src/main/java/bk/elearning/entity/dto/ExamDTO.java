package bk.elearning.entity.dto;

import bk.elearning.entity.Exam;
import bk.elearning.entity.relationship.ExamCourse;

public class ExamDTO {

	private int id;
	private Exam exam;
	private ExamCourse examCourse;
	public ExamDTO() {
		super();
	}
	public Exam getExam() {
		return exam;
	}
	public void setExam(Exam exam) {
		this.exam = exam;
	}
	public ExamCourse getExamCourse() {
		return examCourse;
	}
	public void setExamCourse(ExamCourse examCourse) {
		this.examCourse = examCourse;
	}
	public ExamDTO(int id,Exam exam, ExamCourse examCourse) {
		super();
		this.id=id;
		this.exam = exam;
		this.examCourse = examCourse;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
}
