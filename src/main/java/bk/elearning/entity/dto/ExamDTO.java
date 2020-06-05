package bk.elearning.entity.dto;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

import bk.elearning.entity.relationship.ExamCourse;
import bk.elearning.entity.relationship.StudentExam;

public class ExamDTO {

	private int id;

	private String code;

	private int time;

	private float grade;

	private String name;

	private String descriptor;

	private String status;

	@Temporal(TemporalType.TIMESTAMP)

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm",timezone = "GMT+7")
	private Date timeOpen;

	@Temporal(TemporalType.TIMESTAMP)

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm",timezone = "GMT+7")
	private Date timeClose;

	private ExamCourse examCourse;
	
	private StudentExam studentExam;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public float getGrade() {
		return grade;
	}

	public void setGrade(float grade) {
		this.grade = grade;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescriptor() {
		return descriptor;
	}

	public void setDescriptor(String descriptor) {
		this.descriptor = descriptor;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getTimeOpen() {
		return timeOpen;
	}

	public void setTimeOpen(Date timeOpen) {
		this.timeOpen = timeOpen;
	}

	public Date getTimeClose() {
		return timeClose;
	}

	public void setTimeClose(Date timeClose) {
		this.timeClose = timeClose;
	}

	public ExamCourse getExamCourse() {
		return examCourse;
	}

	public void setExamCourse(ExamCourse examCourse) {
		this.examCourse = examCourse;
	}
	
	

	public StudentExam getStudentExam() {
		return studentExam;
	}

	public void setStudentExam(StudentExam studentExam) {
		this.studentExam = studentExam;
	}

	public ExamDTO(int id, String code, int time, float grade, String name, String descriptor, String status,
			Date timeOpen, Date timeClose, ExamCourse examCourse) {
		super();
		this.id = id;
		this.code = code;
		this.time = time;
		this.grade = grade;
		this.name = name;
		this.descriptor = descriptor;
		this.status = status;
		this.timeOpen = timeOpen;
		this.timeClose = timeClose;
		this.examCourse = examCourse;
		
	}

	public ExamDTO(int id, String code, int time, float grade, String name, String descriptor, String status,
			Date timeOpen, Date timeClose) {
		super();
		this.id = id;
		this.code = code;
		this.time = time;
		this.grade = grade;
		this.name = name;
		this.descriptor = descriptor;
		this.status = status;
		this.timeOpen = timeOpen;
		this.timeClose = timeClose;
	}

}
