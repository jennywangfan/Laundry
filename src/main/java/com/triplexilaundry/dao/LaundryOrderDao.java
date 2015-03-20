
package com.triplexilaundry.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.triplexilaundry.domain.LaundryOrder;
import com.triplexilaundry.domain.OrderStatus;
import com.triplexilaundry.domain.company.Employee;

/**
 * <p>Title: LaundryOrderDao</p>
 * <p>Description: </p>
 * <p>All Right Reserved</p> 
 * @author Fan Wang
 * @date Feb 26, 2015
 */

@Repository
public class LaundryOrderDao {

	@PersistenceContext
	private EntityManager entityManger;
	
	private static final Logger log = LoggerFactory.getLogger(LaundryOrderDao.class);
	
	public void persist(LaundryOrder order){
		log.debug("persist an order");
		try{
			entityManger.persist(order);
			entityManger.flush();
		}catch(RuntimeException re){
			log.error("fail to persist order", re);
		}
	}
	
	public void merge(LaundryOrder order){
		log.debug("merge an order");
		try{
			entityManger.merge(order);
			entityManger.flush();
		}catch(RuntimeException re){
			log.error("fail to merge order", re);
		}
	}
	
	public LaundryOrder findById(long orderId){
		log.debug("find order by Id");
		LaundryOrder order = null;
		try{
			order = entityManger.find(LaundryOrder.class, orderId);
		
		}catch(RuntimeException re){
			log.error("fail to find order by id " + orderId, re);
		}
		return order;
	}
	
	public List<?> findOrderByCustomerUserName(String customername){
		log.debug("find order by username");
		List<?> orderList = null;
		try{
			String sql = "select order from LaundryOrder order "
					+ "join fetch order.customer customer where customer.username = : name";
			Query query = entityManger.createQuery(sql);
			query.setParameter("name", customername);
			orderList = query.getResultList();
		
		}catch(RuntimeException re){
			log.error("fail to find order by username " + customername, re);
		}
		return orderList;
	}
	
	public List<?> findOrderByAssigedEmployee(String employeename){
		log.debug("find order by employeename");
		List<?> orderList = null;
		try{
			String sql = "select order from LaundryOrder order "
					+ "join fetch order.csRep  where csrep.username = : name";
			Query query = entityManger.createQuery(sql);
			query.setParameter("name", employeename);
			orderList = query.getResultList();
		
		}catch(RuntimeException re){
			log.error("fail to find order by username " + employeename, re);
		}
		return orderList;
	}

	/**
	* <p>Title: assignOrderTo</p>
	* <p>Description: </p>
	* @return
	*/
	public Employee assignOrderTo() {
		//String sql = "select order.ceRep from LaundryOrder order group by order.csRep";
		return null;
		
	}

	/**
	* <p>Title: getAllOrders</p>
	* <p>Description: </p>
	* @param userName
	* @return
	*/
	public List<LaundryOrder> getAllOrders(String userName) {
		log.info("get all orders for "+userName);
		try{
		    String sql = "select order from LaundryOrder order where order.csRep.username = :name";
		    Query query = entityManger.createQuery(sql);
		    query.setParameter("name", userName);
		    @SuppressWarnings("unchecked")
			List<LaundryOrder> orderList = query.getResultList();
		    for(LaundryOrder o : orderList)
		    	o.getAddress();
		    log.info("success to get all orders for "+userName);
		    return orderList;
		}catch(RuntimeException re){
			log.error("fail to get all orders for "+userName,re);
			throw re;
		}
	}

	/**
	* <p>Title: getAllOrders</p>
	* <p>Description: </p>
	* @param userName
	* @param orderS
	* @return
	*/
	public List<LaundryOrder> getAllOrders(String userName, OrderStatus orderS) {
		log.info("get all orders for "+userName + " with order status "+orderS.getStatusDes());
		try{
		    String sql = "select order from LaundryOrder order where order.csRep.username = :name and order.orderStatus = :orderstatus";
		    Query query = entityManger.createQuery(sql);
		    query.setParameter("name", userName);
		    query.setParameter("orderstatus", orderS);
		    @SuppressWarnings("unchecked")
			List<LaundryOrder> orderList = query.getResultList();
		    for(LaundryOrder o : orderList)
		    	o.getAddress();
		    log.info("success to get all orders for "+userName);
		    return orderList;
		}catch(RuntimeException re){
			log.error("fail to get all orders for "+userName,re);
			throw re;
		}
	}
	
}
