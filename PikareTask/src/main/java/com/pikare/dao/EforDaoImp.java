package com.pikare.dao;


import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import com.pikare.model.Efor;
import com.pikare.model.Kategori;


public class EforDaoImp implements EforDao {
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Autowired
	SessionFactory sessionFactory;



	@Override
	public List<Efor> get() {
		List<Efor> efor = new ArrayList<Efor>();
		Session session = null;
		Transaction txn = null;
		try {  
		    session = sessionFactory.openSession();  
		    txn = session.beginTransaction();
		    Query query = session.createQuery("from Efor"); //You will get Weayher object
		    efor = (List<Efor>)query.list();
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
		return efor;
	}

	@Override
	public void add(Efor efor) {
		Session session = null;
		Transaction txn = null;
		try {  
		    session = sessionFactory.openSession();  
		    txn = session.beginTransaction();
		    session.save(efor); 
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
	



}
