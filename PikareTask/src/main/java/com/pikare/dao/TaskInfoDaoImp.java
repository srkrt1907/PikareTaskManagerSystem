package com.pikare.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import com.pikare.model.Task;
import com.pikare.model.TaskInfo;

public class TaskInfoDaoImp implements TaskInfoDao {

	

	@Autowired
	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public boolean save(TaskInfo task) {
		Session session = null;
		Transaction txn = null;
		try {  
		    session = sessionFactory.openSession();  
		    txn = session.beginTransaction();
		    session.save(task); 
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
	public boolean update(TaskInfo task) {
		Session session = null;
		Transaction txn = null;
		try {  
		    session = sessionFactory.openSession();  
		    txn = session.beginTransaction();
		    session.update(task); 
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
	public boolean delete(TaskInfo task) {
		Session session = null;
		Transaction txn = null;
		try {  
		    session = sessionFactory.openSession();  
		    txn = session.beginTransaction();
		    session.delete(task); 
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
	public List<TaskInfo> getAll(String taskNo) {
		List<TaskInfo> task = new ArrayList<TaskInfo>();
		Session session = null;
		Transaction txn = null;
		try {  
		    session = sessionFactory.openSession();  
		    txn = session.beginTransaction();
		    Query query = session.createQuery("from TaskInfo where taskNo = '"+taskNo+"'"); //You will get Weayher object
		    task = (List<TaskInfo>)query.list();
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
		return task;
	}

	@Override
	public TaskInfo getByID(int id) {
		// TODO Auto-generated method stubsession.get(User.class, user_id);
		
		TaskInfo task = new TaskInfo();
		Session session = null;
		Transaction txn = null;
		try {  
		    session = sessionFactory.openSession();  
		    txn = session.beginTransaction();
		    task = (TaskInfo)session.get(TaskInfo.class, id);
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
