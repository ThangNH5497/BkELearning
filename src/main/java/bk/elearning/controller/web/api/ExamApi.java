package bk.elearning.controller.web.api;

import java.io.IOException;
import java.net.URL;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
import bk.elearning.service.IExamService;
import bk.elearning.service.IQuestionService;

@RestController
@RequestMapping(path = "api/exams")
public class ExamApi {

	@Autowired
	IExamService examService;
	
	@Autowired
	IQuestionService questionService;

	@GetMapping("/page/students/{studentId}")
	public PaginationResult<ExamDTO> getByCode(@PathVariable Integer studentId, @RequestParam String filter,
			@RequestParam int page, @RequestParam int size) {
		try {
			return examService.getByStudent(studentId, filter, page, size);
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

	@GetMapping("/itext/{id}")
	public void testItext(@PathVariable Integer id) {
		
		/*
		 * Question q=questionService.getById(id);
		 * 
		 * String DEST = "D:\\helloWorld01.pdf";
		 * 
		 * StringBuilder HTML =new StringBuilder(); String content=q.getContent();
		 * content=content.substring(content.indexOf("<p>")+3);
		 * content="<p><b>Câu 1. </b>"+content; HTML.append(content); File file = new
		 * File(DEST); file.getParentFile().mkdirs();
		 * 
		 * try { System.out.println(HTML.toString()); createPdf(HTML.toString(), DEST);
		 * } catch (IOException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); }
		 */
		try {
			String DEST = "D:\\helloWorld01.pdf";
			String ADDRESS = "http://localhost:8080/BkELearning/test";
			createPdf(new URL(ADDRESS), DEST);
		} catch (Exception e) {
			// TODO: handle exception
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
	    MediaDeviceDescription mediaDeviceDescription =
	        new MediaDeviceDescription(MediaType.SCREEN);
	    mediaDeviceDescription.setWidth(pageSize.getWidth());
	    properties.setMediaDeviceDescription(mediaDeviceDescription);
	    HtmlConverter.convertToPdf(url.openStream(), pdf, properties);
	}
}
