
package com.triplexilaundry.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * <p>Title: EntityManagerHelper</p>
 * <p>Description: </p>
 * <p>All Right Reserved</p> 
 * @author Fan Wang
 * @date Feb 22, 2015
 */
public class EntityManagerHelper {

	private static EntityManagerFactory emf;
	private static ThreadLocal<EntityManager> threadLocal;
	static{
		emf = Persistence.createEntityManagerFactory("punit");
		threadLocal = new ThreadLocal<EntityManager>();
	}
	private EntityManagerHelper(){
		
	}
	
	public static EntityManager getEntityManager(){
		EntityManager em = threadLocal.get();
		if(em == null || !em.isOpen()){
			em = emf.createEntityManager();
			threadLocal.set(em);
		}
		return em;
	}
	
	public static void closeEntityManger(){
		EntityManager em = threadLocal.get();
		if(em != null && em.isOpen()){
			threadLocal.set(null);
			em.close();
		}
	}
	
	public static void beginTransaction(){
		getEntityManager().getTransaction().begin();
	}
	
	public static void commit(){
		getEntityManager().getTransaction().commit();
	}
}
