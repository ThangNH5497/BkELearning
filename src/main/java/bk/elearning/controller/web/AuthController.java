package bk.elearning.controller.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import bk.elearning.entity.dto.CustomUserDetails;
import bk.elearning.utils.Constant;

@Controller
public class AuthController {

	@RequestMapping(value = { "/login" })
	public String login(@RequestParam(value = "error", required = false) final String error, final Model model,
			HttpServletRequest httpServletRequest) {
		if (error != null) {
			model.addAttribute("message", "Tài Khoản hoặc Mật Khẩu Không Đúng!");
		}
		return "commons/login";
	}

	@RequestMapping("/trang-chu")
	public String getHomePage() {
		CustomUserDetails user = null;
		try {
			user = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		if (user != null) {
			switch (user.getRole()) {
			case Constant.ROLE_ADMIN: {
				return "manager/admin/home";
			}

			case Constant.ROLE_TEACHER: {
				return "manager/teacher/home";
			}
			
			case Constant.ROLE_STUDENT: {
				return "web/home";
			}

			default: {
				return "commons/login";
			}

			}
		}
		return "commons/login";

	}

	@RequestMapping("/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}

		return "redirect:/trang-chu";
	}
}
