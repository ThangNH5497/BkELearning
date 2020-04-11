package bk.elearning.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import bk.elearning.entity.Teacher;
import bk.elearning.entity.dto.PaginationResult;
import bk.elearning.repository.ITeacherRepository;
import bk.elearning.service.IPaginationResultService;
import bk.elearning.service.ITeacherService;
import bk.elearning.utils.Constant;
import bk.elearning.utils.FileUpload;
import bk.elearning.utils.TeacherMapperUtil;
import bk.elearning.utils.Util;

@Service
public class TeacherServiceImpl implements ITeacherService, IPaginationResultService<Teacher> {

	@Autowired
	private ITeacherRepository teacherRepository;


	@Override
	public Teacher getById(int id) {
		// TODO Auto-generated method stub
		
		return teacherRepository.getById(id);
	}

	@Override
	public List<Teacher> getAll() {
		// TODO Auto-generated method stub
		return teacherRepository.getAll();
	}

	// thêm mới đối tượng teacher không có ảnh đại diện
	@Override
	public int save(Teacher t) {
		// TODO Auto-generated method stub
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		t.setPassword(passwordEncoder.encode(t.getPassword()));
		t.setRole(Constant.ROLE_TEACHER);
		if (t.getImage() == "")
			t.setImage(Constant.DEFAULT_USER_IMAGE);
		t.setCourses(null);
		return teacherRepository.save(t);
	}

	/**
	 * Thêm mới đối tượng teacher Tham số file là ảnh đại diện.
	 */
	public int save(Teacher t, MultipartFile file) {
		// TODO Auto-generated method stub
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		t.setPassword(passwordEncoder.encode(t.getPassword()));
		t.setRole(Constant.ROLE_TEACHER);

		t.setCourses(null);
		if (file != null) {
			String filePath=FileUpload.saveFile(file,Constant.UPLOAD_USER_DIR);
			if(filePath!=null)
			{
				t.setImage(filePath);
			}
			else t.setImage(Constant.DEFAULT_USER_IMAGE);
		}
		// dat hinh anh default
		else
			t.setImage(Constant.DEFAULT_USER_IMAGE);
		return teacherRepository.save(t);
	}

	// xoa theo id
	@Override
	public int delete(int id) {
		// TODO Auto-generated method stub
		return teacherRepository.delete(id);
	}

	// cập nhật không có hình ảnh
	@Override
	public int update(Teacher t) {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * Cập Nhật dữ liệu Tham số teacher đối tượng cập nhât Tham số file là ảnh đại
	 * diện.
	 */
	@Override
	public int update(Teacher teacher, MultipartFile file) {
		Teacher teacherUpdate = teacherRepository.getById(teacher.getId());
		teacherUpdate.setEmail(teacher.getEmail());
		teacherUpdate.setPhoneNumber(teacher.getPhoneNumber());
		teacherUpdate.setDateOfBirth(teacher.getDateOfBirth());
		teacherUpdate.setDepartment(teacher.getDepartment());
		teacherUpdate.setFullName(teacher.getFullName());
		teacherUpdate.setPosition(teacher.getPosition());
		if (file != null) {
			String filePath=FileUpload.saveFile(file,Constant.UPLOAD_USER_DIR);
			if(filePath!=null)
			{
				teacherUpdate.setImage(filePath);
			}
			else teacherUpdate.setImage(Constant.DEFAULT_USER_IMAGE);
			
		}
		return teacherRepository.update(teacherUpdate);
	}

	// lấy theo username
	@Override
	public Teacher getByUsername(String username) {
		// TODO Auto-generated method stub
		return teacherRepository.getByUsername(username);

	}

	// lấy theo code
	@Override
	public Teacher getByCode(String code) {
		// TODO Auto-generated method stub
		return teacherRepository.getByCode(code);
	}

	/**
	 * tra ve du lieu va phan trang du lieu Tham số page là chỉ số trang Tham số
	 * size là lượng phần tử cần lấy
	 */
	@Override
	public PaginationResult<Teacher> getPage(int page, int size) {
		// TODO Auto-generated method stub
		if (page > 0)
			return teacherRepository.getPage(page - 1, size);
		return null;
	}

	
	

	/**
	 * Xóa du lieu va phan trang du lieu Tham số ids là danh sách id cần xóa
	 */

	@Override
	public int deleteMultiple(ArrayList<Integer> ids) {
		// TODO Auto-generated method stub
		int checkErro = 0;
		for (Integer integer : ids) {
			try {
				teacherRepository.delete(integer);
			} catch (Exception e) {
				// TODO: handle exception
				checkErro++;
			}
		}
		if (checkErro == 0)
			return 1;
		return 1;
	}
	// luu tu file exel
	public int[] saveFromFile(MultipartFile file) {
		// TODO Auto-generated method stub

		int success = 0;
		int error = 0;
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		if (file != null) {
			List<Teacher> teachers = FileUpload.processFileExel(file, new TeacherMapperUtil(true));
			for (Teacher teacher : teachers) {
				try {

					teacher.setPassword(passwordEncoder.encode(teacher.getPassword()));
					if (teacher.getImage() == null || teacher.getImage().equals("")) {
						teacher.setImage(Constant.DEFAULT_USER_IMAGE);
					}
					teacher.setRole(Constant.ROLE_TEACHER);
					if (teacherRepository.save(teacher) == 1)
						success++;
				} catch (Exception e) {
				}

			}
			error = teachers.size() - success;
		}

		return new int[] { success, error };

	}

	@Override
	public PaginationResult<Teacher> getSearchPage(String key, int page, int size) {
		// TODO Auto-generated method stub
		if (page > 0)
			return teacherRepository.search(key,page - 1, size);
		return null;
	}





}
