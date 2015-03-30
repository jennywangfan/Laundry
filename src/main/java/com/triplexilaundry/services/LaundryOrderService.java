package com.triplexilaundry.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.triplexilaundry.dao.CustomerDao;
import com.triplexilaundry.dao.EmployeeDao;
import com.triplexilaundry.dao.LaundryItemDao;
import com.triplexilaundry.dao.LaundryOrderDao;
import com.triplexilaundry.domain.Address;
import com.triplexilaundry.domain.LaundryItem;
import com.triplexilaundry.domain.LaundryOrder;
import com.triplexilaundry.domain.OrderItem;
import com.triplexilaundry.domain.OrderStatus;
import com.triplexilaundry.domain.company.Customer;
import com.triplexilaundry.domain.company.Employee;
import com.triplexilaundry.exception.ClientServerCategoryIdNotMatchException;
import com.triplexilaundry.exception.NotAllowToOperationException;
import com.triplexilaundry.extjsdata.ConfirmOrder;
import com.triplexilaundry.extjsdata.LaundryDataCreateModel;
import com.triplexilaundry.extjsdata.LaundryItemModel;
import com.triplexilaundry.extjsdata.LaundryOrderModel;
import com.triplexilaundry.extjsdata.OrderItemCreateModel;

/**
 * <p>
 * Title: OrderService
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * All Right Reserved
 * </p>
 * 
 * @author Fan Wang
 * @date Feb 26, 2015
 */
@Service("laundryOrderService")
public class LaundryOrderService {
	@Autowired
	private LaundryOrderDao laundryOrderDao;
	@Autowired
	private CustomerDao customerDao;
	
	@Autowired
	private LaundryItemDao laundryItemDao;
	
	@Autowired
	private EmployeeDao employeeDao;

	private static final Logger log = LoggerFactory
			.getLogger(LaundryOrderDao.class);

	@Transactional
	public void createNewOrder(LaundryOrder order) {

		// Employee e = laundryOrderDao.assignOrderTo();
		laundryOrderDao.persist(order);
		Customer customer = order.getCustomer();
		customerDao.merge(customer);

	}

	@Transactional
	public void modifyOrder(LaundryOrder order) {
		log.info("modify an order");
		laundryOrderDao.merge(order);
	}

	@Transactional
	public void cancelOrderById(long orderId,String userName) throws NotAllowToOperationException {
		laundryOrderDao.cancelOrderById(orderId,userName);

	}

	// get all orders belongs to a customer service rep
	@Transactional
	public Map<String, Object> getAllOrdersForCS(String userName, int page, int recordNumLimit) {
		Map<String, Object> orderMap = laundryOrderDao.getAllOrders(userName,page,recordNumLimit);
		@SuppressWarnings("unchecked")
		List<LaundryOrder> orderList = (List<LaundryOrder>) orderMap.get("results");
		List<LaundryOrderModel> extOrderList = null;
		if (orderList != null)
			extOrderList = new ArrayList<>();
		for (LaundryOrder order : orderList) {
			LaundryOrderModel lom = new LaundryOrderModel();
			lom.setOrderId(order.getOrderId());
			Customer cus = order.getCustomer();
			if (cus != null)
				lom.setOrderBy(cus.getUserName());
			Employee temp = order.getCsRep();
			if (temp != null)
				lom.setCsRep(temp.getFullName());
			temp = order.getPickedUpBy();
			if (temp != null)
				lom.setPickedUpBy(temp.getFullName());
			temp = order.getDeliveredBy();
			if (temp != null)
				lom.setDeliveredBy(temp.getFullName());
			lom.setPrice(order.getPrice());
			lom.setActualIncome(order.getActualIncome());
			lom.setAddress(order.getAddress());
			lom.setPreferedPickupStime(order.getPreferedPickupStime());
			lom.setPreferedPickupEtime(order.getPreferedPickupEtime());
			lom.setOrderStatus(OrderStatus.getChineseOrderStatus(order
					.getOrderStatus()));
			lom.setLastUpdateTime(order.getLastUpdateTime());
			temp = order.getLastUpdatedBy();
			if (temp != null)
				lom.setLastUpdatedBy(temp.getFullName());
			lom.setComments(order.getComments());
			List<OrderItem> laundryDetail = order.getLaundryDetail();
			if (laundryDetail != null) {
				List<LaundryItemModel> itemList = new ArrayList<>();
				for (OrderItem oi : laundryDetail) {
					LaundryItemModel lim = new LaundryItemModel();
					lim.setItemName(oi.getItem().getItemName());
					lim.setAmount(oi.getCount());
					lim.setPricePerItem(oi.getItem().getUnitPrice());
					// lim.setTotalPrice();
					itemList.add(lim);
				}
				lom.setOrderItems(itemList);
			}
			extOrderList.add(lom);
		}
        orderMap.put("results",extOrderList);
		
		return orderMap;
	}

