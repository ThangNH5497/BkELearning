package bk.elearning.repository.impl;

import java.util.ArrayList;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import bk.elearning.entity.Course;
import bk.elearning.repository.ICourseRepository;

@Transactional
@SuppressWarnings({ "rawtypes", "deprecation" })
@Repository
public class CourseRepositoryImpl extends SubjectComponentRepositoryImpl<Course> implements ICourseRepository {
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Boolean isStudentExis(int studentId,int courseId) {
		// TODO Auto-generated method stub
		String hqlQuery = "select count(distinct c.id) from Course c JOIN c.studentCourses ct where ct.student.id=:studentId AND ct.course.id=:courseId";
		Session session = sessionFactory.getCurrentSession();
		try {
			Query query = session.createQuery(hqlQuery);
			query.setParameter("studentId", studentId);
			query.setParameter("courseId", courseId);
			if ((Long) query.uniqueResult() > 0l)
				return true;
		} catch (Exception e) {
			// TODO: handle exception
		}

		return false;
	}

	@Override
	public int removeStudents(int courseId, ArrayList<Integer> ids) {
		// TODO Auto-generated method stub
		String hqlQuery = "DELETE FROM StudentCourse sc where sc.course.id=:courseId AND sc.student.id=:studentId";
		int success = 0;
		Session session = sessionFactory.getCurrentSession();
		try {
			
			Query query=session.createQuery(hqlQuery);
			query.setParameter("courseId", courseId);
			for (Integer integer : ids) {
				try {
					query.setParameter("studentId", integer);
					query.executeUpdate();
					success++;
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println("ex trong :"+e.toString());
					session.clear();
					query=session.createQuery(hqlQuery);
				}
			}
		} catch (Exception e) {
			System.out.println("ex ngoài :"+e.toString());
			// TODO: handle exception
			session.clear();
		}

		return success;
	}

}
