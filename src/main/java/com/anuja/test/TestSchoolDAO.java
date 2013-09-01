package com.anuja.test;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.List;

import com.anuja.entity.School;
import com.anuja.entity.SchoolName;
import com.anuja.dao.SchoolDAO;
import com.anuja.util.MongoDBUtil;
import com.anuja.util.SchoolTransformer;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.code.morphia.Morphia;


import com.mongodb.Mongo;

public class TestSchoolDAO {
	private final static Logger logger = LoggerFactory
	.getLogger(TestSchoolDAO.class);

	private Mongo mongo;
	private Morphia morphia;
	private SchoolDAO schoolDao;
	private final String dbname = "peopledb";

	@Before
	public void initiate() {
		mongo = MongoDBUtil.getMongo();
		morphia = new Morphia();
		morphia.map(School.class);
		schoolDao = new SchoolDAO(mongo, morphia, dbname);
	
	}

	@Test
	public void test() {
		long counter = schoolDao.count();
		logger.debug("The count is [" + counter + "]");

		School s = new School();
		SchoolName sn = new SchoolName();
		sn.setName("St Peters");
		s.setSchoolName(sn);
		schoolDao.save(s);

		long newCounter = schoolDao.count();
		logger.debug("The new count is [" + newCounter + "]");

		assertTrue((counter + 1) == newCounter);

		List<School> iteratorschool = schoolDao.findAll();            
		logger.debug(SchoolTransformer.schoolsList(iteratorschool));
	}
}
