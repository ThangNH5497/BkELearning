package bk.elearning.repository.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import bk.elearning.entity.Course;
import bk.elearning.repository.ICourseRepository;

@Transactional
@SuppressWarnings("rawtypes")
@Repository
public class CourseRepositoryImpl extends SubjectComponentRepositoryImpl<Course> implements ICourseRepository {

	

}
