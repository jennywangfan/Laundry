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

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.triplexilaundry.domain.OrderStatus;
import com.triplexilaundry.exception.NotAllowToOperationException;
import com.triplexilaundry.extjsdata.ConfirmOrder;
import com.triplexilaundry.extjsdata.ExtJSReturn;
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
			log.info("return all orders with all status");
			Map<String, Object> extOrderList = orderService.getAllOrdersForCS(userName,page,limit);
			return ExtJSReturn.mapOrderListOK(extOrderList);
		}
		}catch(Exception e){
			log.error("fail to return orders",e);
			return ExtJSReturn.mapError("获取订单列表失败!");
		}
		
		
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/cancelOrder.action")
	public @ResponseBody Map<String, ? extends Object> cancelOrder(@RequestParam long orderId){
		log.info("cancel order "+orderId);
		try{
			
			String userName = this.getCurrentUserId();
			orderService.cancelOrderById(orderId,userName);
			log.info("success to cancel order " + orderId +"by " +userName);
			return ExtJSReturn.simpleMapResult(true, "取消订单成功!");
		}catch(NotAllowToOperationException ne){
			return ExtJSReturn.simpleMapResult(false, ne.getMessage());
		}catch(RuntimeException re){
			return ExtJSReturn.simpleMapResult(false, "取消订单失败!");
		}
	}
	
	@RequestMapping(method = RequestMethod.GET,value = "/searchOrder.action")
	public @ResponseBody Map<String, ? extends Object> searchOrder(@RequestParam int orderStatus,
			@RequestParam String cellPhone, @RequestParam String orderId,@RequestParam int page,@RequestParam int limit){
		log.info("search order with cellPhone or orderId " + cellPhone +" "+orderId);
		try{
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
			if(orderS != null){
			String userName = this.getCurrentUserId();
			Map<String,Object> extOrderList = orderService.findOrder(orderS,userName,cellPhone,orderId,page,limit);
			return ExtJSReturn.mapOrderListOK(extOrderList);
			
			}else{
				//add codes for other purpose of searching
				log.info("no orderstatus is found,will do nothing for search");
				return ExtJSReturn.mapError("没有指定搜索订单的状态!");
			}
		}catch(Exception e){
			log.error("fail to find order",e);
			return ExtJSReturn.simpleMapResult(false, "搜索订单失败!");
		}
	}
	@RequestMapping(method = RequestMethod.POST,value = "/contactCustomer.action")
	public @ResponseBody Map<String, ? extends Object> processOrder(@RequestBody ConfirmOrder updatedOrder){
		log.info("contacted customer for order ");
		try{
			orderService.contactOrder(updatedOrder);
			return ExtJSReturn.simpleMapResult(true, "联系客户确认订单成功！");
		}catch(Exception e){
			log.error("fail to update the order to processed");
			return ExtJSReturn.simpleMapResult(false, "联系客户后更新订单失败！");
		}
		
		
	}
	

}
