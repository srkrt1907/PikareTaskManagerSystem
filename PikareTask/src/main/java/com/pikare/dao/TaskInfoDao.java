package com.pikare.dao;

import java.util.List;

import com.pikare.model.TaskInfo;

public interface TaskInfoDao {

	boolean save(TaskInfo task);
	boolean update(TaskInfo task);
	boolean delete(TaskInfo task);
	List<TaskInfo> getAll(String taskNo);
	TaskInfo getByID(int id);
}
