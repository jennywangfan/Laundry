
package com.triplexilaundry.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.triplexilaundry.domain.company.EmployeeRole;

/**
 * <p>Title: EmployeeRoleDao</p>
 * <p>Description: </p>
 * <p>All Right Reserved</p> 
 * @author Fan Wang
 * @date Mar 13, 2015
 */
@Repository
public class EmployeeRoleDao {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	private static final Logger log = LoggerFactory.getLogger(EmployeeRoleDao.class);

	/**
	* <p>Title: findEmployeeRoldById</p>
	* <p>Description: </p>
	* @param intValue
	* @return
	*/
	public EmployeeRole findEmployeeRoldById(int id) {
		log.info("find employee rold by id "+ id);
		try{
			EmployeeRole er = entityManager.find(EmployeeRole.class, id);
			return er;
		}catch(RuntimeException re){
			log.error("fail to get employeerole by id" + id);
			throw re;
		}
	}

}
