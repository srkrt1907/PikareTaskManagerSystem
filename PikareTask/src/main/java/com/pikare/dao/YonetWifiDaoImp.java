package com.pikare.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import com.pikare.model.Task;
import com.pikare.model.YonetWifi;

public class YonetWifiDaoImp implements YonetWifiDao {

	

	@Autowired
	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public boolean save(YonetWifi wifi) {
		Session session = null;
		Transaction txn = null;
		try {  
		    session = sessionFactory.openSession();  
		    txn = session.beginTransaction();
		    session.save(wifi); 
		    txn.commit();
		    System.out.println("başarli bir şekilde eklendi");
		    

		} catch (Exception e) { 
		    System.out.println(e.getMessage());
		    return false;
		} finally {
		    if (!txn.wasCommitted()) {
		        txn.rollback();
		    }

		    session.flush();  
		    session.close();   
		}
		return true;
	}

	@Override
	public boolean update(YonetWifi update) {
		Session session = null;
		Transaction txn = null;
		try {  
		    session = sessionFactory.openSession();  
		    txn = session.beginTransaction();
		    session.update(update); 
		    txn.commit();
		    System.out.println("başarli bir şekilde eklendi");
		    

		} catch (Exception e) { 
		    System.out.println(e.getMessage());
		    return false;
		} finally {
		    if (!txn.wasCommitted()) {
		        txn.rollback();
		    }

		    session.flush();  
		    session.close();   
		}
		return true;
	}

	@Override
	public boolean delete(YonetWifi wifi) {
		Session session = null;
		Transaction txn = null;
		try {  
		    session = sessionFactory.openSession();  
		    txn = session.beginTransaction();
		    session.delete(wifi); 
		    txn.commit();
		    System.out.println("başarli bir şekilde eklendi");
		    

		} catch (Exception e) { 
		    System.out.println(e.getMessage());
		    return false;
		} finally {
		    if (!txn.wasCommitted()) {
		        txn.rollback();
		    }

		    session.flush();  
		    session.close();   
		}
		return true;
	}

	@Override
	public List<YonetWifi> getAll() {
		List<YonetWifi> wifi = new ArrayList<YonetWifi>();
		Session session = null;
		Transaction txn = null;
		try {  
		    session = sessionFactory.openSession();  
		    txn = session.beginTransaction();
		    Query query = session.createQuery("from YonetWifi"); //You will get Weayher object
		    wifi = (List<YonetWifi>)query.list();
		    txn.commit();
		    System.out.println("ba�arli bir şekilde eklendi");

		} catch (Exception e) { 
		    System.out.println(e.getMessage());
		} finally {
		    if (!txn.wasCommitted()) {
		        txn.rollback();
		    }

		    session.flush();  
		    session.close();   
		}
		return wifi;
	}

	@Override
	public YonetWifi getByID(int id) {
		// TODO Auto-generated method stubsession.get(User.class, user_id);
		
		YonetWifi wifi = new YonetWifi();
		Session session = null;
		Transaction txn = null;
		try {  
		    session = sessionFactory.openSession();  
		    txn = session.beginTransaction();
		    wifi = (YonetWifi)session.get(YonetWifi.class, id);
		    txn.commit();
		    System.out.println("ba�arli bir şekilde eklendi");

		} catch (Exception e) { 
		    System.out.println(e.getMessage());
		} finally {
		    if (!txn.wasCommitted()) {
		        txn.rollback();
		    }
		    session.flush();  
		    session.close();   
		}
		return null;
	}

}
