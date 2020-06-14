package bk.elearning.controller.manager;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/manager")
public class ManagerController {

	@RequestMapping("/downloadexampaper")
	public String getDownloadPage(@RequestParam int id,@RequestParam String type,@RequestParam String border)
	{
		return "manager/commons/download-exampaper";
	}
}