	/**
	 * <p>
	 * Title: getAllOrdersForCS
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param userName
	 * @param orderS
	 * @param recordNumLimit 
	 * @param pageNum 
	 * @return
	 */
	@Transactional
	public Map<String, Object> getAllOrdersForCS(String userName,
			OrderStatus orderS, int page, int recordNumLimit) {
		Map<String, Object> orderMap = laundryOrderDao.getAllOrders(userName,
				orderS,page,recordNumLimit);
		@SuppressWarnings("unchecked")
		List<LaundryOrder> orderList = (List<LaundryOrder>) orderMap.get("results");
		List<LaundryOrderModel> extOrderList = null;
		if (orderList != null)
			extOrderList = new ArrayList<>();
		for (LaundryOrder order : orderList) {
			LaundryOrderModel lom = new LaundryOrderModel();
			lom.setOrderId(order.getOrderId());
			Customer cus = order.getCustomer();
			if (cus != null)
				lom.setOrderBy(cus.getUserName());
			Employee temp = order.getCsRep();
			if (temp != null)
				lom.setCsRep(temp.getFullName());
			temp = order.getPickedUpBy();
			if (temp != null)
				lom.setPickedUpBy(temp.getFullName());
			temp = order.getDeliveredBy();
			if (temp != null)
				lom.setDeliveredBy(temp.getFullName());
			lom.setPrice(order.getPrice());
			lom.setActualIncome(order.getActualIncome());
			lom.setAddress(order.getAddress());
			lom.setPreferedPickupStime(order.getPreferedPickupStime());
			lom.setPreferedPickupEtime(order.getPreferedPickupEtime());
			lom.setOrderStatus(OrderStatus.getChineseOrderStatus(order
					.getOrderStatus()));
			lom.setLastUpdateTime(order.getLastUpdateTime());
			lom.setCreateDate(order.getCreateDate());
			temp = order.getLastUpdatedBy();

			if (temp != null)
				lom.setLastUpdatedBy(temp.getFullName());
			lom.setComments(order.getComments());
			List<OrderItem> laundryDetail = order.getLaundryDetail();
			if (laundryDetail != null) {
				List<LaundryItemModel> itemList = new ArrayList<>();
				for (OrderItem oi : laundryDetail) {
					LaundryItemModel lim = new LaundryItemModel();
					lim.setItemName(oi.getItem().getItemName());
					lim.setAmount(oi.getCount());
					lim.setPricePerItem(oi.getItem().getUnitPrice());
					// lim.setTotalPrice();
					itemList.add(lim);
				}
				lom.setOrderItems(itemList);
			}
			extOrderList.add(lom);
		}
		
		orderMap.put("results",extOrderList);
		
		return orderMap;
	}

