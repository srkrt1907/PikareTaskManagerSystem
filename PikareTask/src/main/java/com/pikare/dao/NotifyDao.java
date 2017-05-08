package com.pikare.dao;

import java.util.ArrayList;

import com.pikare.model.Notify;

public interface NotifyDao {
	
	boolean save(Notify notif);
	boolean update(String taskNo);
	ArrayList<Notify> getByUserName(String Username);
	int getCountByUsername(String Username);
	boolean updateAll(String username);
	

}
