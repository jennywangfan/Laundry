
package com.triplexilaundry.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.triplexilaundry.dao.CustomerDao;
import com.triplexilaundry.dao.LaundryOrderDao;
import com.triplexilaundry.domain.LaundryOrder;
import com.triplexilaundry.domain.company.Customer;
import com.triplexilaundry.domain.company.Employee;

/**
 * <p>Title: OrderService</p>
 * <p>Description: </p>
 * <p>All Right Reserved</p> 
 * @author Fan Wang
 * @date Feb 26, 2015
 */
@Service
public class LaundryOrderService {
    @Autowired
	private LaundryOrderDao laundryOrderDao;
    @Autowired
    private CustomerDao customerDao;
    
    private static final Logger log = LoggerFactory.getLogger(LaundryOrderDao.class);
    @Transactional
    public void createNewOrder(LaundryOrder order){
    	log.debug("create a new order");
    	//Assign order to a customer service rep
    	Employee e = laundryOrderDao.assignOrderTo();
    	laundryOrderDao.persist(order);
    	Customer customer = order.getCustomer();
    	customerDao.merge(customer);
    	
    	
    }
    
    @Transactional
    public void modifyOrder(LaundryOrder order){
    	log.debug("modify an order");
    	laundryOrderDao.merge(order);
    }
    
    @Transactional
    public void cancelOrder(LaundryOrder order){
    	
    }
	
}
