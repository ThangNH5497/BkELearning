package bk.elearning.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import bk.elearning.entity.ExamPaper;
import bk.elearning.entity.Teacher;
import bk.elearning.entity.dto.CustomUserDetails;
import bk.elearning.repository.IExamPaperRepository;
import bk.elearning.service.IExamPaperService;
import bk.elearning.utils.Constant;

@Service
public class ExamPaperServiceImpl implements IExamPaperService {

	@Autowired
	IExamPaperRepository examPaperRepository;

	@Override
	public ExamPaper getById(int id) {
		// TODO Auto-generated method stub
		return examPaperRepository.getById(id);
	}

	@Override
	public List<ExamPaper> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int save(ExamPaper examPaper) {
		// TODO Auto-generated method stub
		try {
			// user loged (teacher)
			CustomUserDetails user = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication()
					.getPrincipal();
			if (user.getRole().equals(Constant.ROLE_TEACHER)) {
				Teacher teacher = new Teacher();
				teacher.setId(user.getId());
				examPaper.setTeacher(teacher);
				examPaper.setBankType(Constant.BANK_TYPE_PRIVATE);
			}
			else if (user.getRole().equals(Constant.ROLE_ADMIN))
			{
				examPaper.setTeacher(null);
				examPaper.setBankType(Constant.BANK_TYPE_PUBLIC);
			}
			else return 0;
			examPaperRepository.save(examPaper);
			return examPaper.getId();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return 0;
	}

	@Override
	public int delete(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(ExamPaper t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ExamPaper getByCode(String code) {
		// TODO Auto-generated method stub
		return examPaperRepository.getByCode(code);
	}

	@Override
	public int deleteMultiple(ArrayList<Integer> ids) {
		// TODO Auto-generated method stub
		return 0;
	}

}
