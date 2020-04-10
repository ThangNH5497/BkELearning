package bk.elearning.controller.web.api;

import org.apache.commons.text.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bk.elearning.entity.User;
import bk.elearning.service.IUserService;
import bk.elearning.utils.Message;

@RestController
@RequestMapping(path = "api/users")
public class UserApi {

	@Autowired
	private IUserService userService;
	@PostMapping(path = "/test")
	public Message test(@RequestBody String str) {
		 String unEscapedHTML = StringEscapeUtils.escapeHtml4(str);
		 System.out.println("escaped : "+unEscapedHTML);
		 unEscapedHTML=StringEscapeUtils.unescapeHtml4(unEscapedHTML);

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
}
