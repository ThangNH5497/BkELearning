package bk.elearning.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import bk.elearning.entity.relationship.ExamInfo;
import bk.elearning.entity.relationship.ExamPaperInfo;

@Entity
@Table(name="exam_paper")
public class ExamPaper extends AbstractEntity{

	@Column(name="code",unique = true)
	private String code;
	
	@Column(name="name")
	private String name;

	
	@ManyToOne
	@JoinColumn(name = "teacher_id")
	private Teacher teacher;
	
	@ManyToOne
	@JoinColumn(name = "subject_id")
	private Subject subject;
	
	@JsonIgnore
	@OneToMany(mappedBy="examPaper",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Set<ExamPaperInfo> examPaperInfos;
	
	@JsonIgnore
	@OneToMany(mappedBy="examPaper",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Set<ExamInfo> examInfos;

	private String bankType;
	
	private String descriptor;
	
	public ExamPaper() {
		super();
	}

	public ExamPaper(String code, String name,  Teacher teacher,Subject subject, Set<ExamPaperInfo> examPaperInfos,Set<ExamInfo> examInfos,String bankType,String descriptor) {
		super();
		this.code = code;
		this.name = name;
		this.teacher = teacher;
		this.examPaperInfos = examPaperInfos;
		this.examInfos=examInfos;
		this.bankType=bankType;
		this.descriptor=descriptor;
		this.subject=subject;
	}
	
	

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public Set<ExamPaperInfo> getExamPaperInfos() {
		return examPaperInfos;
	}

	public void setExamPaperInfos(Set<ExamPaperInfo> examPaperInfos) {
		this.examPaperInfos = examPaperInfos;
	}

	public Set<ExamInfo> getExamInfos() {
		return examInfos;
	}

	public void setExamInfos(Set<ExamInfo> examInfos) {
		this.examInfos = examInfos;
	}

	public String getBankType() {
		return bankType;
	}

	public void setBankType(String bankType) {
		this.bankType = bankType;
	}

	public String getDescriptor() {
		return descriptor;
	}

	public void setDescriptor(String descriptor) {
		this.descriptor = descriptor;
	}
	
}