	/**
	* <p>Title: findOrder</p>
	* <p>Description: </p>
	 * @param orderS 
	* @param userName
	* @param cellPhone
	* @param orderId
	 * @param limit 
	 * @param page 
	* @return
	*/
	@Transactional
	public Map<String,Object> findOrder(OrderStatus orderS, String userName,
			String cellPhone, String orderId, int page, int limit) {
		// TODO Auto-generated method stub
		Map<String,Object> orderMap = laundryOrderDao.searchOrder(orderS,userName,cellPhone,orderId,page,limit);
		@SuppressWarnings("unchecked")
		List<LaundryOrder> orderList = (List<LaundryOrder>) orderMap.get("results");
		List<LaundryOrderModel> extOrderList = null;
		if (orderList != null){
			extOrderList = new ArrayList<>();
		for (LaundryOrder order : orderList) {
			LaundryOrderModel lom = new LaundryOrderModel();
			lom.setOrderId(order.getOrderId());
			Customer cus = order.getCustomer();
			if (cus != null)
				lom.setOrderBy(cus.getUserName());
			Employee temp = order.getCsRep();
			if (temp != null)
				lom.setCsRep(temp.getFullName());
			temp = order.getPickedUpBy();
			if (temp != null)
				lom.setPickedUpBy(temp.getFullName());
			temp = order.getDeliveredBy();
			if (temp != null)
				lom.setDeliveredBy(temp.getFullName());
			lom.setPrice(order.getPrice());
			lom.setActualIncome(order.getActualIncome());
			lom.setAddress(order.getAddress());
			lom.setPreferedPickupStime(order.getPreferedPickupStime());
			lom.setPreferedPickupEtime(order.getPreferedPickupEtime());
			lom.setOrderStatus(OrderStatus.getChineseOrderStatus(order
					.getOrderStatus()));
			lom.setLastUpdateTime(order.getLastUpdateTime());
			lom.setCreateDate(order.getCreateDate());
			temp = order.getLastUpdatedBy();

			if (temp != null)
				lom.setLastUpdatedBy(temp.getFullName());
			lom.setComments(order.getComments());
			List<OrderItem> laundryDetail = order.getLaundryDetail();
			if (laundryDetail != null) {
				List<LaundryItemModel> itemList = new ArrayList<>();
				for (OrderItem oi : laundryDetail) {
					LaundryItemModel lim = new LaundryItemModel();
					lim.setItemName(oi.getItem().getItemName());
					lim.setAmount(oi.getCount());
					lim.setPricePerItem(oi.getItem().getUnitPrice());
					// lim.setTotalPrice();
					itemList.add(lim);
				}
				lom.setOrderItems(itemList);
			}
			extOrderList.add(lom);
		}
		}
		
		orderMap.put("results",extOrderList);
		
		return orderMap;
	}

	/**
	* <p>Title: contactOrder</p>
	* <p>Description: </p>
	* @param updatedOrder
	 * @throws ParseException 
	*/
	@Transactional 
	public void contactOrder(ConfirmOrder updatedOrder) throws ParseException {
		long orderId = Long.valueOf(updatedOrder.getOrderId());
		LaundryOrder order = laundryOrderDao.findById(orderId);
		Address address = order.getAddress();
		address.setState(updatedOrder.getState());
		address.setCity(updatedOrder.getCity());
		address.setDistrict(updatedOrder.getDistrict());
		address.setStreet(updatedOrder.getState());
		address.setStreetNum(updatedOrder.getStreetNum());
		address.setFullName(updatedOrder.getFullName());
		address.setPhoneNumber(updatedOrder.getPhoneNumber());
		SimpleDateFormat format1 = new SimpleDateFormat("mm/dd/yyyy h:mm a");
		SimpleDateFormat format2 = new SimpleDateFormat("mm/dd/yyyy");
		Date pSdate = null;
		if(updatedOrder.getPreferedPickupETime() != null)
		  pSdate = format1.parse(updatedOrder.getPreferedPickupSDate() +" " +updatedOrder.getPreferedPickupSTime());
		else
			pSdate = format2.parse(updatedOrder.getPreferedPickupSDate());
		if(pSdate != null)
			order.setPreferedPickupStime(pSdate);
		Date pEdate = null;
		if(updatedOrder.getPreferedPickupETime() != null)
			pEdate = format1.parse(updatedOrder.getPreferedPickupEDate()+" "+updatedOrder.getPreferedPickupETime());
		else
			pEdate = format2.parse(updatedOrder.getPreferedPickupEDate());			
		if(pEdate != null)
			order.setPreferedPickupEtime(pEdate);
	    laundryOrderDao.updateOrderAContact(order);  
		
	}

