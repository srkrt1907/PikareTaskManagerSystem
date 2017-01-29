package com.pikare.dao;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.bind.ParseConversionEvent;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.pikare.model.FilterClass;
import com.pikare.model.Task;
import com.pikare.model.Users;

public class TaskDaoImp implements TaskDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	
	
	@Override
	public void addTask(Task task) {
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
		} finally {
		    if (!txn.wasCommitted()) {
		        txn.rollback();
		    }

		    session.flush();  
		    session.close();   
		}
		

	}

	@Override
	public void updateTask(Task task) {
		// TODO Auto-generated method stub
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
	public List<Task> listTasks() {
		
		List<Task> task = new ArrayList<Task>();
		Session session = null;
		Transaction txn = null;
		try {  
		    session = sessionFactory.openSession();  
		    txn = session.beginTransaction();
		    Query query = session.createQuery("from Task"); //You will get Weayher object
		    task = (List<Task>)query.list();
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
		return task;
	}

	@Override
	public Task getTaskById(String id) {
		// TODO Auto-generated method stub
		Task task = new Task();
		Session session = null;
		try {  
		    session = sessionFactory.openSession();  
		    Query query= session.createQuery("from Task where taskNo=:task");
			query.setParameter("task", id);
			task = (Task) query.uniqueResult();
			
		    task =  (Task) session.get(Task.class, id);
		    System.out.println("başarili bir Şekilde eklendi");

		} catch (Exception e) { 
		    System.out.println(e.getMessage());
		}
		    session.flush();  
		    session.close();   
		
		 return task;
	}

	@Override
	public void removePerson(int id) {
		// TODO Auto-generated method stub

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Task> getTaskByUser(String user) {
		List<Task> task = new ArrayList<Task>();
		Session session = null;
		Transaction txn = null;
		try {  
		    session = sessionFactory.openSession();  
		    txn = session.beginTransaction();
		    Query query = session.createQuery("from Task where taskSahibi ='"+user+"'"); //You will get Weayher object
		    task = (List<Task>)query.list();
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
		return task;
	}

	@Override
	public List<FilterClass> getCountClose(String user, String kategori) {
		// TODO Auto-generated method stub
		Session session = null;
		Transaction txn = null;
		List<FilterClass> list = null;
		try {  
		    session = sessionFactory.openSession();  
		    txn = session.beginTransaction();
		    Query query = null;
		    String quer = "select count(*) , WEEKOFYEAR(closeWeek) as hafta , YEAR(NOW()) as dt ,'kapananlar' as kapananlar    from Task WHERE status = 'CLOSED' and openWeek IS NOT NULL ";
		    if(!user.isEmpty())
		    	quer +=" AND taskSahibi='"+user+"' ";
		    if(!kategori.isEmpty())
		    	quer +=" AND kategori='"+kategori+"' ";
		    
		    quer += " group by WEEKOFYEAR(closeWeek)";
//		    query = session.createQuery(quer);
//		    query.setMaxResults(10);
//		    result = query.list();
		    
		    
		    Iterator kittensAndMothers = session.createQuery(
		            quer)
		            .list()
		            .iterator();
		    
		    list = new ArrayList<FilterClass>();
		    while ( kittensAndMothers.hasNext() ) {
		        Object[] sonuc = (Object[]) kittensAndMothers.next();
		        FilterClass temp= new FilterClass();
		        Long sayi = (Long) sonuc[0];
		       // int hafta = (int) sonuc[1];
		       // int yil = (int) sonuc[2];
		        String ad = (String)sonuc[3];
		        
		       // String closeWeek = Integer.toString(yil) +"-"+Integer.toString(hafta);
 		        temp.setCount(new BigDecimal(sayi).intValueExact());
		        temp.setCloseWeek("");
		        temp.setUser(ad);
		        
		        list.add(temp);
		    }
		    
		    if(!user.isEmpty())
		    	return list;
		    
		    quer = "select  count(*) , WEEKOFYEAR(openWeek) as hafta , YEAR(NOW()) as dt ,'bekleyenler' as bekleyenler   from Task WHERE status = 'WAITING' "; //You will get Weayher object
		    if(!kategori.isEmpty())
		    	quer += " and kategori='" +kategori +"' ";
		    			    	
		   quer += " and openWeek IS NOT NULL group by WEEKOFYEAR(openWeek)";
		   kittensAndMothers = session.createQuery(
		            quer)
		            .list()
		            .iterator();
		    while ( kittensAndMothers.hasNext() ) {
		        Object[] sonuc = (Object[]) kittensAndMothers.next();
		        FilterClass temp= new FilterClass();
		        Long sayi = (Long) sonuc[0];
		       // int hafta = (int) sonuc[1];
		       // int yil = (int) sonuc[2];
		        String ad = (String)sonuc[3];
		        
		        //String closeWeek = Integer.toString(yil) +"-"+Integer.toString(hafta);
 		        temp.setCount(new BigDecimal(sayi).intValueExact());
		        temp.setCloseWeek("");
		        temp.setUser(ad); 
		        list.add(temp);
		    }
		    
		    txn.commit();
		    System.out.println("başarili bir şekilde eklendi");

		} catch (Exception e) { 
			System.out.println(e);
		    System.out.println(e.getMessage());
		} finally {
		    if (!txn.wasCommitted()) {
		        txn.rollback();
		    }

		    session.flush();  
		    session.close();   
		}
		return list;
	}

	@Override
	public List<String> getWeek() {
		List<String> week = new ArrayList<String>();
		List result = null;
		Session session = null;
		Transaction txn = null;
		try {  
		    session = sessionFactory.openSession();  
		    txn = session.beginTransaction();
		    Query query = session.createQuery(" SELECT DISTINCT YEAR(openWeek), WEEKOFYEAR(openWeek)  from Task where openWeek is not null order by openWeek" ); //You will get Weayher object
		    result = query.list();
		    
		    for(int i = 0 ; i< result.size() ; i++)
		    {
		    	Object[] o = (Object[])result.get(i);
		    	//int tarih = (int)o[0];
		    	//int hafta = (int)o[1];
		    	int tarih= Integer.parseInt(o[0].toString());
		    	int hafta= Integer.parseInt(o[1].toString());
		    	
		    	week.add(Integer.toString(tarih) + "-" + Integer.toString(hafta));
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
		return week;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Task> getByWeek(int hafta, int yil, String user,
			String kategori,String anakategori,String status,String ilktarih,String sontarih) {
		List<Task> task = new ArrayList<Task>();
		Session session = null;
		Transaction txn = null;
		try {  
		    session = sessionFactory.openSession();  
		    txn = session.beginTransaction();
		    
		    String query  = " from Task  where ";
		    String week = "";
		    if(status.equals("CLOSED") || status.isEmpty())
		    {
		    	query += "status = " + "'CLOSED' ";
		    	week = "closeWeek";
		    }
		    else if(status.equals("OPEN"))
		    {
		    	query += "status = '" + status + "' ";
		    	week = "openWeek";
		    }
		    else if(status.equals("WAITING"))
		    {
		    	query += "status = '" + status + "' ";
		    	week = "openWeek";
		    }
		    
		    
		    if(hafta > 0)
		    	query += " AND WEEKOFYEAR("+week+") = " + hafta + " ";
		    if(yil > 0) 
		    	query += " AND YEAR("+week+" ) = '" +yil+"' ";
		    if(!user.isEmpty())
		    	query += " AND taskSahibi = '"+user+"' ";
		    if(!anakategori.isEmpty())
		    	query += " AND kategori IN (select kategori from Kategori where anaKategori = '"+anakategori+"') ";
		    
		    if(!ilktarih.isEmpty())
		    	query += " AND assigmnetDate >= '" + ilktarih+ "' ";
	    
		    if(!sontarih.isEmpty())
		    	query += " AND assigmnetDate <= '" + sontarih+ "' ";
		  
		    Query queryList = session.createQuery(query); //You will get Weayher object
		    task = (List<Task>)queryList.list();
		    
		   
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
		return task;
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List Filtrele(String kisi) {
		List list = null;
		Session session = null;
		Transaction txn = null;
		try {  
		    session = sessionFactory.openSession();  
		    txn = session.beginTransaction();
		    String que = "SELECT kategori,taskSahibi,COUNT(*) FROM Task where taskSahibi='"+ kisi +"' and kategori IS NOT NULL group by kategori , taskSahibi ORDER BY taskSahibi ";
		    Query query = session.createQuery(que); //You will get Weayher object
		    list = (List<Task>)query.list();
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
		return list;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List getClosedTask(String kisi , int hafta, int yil, String ilkTarih , String SonTarih , String kategori) {
		
		List list = new ArrayList<String>();
		Session session = null;
		Transaction txn = null;
		try {  
		    session = sessionFactory.openSession();  
		    txn = session.beginTransaction();
		    
		  //  String sql = "SELECT distinct Task.taskSahibi , ct FROM  Task LEFT OUTER JOIN ( SELECT taskSahibi, COUNT(*) as ct FROM Task where status='CLOSED' ";      		
		  //  String groupBy = " GROUP BY taskSahibi ) as CountQuery ON Task.taskSahibi = CountQuery.taskSahibi order by Task.taskSahibi ";
		    
		    
		    String sql = "select taskSahibi , count(*) from Task where status = 'CLOSED' ";
		    String groupBy = " group by taskSahibi";
		    
		    if(!kisi.isEmpty())
		    	sql += " AND taskSahibi = '" + kisi+ "' ";
		    if(hafta > 0)
		    	sql += " AND WEEKOFYEAR(closeWeek) = " + hafta + " ";
		    if(yil > 0) 
		    	sql += " AND YEAR(closeWeek) = '" +yil+"' ";
		    	
		    if(!ilkTarih.isEmpty())
		    		sql += " AND closeWeek >= '" + ilkTarih+ "' ";
		    
		    if(!SonTarih.isEmpty())
	    		sql += " AND closeWeek <= '" + SonTarih+ "' ";
		   
		    if(!kategori.isEmpty())
	    		sql += " AND closeWeek >= '" + ilkTarih+ "' ";
	    
		    sql += groupBy;
		    
		    Query query = session.createQuery(sql); //You will get Weayher object
		    list = query.list();
		    query.list().size();
		    txn.commit();
		    System.out.println("başarili bir şekilde eklendi");

		} catch (Exception e) { 
		    System.out.println(e.getMessage());
		    return null;
		} finally {
		    if (!txn.wasCommitted()) {
		        txn.rollback();
		    }
    
		    session.flush();  
		    session.close();   
		}
		return list;
		
	}
	
	@Override
	public List getOpenTask(String kisi , int hafta, int yil, String ilkTarih , String SonTarih , String kategori) {//ALDİGİ TASKLAR
		
		List list = null;
		Session session = null;
		Transaction txn = null;
		try {  
		    session = sessionFactory.openSession();  
		    txn = session.beginTransaction();
		    
		    //String sql = "SELECT distinct Task.taskSahibi,  COALESCE(ct,0) FROM  Task LEFT JOIN(SELECT Task.taskSahibi, COUNT(*) as ct FROM Task where status='OPEN' ";      		
		    //String groupBy = "GROUP BY Task.taskSahibi ) as CountQuery ON Task.taskSahibi = CountQuery.taskSahibi order by Task.taskSahibi ";
		    
		    String sql = "select taskSahibi , count(*) from Task where assigmnetDate is not null ";
		    String groupBy = " group by taskSahibi";
		    
		    if(!kisi.isEmpty())
		    	sql += " AND taskSahibi = '" + kisi+ "' ";
		    if(hafta > 0)
		    	sql += " AND WEEKOFYEAR(assigmnetDate) = " + hafta + " ";
		    if(yil > 0) 
		    	sql += " AND YEAR(assigmnetDate) = '" +yil+"' ";
		    	
		    if(!ilkTarih.isEmpty())
		    		sql += " AND assigmnetDate >= '" + ilkTarih+ "' ";
		    
		    if(!SonTarih.isEmpty())
	    		sql += " AND assigmnetDate <= '" + SonTarih+ "' ";
		   
		    if(!kategori.isEmpty())
	    		sql += " AND assigmnetDate >= '" + ilkTarih+ "' ";
	    
		    sql += groupBy;
		    
		    Query query = session.createQuery(sql); //You will get Weayher object
		    
		    list = query.list();
		    query.list().size();
		    txn.commit();
		    System.out.println("başarili bir şekilde eklendi");

		} catch (Exception e) { 
		    System.out.println(e.getMessage());
		    return null;
		} finally {
		    if (!txn.wasCommitted()) {
		        txn.rollback();
		    }

		    session.flush();  
		    session.close();   
		}
		return list;
		
	}
	@Override
public List getAllOpenTask() {//ALDİGİ TASKLAR
	
	List list = null;
	Session session = null;
	Transaction txn = null;
	try {  
	    session = sessionFactory.openSession();  
	    txn = session.beginTransaction();
	    
	    //String sql = "SELECT distinct Task.taskSahibi,  COALESCE(ct,0) FROM  Task LEFT JOIN(SELECT Task.taskSahibi, COUNT(*) as ct FROM Task where status='CLOSED' ";      		
	    //String groupBy = "GROUP BY Task.taskSahibi ) as CountQuery ON Task.taskSahibi = CountQuery.taskSahibi order by Task.taskSahibi ";
	       
	    String sql = "select taskSahibi , count(*) from Task where status = 'OPEN' ";
	    String groupBy = " group by taskSahibi";
	    sql += groupBy;
	    
	    Query query = session.createQuery(sql); //You will get Weayher object
	    
	    list = query.list();
	    query.list().size();
	    txn.commit();
	    System.out.println("başarili bir şekilde eklendi");

	} catch (Exception e) { 
	    System.out.println(e.getMessage());
	    return null;
	} finally {
	    if (!txn.wasCommitted()) {
	        txn.rollback();
	    }
	    session.flush();  
	    session.close();   
	}
	return list;
	
}
	

}
