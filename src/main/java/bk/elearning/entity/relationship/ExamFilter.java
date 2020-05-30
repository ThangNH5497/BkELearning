package bk.elearning.entity.relationship;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import bk.elearning.entity.AbstractEntity;
import bk.elearning.entity.Exam;

@Entity
@Table(name="exam_filter")
public class ExamFilter extends AbstractEntity implements Serializable{

	private int number;
	
	private int level;
	
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	@JoinColumn(name = "question_id")
	private Exam exam;
	
	public ExamFilter() {}
	
	public ExamFilter(int number, int level) {
		super();
		this.number = number;
		this.level = level;
	
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	
	

	public Exam getExam() {
		return exam;
	}

	public void setExam(Exam exam) {
		this.exam = exam;
	}

	
	
	
}
