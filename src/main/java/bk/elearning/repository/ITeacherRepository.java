package bk.elearning.repository;

import java.util.List;

import bk.elearning.entity.Teacher;

public interface ITeacherRepository extends IGeneralRepository<Teacher>{

	List<Teacher> getByUsername(String username);
	List<Teacher> getByCode(String code);

}
