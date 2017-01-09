package main.java.com.roxoft.dao;
import java.util.List;

import main.java.com.roxoft.model.User;

public interface IUserDao {
	
	public void update(int id);
	public void delete(int id);
	public void insert();
	public User getUserById(int id);
	public List<User> getAll();
	public void insert(User user);

}
