package com.Ibase.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="shop")
public class IbaseShop {
	
	@Id
	private String shopId;
	
//	@NotEmpty(message = "OwnerId must not be empty")
	private String ownerId;
	
//	@NotEmpty(message = "Shopname must not be empty")
	private String shopName;
	private String description;
	
//	@NotEmpty(message = "address must not be empty")
	private String address;
	
	
	private Number telephone;
	private String shopLogo;
	private Double rating;
	private Date createDate;
	
	public IbaseShop( String ownerId, String shopName, String description, String address,
			Number telephone, String shopLogo, Double rating, String shopId, Date createDate) {
		super();
		this.shopId = shopId;
		this.ownerId = ownerId;
		this.shopName = shopName;
		this.description = description;
		this.address = address;
		this.telephone = telephone;
		this.shopLogo = shopLogo;
		this.rating = rating;
		this.createDate = createDate;
	}

	public String getShopId() {
		return shopId;
	}

	public void setShopId(String shopId) {
		this.shopId = shopId;
	}

	public String getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Number getTelephone() {
		return telephone;
	}

	public void setTelephone(Number telephone) {
		this.telephone = telephone;
	}

	public String getShopLogo() {
		return shopLogo;
	}

	public void setShopLogo(String shopLogo) {
		this.shopLogo = shopLogo;
	}

	public Double getRating() {
		return rating;
	}

	public void setRating(Double rating) {
		this.rating = rating;
	}
	
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	
	@Override
	public String toString() {
		return "IbaseShop [shopId=" + shopId + ", ownerId=" + ownerId + ", shopName=" + shopName + ", description="
				+ description + ", address=" + address + ", telephone=" + telephone + ", shopLogo=" + shopLogo
				 + ", rating=" + rating + "]";
	}
	
	
}