	/**
	* <p>Title: createOrderForCustomer</p>
	* <p>Description: </p>
	* @param order
	 * @throws ClientServerCategoryIdNotMatchException 
	 * @throws ParseException 
	*/
	@Transactional
	public void createOrderForCustomer(String currentUser,LaundryDataCreateModel order) throws ClientServerCategoryIdNotMatchException, ParseException {
		String orderBy = order.getOrderBy();
		Customer customer = customerDao.findByUserName(orderBy);
		LaundryOrder lOrder = new LaundryOrder();
		
		Address address = new Address();
		address.setCity(order.getCity());
		address.setDistrict(order.getDistrict());
		address.setFullName(order.getFullName());
		address.setPhoneNumber(order.getPhoneNumber());
		address.setState(order.getState());
		address.setStreet(order.getStreet());
		address.setStreetNum(order.getStreetNum());
		lOrder.setAddress(address);
		Date pSdate = null;
		SimpleDateFormat format1 = new SimpleDateFormat("mm/dd/yyyy h:mm a");
		SimpleDateFormat format2 = new SimpleDateFormat("mm/dd/yyyy");
		if(order.getPreferedPickupETime() != null)
		  pSdate = format1.parse(order.getPreferedPickupSDate() +" " +order.getPreferedPickupSTime());
		else
			pSdate = format2.parse(order.getPreferedPickupSDate());
		if(pSdate != null)
			lOrder.setPreferedPickupStime(pSdate);
		Date pEdate = null;
		if(order.getPreferedPickupETime() != null)
			pEdate = format1.parse(order.getPreferedPickupEDate()+" "+order.getPreferedPickupETime());
		else
			pEdate = format2.parse(order.getPreferedPickupEDate());			
		if(pEdate != null)
			lOrder.setPreferedPickupEtime(pEdate);
		List<OrderItemCreateModel> orderCreateItems = order.getOrderItems();
		List<OrderItem> orderItems = new ArrayList<OrderItem>();
		double price = 0;
		for(OrderItemCreateModel item : orderCreateItems){
			OrderItem oi = new OrderItem();
			oi.setCount(item.getAmount());
			double tPrice = item.getTotalPrice();
			price += tPrice;
			oi.setTotalPrice(tPrice);
			oi.setBelongto(lOrder);
			LaundryItem li = null;
			try {
			   li = laundryItemDao.findLaundryById(item.getItemId());
			} catch (ClientServerCategoryIdNotMatchException e) {
				throw e;
			}
			oi.setItem(li);
			orderItems.add(oi);
			
		}
		lOrder.setLaundryDetail(orderItems);
		if(customer == null){
			customer = new Customer();
			if(orderBy.length() == 0)
				orderBy = order.getPhoneNumber();
			customer.setUserName(orderBy);
			List<Address> addressList = new ArrayList<>();
		    addressList.add(address);			    
		    customer.setAddressList(addressList);
			
		}else{
			customer.getAddressList().add(address);
			
		}
		
		lOrder.setCustomer(customer);
		Employee csRep = employeeDao.findEmployeeByUserName(currentUser);
		lOrder.setCsRep(csRep);
		lOrder.setCreateDate(new Date());
		lOrder.setLastUpdatedBy(csRep);
		lOrder.setLastUpdateTime(new Date());
		lOrder.setOrderStatus(OrderStatus.WAITINGFORPICKUP);
		lOrder.setPrice(price);
		laundryOrderDao.persist(lOrder);
	}

}
