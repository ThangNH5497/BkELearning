package bk.elearning.utils;

import java.util.List;

import org.apache.poi.ss.usermodel.Sheet;

public interface IModelMapper<T> {

	List<T> mapToModel(Sheet sheet);
	
	void mapImages(Sheet sheet,List<T> list,List<String> fieldNames);
}
