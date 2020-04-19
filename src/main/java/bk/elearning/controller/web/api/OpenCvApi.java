package bk.elearning.controller.web.api;

import org.hibernate.annotations.Parameter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import bk.elearning.utils.OpenCvUtil;

@RestController
@RequestMapping(path = "/api/opencv")
public class OpenCvApi {
	
	@GetMapping(path="/test")
	public void testOpenCV(@RequestParam int number)
	{
		new OpenCvUtil().test(number);
	}
}
