package com.pikare.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import com.pikare.model.Kategori;


public class KategoriDaoImp implements KategoriDao {
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Autowired
	SessionFactory sessionFactory;

	@Override
	public void add(Kategori kategori) {
		Session session = null;
		Transaction txn = null;
		try {  
		    session = sessionFactory.openSession();  
		    txn = session.beginTransaction();
		    session.save(kategori); 
		    txn.commit();
		    System.out.println("başarli bir şekilde eklendi");

		} catch (Exception e) { 
		    System.out.println(e.getMessage());
		} finally {
		    if (!txn.wasCommitted()) {
		        txn.rollback();
		    }

		    session.flush();  
		    session.close();   
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Kategori> get() {
		List<Kategori> kategori = new ArrayList<>();
		Session session = null;
		Transaction txn = null;
		try {  
		    session = sessionFactory.openSession();  
		    txn = session.beginTransaction();
		    Query query = session.createQuery("from Kategori"); //You will get Weayher object
		    kategori = (List<Kategori>)query.list();
		    txn.commit();
		    System.out.println("ba�arli bir �ekilde eklendi");

		} catch (Exception e) { 
		    System.out.println(e.getMessage());
		} finally {
		    if (!txn.wasCommitted()) {
		        txn.rollback();
		    }

		    session.flush();  
		    session.close();   
		}
		return kategori;
	}

	@Override
	public List<String> getAnaKategori() {
		Session session = null;
		Transaction txn = null;
		List<String> ana = new ArrayList<String>();
		List list;
		try {  
		    session = sessionFactory.openSession();  
		    txn = session.beginTransaction();
		    Query query = session.createQuery("SELECT DISTINCT anaKategori from Kategori"); //You will get Weayher object
		    list = query.list();
		    txn.commit();
		    System.out.println("ba�arli bir �ekilde eklendi");
		    
		    for(int i = 0 ; i< list.size() ; i++)
		    {
		    	ana.add((String) list.get(i));
		    }

		} catch (Exception e) { 
		    System.out.println(e.getMessage());
		} finally {
		    if (!txn.wasCommitted()) {
		        txn.rollback();
		    }

		    session.flush();  
		    session.close();   
		}
		return ana;
	}

	@Override
	public List<String> getEforHarf() {
		Session session = null;
		Transaction txn = null;
		List<String> eforharf = new ArrayList<String>();
		List list;
		try {  
		    session = sessionFactory.openSession();  
		    txn = session.beginTransaction();
		    Query query = session.createQuery("SELECT DISTINCT eforHarf from Kategori order by eforHarf"); //You will get Weayher object
		    list = query.list();
		    txn.commit();
		    System.out.println("başarli bir �ekilde eklendi");
		    
		    for(int i = 0 ; i< list.size() ; i++)
		    {
		    	eforharf.add((String) list.get(i));
		    }

		} catch (Exception e) { 
		    System.out.println(e.getMessage());
		} finally {
		    if (!txn.wasCommitted()) {
		        txn.rollback();
		    }

		    session.flush();  
		    session.close();   
		}
		return eforharf;
	}
	



}
