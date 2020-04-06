package bk.elearning.repository;

import java.util.List;

import bk.elearning.entity.Teacher;

public interface ITeacherRepository extends IUserRepository<Teacher>{

	List<Teacher> test(String key);
	
}
