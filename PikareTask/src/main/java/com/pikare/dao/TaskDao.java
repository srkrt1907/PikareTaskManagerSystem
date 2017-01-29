package com.pikare.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.pikare.model.FilterClass;
import com.pikare.model.Task;

public interface TaskDao{
	
	public void addTask(Task task);
	public void updateTask(Task task);
	public List<Task> listTasks();
	public Task getTaskById(String id);
	public List<Task> getTaskByUser(String user);
	public void removePerson(int id);
	public List<FilterClass> getCountClose(String user, String kategori);
	public List<String> getWeek();
	public List<Task> getByWeek(int hafta,int yil , String user , String kategori,String anakategori,String status , String ilktarik,String sontarih);
	public List Filtrele(String kisi);
	public List getAllOpenTask();
	public List getOpenTask(String kisi , int hafta, int yil, String ilkTarih , String SonTarih , String kategori);
	public List getClosedTask(String kisi , int hafta, int yil, String ilkTarih , String SonTarih , String kategori);
}
