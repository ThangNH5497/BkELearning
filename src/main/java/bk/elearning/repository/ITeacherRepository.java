package bk.elearning.repository;

import bk.elearning.entity.Teacher;

public interface ITeacherRepository extends IGeneralRepository<Teacher>{

	Teacher getByUsername(String username);
	Teacher getByCode(String code);
	
}
