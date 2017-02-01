package com.pikare.session;

import java.util.List;

public class GenericResponse {
	
	String all;
	String open;
	String close;
	String name;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAll() {
		return all;
	}
	public void setAll(String all) {
		this.all = all;
	}
	public String getOpen() {
		return open;
	}
	public void setOpen(String open) {
		this.open = open;
	}
	public String getClose() {
		return close;
	}
	public void setClose(String close) {
		this.close = close;
	}
	List closeTask;
	List openTask;
	List assignTask;
	public List getCloseTask() {
		return closeTask;
	}
	public void setCloseTask(List closeTask) {
		this.closeTask = closeTask;
	}
	public List getOpenTask() {
		return openTask;
	}
	public void setOpenTask(List openTask) {
		this.openTask = openTask;
	}
	public List getAssignTask() {
		return assignTask;
	}
	public void setAssignTask(List assignTask) {
		this.assignTask = assignTask;
	}

}
