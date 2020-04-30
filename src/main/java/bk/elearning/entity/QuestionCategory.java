package bk.elearning.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="question_category")
public class QuestionCategory extends AbstractEntity{

	@Column(name="category_name")
	private String categoryName;
	
	@JsonIgnore
	@OneToMany(mappedBy="category",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Question> questions;

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

	public QuestionCategory(int id,String categoryName, List<Question> questions) {
		super(id);
		this.categoryName = categoryName;
		this.questions = questions;
	}

}
