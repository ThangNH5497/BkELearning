package bk.elearning.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import bk.elearning.entity.Teacher;
import bk.elearning.entity.dto.PaginationResult;

public interface ITeacherService extends IGeneralService<Teacher>,IPaginationResultService<Teacher>{

	public Teacher getByUsername(String username);
	public Teacher getByCode(String code);
	//luu voi anh
	public int save(Teacher t,MultipartFile file);
	public int update(Teacher teacher, MultipartFile file);
	public int deleteMultiple(ArrayList<Integer> ids);
	public int[] saveFromFile(MultipartFile file);

}
