package bk.elearning.controller.manager.teacher;

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
	public String questionManagement(@RequestParam String subject,@RequestParam String level,@RequestParam String type)
	{		
		return "manager/teacher/question-list";
	}
	@RequestMapping("/ql-cau-hoi/cap-nhat")
	public String editQuestion(@RequestParam(name="id") int questionId)
	{		
		return "manager/teacher/edit-question";
	}
	@RequestMapping("/ql-cau-hoi/them-cau-hoi")
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
	
	@RequestMapping("/ql-de-thi/danh-sach")
	public String getExamPaperListView(@RequestParam(name="subject") String subject)
	{
		return "manager/teacher/exampaper-list";
	}
	
	@RequestMapping("/ql-lop-hoc/ql-bai-thi")
	public String getExamListView(@RequestParam(name="course") Integer courseId)
	{
		return "manager/teacher/exam-list";
	}
	@RequestMapping("/ql-lop-hoc/ql-bai-thi/them-moi")
	public String addExamView(@RequestParam(name="course") Integer courseId)
	{
		return "manager/teacher/add-exam";
	}
	@RequestMapping("/ql-lop-hoc/ql-bai-thi/cap-nhat")
	public String getUpdateExamView(@RequestParam(name="examId") Integer examId)
	{
		return "manager/teacher/edit-exam";
	}
	@RequestMapping("/ql-de-thi/them-moi")
	public String getAddExamPaperView()
	{
		return "manager/teacher/add-exampaper";
	}
	
	@RequestMapping("/ql-de-thi/cap-nhat")
	public String getEditExamPaperView(@RequestParam(name="id") int id)
	{
		return "manager/teacher/edit-exampaper";
	}
	
	@RequestMapping("/ql-danh-muc")
	public String getCategoryView(@RequestParam(required = false,name="subject") Integer subjectId)
	{
		return "manager/teacher/category-list";
	}
}
