package bk.elearning.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import bk.elearning.entity.relationship.StudentExam;

@Entity
@Table(name="student_exam_detail_lock")
public class StudentExamDetailLock extends AbstractEntity{

	private boolean lockAccess;
	
	private String reason;
	
	@JsonIgnore
	@OneToOne(mappedBy = "lock",fetch = FetchType.LAZY)
	
	private StudentExam studentExam;

	

	public boolean isLockAccess() {
		return lockAccess;
	}

	public void setLockAccess(boolean lockAccess) {
		this.lockAccess = lockAccess;
	}

	public StudentExam getStudentExam() {
		return studentExam;
	}

	public void setStudentExam(StudentExam studentExam) {
		this.studentExam = studentExam;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public StudentExamDetailLock() {
		super();
	}

	public StudentExamDetailLock(int id,boolean lockAccess, String reason) {
		super(id);
		this.lockAccess = lockAccess;
		this.reason = reason;
	}
	
	
	
}
