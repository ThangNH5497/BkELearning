package bk.elearning.entity.dto;

import java.util.ArrayList;

public class QuestionFilter {

	int subjectId;
	
	ArrayList<Integer> categoryIds;
	ArrayList<Integer> levels;
	ArrayList<String> types;
	ArrayList<Integer> existQuestionIds;
	int count;
	
	public QuestionFilter()
	{
		
	}

	public int getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}

	public ArrayList<Integer> getCategoryIds() {
		return categoryIds;
	}

	public void setCategoryIds(ArrayList<Integer> categoryIds) {
		this.categoryIds = categoryIds;
	}

	public ArrayList<Integer> getLevels() {
		return levels;
	}

	public void setLevels(ArrayList<Integer> levels) {
		this.levels = levels;
	}

	public ArrayList<String> getTypes() {
		return types;
	}

	public void setTypes(ArrayList<String> types) {
		this.types = types;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public ArrayList<Integer> getExistQuestionIds() {
		return existQuestionIds;
	}

	public void setExistQuestionIds(ArrayList<Integer> existQuestionIds) {
		this.existQuestionIds = existQuestionIds;
	}

	



}
