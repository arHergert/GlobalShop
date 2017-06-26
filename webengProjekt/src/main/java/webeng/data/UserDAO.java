package webeng.data;


import java.util.List;

import webeng.transferobjects.User;

public interface UserDAO {

	 public void addUser(User newUser);
	 public User getUser(User newUser);
	 public List<User> findAllUser();
	 public void updateUser(User tempUser);
	 public void updateUserSession(User tempUser);
	 public void deleteUser(User deletedUser);
	 public boolean isEmailRegistrated(User searchUser);
	
}//end interface UserDAO
