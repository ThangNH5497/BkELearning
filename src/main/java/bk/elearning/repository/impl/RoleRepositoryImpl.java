package bk.elearning.repository.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import bk.elearning.entity.Role;
import bk.elearning.repository.IRoleRepository;

@Repository
@Transactional
public class RoleRepositoryImpl extends GeneralRepositoryImpl<Role> implements IRoleRepository{

	@Autowired
	private SessionFactory sessionFactory;
	@Override
	public List<Role> getByIds(int[] ids) {
		// TODO Auto-generated method stub
		return null;
	}
	//get by name
	public List<Role> getByName(String roleName) {
		// TODO Auto-generated method stub
		String hqlQuery="from Role r where role.roleName=:param";
		List<Role> list=null;
		try {
			Session session = this.sessionFactory.getCurrentSession();
			Query q=session.createQuery(hqlQuery);
			q.setParameter("param", roleName);
			list=q.list();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}
	
	

}
