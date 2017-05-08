package com.pikare.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import com.pikare.model.Notify;
import com.pikare.model.Task;

public class NotifyDaoImp implements NotifyDao {
	
	@Autowired
	SessionFactory sessionFactory;
	
	
	
	public boolean save(Notify notif) {
		Session session = null;
		Transaction txn = null;
		try {  
		    session = sessionFactory.openSession();  
		    txn = session.beginTransaction();
		    session.saveOrUpdate(notif);
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

	
	public boolean update(String taskNo) {
		Session session = null;
		Transaction txn = null;
		try {  
		    session = sessionFactory.openSession();  
		    txn = session.beginTransaction();
		    Query query = session.createQuery("Update Notify set okundu = 1 where taskNO = '"+taskNo+"'"); //You will get Weayher object
		    System.out.println();
		    
		    int result = query.executeUpdate();
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
	public boolean updateAll(String username) {
		Session session = null;
		Transaction txn = null;
		try {  
		    session = sessionFactory.openSession();  
		    txn = session.beginTransaction();
		    Query query = session.createQuery("Update Notify set okundu = 1 where username = '"+username+"'" ); //You will get Weayher object
		    System.out.println();
		    
		    int result = query.executeUpdate();
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
	
	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Notify> getByUserName(String Username)
	{
		ArrayList<Notify> notif = new ArrayList<Notify>();
		Session session = null;
		Transaction txn = null;
		try {  
			session = sessionFactory.openSession();  
		    txn = session.beginTransaction();
		    Query query = session.createQuery("from Notify where username ='"+Username+"' and okundu = '0' "); //You will get Weayher object
		    System.out.println();
		    
		    notif = (ArrayList<Notify>)query.list();
		    query.list().size();
		    txn.commit();
		    System.out.println("başarili bir şekilde eklendi");


		} catch (Exception e) { 
		    System.out.println(e.getMessage());
		} finally {
		    if (!txn.wasCommitted()) {
		        txn.rollback();
		    }

		    session.flush();  
		    session.close();   
		}
		return notif;
	}
	
	@Override
	public int getCountByUsername(String Username)
	{
		List list = null;
		Session session = null;
		Transaction txn = null;
	    int sayi = 0;
		try {  
			session = sessionFactory.openSession();  
		    txn = session.beginTransaction();
		    Query query = session.createQuery("select count(*) from Notify where username ='"+Username+"' and okundu = '0' "); //You will get Weayher object
		    System.out.println();
		    
		    list = query.list();

		    for(int i = 0 ; i< list.size() ; i++)
		    {
		    	Object[] o = (Object[])list.get(i);
		    	sayi= Integer.parseInt(o[0].toString());	    	
		    }	    

		    txn.commit();
		    System.out.println("başarili bir şekilde eklendi");


		} catch (Exception e) { 
		    System.out.println(e.getMessage());
		} finally {
		    if (!txn.wasCommitted()) {
		        txn.rollback();
		    }

		    session.flush();  
		    session.close();   
		}
		return sayi;

	}


	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}


	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

}
