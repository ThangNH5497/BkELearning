package bk.elearning.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.text.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import bk.elearning.entity.Answer;
import bk.elearning.entity.ExamPaper;
import bk.elearning.entity.User;
import bk.elearning.entity.dto.CustomUserDetails;
import bk.elearning.entity.relationship.ExamPaperQuestion;
import bk.elearning.entity.relationship.ExamPaperQuestionAnswer;
import bk.elearning.repository.IExamPaperRepository;
import bk.elearning.service.IExamPaperService;
import bk.elearning.utils.Constant;
import bk.elearning.utils.Util;

@Service
public class ExamPaperServiceImpl implements IExamPaperService {

	@Autowired
	IExamPaperRepository examPaperRepository;

	@Override
	public ExamPaper getById(int id) {
		// TODO Auto-generated method stub
		try {
			ExamPaper ep= examPaperRepository.getById(id);
			
			for (ExamPaperQuestion epq : ep.getExamPaperQuestions()) {
				epq.getQuestion().setContent(StringEscapeUtils.unescapeHtml4(epq.getQuestion().getContent()));
				
				List<Answer> answers=epq.getQuestion().getAnswers();
				
				for(int i=0;i<answers.size();i++)
				{
					answers.set(i, epq.getExamPaperQuestionAnswers().get(i).getAnswer());
					answers.get(i).setContent(StringEscapeUtils.unescapeHtml4(answers.get(i).getContent()));
					/*
					for(int j=i;j<answers.size();j++)
					{
						if(epq.getExamPaperQuestionAnswers().get(j).getAnswer().getId()==answers.get(i).getId())
						{
							
						}
					}*/
				}
								
			}
			return ep;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
		
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
			if (user!=null) {
				User u=new User(user.getId());
				examPaper.setUser(u);
				if(user.getRole().equals(Constant.ROLE_ADMIN))
				{
					examPaper.setBankType(Constant.BANK_TYPE_ADMIN);
				}
				else if(user.getRole().equals(Constant.ROLE_TEACHER))
				{
					examPaper.setBankType(Constant.BANK_TYPE_TEACHER);
				}
			}
			else return 0;
			examPaper.setCreateAt(Util.getDate());
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
	public int update(ExamPaper examPaper) {
		// TODO Auto-generated method stub
		try {
			ExamPaper examPaperUpdate=examPaperRepository.getById(examPaper.getId());
			examPaperUpdate.getExamPaperQuestions().clear();
			for (ExamPaperQuestion exq : examPaper.getExamPaperQuestions()) {
				exq.setExamPaper(examPaperUpdate);
				exq.getExamPaperQuestionAnswers().clear();
				int j=1;
				for (ExamPaperQuestionAnswer exqa : exq.getExamPaperQuestionAnswers()) {
					exqa.setExamPaperQuestion(exq);					
				}
				
				examPaperUpdate.getExamPaperQuestions().add(exq);
			}
			examPaperUpdate.setUpdateAt(Util.getDate());
			return examPaperRepository.update(examPaperUpdate);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.toString());
		}
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
