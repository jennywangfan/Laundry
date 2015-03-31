/**
 * <p>Title: DepartmentWrapperService.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2015</p>
 * <p>All Right Reserved</p>
 * @author Fan Wang
 * @date Mar 14, 2015
 * @version 1.0
 */
package com.triplexilaundry.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.triplexilaundry.dao.CompanyDepartmentDao;
import com.triplexilaundry.domain.company.CompanyDepartment;
import com.triplexilaundry.extjsdata.DepartmentDataCreateModel;

/**
 * <p>Title: DepartmentWrapperService</p>
 * <p>Description: service for handle operations on Department</p>
 * <p>All Right Reserved </p>
 * 
 * @author Fan Wang
 * @date Mar 14, 2015
 */
@Service("depWrapperService")
public class DepartmentWrapperService {
	@Autowired
	CompanyDepartmentDao depDao;

	/**
	 * <p>
	 * Title: createDepartment
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param data
	 */
	@Transactional
	public void createDepartment(DepartmentDataCreateModel data) {
		// TODO Auto-generated method stub
		if (data != null) {
			CompanyDepartment cd = new CompanyDepartment();
			cd.setDepName(data.getDepartmentName());
			cd.setDepDesc(data.getDepartmentDesc());
			depDao.persist(cd);
		}

	}

	/**
	* <p>Title: editDepartment</p>
	* <p>Description: </p>
	* @param data
	*/
	@Transactional
	public void editDepartment(DepartmentDataCreateModel data) {
		// TODO Auto-generated method stub
		if(data != null){
			CompanyDepartment cd = new CompanyDepartment();
			cd.setDepName(data.getDepartmentName());
			cd.setDepDesc(data.getDepartmentDesc());
			cd.setDepartmentId(Integer.valueOf(data.getDepartmentId()));
			depDao.merge(cd);
		}
		
	}

}
