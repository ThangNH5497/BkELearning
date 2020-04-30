package bk.elearning.controller.manager.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
	
	@RequestMapping("/ql-cau-hoi/kho-chung")
	public String getQuestionListViewCommon(@RequestParam String subject,@RequestParam String level,@RequestParam String type)
	{
		return "manager/admin/question-list-common";
	}
	
	@RequestMapping("/ql-cau-hoi/kho-chung/cap-nhat")
	public String editQuestionView(@RequestParam int id)
	{
		return "manager/admin/edit-question";
	}
	
	@RequestMapping("/ql-cau-hoi/them-cau-hoi")
	public String addQuestionView()
	{
		return "manager/admin/add-question";
	}
	
	@RequestMapping("/ql-cau-hoi/kho-giang-vien")
	public String getQuestionListViewTeacher(@RequestParam String teacher,@RequestParam String subject,@RequestParam String level,@RequestParam String type)
	{
		return "manager/admin/question-list-teacher";
	}
	
	@RequestMapping("/ql-mon-hoc/danh-sach-lop")
	public String courseListView(@RequestParam("id") int id)
	{
		return "manager/admin/course-list";
	}
}
