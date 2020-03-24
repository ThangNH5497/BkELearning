package bk.elearning.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import bk.elearning.entity.Teacher;

public interface ITeacherService extends IGeneralService<Teacher>{

	public Teacher getByUsername(String username);
	public Teacher getByCode(String code);
	//luu voi anh
	public int save(Teacher t,MultipartFile file);
	public int update(Teacher teacher, MultipartFile file);
	public int deleteMultiple(ArrayList<Integer> ids);
	public Long getCount();
	public List<Teacher> searchTeachers(String type, String key, int start, int count);
	public Long searchCountTeachers(String type, String key);

}
