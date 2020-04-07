package bk.elearning.repository;

import org.springframework.stereotype.Repository;

import bk.elearning.entity.User;

@Repository
public interface IUserRepository extends IUserGenericRepository<User> {

}
