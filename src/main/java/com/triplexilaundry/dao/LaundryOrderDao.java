
package com.triplexilaundry.dao;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.triplexilaundry.domain.LaundryOrder;
import com.triplexilaundry.domain.OrderStatus;
import com.triplexilaundry.domain.company.Employee;
import com.triplexilaundry.exception.NotAllowToOperationException;

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
	* <p>Description: get all records belongs to userName</p>
	* @param userName
	 * @param recordNumLimit 
	 * @param pageNum 
	* @return
	*/
	public Map<String, Object> getAllOrders(String userName, int page, int recordNumLimit) {
		log.info("get all orders for "+userName);
		try{
			 String sql = "select o from LaundryOrder o where o.csRep.username = :name ";
			    String sqlcount = "select count(o.orderId) from LaundryOrder o where o.csRep.username = :name";
			  	Query querycount = entityManger.createQuery(sqlcount);
			  	querycount.setParameter("name", userName);
			  
			  	long totalCount = (long) querycount.getSingleResult();
			    Query query = entityManger.createQuery(sql);
			    query.setParameter("name", userName);
			  
			    query.setMaxResults(recordNumLimit);
			    query.setFirstResult((page-1)*recordNumLimit);
			    
			    @SuppressWarnings("unchecked")
				List<LaundryOrder> orderList = query.getResultList();
			    Map<String, Object> result = new HashMap<>(2);
			    result.put("totalCount", totalCount);
			    result.put("results", orderList);
			     log.info("success to get all orders for "+userName);
			    return result;
		}catch(RuntimeException re){
			log.error("fail to get all orders for "+userName,re);
			throw re;
		}
	}

	/**
	* <p>Title: getAllOrders</p>
	* <p>Description: get all orders belongs to userName with order status orderS</p>
	* @param userName
	* @param orderS
	 * @param recordNumLimit 
	 * @param pageNum 
	* @return
	*/
	public Map<String, Object> getAllOrders(String userName, OrderStatus orderS, int page, int recordNumLimit) {
		log.info("get all orders for "+userName + " with order status "+orderS.getStatusDes());
		try{
		    String sql = "select o from LaundryOrder o where o.csRep.username = :name and o.orderStatus = :orderstatus";
		    String sqlcount = "select count(o.orderId) from LaundryOrder o where o.csRep.username = :name and o.orderStatus = :orderstatus";
		  	Query querycount = entityManger.createQuery(sqlcount);
		  	querycount.setParameter("name", userName);
		  	querycount.setParameter("orderstatus", orderS);
		  	long totalCount = (long) querycount.getSingleResult();
		    Query query = entityManger.createQuery(sql);
		    query.setParameter("name", userName);
		    query.setParameter("orderstatus", orderS);
		    query.setMaxResults(recordNumLimit);
		    query.setFirstResult((page-1)*recordNumLimit);
		    
		    @SuppressWarnings("unchecked")
			List<LaundryOrder> orderList = query.getResultList();
		    Map<String, Object> result = new HashMap<>(2);
		    result.put("totalCount", totalCount);
		    result.put("results", orderList);
		     log.info("success to get all orders for "+userName);
		    return result;
		}catch(RuntimeException re){
			log.error("fail to get all orders for "+userName,re);
			throw re;
		}
	}

	/**
	* <p>Title: cancelOrderById</p>
	* <p>Description: cancel order with Id orderId</p>
	* @param orderId
	 * @throws NotAllowToOperationException 
	*/
	public LaundryOrder cancelOrderById(long orderId,String userName) throws NotAllowToOperationException {
		// TODO Auto-generated method stub
		log.info("cancel order with id and userName " + orderId + "  " + userName);
		try{
			LaundryOrder order = entityManger.find(LaundryOrder.class, orderId);
			if(userName.equals(order.getCsRep().getUsername())){
				order.setOrderStatus(OrderStatus.CANCELED);
				order.setLastUpdatedBy(order.getCsRep());
				order.setLastUpdateTime(new Date());
			}
			else{
				Employee manager = order.getCsRep().getReportTo();
				if(userName.equals(manager.getUsername()))
				{
					order.setOrderStatus(OrderStatus.CANCELED);
					order.setLastUpdatedBy(manager);
					order.setLastUpdateTime(new Date());
				}
				else{
					throw new NotAllowToOperationException("没有权限取消该订单");
				}
			}
			return order;
		}catch(RuntimeException re){
			log.error("fail to cancel order with id and userName " + orderId + " "+userName,re);
			throw re;
			
		} catch (NotAllowToOperationException e) {
			log.error("fail to cancel order with id and userName " + orderId + " "+userName,e);
			throw e;
		}
		
	}

	/**
	* <p>Title: searchOrder</p>
	* <p>Description: </p>
	 * @param orderS 
	* @param userName
	* @param cellPhone
	* @param orderId
	 * @param limit 
	 * @param page 
	* @return
	*/
	public Map<String, Object> searchOrder(OrderStatus orderS, String userName,
			String cellPhone, String orderId, int page, int limit) {
		String sqlCount = null;
		String sql = null;
		Map<String,Object> result = new HashMap<>(2);
		if(cellPhone == null || cellPhone.length() == 0){
			 LaundryOrder order = entityManger.find(LaundryOrder.class, Long.valueOf(orderId));
			 if(order == null){
				 //result.put("totalCount", 0);
				 result.put("results", null);
				 result.put("totalCount", 0);
			 }
			 else{
				 List<LaundryOrder> resultList = new ArrayList<>();
				 resultList.add(order);
				 result.put("results", resultList);
				 result.put("totalCount", 1);
			 }
			 return result;
		}
		else if(orderId == null || orderId.length() == 0){
			  sql = "select o from LaundryOrder o where o.csRep.username = :name and o.customer.userName = :cellPhone";
			  sqlCount = "select count(o.orderId) from LaundryOrder o where o.csRep.username = :name and o.customer.userName = :cellPhone";
				Query querycount = entityManger.createQuery(sqlCount);
			  	querycount.setParameter("name", userName);
			  	//querycount.setParameter("orderstatus", orderS);
			  	querycount.setParameter("cellPhone", cellPhone);
			  	long totalCount = (long) querycount.getSingleResult();
			    Query query = entityManger.createQuery(sql);
			    query.setParameter("name", userName);
			    //query.setParameter("orderstatus", orderS);
			    query.setParameter("cellPhone", cellPhone);
			    query.setMaxResults(limit);
			    query.setFirstResult((page-1)*limit);
			    
			    @SuppressWarnings("unchecked")
				List<LaundryOrder> orderList = query.getResultList();
			    result.put("totalCount", totalCount);
			    result.put("results", orderList);
			    return result;
		}else{
			  sql = "select o from LaundryOrder o where o.csRep.username = :name  and o.customer.userName = :cellPhone and o.orderId = :orderId";
			  sqlCount = "select count(o.orderId) from LaundryOrder o where o.csRep.username = :name  and o.customer.userName = :cellPhone and o.orderId = :orderId";
				Query querycount = entityManger.createQuery(sqlCount);
			  	querycount.setParameter("name", userName);
			  	//querycount.setParameter("orderstatus", orderS);
			  	querycount.setParameter("cellPhone", cellPhone);
			  	querycount.setParameter("orderId", Long.valueOf(orderId));
			  	long totalCount = (long) querycount.getSingleResult();
			    Query query = entityManger.createQuery(sql);
			    query.setParameter("name", userName);
			    //query.setParameter("orderstatus", orderS);
			    query.setParameter("cellPhone", cellPhone);
			    query.setParameter("orderId", Long.valueOf(orderId));
			    query.setMaxResults(limit);
			    query.setFirstResult((page-1)*limit);
			    
			    @SuppressWarnings("unchecked")
				List<LaundryOrder> orderList = query.getResultList();
			    result.put("totalCount", totalCount);
			    result.put("results", orderList);
			    return result;
		}
		
	}

	/**
	* <p>Title: updateOrderAContact</p>
	* <p>Description: </p>
	* @param updatedOrder
	 * @throws ParseException 
	*/
	public void updateOrderAContact(LaundryOrder order) {
		
		log.info("update infomation after confirm order with customer");
		try{
			
			order.setOrderStatus(OrderStatus.WAITINGFORPICKUP);
			entityManger.merge(order);
			
		}catch(RuntimeException re){
			log.error("fail to update order after confirm with customer",re);
			throw re;
		} 
		
		
		
	}
		
	
	
}
