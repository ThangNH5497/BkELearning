package bk.elearning.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import bk.elearning.utils.FileUpload;
import bk.elearning.utils.Util;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@RequestMapping("/trang-chu")
	public String getHomePage()
	{
		return "manager/admin/home";
	}
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
	
	@RequestMapping("/ql-mon-hoc/danh-sach-lop")
	public String courseListView(@RequestParam("id") int id)
	{
		return "manager/admin/course-list";
	}
}
