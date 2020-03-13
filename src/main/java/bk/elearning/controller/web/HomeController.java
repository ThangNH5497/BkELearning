package bk.elearning.controller.web;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@Transactional
public class HomeController {
	@Autowired
	private SessionFactory sessionFactory;
	@RequestMapping(method = RequestMethod.GET,path = "/trang-chu")
	public String homePage()
	{
		Session session = this.sessionFactory.getCurrentSession();

		return "manager/admin/home";
	}
	@RequestMapping(method = RequestMethod.GET,path = "/test")
	public String thomePage()
	{
		Session session = this.sessionFactory.getCurrentSession();

		return "manager/admin/test";
	}
	
	
}
