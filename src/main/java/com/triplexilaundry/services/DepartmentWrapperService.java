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
 * <p>
 * Title: DepartmentWrapperService
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * All Right Reserved
 * </p>
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
			cd.setDepName(data.getDepName());
			cd.setDepDesc(data.getDepDesc());
			if(!depDao.findDepartmentByName(data.getDepName()))
			   depDao.persist(cd);
		}

	}

}
