package bk.elearning.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import bk.elearning.entity.User;
import bk.elearning.entity.dto.CustomUserDetails;
import bk.elearning.entity.dto.PaginationResult;
import bk.elearning.entity.dto.PasswordDTO;
import bk.elearning.repository.IUserRepository;
import bk.elearning.service.IUserService;
import bk.elearning.utils.Constant;
import bk.elearning.utils.FileUpload;

@Service
public class UserServiceImpl implements IUserService{

	@Autowired
	private IUserRepository userRepository;



	@Override
	public User getById(int id) {
		// TODO Auto-generated method stub
		return userRepository.getById(id);
	
	}

	@Override
	public List<User> getAll() {
		// TODO Auto-generated method stub
		return userRepository.getAll();
		
	}
	@Override
	public User getByUsername(String username) {
		// TODO Auto-generated method stub
		return userRepository.getByUsername(username);
		
	}
	@Override
	public int save(User t) {
		// TODO Auto-generated method stub
		return userRepository.save(t);
	}

	@Override
	public int delete(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(User t) {
		// TODO Auto-generated method stub
		
		return 0;
	}

	@Override
	public User getByCode(String code) {
		// TODO Auto-generated method stub
		return userRepository.getByCode(code);
	}

	@Override
	public int deleteMultiple(ArrayList<Integer> ids) {
		// TODO Auto-generated method stub
		return 0;
	}

	

	@Override
	public int save(User t, MultipartFile file) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(User user, MultipartFile file) {
		// TODO Auto-generated method stub
		
		try {
			User userUpdate=userRepository.getById(user.getId());
			
			CustomUserDetails userLoged = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication()
					.getPrincipal();
			
			if(userLoged.getId()==userUpdate.getId())
			{
				userUpdate.setEmail(user.getEmail());
				userUpdate.setPhoneNumber(user.getPhoneNumber());
				userUpdate.setDateOfBirth(user.getDateOfBirth());
				userUpdate.setAddr(user.getAddr());
				if (file != null) {
					String filePath=FileUpload.saveFile(file,Constant.UPLOAD_USER_DIR);
					if(filePath!=null)
					{
						userLoged.setImage(filePath);
						userUpdate.setImage(filePath);
					}
				}
				return userRepository.update(userUpdate);
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("loi update user : "+e.toString());
		}
		
		return 0;
	}

	@Override
	public int[] saveFromFile(MultipartFile file) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PaginationResult<User> getPage(int page, int size) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PaginationResult<User> getSearchPage( String key, int page, int size) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updatePassword(PasswordDTO passwordDTO) {
		// TODO Auto-generated method stub
		try {
			User userUpdate=userRepository.getById(passwordDTO.getUserId());
			
			CustomUserDetails userLoged = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication()
					.getPrincipal();
			
			if(userLoged.getId()==userUpdate.getId())
			{
				
				BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
				boolean check = passwordEncoder.matches(passwordDTO.getOldPassword(), userUpdate.getPassword());

				if (check) {
					String newPsassword = passwordEncoder.encode(passwordDTO.getNewPassword());
					userUpdate.setPassword(newPsassword);
					userRepository.update(userUpdate);
					return Constant.STATUS_SUCCESS;
				} 
				else return Constant.STATUS_PASSWORD_ERROR;
				
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("loi update mat khau user : "+e.toString());
		}
		
		return 0;
	}

	@Override
	public int sentEmail(String email) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int forgotPassword(User user) {
		// TODO Auto-generated method stub
		try {
			User userUpdate=userRepository.getById(user.getId());
			
			
			if(userUpdate!=null)
			{
				
				BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			
				String newPsassword = passwordEncoder.encode(user.getPassword());
				userUpdate.setPassword(newPsassword);
				return	userRepository.update(userUpdate);
				
				
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("loi update mat khau user : "+e.toString());
		}
		
		return 0;
	}

}
