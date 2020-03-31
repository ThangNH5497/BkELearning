package bk.elearning.repository.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import bk.elearning.entity.Student;
import bk.elearning.repository.IStudentRepository;

@Repository
@Transactional
public class StudentRepositoryImpl extends UserRepositoryImpl<Student> implements IStudentRepository {

	@Autowired
	private SessionFactory sessionFactory;

	

}
