package bk.elearning.controller.teacher;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/teacher")
public class TeacherController {

	@RequestMapping("/trang-chu")
	public String getHomePage()
	{
		return "manager/teacher/home";
	}
	@RequestMapping("/ql-lop-hoc")
	public String getStudentManagementView(@RequestParam("id") int id)
	{
		return "manager/teacher/course-management";
	}

	
	@RequestMapping("/ql-lop-hoc/lop-hoc")
	public String detailSubjectManagementView(@RequestParam("id") int id)
	{
		return "manager/teacher/course-detail";
	}
}
