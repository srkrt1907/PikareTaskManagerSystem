package com.pikare.dao.login;

import com.pikare.model.Users;



public interface LoginDao{
	Users findByUserName(String username);
}
