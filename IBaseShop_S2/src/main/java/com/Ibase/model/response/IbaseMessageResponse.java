package com.Ibase.model.response;

public class IbaseMessageResponse {
	private String message;

	
	public IbaseMessageResponse(String message) {
		super();
		this.message = message;
	}
	
	

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
