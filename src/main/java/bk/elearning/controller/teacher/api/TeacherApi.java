package bk.elearning.controller.teacher.api;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bk.elearning.entity.Teacher;

@RestController
@RequestMapping(path = "/teacher")
@Transactional
public class TeacherApi {

	@Autowired
	private SessionFactory sessionFactory;
	
	@GetMapping(path = "/teachers")
	public List<Teacher> getAllTeacher()
	{
		List<Teacher> teachers=null;
		long start = System.currentTimeMillis();
		try {
			Session session = this.sessionFactory.getCurrentSession();
			String hql="from Teacher";
			teachers=session.createQuery(hql).list();
		} catch (Exception e) {
			// TODO: handle exception
		}
		long end = System.currentTimeMillis();
		long t = end - start;
		System.out.println("Tổng thời gian: " + t + " millisecond");
		return teachers;
	}
}
