package com.triplexilaundry.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.triplexilaundry.dao.CustomerDao;
import com.triplexilaundry.dao.LaundryOrderDao;
import com.triplexilaundry.domain.LaundryOrder;
import com.triplexilaundry.domain.OrderItem;
import com.triplexilaundry.domain.OrderStatus;
import com.triplexilaundry.domain.company.Customer;
import com.triplexilaundry.domain.company.Employee;
import com.triplexilaundry.extjsdata.LaundryItemModel;
import com.triplexilaundry.extjsdata.LaundryOrderModel;

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
	public void cancelOrder(LaundryOrder order) {

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
					lim.setItemName(oi.getItem().getCategory());
					lim.setAmount(oi.getCount());
					lim.setPricePerItem(oi.getItem().getPrice());
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
			temp = order.getLastUpdatedBy();

			if (temp != null)
				lom.setLastUpdatedBy(temp.getFullName());
			lom.setComments(order.getComments());
			List<OrderItem> laundryDetail = order.getLaundryDetail();
			if (laundryDetail != null) {
				List<LaundryItemModel> itemList = new ArrayList<>();
				for (OrderItem oi : laundryDetail) {
					LaundryItemModel lim = new LaundryItemModel();
					lim.setItemName(oi.getItem().getCategory());
					lim.setAmount(oi.getCount());
					lim.setPricePerItem(oi.getItem().getPrice());
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

}
