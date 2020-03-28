package bk.elearning.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@RequestMapping("/ql-giang-vien")
	public String getTeacherManagementView()
	{
		return "manager/admin/teacher-management";
	}
	
	@RequestMapping("/ql-sinh-vien")
	public String getStudentManagementView()
	{
		return "manager/admin/student-management";
	}
}
