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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.triplexilaundry.domain.company.CompanyDepartment;
import com.triplexilaundry.extjsdata.ComboboxModel;
import com.triplexilaundry.extjsdata.DepartmentDataReturnModel;

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
	public List<DepartmentDataReturnModel> findAllDepartmentForAdmin() {
		log.info("get all departments");
		try {
			String sql = "select d.departmentId,d.depName,d.depDesc,count(e)"
					+ " from Employee e right join e.department d group by d.departmentId";
			Query query = entityManager.createQuery(sql);

			@SuppressWarnings("unchecked")
			List<Object[]> departmentList = query.getResultList();
			List<DepartmentDataReturnModel> extDepartmentList = null;
			if (departmentList != null) {
				extDepartmentList = new ArrayList<>();
				for (Object[] o : departmentList) {
					DepartmentDataReturnModel drm = new DepartmentDataReturnModel();
					drm.setDepartmentId((int) o[0]);
					drm.setDepartmentName((o[1] == null ? "" : (String) o[1]));
					drm.setDepartmentDesc((o[2] == null ? "" : (String) o[2]));
					drm.setDepartmentNum((o[3] == null ? 0 : (long) o[3]));
					extDepartmentList.add(drm);
				}
			}
			return extDepartmentList;
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
	public List<ComboboxModel> getDepartmentListForCombo() {
		log.info("get department list of combobox");
		try {
			String sql = "select d.depName,d.departmentId from CompanyDepartment d";
			Query query = entityManager.createQuery(sql);
			@SuppressWarnings("unchecked")
			List<Object[]> departmentList = query.getResultList();

			List<ComboboxModel> extDepartmentList = null;
			if (departmentList != null) {
				extDepartmentList = new ArrayList<>();
				for (Object[] o : departmentList) {
					ComboboxModel cm = new ComboboxModel();
					cm.setName(o[0] == null ? "" : (String) o[0]);
					cm.setAttribute(o[1] == null ? -1 : ((Integer) o[1]).intValue());
					extDepartmentList.add(cm);
				}
			}
			return extDepartmentList;
		} catch (RuntimeException re) {
			log.error("fail when try to load department list for combobox");
			throw re;
		}
	}

}
