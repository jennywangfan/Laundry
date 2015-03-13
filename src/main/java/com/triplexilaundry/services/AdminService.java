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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.triplexilaundry.dao.AccessRoleDao;
import com.triplexilaundry.dao.CompanyDepartmentDao;
import com.triplexilaundry.dao.EmployeeDao;
import com.triplexilaundry.domain.company.CompanyDepartment;
import com.triplexilaundry.domain.company.Employee;
import com.triplexilaundry.domain.company.EmployeeRole;
import com.triplexilaundry.extjsdata.ComboboxModel;
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
	
	@Autowired
	private AccessRoleDao accessRoleDao;
	
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
		List<Object[]> employeeList = employeeDao.findAllEmployeesForAdmin();
		List<EmployeeDataReturnModel> extEmployeeList = null;
		if(employeeList != null)
		    extEmployeeList = new ArrayList<>();
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
	
	}
	/**
	* <p>Title: findAllDepartments</p>
	* <p>Description: </p>
	* @return
	*/
	@Transactional
	public List<DepartmentDataReturnModel> findAllDepartments() {
		List<Object[]> departmentList = departmentDao.findAllDepartmentForAdmin();
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
	/**
	* <p>Title: getDepartmentForCombo</p>
	* <p>Description: </p>
	* @return
	*/
	@Transactional
	public List<ComboboxModel> getDepartmentForCombo() {
		List<Object[]> departmentList= departmentDao.getDepartmentListForCombo();
		List<ComboboxModel> extDepartmentList = null;
		if (departmentList != null) {
			extDepartmentList = new ArrayList<>();
			for (Object[] o : departmentList) {
				ComboboxModel cm = new ComboboxModel();
				cm.setName(o[0] == null ? "" : (String) o[0]);
				cm.setAttribute(o[1] == null ? "" : o[1].toString());
				extDepartmentList.add(cm);
			}
		}
		return extDepartmentList;
	}
	/**
	* <p>Title: getEmployeeRoleForCombo</p>
	* <p>Description: </p>
	* @return
	*/
	@Transactional
	public List<ComboboxModel> getEmployeeRoleForCombo() {
		// TODO Auto-generated method stub
		List<Object[]> employeeRoleList = employeeDao.getEmployeeRoleListForCombo();
		List<ComboboxModel> extEmployeeRoleList = null;
		if(employeeRoleList != null)
			extEmployeeRoleList = new ArrayList<>();
		for(Object[] o : employeeRoleList){
			ComboboxModel cm = new ComboboxModel();
			cm.setName(o[0] == null ? "" : (String) o[0]);
			cm.setAttribute(o[1]==null ? "" :o[1].toString());
			extEmployeeRoleList.add(cm);
		}
		return extEmployeeRoleList;
	}
	/**
	* <p>Title: getAccessRoleListForCombo</p>
	* <p>Description: </p>
	* @return
	*/
	public List<ComboboxModel> getAccessRoleForCombo() {
		List<Object[]> roleList = accessRoleDao.getRoleListForCombo();
		
		List<ComboboxModel> extRoleList = null;
		if(roleList != null)
		   extRoleList = new ArrayList<>();
		for(Object[] o : roleList){
			ComboboxModel cm = new ComboboxModel();
			cm.setName(o[0] == null ? "":(String)o[0]);
			cm.setAttribute(o[1]==null ? "":o[1].toString());
			extRoleList.add(cm);
		}
		return extRoleList;
	}
	/**
	* <p>Title: getDepManagersForCombo</p>
	* <p>Description: </p>
	* @return
	*/
	public List<ComboboxModel> getDepManagersForCombo(int departmentId) {
		List<Object[]> managerList = employeeDao.getManagerListForCombo(departmentId);
		List<ComboboxModel> extManagerList = null;
		if(managerList != null)
			extManagerList = new ArrayList<>();
		for(Object[] o : managerList){
			ComboboxModel cm = new ComboboxModel();
			cm.setName(o[0] == null ? "":(String)o[0]);
			cm.setAttribute(o[1] == null ? "":(String)o[1]);
			extManagerList.add(cm);
		}
		return extManagerList;
	}
}
