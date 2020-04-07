package bk.elearning.repository.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import bk.elearning.entity.Student;
import bk.elearning.repository.IStudentRepository;

@Repository
@Transactional
public class StudentRepositoryImpl extends UserGenericRepositoryImpl<Student> implements IStudentRepository {

	@Autowired
	private SessionFactory sessionFactory;

	

}
