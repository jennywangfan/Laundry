/**
* <p>Title: DepartmentDao.java</p>
* <p>Description: </p>
* <p>Copyright: Copyright (c) 2015</p>
* <p>All Right Reserved</p>
* @author Fan Wang
* @date Feb 26, 2015
* @version 1.0
*/
package com.triplexilaundry.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.JoinType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.triplexilaundry.domain.company.CompanyDepartment;
import com.triplexilaundry.extjsdata.DepartmentDataReturnModel;

/**
 * <p>Title: DepartmentDao</p>
 * <p>Description: </p>
 * <p>All Right Reserved</p> 
 * @author Fan Wang
 * @date Feb 26, 2015
 */

@Repository
public class CompanyDepartmentDao {

	@PersistenceContext
	private EntityManager entityManager;
	
	private static final Logger log = LoggerFactory.getLogger(CompanyDepartmentDao.class);
	
	public void persist(CompanyDepartment department){
	    log.debug("add department");
	    try{
	    	entityManager.persist(department);
	    	entityManager.flush();
	    }catch(RuntimeException re){
	    	log.error("fail to persist department",re);
	    }
	}
	
	public void remove(CompanyDepartment department){
		 log.debug("remove department");
		    try{
		    	entityManager.remove(department);
		    	entityManager.flush();
		    }catch(RuntimeException re){
		    	log.error("fail to remove department",re);
		    }
	}
	
	public void merge(CompanyDepartment department){
		 log.debug("merge department");
		    try{
		    	entityManager.merge(department);
		    	entityManager.flush();
		    }catch(RuntimeException re){
		    	log.error("fail to merge department",re);
		    }
	}

	/**
	* <p>Title: findAllDepartmentForAdmin</p>
	* <p>Description: </p>
	* @return
	*/
	public List<DepartmentDataReturnModel> findAllDepartmentForAdmin() {
		 log.debug("get all departments");
		    try{
		    	String sql = "select d.departmentId,d.depName,d.depDesc,count(e)"
		    			+ " from Employee e right join e.department d group by d.departmentId";
		    	Query query = entityManager.createQuery(sql);
		    	
		    	List<Object[]> departmentList = query.getResultList();
		    	List<DepartmentDataReturnModel> extDepartmentList = new ArrayList<>();
		    	for(Object[] o : departmentList){
		    		DepartmentDataReturnModel drm = new DepartmentDataReturnModel();
		    		drm.setDepartmentId((int) o[0]);
		    		drm.setDepartmentName( (o[1]==null ? "" :(String) o[1]));
		    		drm.setDepartmentDesc( (o[2]== null ? "" :(String) o[2]));
		    		drm.setDepartmentNum((o[3] == null ? 0 : (long)o[3]));
		    		extDepartmentList.add(drm);
		    	}
		    	return extDepartmentList;
		    }catch(RuntimeException re){
		    	log.error("fail to get all departments",re);
		    }
			return null;
	}

	
}
