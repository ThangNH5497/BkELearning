package bk.elearning.entity.dto;

public class StudentResultQuestionDTO {

	private int examPaperQuestionAnswerId;
	private String studentAnswer;
	public StudentResultQuestionDTO() {
		super();
	}
	
	public int getExamPaperQuestionAnswerId() {
		return examPaperQuestionAnswerId;
	}

	public void setExamPaperQuestionAnswerId(int examPaperQuestionAnswerId) {
		this.examPaperQuestionAnswerId = examPaperQuestionAnswerId;
	}

	public String getStudentAnswer() {
		return studentAnswer;
	}

	public void setStudentAnswer(String studentAnswer) {
		this.studentAnswer = studentAnswer;
	}

	
	
	
}
