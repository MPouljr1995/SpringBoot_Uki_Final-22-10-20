package com.Ibase.model;


import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "feedback")
public class IbaseFeedback {
	
	@Id
	private String feedbackId;
	private String userId;
	private String productId;
	private String feedback;
	private Double rating;
	private Date feedbackDate;
	
	private IbaseFeedback(String feedbackId, String userId, String productId, String feedback, Double rating,
			Date feedbackDate) {
		super();
		this.feedbackId = feedbackId;
		this.userId = userId;
		this.productId = productId;
		this.feedback = feedback;
		this.rating = rating;
		this.feedbackDate = feedbackDate;
	}

	public String getFeedbackId() {
		return feedbackId;
	}

	public void setFeedbackId(String feedbackId) {
		this.feedbackId = feedbackId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getFeedback() {
		return feedback;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}

	public Double getRating() {
		return rating;
	}

	public void setRating(Double rating) {
		this.rating = rating;
	}

	public Date getFeedbackDate() {
		return feedbackDate;
	}

	public void setFeedbackDate(Date feedbackDate) {
		this.feedbackDate = feedbackDate;
	}

	@Override
	public String toString() {
		return "IbaseFeedback [feedbackId=" + feedbackId + ", userId=" + userId + ", productId=" + productId
				+ ", feedback=" + feedback + ", rating=" + rating + ", feedbackDate=" + feedbackDate + "]";
	}
	
	
	
	
	
}
