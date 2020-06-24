package bk.elearning.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import bk.elearning.entity.Student;
import bk.elearning.entity.dto.PaginationResult;
import bk.elearning.repository.IStudentRepository;
import bk.elearning.service.IStudentService;
import bk.elearning.utils.Constant;
import bk.elearning.utils.FileUpload;
import bk.elearning.utils.StudentMapperUtil;

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
		t.setRole(Constant.ROLE_STUDENT);
		if (t.getImage() == "")
			t.setImage(Constant.DEFAULT_USER_IMAGE);
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
		t.setRole(Constant.ROLE_STUDENT);

		t.setStudentCourses(null);
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
			String filePath=FileUpload.saveFile(file,Constant.UPLOAD_USER_DIR);
			if(filePath!=null)
			{
				studentUpdate.setImage(filePath);
			}
			else studentUpdate.setImage(Constant.DEFAULT_USER_IMAGE);
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
		if(page>0) return studentRepository.getPage(page-1, size);
		else return null;
	}

	/**
	 * tra ve du lieu va phan trang du lieu tham so filer bộ lọc trường dữ liệu Tham
	 * số key là từ khóa tìm kiếm Tham số page là chỉ số trang Tham số size là lượng
	 * phần tử cần lấy
	 */
	@Override
	public PaginationResult<Student> getSearchPage(String key, int page, int size) {
		// TODO Auto-generated method stub
		if(page>0) return studentRepository.search(key, page-1, size);
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
			List<Student> students = FileUpload.processFileExel(file, new StudentMapperUtil(true));
			for (Student student : students) {
				try {
					
					student.setPassword(passwordEncoder.encode(student.getPassword()));
					if (student.getImage() == null || student.getImage().equals("")) {
						student.setImage(Constant.DEFAULT_USER_IMAGE);
					}
					student.setRole(Constant.ROLE_STUDENT);
					if (studentRepository.save(student) == 1)
						success++;
				} catch (Exception e) {
				}

			}
			error=students.size()-success;
		}
		
		return new int[] {success,error};

	}

	@Override
	public PaginationResult<Student> getPageByCourse(int courseId, int page, int size) {
		// TODO Auto-generated method stub
		
		if(page>0)
			return studentRepository.getPageByCourse(courseId,page-1,size);
		return null;
	}

	@Override
	public PaginationResult<Student> searchByCourse(int courseId, String key, int page, int size) {
		// TODO Auto-generated method stub
		if(page>0)
			return studentRepository.searchByCourse(courseId,key,page-1,size);
		return null;
	}

}
