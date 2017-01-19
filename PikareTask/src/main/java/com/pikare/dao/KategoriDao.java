package com.pikare.dao;

import java.util.List;

import com.pikare.model.Kategori;



public interface KategoriDao  {
	
	void add(Kategori kategori);
	List<Kategori> get();
	List<String> getAnaKategori();
	List<String> getEforHarf();
	
}
