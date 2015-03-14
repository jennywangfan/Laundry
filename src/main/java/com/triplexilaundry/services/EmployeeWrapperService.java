/**
* <p>Title: UserWrapperService.java</p>
* <p>Description: </p>
* <p>Copyright: Copyright (c) 2015</p>
* <p>All Right Reserved</p>
* @author Fan Wang
* @date Mar 13, 2015
* @version 1.0
*/
package com.triplexilaundry.services;

import java.security.NoSuchAlgorithmException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.triplexilaundry.dao.AccessRoleDao;
import com.triplexilaundry.dao.CompanyDepartmentDao;
import com.triplexilaundry.dao.EmployeeDao;
import com.triplexilaundry.dao.EmployeeRoleDao;
import com.triplexilaundry.domain.company.AccessRole;
import com.triplexilaundry.domain.company.CompanyDepartment;
import com.triplexilaundry.domain.company.Employee;
import com.triplexilaundry.domain.company.EmployeeRole;
import com.triplexilaundry.extjsdata.EmployeeDataCreateModel;
import com.triplexilaundry.util.PasswordEcoder;

/**
 * <p>Title: UserWrapperService</p>
 * <p>Description: </p>
 * <p>All Right Reserved</p> 
 * @author Fan Wang
 * @date Mar 13, 2015
 */
@Service("employeeWrapperService")
public class EmployeeWrapperService {
	
	@Autowired
	private CompanyDepartmentDao departmentDao;
	@Autowired
	private EmployeeDao employeeDao;
	@Autowired
	private AccessRoleDao accessRoleDao;
	@Autowired
	private EmployeeRoleDao employeeRoledao;
	

	/**
	* <p>Title: createUser</p>
	* <p>Description: </p>
	* @param data
	 * @return 
	 * @throws NoSuchAlgorithmException 
	*/
	@Transactional
	public void createUser(EmployeeDataCreateModel data) throws NoSuchAlgorithmException {
		
		Employee em = new Employee();
		if(data != null){
			em.setUsername(data.getUserName());
			em.setPassword(PasswordEcoder.encrypt(data.getPassword()));
			em.setFullName(data.getFullName());
			em.setStartDate(data.getStartDate());
			em.setCreateDate(new Date());
			String depId = data.getDepartmentId();
			String employeeRoleId = data.getEmployeeRoleId();
			String accessRoleId = data.getAccessRoleId();
			String reportManagerId = data.getReportManagerId();
			if(depId != null){
				CompanyDepartment dep = departmentDao.findDepartmentById(Integer.valueOf(depId).intValue());
				em.setDepartment(dep);
			}
			if(employeeRoleId != null){
				EmployeeRole er = employeeRoledao.findEmployeeRoldById(Integer.valueOf(employeeRoleId).intValue());
				em.setEmployeeRole(er);
			}
			if(accessRoleId != null){
				AccessRole ar = accessRoleDao.findById(Integer.valueOf(accessRoleId).intValue());
				em.setAccessRole(ar);
			}
			if(reportManagerId != null)
			{
				Employee e = employeeDao.findEmployeeByUserName(reportManagerId);
				em.setReportTo(e);
			}
			
		}
		employeeDao.persist(em);
		
	}

}
