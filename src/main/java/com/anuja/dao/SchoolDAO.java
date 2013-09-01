package com.anuja.dao;


import java.util.List;
import java.util.regex.Pattern;

import com.anuja.entity.School;

import com.google.code.morphia.Morphia;
import com.google.code.morphia.dao.BasicDAO;
import com.google.code.morphia.emul.org.bson.types.ObjectId;
import com.mongodb.Mongo;


public class SchoolDAO extends BasicDAO<School, ObjectId> {
	
	public SchoolDAO(Mongo mongo, Morphia morphia, String dbName) {
		super(mongo, morphia, dbName);		
	}
	
	

	public List<School> findByFname(String sname){
		Pattern regExp = Pattern.compile(sname + ".*", Pattern.CASE_INSENSITIVE);		
		return ds.find(entityClazz).filter("schoolName", regExp).asList();  
	}	

	public List<School> findAll(){
		return ds.find(School.class).asList();
	}

	public List<School> getSortedListBySchoolName(){
		return ds.find(School.class).order("schoolName").asList();
	}
	
	public List<School> getSchoolListBySubject(String subjectname){
		if (subjectname != null && !subjectname.equalsIgnoreCase("")){
			return ds.find(School.class).filter("subject = ", subjectname).asList();
		}else{
			return ds.find(School.class).asList();
		}
	}

} 

