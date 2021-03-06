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

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.triplexilaundry.domain.company.CompanyDepartment;

/**
 * <p>
 * Title: DepartmentDao
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * All Right Reserved
 * </p>
 * 
 * @author Fan Wang
 * @date Feb 26, 2015
 */

@Repository
public class CompanyDepartmentDao {

	@PersistenceContext
	private EntityManager entityManager;

	private static final Logger log = LoggerFactory
			.getLogger(CompanyDepartmentDao.class);

	public void persist(CompanyDepartment department) {
		log.info("add department");
		try {
			entityManager.persist(department);
			entityManager.flush();
		} catch (RuntimeException re) {
			log.error("fail to persist department", re);
		}
	}

	public void remove(CompanyDepartment department) {
		log.info("remove department");
		try {
			entityManager.remove(department);
			entityManager.flush();
		} catch (RuntimeException re) {
			log.error("fail to remove department", re);
		}
	}

	public void merge(CompanyDepartment department) {
		log.info("merge department");
		try {
			entityManager.merge(department);
			entityManager.flush();
		} catch (RuntimeException re) {
			log.error("fail to merge department", re);
		}
	}

	/**
	 * <p>
	 * Title: findAllDepartmentForAdmin
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @return
	 */
	public List<Object[]> findAllDepartmentForAdmin() {
		log.info("get all departments");
		try {
			String sql = "select d.departmentId,d.depName,d.depDesc,count(e)"
					+ " from Employee e right join e.department d group by d.departmentId";
			Query query = entityManager.createQuery(sql);

			@SuppressWarnings("unchecked")
			List<Object[]> departmentList = query.getResultList();
			return departmentList;
			
		} catch (RuntimeException re) {
			log.error("fail to get all departments", re);
			throw re;
		}

	}

	/**
	 * <p>
	 * Title: getDepartmentListForCombo
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @return
	 */
	public List<Object[]> getDepartmentListForCombo() {
		log.info("get department list of combobox");
		try {
			String sql = "select d.depName,d.departmentId from CompanyDepartment d";
			Query query = entityManager.createQuery(sql);
			@SuppressWarnings("unchecked")
			List<Object[]> departmentList = query.getResultList();

		    return departmentList;
		} catch (RuntimeException re) {
			log.error("fail when try to load department list for combobox",re);
			throw re;
		}
	}

	/**
	* <p>Title: findDepartmentById</p>
	* <p>Description: </p>
	* @param intValue
	* @return
	*/
	public CompanyDepartment findDepartmentById(int id) {
		// TODO Auto-generated method stub
		log.info("find department by id" + id);
		try{
			CompanyDepartment department = entityManager.find(CompanyDepartment.class, id);
			return department;
		}catch(RuntimeException re){
			log.error("fail to find department by id" + id,re);
			throw re;
		}
	}

	/**
	* <p>Title: findDepartmentByName</p>
	* <p>Description: </p>
	* @param depName
	* @return
	*/
	public boolean findDepartmentByName(String depName) {
		log.info("find department by name "+ depName);
		try{
			String sql = "select d from CompanyDepartment d where d.depName = :name";
			Query query = entityManager.createQuery(sql);
			query.setParameter("name", depName);
			int cd = query.getFirstResult();
			if(cd == 0)
				return false;
			else return true;
		}catch(RuntimeException re){
			log.error("fail department by name "+depName,re);
			throw re;
		}
	}

	/**
	* <p>Title: deleteDepById</p>
	* <p>Description: </p>
	* @param valueOf
	*/
	public void deleteDepById(int id) {
		// TODO Auto-generated method stub
		log.info("remove department by id" + id);
		try{
			
			String sql = "delete from CompanyDepartment cd where cd.departmentId = :dId";
			Query query = entityManager.createQuery(sql);
			query.setParameter("dId", id);
			query.executeUpdate();
			log.info("success to delete department by id "+id);
		}catch(RuntimeException re){
			log.error("fail to delete department by id "+id,re);
			throw re;
		}
	}

}
