package com.pikare.session;

import java.util.List;

public class GenericResponse {
	
	String all;
	String open;
	String close;
	String name;
	String pending;
	String acikEfor;
	String kapalEfor;
	

	String efor;
	
	
	public String getAcikEfor() {
		return acikEfor;
	}
	public String getEfor() {
		return efor;
	}
	public void setEfor(String efor) {
		this.efor = efor;
	}
	public void setAcikEfor(String acikEfor) {
		this.acikEfor = acikEfor;
	}
	public String getKapalEfor() {
		return kapalEfor;
	}
	public void setKapalEfor(String kapalEfor) {
		this.kapalEfor = kapalEfor;
	}

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
	public String getPending() {
		return pending;
	}
	public void setPending(String pending) {
		this.pending = pending;
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
	

}
