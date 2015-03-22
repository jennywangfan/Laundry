/**
* <p>Title: OrderListController.java</p>
* <p>Description: </p>
* <p>Copyright: Copyright (c) 2015</p>
* <p>All Right Reserved</p>
* @author Fan Wang
* @date Mar 17, 2015
* @version 1.0
*/
package com.triplexilaundry.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.triplexilaundry.domain.OrderStatus;
import com.triplexilaundry.extjsdata.ExtJSReturn;
import com.triplexilaundry.extjsdata.LaundryOrderModel;
import com.triplexilaundry.services.LaundryOrderService;

/**
 * <p>Title: OrderListController</p>
 * <p>Description: </p>
 * <p>All Right Reserved</p> 
 * @author Fan Wang
 * @date Mar 17, 2015
 */
@Controller
public class OrderListController extends AbstractControllerService{
	
	@Autowired
	private LaundryOrderService orderService;
	
	private static final Logger log = LoggerFactory.getLogger(OrderListController.class);
	
	@RequestMapping(method= RequestMethod.GET,value = "/getAllOrdersForCS.action")
	public @ResponseBody Map<String, ? extends Object> getOrdersForPage(@RequestParam int orderStatus,
			@RequestParam int page,
			@RequestParam int limit){
		log.info("handle getAllOrdersForCS request");
		try{
			//int iStatus = Integer.valueOf(orderStatus);
			OrderStatus orderS = null;
			switch(orderStatus){
			case 1:
				orderS = OrderStatus.PENDINGPROCESS;
				break;
			case 2:
				orderS = OrderStatus.WAITINGFORPICKUP;
				break;
			case 3:
				orderS = OrderStatus.CANCELED;
				break;
			default:
				break;
			}
			
		String userName = this.getCurrentUserId();
		if(orderS != null){
			
			log.info("return orders with status " + orderS.getStatusDes());
			Map<String, Object> extOrderList = orderService.getAllOrdersForCS(userName,orderS,page,limit);
			return ExtJSReturn.mapOrderListOK(extOrderList);
		}
		else{
			log.error("return all orders with all status");
			Map<String, Object> extOrderList = orderService.getAllOrdersForCS(userName,page,limit);
			return ExtJSReturn.mapOrderListOK(extOrderList);
		}
		}catch(Exception e){
			log.error("fail to return orders",e);
			return ExtJSReturn.mapError("获取订单列表失败");
		}
		
		
	}

}
