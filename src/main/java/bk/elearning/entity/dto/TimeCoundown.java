package bk.elearning.entity.dto;

public class TimeCoundown {

	private int timeLeft;
	private Long timeStart;
	private Long timeEnd;
	private int studentExamId;

	public int getTimeLeft() {
		return timeLeft;
	}

	public void setTimeLeft(int timeLeft) {
		this.timeLeft = timeLeft;
	}

	public Long getTimeStart() {
		return timeStart;
	}

	public void setTimeStart(Long timeStart) {
		this.timeStart = timeStart;
	}

	public int getStudentExamId() {
		return studentExamId;
	}

	public void setStudentExamId(int studentExamId) {
		this.studentExamId = studentExamId;
	}

	public TimeCoundown() {
		super();
	}

	public TimeCoundown(int timeLeft, Long timeStart, int studentExamId) {
		super();
		this.timeLeft = timeLeft;
		this.timeStart = timeStart;
		this.studentExamId = studentExamId;
	}

	public Long getTimeEnd() {
		return timeEnd;
	}

	public void setTimeEnd(Long timeEnd) {
		this.timeEnd = timeEnd;
	}
	
	
}
