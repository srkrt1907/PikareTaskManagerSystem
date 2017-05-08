package com.pikare.session;

import java.io.Serializable;
import java.util.ArrayList;

import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.pikare.model.Notify;

@Component
@Scope(value="session", proxyMode=ScopedProxyMode.TARGET_CLASS)
public class PikareSession implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String username ="";
	String name ="";
	String surname ="";
	String role ="";
	int copy_Id = 0;
	
	int notifCount = 0;
	public int getNotifCount() {
		return notifCount;
	}
	public void setNotifCount(int notifCount) {
		this.notifCount = notifCount;
	}
	public ArrayList<Notify> getSessionNotif() {
		return sessionNotif;
	}
	public void setSessionNotif(ArrayList<Notify> sessionNotif) {
		this.sessionNotif = sessionNotif;
	}
	ArrayList<Notify> sessionNotif = new ArrayList<Notify>();
	
	public int getCopy_Id() {
		return copy_Id;
	}
	public void setCopy_Id(int copy_Id) {
		this.copy_Id = copy_Id;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}

}
