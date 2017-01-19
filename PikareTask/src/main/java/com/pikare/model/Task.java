package com.pikare.model;

import java.sql.Date;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Task")
public class Task {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@Column(name = "taskNo",unique=true)
	String taskNo;
	
	String taskName;
	String taskSahibi;
	String isTanimi;
	String acil;
	String status;
	Date openWeek = new Date(Calendar.getInstance().getTime().getTime());
	Date closeWeek = new Date(Calendar.getInstance().getTime().getTime());
	String talepSahibi;
	String yonetici;
	String priority;
	
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	String kategori;
	public String getKategori() {
		return kategori;
	}
	public void setKategori(String kategori) {
		this.kategori = kategori;
	}
	public String getTaskNo() {
		return taskNo;
	}
	public void setTaskNo(String taskNo) {
		this.taskNo = taskNo;
	}
	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	public String getTaskSahibi() {
		return taskSahibi;
	}
	public void setTaskSahibi(String taskSahibi) {
		this.taskSahibi = taskSahibi;
	}
	public String getIsTanimi() {
		return isTanimi;
	}
	public void setIsTanimi(String isTanimi) {
		this.isTanimi = isTanimi;
	}
	public String getAcil() {
		return acil;
	}
	public void setAcil(String acil) {
		this.acil = acil;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getOpenWeek() {
		return openWeek;
	}
	public void setOpenWeek(Date openWeek) {
		this.openWeek = openWeek;
	}
	public Date getCloseWeek() {
		return closeWeek;
	}
	public void setCloseWeek(Date closeWeek) {
		this.closeWeek = closeWeek;
	}
	public String getTalepSahibi() {
		return talepSahibi;
	}
	public void setTalepSahibi(String talepSahibi) {
		this.talepSahibi = talepSahibi;
	}
	public String getYonetici() {
		return yonetici;
	}
	public void setYonetici(String yonetici) {
		this.yonetici = yonetici;
	}

}
