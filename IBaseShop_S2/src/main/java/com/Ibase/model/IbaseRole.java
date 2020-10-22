package com.Ibase.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "roles")
public class IbaseRole {
	@Id 
	private String id;
	private IbaseERole name;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public IbaseERole getName() {
		return name;
	}
	public void setName(IbaseERole name) {
		this.name = name;
	}
	
}
