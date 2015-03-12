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

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.triplexilaundry.domain.company.AccessRole;
import com.triplexilaundry.extjsdata.ComboboxModel;

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
		
		log.info("find accessrold by id");
		
		try{
		AccessRole role = entityManager.find(AccessRole.class, id);
    	if(role != null)
    	log.info("successfully find accessrole by id  " + id);
    	else
    		log.info("no role with id " + id);
    	return role;
		}catch(RuntimeException re){
			log.error("fail to find accessrole by id " + id,re);
			throw re;
		}
		
    	
    }
    
    public List<?> findByName(String roleName){
    	log.info("find accessrole by role name");
    	
    	try{
    		String sql = "select role from AccessRole role where role.authority = : paraAuth";
    		Query query = entityManager.createQuery(sql);
    		query.setParameter("paraAuth", roleName);
    		List<?> rolelist = query.getResultList();
    		if(rolelist == null || rolelist.size() ==0)
    			log.info("no roles with rolename" + roleName);
    		return rolelist;
    	}catch(RuntimeException re){
    		log.error("fail to find accessrole by name",re);
    		throw re;
    	}
    	
    }
    
    public List<ComboboxModel> getRoleListForCombo(){
    	log.info("get accessrole list for combobox");
    	try{
    		String sql = "select a.authority, a.roleId from AccessRole a";
    		Query query = entityManager.createQuery(sql);
    		@SuppressWarnings("unchecked")
			List<Object[]> roleList = query.getResultList();
    		List<ComboboxModel> extRoleList = new ArrayList<>();
    		for(Object[] o : roleList){
    			ComboboxModel cm = new ComboboxModel();
    			cm.setName(o[0] == null ? "":(String)o[0]);
    			cm.setAttribute(o[1]==null ? -1:((Integer)o[1]).intValue());
    			extRoleList.add(cm);
    		}
    		return extRoleList;
    	}catch(RuntimeException re){
    		log.error("fail to get role list for combobox");
    		throw re;
    	}
    	
    }
}
