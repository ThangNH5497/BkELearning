package bk.elearning.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
	
	@RequestMapping("/ql-mon-hoc")
	public String getSubjectManagementView()
	{
		return "manager/admin/subject-management";
	}
	
	@RequestMapping("/ql-mon-hoc/mon-hoc")
	public String detailSubjectManagementView(@RequestParam("id") int id)
	{
		return "manager/admin/subject-detail";
	}
}
