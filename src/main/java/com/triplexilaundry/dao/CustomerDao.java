/**
* <p>Title: CustomerDao.java</p>
* <p>Description: </p>
* <p>Copyright: Copyright (c) 2015</p>
* <p>All Right Reserved</p>
* @author Fan Wang
* @date Feb 18, 2015
* @version 1.0
*/
package com.triplexilaundry.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.triplexilaundry.domain.company.Customer;

/**
 * <p>Title: CustomerDao</p>
 * <p>Description: </p>
 * <p>All Right Reserved</p> 
 * @author Fan Wang
 * @date Feb 18, 2015
 */
@Repository
public class CustomerDao {
	@PersistenceContext
	private EntityManager entityManager;
	private static final Logger log = LoggerFactory.getLogger(CustomerDao.class);
	
	public void persist(Customer customer){
		log.debug("persisting customer instance");
		try {
			entityManager.persist(customer);
			entityManager.flush();
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	
	public void merge(Customer customer) {
		log.debug("merge customer instance");
		try {
			entityManager.merge(customer);
			entityManager.flush();
			log.debug("merge successful");
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
		
	}

}
