package com.stackroute.goplaces.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="Place")
public class Place {
	@Id
	private int id;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	private String name;
	private String formatted_address;
	//private Geometry geometry;
	
	
//	public Place() {
//		
//	}
//	public Place(String name, String formatted_address, Geometry geometry) {
//		
//		this.name = name;
//		this.formatted_address = formatted_address;
//		this.geometry = geometry;
//	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFormatted_address() {
		return formatted_address;
	}
	public void setFormatted_address(String formatted_address) {
		this.formatted_address = formatted_address;
	}
//	public Geometry getGeometry() {
//		return geometry;
//	}
//	public void setGeometry(Geometry geometry) {
//		this.geometry = geometry;
//	}
	
}
