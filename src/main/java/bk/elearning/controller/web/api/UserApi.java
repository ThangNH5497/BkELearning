package bk.elearning.controller.web.api;

import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.text.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import bk.elearning.entity.Teacher;
import bk.elearning.entity.User;
import bk.elearning.entity.dto.PasswordDTO;
import bk.elearning.service.IUserService;
import bk.elearning.utils.Constant;
import bk.elearning.utils.Message;

@RestController
@RequestMapping(path = "api/users")
public class UserApi {

	@Autowired
	private IUserService userService;

	@PostMapping(path = "/test")
	public Message test(@RequestBody String str) {
		String unEscapedHTML = StringEscapeUtils.escapeHtml4(str);
		System.out.println("escaped : " + unEscapedHTML);
		unEscapedHTML = StringEscapeUtils.unescapeHtml4(unEscapedHTML);

		return new Message(unEscapedHTML);

	}

	// lay theo id
	@GetMapping(path = "/{id}")
	public User getUserById(@PathVariable int id) {
		User user = null;
		try {
			user = userService.getById(id);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return user;
	}

	// lay theo code
	@GetMapping(path = "/code/{code}")
	public User getUserByCode(@PathVariable String code) {
		User user = null;
		try {
			user = userService.getByCode(code);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return user;
	}

	// lay theo cuserName
	@GetMapping(path = "/username/{username}")
	public User getUserByUsername(@PathVariable String username) {
		User user = null;
		try {
			user = userService.getByUsername(username);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return user;
	}

	// update
	@PutMapping("/{id}")
	public Message updateInfo(@PathVariable int id, @RequestPart("user") User user,
			@RequestPart(name = "file", required = false) MultipartFile file) {
		user.setId(id);
		try {
			if (userService.update(user, file) == 1)
				return new Message(Constant.STATUS_SUCCESS, "Cập Nhật Thành Công ");
		} catch (Exception e) {

		}

		return new Message(Constant.STATUS_ERROR, "Cập Nhật Thất Bại. Xin Thử Lại Sau!");

	}

	@PutMapping("/{id}/password")
	public Message updatePassword(@PathVariable int id, @RequestBody PasswordDTO passwordDTO) {
		passwordDTO.setUserId(id);
		try {
			int status = userService.updatePassword(passwordDTO);
			if (status == Constant.STATUS_SUCCESS)
				return new Message(Constant.STATUS_SUCCESS, "Cập Nhật Thành Công ");
			else if (status == Constant.STATUS_PASSWORD_ERROR)
				return new Message(Constant.STATUS_PASSWORD_ERROR, "Mật khẩu hiện tại chưa chính xác");
		} catch (Exception e) {

		}

		return new Message(Constant.STATUS_ERROR, "Cập Nhật Thất Bại. Xin Thử Lại Sau!");

	}

	@PutMapping("/password")
	public Message forgotPassword(@RequestBody User user) {
		try {
			if (userService.forgotPassword(user)==1)
				return new Message(Constant.STATUS_SUCCESS, "Cập Nhật Thành Công ");

		} catch (Exception e) {

		}

		return new Message(Constant.STATUS_ERROR, "Cập Nhật Thất Bại. Xin Thử Lại Sau!");

	}

	@PostMapping(path = "/emails/{email}/code")
	public Message sendCode(@PathVariable String email, HttpServletRequest request) {
		HttpSession session = request.getSession();
		Random ran = new Random();
		String code = "";
		for (int i = 0; i < 4; i++) {
			code += String.valueOf(ran.nextInt(9));
		}

		try {
			String sub = "Mã Xác Nhận Tài Khoản BkELearning";
			String msg = "Mã Xác Nhận Của Bạn Là : " + code;
			if (bk.elearning.utils.Util.send("bachthang54@gmail.com", "honglam123", email, sub, msg)) {
				session.setAttribute("code", code);
				return new Message(Constant.STATUS_SUCCESS, "Gửi mã email thành công!");
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("loi email : " + e.toString());
		}

		return new Message(Constant.STATUS_ERROR, "Gửi mã email thất bại!");
	}

	@GetMapping(path = "/check-code")
	public Message checkCode(@RequestParam(name = "code") String code, HttpServletRequest request) {
		HttpSession session = request.getSession();
		try {
			String serverCode = (String) session.getAttribute("code");
			System.out.println("servercode " + serverCode);
			System.out.println("code " + code);
			if (serverCode.equals(code)) {
				session.removeAttribute("code");
				return new Message(Constant.STATUS_SUCCESS, "Xác nhận mã thành công");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		return new Message(Constant.STATUS_ERROR, "Xác nhận mã thất bại");
	}
}
