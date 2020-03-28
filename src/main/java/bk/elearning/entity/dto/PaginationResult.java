package bk.elearning.entity.dto;

import java.util.List;

public class PaginationResult<T> {

	private Long count;
	private List<T> data;
	
	public PaginationResult() {
		super();
	}

	public PaginationResult(Long count, List<T> data) {
		super();
		this.count = count;
		this.data = data;
	}

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}
	
	
}
