package bk.elearning.repository.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import bk.elearning.entity.ExamPaper;
import bk.elearning.entity.dto.PaginationResult;
import bk.elearning.repository.IExamPaperRepository;
import bk.elearning.utils.Constant;

@Repository
public class ExamPaperRepositoryImpl extends AbstractGenericRepository<ExamPaper> implements IExamPaperRepository {
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public PaginationResult<ExamPaper> getByTeacherRepoAndSubject(String teacherFilter, String subjectFilter, int start, int size) {
		// TODO Auto-generated method stub
		
		// TODO Auto-generated method stub
				Session session=sessionFactory.getCurrentSession();
				PaginationResult<ExamPaper> page=new PaginationResult<ExamPaper>();
				try {
					String hqlQuery="FROM ExamPaper ep "
							+" WHERE ";
					//get by admin
					if(teacherFilter.equals("ALL"))
					{
						hqlQuery+="  ep.bankType=:bankType";
					}
					else
					{
						hqlQuery+="  ep.user.id=:teacherId";
					}
					
					if(!subjectFilter.equals("ALL"))
					{
						hqlQuery+=" AND ep.subject.id=:subjectId";
					}
					
					Query q=session.createQuery("SELECT new ExamPaper(ep.id,ep.code,ep.name,ep.user,ep.subject,ep.descriptor,ep.time,ep.createAt,ep.updateAt) "+hqlQuery);
					
					Query qCount=session.createQuery("SELECT COUNT(distinct ep.id) "+hqlQuery);
					
					q.setFirstResult(start);
					q.setMaxResults(size);
					if(teacherFilter.equals("ALL"))
					{
						q.setParameter("bankType", Constant.BANK_TYPE_TEACHER);
						qCount.setParameter("bankType", Constant.BANK_TYPE_TEACHER);
					}
					else
					{
						q.setParameter("teacherId", Integer.parseInt(teacherFilter));
						qCount.setParameter("teacherId", Integer.parseInt(teacherFilter));
					}

					if(!subjectFilter.equals("ALL"))
					{
						q.setParameter("subjectId", Integer.parseInt(subjectFilter));
						qCount.setParameter("subjectId", Integer.parseInt(subjectFilter));
					}
					
					page.setCount((Long)qCount.uniqueResult());
					if(page.getCount()>0) page.setData(q.list());
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println(e.toString());
					session.clear();
				}
				return page;
	}

	@Override
	public PaginationResult<ExamPaper> searchByTeacherRepoAndSubject(String teacherFilter, String subjectFilter, String key,
			int start, int size) {
		// TODO Auto-generated method stub
		Session session=sessionFactory.getCurrentSession();
		PaginationResult<ExamPaper> page=new PaginationResult<ExamPaper>();
		try {
			String hqlQuery="FROM ExamPaper ep "
					+" WHERE (ep.code like concat('%',:key,'%') or ep.name like concat('%',:key,'%')) ";
			//get by admin
			if(teacherFilter.equals("ALL"))
			{
				hqlQuery+=" AND ep.bankType=:bankType";
			}
			else
			{
				hqlQuery+=" AND ep.user.id=:teacherId";
			}
			
			if(!subjectFilter.equals("ALL"))
			{
				hqlQuery+=" AND ep.subject.id=:subjectId";
			}
			
			Query q=session.createQuery("SELECT new ExamPaper(ep.id,ep.code,ep.name,ep.user,ep.subject,ep.descriptor,ep.time,ep.createAt,ep.updateAt) "+hqlQuery);
			
			Query qCount=session.createQuery("SELECT COUNT(distinct ep.id) "+hqlQuery);
			q.setParameter("key", key);
			qCount.setParameter("key", key);
			q.setFirstResult(start);
			q.setMaxResults(size);
			if(teacherFilter.equals("ALL"))
			{
				q.setParameter("bankType", Constant.BANK_TYPE_TEACHER);
				qCount.setParameter("bankType", Constant.BANK_TYPE_TEACHER);
			}
			else
			{
				q.setParameter("teacherId", Integer.parseInt(teacherFilter));
				qCount.setParameter("teacherId", Integer.parseInt(teacherFilter));
			}

			if(!subjectFilter.equals("ALL"))
			{
				q.setParameter("subjectId", Integer.parseInt(subjectFilter));
				qCount.setParameter("subjectId", Integer.parseInt(subjectFilter));
			}
			
			page.setCount((Long)qCount.uniqueResult());
			if(page.getCount()>0) page.setData(q.list());
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.toString());
			session.clear();
		}
		return page;
	}
	@Override
	public PaginationResult<ExamPaper> getByAdminRepoAndSubject(String subject, int start, int size) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PaginationResult<ExamPaper> searchByAdminRepoAndSubject(String subject, String key, int start, int size) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
