package com.Ibase.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class IbaseBuyProductDetails {
	
	@Id
	private String buyProductId;
	private String shopId;
	private int quantity;
	private Double price;
	
	
	
	public IbaseBuyProductDetails( String buyProductId ,String shopId, int quantity , Double price) {
		super();
		this.buyProductId = buyProductId;
		this.shopId = shopId;
		this.quantity = quantity;
		this.price = price;
	}

	public String getBuyProductId() {
		return buyProductId;
	}
	public void setBuyProductId(String buyProductId) {
		this.buyProductId = buyProductId;
	}

	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}

	public String getShopId() {
		return shopId;
	}

	public void setShopId(String shopId) {
		this.shopId = shopId;
	}

}
