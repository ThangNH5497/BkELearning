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
	public String courseManagementView()
	{		
		return "manager/teacher/course-list";
	}
	@RequestMapping("/ql-cau-hoi")
	public String questionManagement()
	{		
		return "manager/teacher/question-list";
	}
	@RequestMapping("/them-cau-hoi")
	public String addQuestion()
	{		
		return "manager/teacher/add-question";
	}
	
	@RequestMapping("/ql-lop-hoc/ql-sinh-vien")
	public String studentList(@RequestParam("courseId") int courseId)
	{		
		return "manager/teacher/course-student-list";
	}

	@RequestMapping("/ql-lop-hoc/lop-hoc")
	public String detailSubjectManagementView(@RequestParam("id") int id)
	{
		return "manager/teacher/course-detail";
	}
}
