package bk.elearning.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import bk.elearning.entity.Category;
import bk.elearning.entity.User;
import bk.elearning.entity.dto.CustomUserDetails;
import bk.elearning.entity.dto.PaginationResult;
import bk.elearning.repository.ICategoryRepository;
import bk.elearning.service.ICategoryService;
import bk.elearning.utils.Constant;

@Service
public class CategoryServiceImpl implements ICategoryService{

	@Autowired
	ICategoryRepository categoryRepo;
	@Override
	public Category getById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Category> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int save(Category t) {
		// TODO Auto-generated method stub
		try {
			CustomUserDetails user = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication()
					.getPrincipal();
			if(user!=null&&t.getSubject()!=null)
			{
				t.setUser(new User(user.getId()));
				if(user.getRole().equals(Constant.ROLE_ADMIN)) t.setBankType(Constant.BANK_TYPE_ADMIN);
				else if(user.getRole().equals(Constant.ROLE_TEACHER)) t.setBankType(Constant.BANK_TYPE_TEACHER);
				else return 0;
			}
			categoryRepo.save(t);
			return 1;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("ex : "+e.toString());
		}
		return 0;
	}

	@Override
	public int delete(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Category t) {
		try {
			Category cUpdate=categoryRepo.getById(t.getId());
			if(cUpdate!=null)
			{
				cUpdate.setName(t.getName());
			}
			categoryRepo.update(cUpdate);
			return 1;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return 0;
	}

	@Override
	public Category getByCode(String code) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int deleteMultiple(ArrayList<Integer> ids) {
		// TODO Auto-generated method stub
		try {
			int success=0;
			for (Integer integer : ids) {
				categoryRepo.delete(integer);
				success++;
			}
			return success;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return 0;
	}

	@Override
	public PaginationResult<Category> getBySubject(int subjectId, int page, int size) {
		// TODO Auto-generated method stub
		if(page>0)
		{
			//lay theo user dang nhap
			CustomUserDetails user = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication()
					.getPrincipal();
			if(user!=null)
			{
				return categoryRepo.getPageBySubject(subjectId,user.getId(),page-1,size);
			}
			
		}
		return null;
	}

	@Override
	public PaginationResult<Category> searchBySubject(int subjectId, String key, int page, int size) {
		// TODO Auto-generated method stub
		if(page>0)
		{
			CustomUserDetails user = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication()
					.getPrincipal();
			return categoryRepo.searchPageBySubject(subjectId,user.getId(),key,page-1,size);
		}
		return null;
	}

	@Override
	public ArrayList<Category> getBySubject(int subjectId) {
		// TODO Auto-generated method stub
		CustomUserDetails user = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		if(user!=null)
		{
			return categoryRepo.getBySubject(subjectId,user.getId());
		}
		return null;
	}

}
