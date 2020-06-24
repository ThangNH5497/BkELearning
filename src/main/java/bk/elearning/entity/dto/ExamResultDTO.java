package bk.elearning.entity.dto;

public class ExamResultDTO {
	
	private int studentId;
	private String studentCode;
	private String studentName;
	private float studentGrade;

	
	
	public int getStudentId() {
		return studentId;
	}
	public void setStudentId(int studentId) {
		this.studentId = studentId;
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
	public float getStudentGrade() {
		return studentGrade;
	}
	public void setStudentGrade(float studentGrade) {
		this.studentGrade = studentGrade;
	}

	

	public ExamResultDTO(int studentId,String studentCode, String studentName) {
		super();
		this.studentId=studentId;
		this.studentCode = studentCode;
		this.studentName = studentName;
	}
	
	
}
