package webeng.data;


import java.util.List;

import webeng.transferobjects.User;

public interface UserDAO {

	 public void addUser(User newUser);
	 public User getUser(User newUser);
	 public User getUser(int id);
	 public List<User> findAllUsers();
	 public void updateUser(User tempUser);
	 public void deleteUser(User deletedUser);
	 public boolean isEmailRegistered(User searchUser);
	 public User getUser(String email);
	void updateSessionId(User user);
	
}//end interface UserDAO
