/**
* <p>Title: AuditOderDao.java</p>
* <p>Description: </p>
* <p>Copyright: Copyright (c) 2015</p>
* <p>All Right Reserved</p>
* @author Fan Wang
* @date Mar 31, 2015
* @version 1.0
*/
package com.triplexilaundry.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.triplexilaundry.domain.AuditOrderForTracking;

/**
 * <p>Title: AuditOderDao</p>
 * <p>Description: </p>
 * <p>All Right Reserved</p> 
 * @author Fan Wang
 * @date Mar 31, 2015
 */
@Repository
public class AuditOrderDao {

	@PersistenceContext
	private EntityManager entityManager;
	
	private static final Logger log = LoggerFactory.getLogger(AuditOrderDao.class);
	/**
	* <p>Title: persist</p>
	* <p>Description: </p>
	* @param auditOrder
	*/
	public void persist(AuditOrderForTracking auditOrder) {
		log.info("save an auditorder for history tracking");
		try{
			entityManager.persist(auditOrder);
		}catch(RuntimeException re){
			log.error("fail to save an auditorder",re);
			throw re;
		}
	}

}
