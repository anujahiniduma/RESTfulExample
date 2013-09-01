package com.anuja.util;

import java.util.List;

import com.anuja.entity.School;
import com.google.gson.Gson;

public class SchoolTransformer {
	
	public static String schoolsList(List<School> schoolData)
	{
		String schools = null;
		Gson gson = new Gson();
		schools = gson.toJson(schoolData);
		return schools;
	}
}
