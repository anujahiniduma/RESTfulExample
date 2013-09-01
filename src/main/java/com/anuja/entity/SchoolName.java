package com.anuja.entity;

import com.google.code.morphia.annotations.Id;
import org.bson.types.ObjectId;

public class SchoolName {

	@Id private ObjectId id;
	private String schoolName;
	
	public void setName(String name) {
		this.schoolName = name;
	}
	public String getName() {
		return schoolName;
	}
}
