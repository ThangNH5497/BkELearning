package bk.elearning.repository;

public interface IUserRepository<T> extends IGenericRepository<T>{

	T getByUsername(String username);
}
