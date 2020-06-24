package bk.elearning.entity.dto;

import bk.elearning.entity.StudentExamDetailLock;

public class StudentExamDTO {

	private int id;
	
	private String code;
	
	private String studentCode;
	
	private String studentName;
	
	private StudentExamDetailLock lock;

	public StudentExamDTO(int id, String code, String studentCode, String studentName) {
		super();
		this.id = id;
		this.code = code;
		this.studentCode = studentCode;
		this.studentName = studentName;
	}
	
	public StudentExamDTO(int id, String code, String studentCode, String studentName,StudentExamDetailLock lock) {
		super();
		this.id = id;
		this.code = code;
		this.studentCode = studentCode;
		this.studentName = studentName;
		this.lock=lock;
	}
	
	

	public StudentExamDetailLock getLock() {
		return lock;
	}




	public void setLock(StudentExamDetailLock lock) {
		this.lock = lock;
	}




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

	public String getStudentCode() {
		return studentCode;
	}

	public void setStudentCode(String studentCode) {
		this.studentCode = studentCode;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	
	
}
