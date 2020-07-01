package bk.elearning.service;

import bk.elearning.entity.User;
import bk.elearning.entity.dto.PasswordDTO;

public interface IUserService extends IUserGenericService<User>{

	public int updatePassword(PasswordDTO passwordDTO);

	public int sentEmail(String email);

	public int forgotPassword(User user);

	
}
