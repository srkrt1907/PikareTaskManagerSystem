package com.pikare.dao;

import java.util.List;

import com.pikare.model.UserRole;
import com.pikare.model.Users;



public interface UserDao  {
	
	void register(Users user);
	void update(Users user);
	void changePassword(String username , String pass);
	List<Users> getAllUser();
	Users getUser(String username);
	void addUserRole(UserRole userRole);
	List<UserRole> getUserRole(String username);
	Users getUserByUsername(String username);
	
	List<Users> getByUserRoles();
}
