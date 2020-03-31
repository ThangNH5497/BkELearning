package bk.elearning.utils;

import java.util.List;

import org.apache.poi.ss.usermodel.Sheet;

import bk.elearning.entity.Teacher;

public class TeacherMapperUtil extends UserMapperUtil<Teacher> implements IModelMapper<Teacher> {

	@Override
	public List<Teacher> mapToModel(Sheet sheet) {
		// TODO Auto-generated method stub
		List<Teacher> list = mapFields(sheet, Teacher.class);	
		return list;
	}

}