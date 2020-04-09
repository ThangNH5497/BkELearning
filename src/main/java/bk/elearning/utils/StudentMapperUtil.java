package bk.elearning.utils;

import java.util.List;

import org.apache.poi.ss.usermodel.Sheet;

import bk.elearning.entity.Student;

public class StudentMapperUtil extends UserMapperUtil<Student> implements IModelMapper<Student> {

	public StudentMapperUtil(boolean loadImage) {
		super(loadImage);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Student> mapToModel(Sheet sheet) {
		// TODO Auto-generated method stub
		List<Student> list = mapFields(sheet, Student.class);	
		return list;
	}

}
