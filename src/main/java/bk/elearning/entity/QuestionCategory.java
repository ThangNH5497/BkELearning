package bk.elearning.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="question_category")
public class QuestionCategory extends AbstractEntity{

	@Column(name="category_name")
	private String categoryName;
	
	@OneToMany(mappedBy="questionCategory",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Question> questions;

	
	public QuestionCategory() {
		super();
	}


	public QuestionCategory(String categoryName, List<Question> questions) {
		super();
		this.categoryName = categoryName;
		this.questions = questions;
	}


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
	
	
	
	
}
