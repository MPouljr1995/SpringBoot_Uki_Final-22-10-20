package com.Ibase.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Ibase.model.IbaseOrder;
import com.Ibase.services.IbaseOrderService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/orders")
public class IbaseOrderController {
	@Autowired
	IbaseOrderService ibaseOrderService;
	
	@GetMapping
	public ResponseEntity<List<IbaseOrder>> getAllOrder() {
		return ibaseOrderService.getAllOrders();
	}
	
	@PostMapping
	public ResponseEntity<IbaseOrder> createOrder(@RequestBody IbaseOrder order){
		return ibaseOrderService.createOrder(order);
	}
	
	@GetMapping("/{orderId}")
	public ResponseEntity<IbaseOrder>getOrderById(@PathVariable String orderId){
		return  ibaseOrderService.getOrderById(orderId);
	}
	
	@DeleteMapping("/{orderId}")
	public ResponseEntity<IbaseOrder>deleteOrderById(@PathVariable String orderId){
		return ibaseOrderService.deleteOrderById(orderId);
	}
	
//	@GetMapping(params="shopId")
//	public ResponseEntity<List<IbaseOrder>>getOrderByShopId(@RequestParam String shopId){
//		return ibaseOrderService.getOrderByShopId(shopId);
//	}
	
	@PutMapping("/{orderId}")
	public ResponseEntity<IbaseOrder>updateOrderById(@RequestBody IbaseOrder newOrder , @PathVariable String orderId){
		//System.out.println(newOrder.toString());
		return ibaseOrderService.updateOrderById(newOrder , orderId);
		
	}
	
}
