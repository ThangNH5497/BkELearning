package bk.elearning.entity.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ExamPageDTO {

	private int id;
	private String code;

	private int time;

	private float grade;

	private String name;

	private String descriptor;

	private String status;

	@Temporal(TemporalType.DATE)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date createAt;

	@Temporal(TemporalType.DATE)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date updateAt;

	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm",timezone = "GMT+7")
	private Date timeOpen;

	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm",timezone = "GMT+7")
	private Date timeClose;
	
	private String role;
	
	//luong bai thi can cham diem
	private int countExamProcess;
	
	//luong bai thi yeu cau xem chi tiet
	private int countStudentRequest;

	public ExamPageDTO(int id,String code, int time, float grade, String name, String descriptor, String status, Date createAt,
			Date updateAt, Date timeOpen, Date timeClose, String role) {
		super();
		this.id=id;
		this.code = code;
		this.time = time;
		this.grade = grade;
		this.name = name;
		this.descriptor = descriptor;
		this.status = status;
		this.createAt = createAt;
		this.updateAt = updateAt;
		this.timeOpen = timeOpen;
		this.timeClose = timeClose;
		this.role = role;
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

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public Date getUpdateAt() {
		return updateAt;
	}

	public void setUpdateAt(Date updateAt) {
		this.updateAt = updateAt;
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

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public int getCountExamProcess() {
		return countExamProcess;
	}

	public void setCountExamProcess(int countExamProcess) {
		this.countExamProcess = countExamProcess;
	}

	public int getCountStudentRequest() {
		return countStudentRequest;
	}

	public void setCountStudentRequest(int countStudentRequest) {
		this.countStudentRequest = countStudentRequest;
	}
	
	

}
