package com.pikare.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;







import com.pikare.model.Task;
import com.pikare.model.UserRole;
import com.pikare.model.Users;

public class UserDaoImp implements UserDao {
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Autowired
	SessionFactory sessionFactory;
	

	@Override
	public void register(Users user) {
		// TODO Auto-generated method stub
		Session session = null;
		Transaction txn = null;
		try {  
		    session = sessionFactory.openSession();  
		    txn = session.beginTransaction();
		    session.saveOrUpdate(user);
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

	@Override
	public void update(Users user) {
		// TODO Auto-generated method stub
		Session session = null;
		Transaction txn = null;
		try {  
		    session = sessionFactory.openSession();  
		    txn = session.beginTransaction();
		    Query query = session.createQuery(" delete from UserRole where username ='"+user.getUsername()+"'" );
		    query.executeUpdate();
		    txn.commit();
		    txn = session.beginTransaction();	    
		    session.update(user); 
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

	@Override
	public void changePassword(String username, String pass) {
		// TODO Auto-generated method stub
		List<Users> users = new ArrayList<Users>();
		Session session = null;
		Transaction txn = null;
		try {  
		    session = sessionFactory.openSession();  
		    txn = session.beginTransaction();

		    
		String hql = "UPDATE Users set password = :pass "  + 
		             "WHERE username = :username";
		Query query = session.createQuery(hql);
		query.setParameter("pass", pass);
		query.setParameter("username", username);
		int result = query.executeUpdate();
		txn.commit();
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

	@Override
	public List<Users> getAllUser() {
		// TODO Auto-generated method stub
		List<Users> users = new ArrayList<Users>();
		Session session = null;
		Transaction txn = null;
		try {  
		    session = sessionFactory.openSession();  
		    txn = session.beginTransaction();
		    Query query = session.createQuery("from Users"); //You will get Weayher object
		    users = (List<Users>)query.list();
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
		return users;
	}

	@Override
	public Users getUser(String username) {
		// List<Users> users = null;
		Users users = new Users();
		Session session = null;
		Transaction txn = null;
		try {  
		    session = sessionFactory.openSession();  
		    txn = session.beginTransaction();
		    Query query = session.createQuery("from Users where username = '"+username+"'"); //You will get Weayher object
		    users = (Users)query.list();
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
		return users;
	}
	
	@Override
	public List<UserRole> getUserRole(String username) {
		// List<Users> users = null;
		List<UserRole> users = new ArrayList<UserRole>();
		Session session = null;
		Transaction txn = null;
		try {  
		    session = sessionFactory.openSession();  
		    txn = session.beginTransaction();
		    Query query = session.createQuery("from UserRole where username = '"+username+"'"); //You will get Weayher object
		    users = (List<UserRole>)query.list();
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
		return users;
	}

	@Override
	public void addUserRole(UserRole userRole) {
		Session session = null;
		Transaction txn = null;
		try {  
		    session = sessionFactory.openSession();  
		    txn = session.beginTransaction();
		    session.save(userRole); 
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

	@Override
	public Users getUserByUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Users> getByUserRoles() {
		List<Users> users = new ArrayList<Users>();
		Session session = null;
		Transaction txn = null;
		try {  
		    session = sessionFactory.openSession();  
		    txn = session.beginTransaction();
		    Query query = session.createQuery(" from Users as item join item.userRole as roles where roles.role= 'ROLE_USER'"); //You will get Weayher object
		    users = (List<Users>)query.list();
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
		return users;
	}



}
