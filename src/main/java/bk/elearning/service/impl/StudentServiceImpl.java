package bk.elearning.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import bk.elearning.entity.Student;
import bk.elearning.entity.dto.PaginationResult;
import bk.elearning.repository.IStudentRepository;
import bk.elearning.service.IStudentService;
import bk.elearning.utils.FileUpload;
import bk.elearning.utils.StudentMapperUtil;
import bk.elearning.utils.Util;

@Service
public class StudentServiceImpl implements IStudentService{


	@Autowired
	private IStudentRepository studentRepository;

	@Override
	public Student getById(int id) {
		// TODO Auto-generated method stub
		return studentRepository.getById( id);
	}

	@Override
	public List<Student> getAll() {
		// TODO Auto-generated method stub
		return studentRepository.getAll();
	}

	// thêm mới đối tượng student không có ảnh đại diện
	@Override
	public int save(Student t) {
		// TODO Auto-generated method stub
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		t.setPassword(passwordEncoder.encode(t.getPassword()));
		t.setRole(Util.ROLE_STUDENT);
		if (t.getImage() == "")
			t.setImage(Util.DEFAULT_USER_IMAGE);
		t.setStudentCourses(null);
		return studentRepository.save(t);
	}

	/**
	 * Thêm mới đối tượng student Tham số file là ảnh đại diện.
	 */
	public int save(Student t, MultipartFile file) {
		// TODO Auto-generated method stub
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		t.setPassword(passwordEncoder.encode(t.getPassword()));
		t.setRole(Util.ROLE_STUDENT);

		t.setStudentCourses(null);
		if (file != null) {
			String filePath = "resources/commons/image/user/user-" + t.getCode() + "."
					+ file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf('.') + 1);
			System.out.println("path : " + filePath);
			if (FileUpload.saveFile(file, filePath)) {
				t.setImage(filePath);
			}
		}
		// dat hinh anh default
		else
			t.setImage(Util.DEFAULT_USER_IMAGE);
		return studentRepository.save(t);
	}

	// xoa theo id
	@Override
	public int delete(int id) {
		// TODO Auto-generated method stub
		return studentRepository.delete( id);
	}

	// cập nhật không có hình ảnh
	@Override
	public int update(Student t) {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * Cập Nhật dữ liệu Tham số student đối tượng cập nhât Tham số file là ảnh đại
	 * diện.
	 */
	@Override
	public int update(Student student, MultipartFile file) {
		Student studentUpdate = studentRepository.getById( student.getId());
		studentUpdate.setEmail(student.getEmail());
		studentUpdate.setPhoneNumber(student.getPhoneNumber());
		studentUpdate.setDateOfBirth(student.getDateOfBirth());
		studentUpdate.setFullName(student.getFullName());
		studentUpdate.setClassName(student.getClassName());
		if (file != null) {
			String filePath = "resources/commons/image/user/user-" + student.getCode() + "."
					+ file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf('.') + 1);
			if (FileUpload.saveFile(file, filePath)) {
				studentUpdate.setImage(filePath);
			}
		}
		return studentRepository.update(studentUpdate);
	}

	// lấy theo username
	@Override
	public Student getByUsername(String username) {
		// TODO Auto-generated method stub
		return studentRepository.getByUsername(username);
	}

	// lấy theo code
	@Override
	public Student getByCode(String code) {
		// TODO Auto-generated method stub
		return studentRepository.getByCode(code);
	}

	
	/**
	 * tra ve du lieu va phan trang du lieu Tham số page là chỉ số trang Tham số
	 * size là lượng phần tử cần lấy
	 */
	@Override
	public PaginationResult<Student> getPage(int page, int size) {
		// TODO Auto-generated method stub
		PaginationResult<Student> pageResult = new PaginationResult<Student>();
		try {
			pageResult.setCount(studentRepository.getCount());
			if (page > 0) {
				pageResult.setData(studentRepository.getAll((page - 1) * size, size));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return pageResult;
	}

	/**
	 * tra ve du lieu va phan trang du lieu tham so filer bộ lọc trường dữ liệu Tham
	 * số key là từ khóa tìm kiếm Tham số page là chỉ số trang Tham số size là lượng
	 * phần tử cần lấy
	 */
	@Override
	public PaginationResult<Student> getSearchPage(String filter, String key, int page, int size) {
		// TODO Auto-generated method stub
		PaginationResult<Student> pageResult = new PaginationResult<Student>();
		try {
			HashMap<String, String> searchFields=new HashMap<String, String>();
			searchFields.put(filter, key);
			pageResult.setCount(studentRepository.getCount(null, searchFields));
			if (page > 0) {
				pageResult.setData(studentRepository.search( null,searchFields, (page - 1) * size, size));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return pageResult;
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
				studentRepository.delete(integer);
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
			List<Student> students = FileUpload.processFileExel(file, new StudentMapperUtil());
			for (Student student : students) {
				try {
					
					student.setPassword(passwordEncoder.encode(student.getPassword()));
					if (student.getImage() == null || student.getImage().equals("")) {
						student.setImage(Util.DEFAULT_USER_IMAGE);
					}
					student.setRole(Util.ROLE_TEACHER);
					if (studentRepository.save(student) == 1)
						success++;
				} catch (Exception e) {
				}

			}
			error=students.size()-success;
		}
		
		return new int[] {success,error};

	}

	

	

}