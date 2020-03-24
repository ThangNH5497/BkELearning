package bk.elearning.repository;

import java.util.List;

import bk.elearning.entity.Role;

public interface IRoleRepository extends IGeneralRepository<Role>{

	Role getByName(String roleName);

}
