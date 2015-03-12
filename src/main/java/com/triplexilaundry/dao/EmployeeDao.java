/**
* <p>Title: EmployeeDao.java</p>
* <p>Description: </p>
* <p>Copyright: Copyright (c) 2015</p>
* <p>All Right Reserved</p>
* @author Fan Wang
* @date Feb 21, 2015
* @version 1.0
*/
package com.triplexilaundry.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.triplexilaundry.domain.company.CompanyDepartment;
import com.triplexilaundry.domain.company.Employee;
import com.triplexilaundry.domain.company.EmployeeRole;
import com.triplexilaundry.extjsdata.EmployeeDataReturnModel;

/**
 * <p>Title: EmployeeDao</p>
 * <p>Description: </p>
 * <p>All Right Reserved</p> 
 * @author Fan Wang
 * @date Feb 21, 2015
 */
@Repository
public class EmployeeDao {
	
	
	
	@PersistenceContext
	private EntityManager entityManager;
	
	private static final Logger log = LoggerFactory.getLogger(EmployeeDao.class);
	
	
	
	public Employee findbyIdForAuthentication( String id){
		
		log.info("find employee by id");
		try{
		
			Employee em = entityManager.find(Employee.class, id);
			if(em != null){
				//get accessRole for authentication
				em.getAccessRole();
				log.info("success to find employee by id " + id);
				
			}	   
			else
			log.info("can't find employee by id " + id);
			return em;
		}catch(RuntimeException re){
			log.error("fail to find employee by id " + id,re);
			throw re;
			
		}
		
	}
	
	public void persist(Employee employee){
		log.info("persist employee");
		try{
			entityManager.persist(employee);
			entityManager.flush();
		}catch(RuntimeException re){
			log.error("fail to persist employee",re);
			throw re;
		}
	}
	
	public void remove(Employee employee){
		log.info("remove employee");
		try{
			entityManager.remove(employee);
			entityManager.flush();
		}catch(RuntimeException re){
			log.error("fail to remove employee", re);
			throw re;
		}
	}
	
	public void merge(Employee employee){
		log.info("merge employee");
		try{
			entityManager.merge(employee);
			entityManager.flush();
		}catch(RuntimeException re){
			log.error("fail to merge employee", re);
			throw re;
		}
	}
	
	public List<?> findByDepartmentId(int depId){
		log.info("find employee by department id");
		try{
			String sql = "select e from Employee e where"
					+ " e.CompanyDepartment.departmentId = : paraDepID";
			Query query = entityManager.createQuery(sql);
			query.setParameter("paraDepID", depId);
			List<?> userList = query.getResultList();
			return userList;
		}catch(RuntimeException re){
			log.error("fail to find employee by department id", re);
			throw re;
		}
		
	}

	/**
	* <p>Title: findAllEmployeesForAdmin</p>
	* <p>Description: </p>
	* @return
	*/
	public List<EmployeeDataReturnModel> findAllEmployeesForAdmin() {
		log.info("find all employee for admin page");
		try{
		String sql = "select e.username,e.department,e.employeeRole,"
				+ "e.reportTo,e.fullName,e.createDate from Employee e";
		Query query = entityManager.createQuery(sql);
		@SuppressWarnings("unchecked")
		List<Object[]> employeeList = query.getResultList();
		
		List<EmployeeDataReturnModel> extEmployeeList = new ArrayList<>();
		for(Object[] o : employeeList){
			EmployeeDataReturnModel em = new EmployeeDataReturnModel();
			em.setUserName((String) o[0]);
			em.setDepartment(o[1]==null? "":((CompanyDepartment)(o[1])).getDepName());
			em.setRole(o[2]==null? "":((EmployeeRole)(o[2])).getRoleName());
			em.setManager(o[3]==null? "":((Employee)(o[3])).getFullName());
			em.setFullName(o[4]==null? "":(String)o[4]);
			em.setCreatedDate(o[2]==null? null:(Date) o[5]);
			extEmployeeList.add(em);
		}
		return extEmployeeList;
		}catch(RuntimeException re){
			log.error("fail to find all employees for admin page");
			throw re;
		}
	}
	
	

	

}
