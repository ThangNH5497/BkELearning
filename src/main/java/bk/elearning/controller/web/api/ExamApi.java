package bk.elearning.controller.web.api;

import java.io.IOException;
import java.net.URL;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.styledxmlparser.css.media.MediaDeviceDescription;
import com.itextpdf.styledxmlparser.css.media.MediaType;

import bk.elearning.entity.dto.ExamDTO;
import bk.elearning.entity.dto.PaginationResult;
import bk.elearning.entity.relationship.StudentExam;
import bk.elearning.service.IExamPaperService;
import bk.elearning.service.IExamService;
import bk.elearning.service.IQuestionService;
import bk.elearning.utils.Constant;
import bk.elearning.utils.Message;

@RestController
@RequestMapping(path = "api/exams")
public class ExamApi {

	@Autowired
	IExamService examService;

	@Autowired
	IExamPaperService examPaperService;

	@Autowired
	IQuestionService questionService;

	@GetMapping("/page/students/{studentId}")
	public PaginationResult<ExamDTO> getPageByStudent(@PathVariable Integer studentId, @RequestParam String filter,
			@RequestParam int page, @RequestParam int size) {
		try {
			return examService.getByStudent(studentId, filter, page, size);
		} catch (Exception e) {

		}
		return null;

	}
	
	@GetMapping("/search/students/{studentId}")
	public PaginationResult<ExamDTO> searchByStudent(@PathVariable Integer studentId, @RequestParam(name = "q") String key,
			@RequestParam int page, @RequestParam int size) {
		try {
			return examService.searchByStudent(studentId, key, page, size);
		} catch (Exception e) {

		}
		return null;

	}

	@GetMapping("/dto/id/{examId}")
	public ExamDTO getById(@PathVariable Integer examId) {
		try {
			return examService.getExamDTOById(examId);
		} catch (Exception e) {

		}
		return null;

	}

	@GetMapping("/id/{examId}/students/{studentId}")
	public ExamDTO doExam(@PathVariable Integer examId, @PathVariable Integer studentId, HttpServletRequest request) {
		try {
			return examService.doExam(examId, studentId, request);
		} catch (Exception e) {

		}
		return null;

	}
	
	@GetMapping("/studentexams/{id}")
	public StudentExam getById(@PathVariable int id) {
		try {
			return examService.getResultDetailByStudentExamId(id);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return null;

	}

	@PostMapping("/studentexams/{studentexamId}/lock")
	public Message createLockExamDetail(@RequestBody StudentExam studentexam) {
		
		if(examService.createLockExamDetail(studentexam)==1)
		{
			return new Message(Constant.STATUS_SUCCESS, "Hệ thống đang xử lý yêu cầu !");
		}
		
		return new Message(Constant.STATUS_ERROR, "Đã Xảy Ra Lỗi . Xin Thử lại Sau!");
	}

	@PostMapping("/itext/{id}")
	public void testItext(@PathVariable int id) {
		/*
		 * String DEST = "D:\\test.pdf";
		 * 
		 * try { ExamPaper examPaper = examPaperService.getById(id); StringBuilder HTML
		 * = new StringBuilder(); HTML.append("<!DOCTYPE html>" + "<html>" + "<head>" +
		 * "<meta charset='UTF-8'> <meta name='viewport' content='width=device-width, initial-scale=1'>"
		 * + " <style type='text/css'>" +
		 * " p{word-wrap: break-word !important; color:red;}" + " </style>"+ "</head>" +
		 * "<body>"); int i=1; for (ExamPaperQuestion epq :
		 * examPaper.getExamPaperQuestions()) { Question question=epq.getQuestion();
		 * question.setContent(StringEscapeUtils.unescapeHtml4(question.getContent()));;
		 * question.getAnswers().clear(); for (ExamPaperQuestionAnswer epqa :
		 * epq.getExamPaperQuestionAnswers()) {
		 * question.getAnswers().add(epqa.getAnswer()); } String content
		 * =question.getContent(); if(i==3) System.out.println(content);
		 * content.substring(content.indexOf("<p>") + 3); content =
		 * "<p><b>Câu "+String.valueOf(i)+". </b>" + content+"</p>"; if(i==3)
		 * System.out.println(content);
		 * 
		 * HTML.append(content); i++; } HTML.append("</body></html>"); File file = new
		 * File(DEST); file.getParentFile().mkdirs(); createPdf(HTML.toString(), DEST);
		 * 
		 * } catch (Exception e) { // TODO: handle exception e.printStackTrace(); }
		 */

		try {

			String DEST = "D:\\test.pdf";

			createPdf(new URL("http://127.0.0.1:8080/BkELearning/test"), DEST);
		} catch (Exception e) { // TODO: handle exception
			System.out.println(e);
		}

	}

	/**
	 * Creates the PDF file.
	 *
	 * @param html the HTML as a String value
	 * @param dest the path of the resulting PDF
	 * @throws IOException signals that an I/O exception has occurred.
	 */

	/*
	 * public void createPdf(String html, String dest) throws IOException {
	 * 
	 * HtmlConverter.convertToPdf(html, new FileOutputStream(dest)); }
	 */

	public void createPdf(URL url, String dest) throws IOException {
		PdfWriter writer = new PdfWriter(dest);
		PdfDocument pdf = new PdfDocument(writer);
		PageSize pageSize = new PageSize(850, 1700);
		pdf.setDefaultPageSize(pageSize);
		ConverterProperties properties = new ConverterProperties();
		MediaDeviceDescription mediaDeviceDescription = new MediaDeviceDescription(MediaType.SCREEN);
		mediaDeviceDescription.setWidth(pageSize.getWidth());
		properties.setMediaDeviceDescription(mediaDeviceDescription);
		HtmlConverter.convertToPdf(url.openStream(), pdf, properties);
	}

}
