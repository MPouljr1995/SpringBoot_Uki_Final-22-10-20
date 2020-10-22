package com.Ibase.model;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotEmpty;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection="Order")
public class IbaseOrder {
	@Id
	private String orderId;
	
	@NotEmpty(message = "UserId must not be empty")
	private String userId;
	
	private List <IbaseBuyProductDetails> buyProductDetails;
	
	private Date orderDateTime;
	
	private Double totalPrice;
	
	
	public IbaseOrder(String orderId, String userId, List <IbaseBuyProductDetails> buyProductDetails , Date orderDateTime , Double totalPrice) {
		super();
		this.orderId = orderId;
		this.userId = userId;
		this.orderDateTime = orderDateTime;
		this.buyProductDetails = buyProductDetails;
		this.totalPrice = totalPrice;
	}
	
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Date getOrderDateTime() {
		return orderDateTime;
	}
	public void setOrderDateTime(Date orderDateTime) {
		this.orderDateTime = orderDateTime;
	}
	public List<IbaseBuyProductDetails> getBuyProductDetails() {
		return buyProductDetails;
	}
	public void setBuyProductDetails(List<IbaseBuyProductDetails> buyProductDetails) {
		this.buyProductDetails = buyProductDetails;
	}
	
	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	@Override
	public String toString() {
		return "IbaseOrder [orderId=" + orderId + ",  userId=" + userId + ", buyProductDetails="
				+ buyProductDetails + ", orderDateTime=" + orderDateTime + "]";
	}

	
	
	
	
	
	
}
