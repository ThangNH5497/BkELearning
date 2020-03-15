package bk.elearning.controller.web;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import bk.elearning.entity.Teacher;

@Controller
@Transactional
public class HomeController {
	@Autowired
	private SessionFactory sessionFactory;

	@RequestMapping(method = RequestMethod.GET, path = "/trang-chu")
	public String homePage() {
		Session session = this.sessionFactory.getCurrentSession();
		String hql = "from Teacher";
		long start = System.currentTimeMillis();
		List<Teacher> list = session.createQuery(hql).list();
		long end = System.currentTimeMillis();
		long t = end - start;
		System.out.println("Tổng thời gian: " + t + " millisecond");
		return "manager/admin/teacher-manager";
	}

	@RequestMapping(method = RequestMethod.GET, path = "/test")
	public String thomePage() {
		Session session = this.sessionFactory.getCurrentSession();

		return "manager/admin/test";
	}

}
