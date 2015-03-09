/**
* <p>Title: AccessRoleDao.java</p>
* <p>Description: </p>
* <p>Copyright: Copyright (c) 2015</p>
* <p>All Right Reserved</p>
* @author Fan Wang
* @date Feb 21, 2015
* @version 1.0
*/
package com.triplexilaundry.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.triplexilaundry.domain.company.AccessRole;

/**
 * <p>Title: AccessRoleDao</p>
 * <p>Description: </p>
 * <p>All Right Reserved</p> 
 * @author Fan Wang
 * @date Feb 21, 2015
 */
@Repository
public class AccessRoleDao {
	@PersistenceContext
	private EntityManager entityManager;
	
	private static final Logger log = LoggerFactory.getLogger(AccessRoleDao.class);

    public AccessRole findById(int id){
		
		log.debug("find accessrold by id");
		AccessRole role= null;
		try{
    	role = entityManager.find(AccessRole.class, id);
    	if(role != null)
    	log.debug("successfully find accessrole by id  " + id);
    	else
    		log.debug("no role with id " + id);
		}catch(RuntimeException re){
			log.error("fail to find accessrole by id " + id,re);
		}
		return role;
    	
    }
    
    public List<?> findByName(String roleName){
    	log.debug("find accessrole by role name");
    	List<?> rolelist = null;
    	try{
    		String sql = "select role from AccessRole role where role.authority = : paraAuth";
    		Query query = entityManager.createQuery(sql);
    		query.setParameter("paraAuth", roleName);
    		rolelist = query.getResultList();
    		if(rolelist == null || rolelist.size() ==0)
    			log.debug("no roles with rolename" + roleName);
    	}catch(RuntimeException re){
    		log.error("fail to find accessrole by name",re);
    	}
    	return rolelist;
    }
}
