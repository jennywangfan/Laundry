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
	public @ResponseBody Map<String, ? extends Object> getAllOrders(){
		log.info("handle getAllOrdersForCS request");
		try{
		String userName = this.getCurrentUserId();
		List<LaundryOrderModel> extOrderList = orderService.getAllOrdersForCS(userName);
		return ExtJSReturn.mapOrderListOK(extOrderList);
		}catch(Exception e){
			return ExtJSReturn.mapError("获取订单列表失败");
		}
		
		
	}

}
