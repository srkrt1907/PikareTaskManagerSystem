package com.pikare.dao.login;



import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pikare.model.Users;

public class LoginDaoImpl implements LoginDao{
	
	@Autowired
	SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	Session session = null;
	Transaction tx = null;

	@Override
	public Users findByUserName(String username) {
		session = sessionFactory.openSession();
		tx = session.getTransaction();
		session.beginTransaction();
		Query query= session.createQuery("from Users where username=:name");
		query.setParameter("name", username);
		Users user = (Users) query.uniqueResult();
		tx.commit();
		return user;
	}

}
