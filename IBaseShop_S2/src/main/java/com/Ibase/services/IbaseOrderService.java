package com.Ibase.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.Ibase.repository.IbaseOrderRepository;
import com.Ibase.model.IbaseOrder;

@Service
public class IbaseOrderService {
	
	@Autowired
	IbaseOrderRepository ibaseOrderRepository;

	public ResponseEntity<List<IbaseOrder>> getAllOrders() {
		try {
			List<IbaseOrder> orders = ibaseOrderRepository.findAll();
			if(orders.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}else {
				return new ResponseEntity<>(orders,HttpStatus.OK);
			}
		}catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public ResponseEntity<IbaseOrder> createOrder(IbaseOrder order) {
		order.setOrderDateTime(new Date());
		try {
			IbaseOrder ord = ibaseOrderRepository.insert(order);
			return new ResponseEntity<>(ord,HttpStatus.CREATED);
		}catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public ResponseEntity<IbaseOrder> getOrderById(String orderId) {
		try {
			Optional<IbaseOrder> order = ibaseOrderRepository.findById(orderId);
			if (order.isPresent()){
				return new ResponseEntity<>(order.get(),HttpStatus.OK);
			}else {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
		}catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public ResponseEntity<IbaseOrder> deleteOrderById(String orderId) {
		try {
			Optional<IbaseOrder> order = ibaseOrderRepository.findById(orderId);
			if (order.isPresent()){
				ibaseOrderRepository.deleteById(orderId);
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		}catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

//	public ResponseEntity<List<IbaseOrder>> getOrderByShopId(String shopId) {
//		try {
//			List<IbaseOrder> orders = ibaseOrderRepository.findByShopId(shopId);
//			if(orders.isEmpty()) {
//				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//			}else {
//				return new ResponseEntity<>(orders,HttpStatus.OK);
//			}
//		}catch (Exception e) {
//			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//	}

	public ResponseEntity<IbaseOrder> updateOrderById(IbaseOrder newOrder , String orderId) {
		Optional <IbaseOrder> oldOrder = ibaseOrderRepository.findById(orderId);
		if(oldOrder.isPresent()) {
			IbaseOrder _order = oldOrder.get();
			_order.setOrderId(newOrder.getOrderId());
			_order.setUserId(newOrder.getUserId());
			_order.setOrderDateTime(newOrder.getOrderDateTime());
			_order.setBuyProductDetails(newOrder.getBuyProductDetails());
			return new ResponseEntity<>(ibaseOrderRepository.save(_order),HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	

}
