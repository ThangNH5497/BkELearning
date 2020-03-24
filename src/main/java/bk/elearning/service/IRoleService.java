package bk.elearning.service;

import java.util.List;

import bk.elearning.entity.Role;

public interface IRoleService extends IGeneralService<Role>{

	public Role getByName(String roleName) ;
}
