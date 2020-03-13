package bk.elearning.controller.manager;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping
public class ManagerHomeController {

	// admin home
	@RequestMapping(method = RequestMethod.GET, path = "/admin/trang-chu")
	public String adminHomePage() {
		return "manager/admin/home";
	}

	// teacher home
	@RequestMapping(method = RequestMethod.GET, path = "/teacher/trang-chu")
	public String teacherHomePage() {
		return "manager/teacher/home";
	}
}
