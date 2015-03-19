
package com.triplexilaundry.services;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.triplexilaundry.dao.CustomerDao;
import com.triplexilaundry.dao.LaundryOrderDao;
import com.triplexilaundry.domain.LaundryOrder;
import com.triplexilaundry.domain.company.Customer;
import com.triplexilaundry.extjsdata.LaundryOrderModel;

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
    	
    	//Employee e = laundryOrderDao.assignOrderTo();
    	laundryOrderDao.persist(order);
    	Customer customer = order.getCustomer();
    	customerDao.merge(customer);
	
    }
    
    @Transactional
    public void modifyOrder(LaundryOrder order){
    	log.info("modify an order");
    	laundryOrderDao.merge(order);
    }
    
    @Transactional
    public void cancelOrder(LaundryOrder order){
    	
    }
    
    // get all orders belongs to a customer service rep
    @Transactional 
    public List<LaundryOrderModel> getAllOrdersForCS(String userName){
       List<LaundryOrder> orderList = laundryOrderDao.getAllOrders(userName);
       List<LaundryOrderModel> extOrderList = null;
       if(orderList != null)
    	   extOrderList = new ArrayList<>();
       for(LaundryOrder order : orderList){
    	   LaundryOrderModel lom = new LaundryOrderModel
    			   (order.getOrderId(), order.getCustomer().getUserName(),
    			    order.getCsRep().getFullName(),order.getPickedUpBy().getUsername(), 
    			    order.getDeliveredBy().getFullName(), order.getPrice(), order.getActualIncome(),
    			    order.getAddress(), order.getPreferedPickupStime(), order.getPreferedPickupEtime(),
    			    order.getOrderStatus(), order.getLastUpdateTime(), order.getComments(),order.getLaundryDetail());
    	   extOrderList.add(lom);
       }
       return extOrderList;
    }
    
	
}
