package com.pikare.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="kategoriefor")
public class Kategori {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	
	@Column(name="ana_Kategori")
	String anaKategori;
	
	@Column(name="kategori" , unique=true)
	String kategori;
	
	@Column(name="efor_harf")
	String eforHarf;
	
	@Column(name="efor_deger")
	int efordeger;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAnaKategori() {
		return anaKategori;
	}

	public void setAnaKategori(String anaKategori) {
		this.anaKategori = anaKategori;
	}

	public String getKategori() {
		return kategori;
	}

	public void setKategori(String kategori) {
		this.kategori = kategori;
	}

	public String getEforHarf() {
		return eforHarf;
	}

	public void setEforHarf(String eforHarf) {
		this.eforHarf = eforHarf;
	}

	public int getEfordeger() {
		return efordeger;
	}

	public void setEfordeger(int efordeger) {
		this.efordeger = efordeger;
	}
	
	
	
	
}
