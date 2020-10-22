
package com.Ibase.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="Product")
public class IbaseProduct {
	@Id
	private String productId;
	private String shopId;
	private String title;
	private String description;
	private double lastPrice;
	private double sellPrice;
	private int warranty;
	private double rating;
	private int stock;
	private String brand;
	private String model;
	private Date date;
	private String image_1;
	private String image_2;
	private String image_3;
	private String image_4;
	private String image_5;
	private String category;
	private int count;
	
	
	
	
	public IbaseProduct(String productId, String shopId, String title, String description, double lastPrice,
			double sellPrice, int warranty, double rating, int stock, String brand, String model, Date date,
			String image_1,String image_2,String image_3,String image_4,String image_5,String category,int count) {
		super();
		this.productId = productId;
		this.shopId = shopId;
		this.title = title;
		this.description = description;
		this.lastPrice = lastPrice;
		this.sellPrice = sellPrice;
		this.warranty = warranty;
		this.rating = rating;
		this.stock = stock;
		this.brand = brand;
		this.model = model;
		this.date = date;
		this.image_1 = image_1;
		this.image_2 = image_2;
		this.image_3 = image_3;
		this.image_4 = image_4;
		this.image_5 = image_5;
		this.category = category;
		this.count = count;
	}


	public String getproductId() {
		return productId;
	}


	public void setproductId(String productId) {
		this.productId = productId;
	}


	public String getshopId() {
		return shopId;
	}


	public void setshopId(String shopId) {
		this.shopId = shopId;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public double getLastPrice() {
		return lastPrice;
	}


	public void setLastPrice(double lastPrice) {
		this.lastPrice = lastPrice;
	}


	public double getSellPrice() {
		return sellPrice;
	}


	public void setSellPrice(double sellPrice) {
		this.sellPrice = sellPrice;
	}


	public int getWarranty() {
		return warranty;
	}


	public void setWarranty(int warranty) {
		this.warranty = warranty;
	}


	public double getRating() {
		return rating;
	}


	public void setRating(double rating) {
		this.rating = rating;
	}


	public int getStock() {
		return stock;
	}


	public void setStock(int stock) {
		this.stock = stock;
	}


	public String getBrand() {
		return brand;
	}


	public void setBrand(String brand) {
		this.brand = brand;
	}


	public String getModel() {
		return model;
	}


	public void setModel(String model) {
		this.model = model;
	}
	
	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}

	public String getImage_1() {
		return image_1;
	}


	public void setImage_1(String image_1) {
		this.image_1 = image_1;
	}


	public String getImage_2() {
		return image_2;
	}


	public void setImage_2(String image_2) {
		this.image_2 = image_2;
	}


	public String getImage_3() {
		return image_3;
	}


	public void setImage_3(String image_3) {
		this.image_3 = image_3;
	}


	public String getImage_4() {
		return image_4;
	}


	public void setImage_4(String image_4) {
		this.image_4 = image_4;
	}


	public String getImage_5() {
		return image_5;
	}


	public void setImage_5(String image_5) {
		this.image_5 = image_5;
	}
	
	@Override
	public String toString() {
		return "IbaseProduct [productId=" + productId + ", shopId=" + shopId + ", title=" + title + ", description="
				+ description + ", lastPrice=" + lastPrice + ", sellPrice=" + sellPrice + ", warranty=" + warranty
				+ ", rating=" + rating + ", stock=" + stock + ", brand=" + brand + ", model=" + model + "]";
	}


	public String getCategory() {
		return category;
	}


	public void setCategory(String category) {
		this.category = category;
	}


	public int getCount() {
		return count;
	}


	public void setCount(int count) {
		this.count = count;
	}


}
