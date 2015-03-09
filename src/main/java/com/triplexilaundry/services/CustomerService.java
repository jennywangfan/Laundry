/**
* <p>Title: CustomerService.java</p>
* <p>Description: </p>
* <p>Copyright: Copyright (c) 2015</p>
* <p>All Right Reserved</p>
* @author Fan Wang
* @date Feb 18, 2015
* @version 1.0
*/
package com.triplexilaundry.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.triplexilaundry.dao.CustomerDao;
import com.triplexilaundry.domain.company.Customer;

/**
 * <p>Title: CustomerService</p>
 * <p>Description: </p>
 * <p>All Right Reserved</p> 
 * @author Fan Wang
 * @date Feb 18, 2015
 */

@Service("customerService")
public class CustomerService {
    @Autowired
	private CustomerDao customerDao;
    @Transactional
    public void saveCustomer(Customer customer){
    	customerDao.persist(customer);
    }
}
