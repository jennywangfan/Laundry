/**
* <p>Title: AdminService.java</p>
* <p>Description: </p>
* <p>Copyright: Copyright (c) 2015</p>
* <p>All Right Reserved</p>
* @author Fan Wang
* @date Feb 26, 2015
* @version 1.0
*/
package com.triplexilaundry.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.triplexilaundry.dao.CompanyDepartmentDao;
import com.triplexilaundry.dao.EmployeeDao;
import com.triplexilaundry.domain.company.CompanyDepartment;
import com.triplexilaundry.domain.company.Employee;
import com.triplexilaundry.extjsdata.DepartmentDataReturnModel;
import com.triplexilaundry.extjsdata.EmployeeDataReturnModel;

/**
 * <p>Title: AdminService</p>
 * <p>Description: </p>
 * <p>All Right Reserved</p> 
 * @author Fan Wang
 * @date Feb 26, 2015
 */

@Service("adminService")
public class AdminService {
	
	@Autowired
	private EmployeeDao employeeDao;
	
	@Autowired
	private CompanyDepartmentDao departmentDao;
	
	@Transactional
	public void addAnEmployee(Employee employee){
		employeeDao.persist(employee);
		
		
	}
	@Transactional
	public void modifyEmployee(Employee employee){
		employeeDao.merge(employee);
	}
	
	@Transactional
	public void addDepartment(CompanyDepartment dep){
		departmentDao.persist(dep);
	}

	@Transactional
	public void removeDepartment(CompanyDepartment dep){
		departmentDao.remove(dep);
	}
	
	@Transactional 
	public void modifyDepartment(CompanyDepartment dep){
		departmentDao.merge(dep);
	}
	/**
	* <p>Title: findAllEmployees</p>
	* <p>Description: </p>
	* @return
	*/
	@Transactional
	public List<EmployeeDataReturnModel> findAllEmployees() {
		// TODO Auto-generated method stub
		
		return employeeDao.findAllEmployeesForAdmin();
	}
	/**
	* <p>Title: findAllDepartments</p>
	* <p>Description: </p>
	* @return
	*/
	@Transactional
	public List<DepartmentDataReturnModel> findAllDepartments() {
		return departmentDao.findAllDepartmentForAdmin();
	}
	/**
	* <p>Title: checkUserName</p>
	* <p>Description: </p>
	* @param userName
	* @return
	*/
	@Transactional
	public Employee checkUserName(String userName) {
		// TODO Auto-generated method stub
		return employeeDao.findbyIdForAuthentication(userName);
	}
}
