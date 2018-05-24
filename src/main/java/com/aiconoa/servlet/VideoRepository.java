package com.aiconoa.servlet;

import org.hibernate.Session;
import org.hibernate.SessionFactory;



import java.util.List;

import org.hibernate.Query;


public class VideoRepository {

	public Video findById(long id) {
		SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
		Session session = sessionFactory.openSession();

		Video videoById = session.find(Video.class, id);

		sessionFactory.close();
		return videoById;
	}
	public List<Comment> findVideoWithComments(long idVideo) {
		SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
		Session session = sessionFactory.openSession();
		
		 String queryHQL = "SELECT c FROM Comment c JOIN FETCH c.comments WHERE v.id = :id";
	        Query<Comment> query = session.createQuery(queryHQL, Comment.class);
	        List<Comment> results = query.getResultList();
	     sessionFactory.close();   
		 return results;
	}
	public void add(Video newVideo) {
		SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
		Session session = sessionFactory.openSession();
		
		
		session.getTransaction().begin();
		session.persist(newVideo);
		sessionFactory.close();   
		
	}
}
