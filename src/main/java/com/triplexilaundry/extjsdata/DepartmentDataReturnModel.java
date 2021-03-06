/**
* <p>Title: DepartmentDataReturnModel.java</p>
* <p>Description: </p>
* <p>Copyright: Copyright (c) 2015</p>
* <p>All Right Reserved</p>
* @author Fan Wang
* @date Mar 6, 2015
* @version 1.0
*/
package com.triplexilaundry.extjsdata;

/**
 * <p>Title: DepartmentDataReturnModel</p>
 * <p>Description: department information send to client</p>
 * <p>All Right Reserved</p> 
 * @author Fan Wang
 * @date Mar 6, 2015
 */
public class DepartmentDataReturnModel {
	
	private int departmentId;
	private String departmentName;
	private String departmentDesc;
	private long departmentNum;
	public int getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public String getDepartmentDesc() {
		return departmentDesc;
	}
	public void setDepartmentDesc(String departmentDesc) {
		this.departmentDesc = departmentDesc;
	}
	public long getDepartmentNum() {
		return departmentNum;
	}
	public void setDepartmentNum(long departmentNum) {
		this.departmentNum = departmentNum;
	}

}
