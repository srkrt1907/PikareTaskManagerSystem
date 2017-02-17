package com.pikare.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="yonetWifi")
public class YonetWifi implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	
	String name;
	String sunucu1;
	String sunucu2;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSunucu1() {
		return sunucu1;
	}
	public void setSunucu1(String sunucu1) {
		this.sunucu1 = sunucu1;
	}
	public String getSunucu2() {
		return sunucu2;
	}
	public void setSunucu2(String sunucu2) {
		this.sunucu2 = sunucu2;
	}
	public String getWebServis() {
		return webServis;
	}
	public void setWebServis(String webServis) {
		this.webServis = webServis;
	}
	String webServis;
	

}
