package com.pikare.dao;

import java.util.List;

import com.pikare.model.YonetWifi;

public interface YonetWifiDao {

	boolean save(YonetWifi wifi);
	boolean update(YonetWifi update);
	boolean delete(YonetWifi wifi);
	List<YonetWifi> getAll();
	YonetWifi getByID(int id);
}
