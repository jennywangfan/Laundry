/**
* <p>Title: UserDataCreateModel.java</p>
* <p>Description: </p>
* <p>Copyright: Copyright (c) 2015</p>
* <p>All Right Reserved</p>
* @author Fan Wang
* @date Mar 13, 2015
* @version 1.0
*/
package com.triplexilaundry.extjsdata;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>Title: UserDataCreateModel</p>
 * <p>Description: class for sending to server after create an employee</p>
 * <p>All Right Reserved</p> 
 * @author Fan Wang
 * @date Mar 13, 2015
 */
public class EmployeeDataCreateModel implements Serializable{
	
	/** long   serialVersionUID */
	private static final long serialVersionUID = 1L;
	private String userName;
	private String password;
	private String passwordRepeat;
	private String departmentId;
	private String employeeRoleId;
	private String accessRoleId;
	private String reportManagerId;
	private String fullName;
	private Date startDate;
	
	
	public EmployeeDataCreateModel(){
		
	}
	
	

	public EmployeeDataCreateModel(String userName, String password,
			String passwordRepeat, String departmentId, String employeeRoleId,
			String accessRoleId, String reportManagerId, String fullName,
			Date startDate) {
		super();
		this.userName = userName;
		this.password = password;
		this.passwordRepeat = passwordRepeat;
		this.departmentId = departmentId;
		this.employeeRoleId = employeeRoleId;
		this.accessRoleId = accessRoleId;
		this.reportManagerId = reportManagerId;
		this.fullName = fullName;
		this.startDate = startDate;
	}


	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPasswordRepeat() {
		return passwordRepeat;
	}
	public void setPasswordRepeat(String passwordRepeat) {
		this.passwordRepeat = passwordRepeat;
	}
	public String getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}
	public String getEmployeeRoleId() {
		return employeeRoleId;
	}
	public void setEmployeeRoleId(String employeeRole) {
		this.employeeRoleId = employeeRole;
	}
	public String getAccessRoleId() {
		return accessRoleId;
	}
	public void setAccessRoleId(String accessRole) {
		this.accessRoleId = accessRole;
	}
	public String getReportManagerId() {
		return reportManagerId;
	}
	public void setReportManagerId(String reportManager) {
		this.reportManagerId = reportManager;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	
	

}
