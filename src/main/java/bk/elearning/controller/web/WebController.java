package bk.elearning.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping()
public class WebController {
	@RequestMapping("/bai-thi")
	public String logout(@RequestParam Integer examId, @RequestParam Integer courseId) {

		return "web/start-exam";
	}

	// id cua de thi
	@RequestMapping("/lam-bai")
	public String doExamView(@RequestParam Integer id) {

		return "web/do-exam";
	}

	// id cua de thi
	@RequestMapping("/hoan-thanh")
	public String finishExamView() {

		return "web/finish-exam";
	}
	@RequestMapping("/test")
	public String test() {

		return "web/test";
	}

}
