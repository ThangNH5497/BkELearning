package bk.elearning.repository.impl;

import org.springframework.stereotype.Repository;

import bk.elearning.entity.User;
import bk.elearning.repository.IUserRepository;

@Repository
public class UserRepositoryImpl extends UserGenericRepositoryImpl<User> implements IUserRepository{



}
