package bk.elearning.repository.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import bk.elearning.entity.Subject;
import bk.elearning.repository.ISubjectRepository;

@Transactional
@Repository
public class SubjectRepositoryImpl extends AbstractGenericRepository<Subject> implements ISubjectRepository{

	@Autowired
	private SessionFactory sessionFactory;
	

}
