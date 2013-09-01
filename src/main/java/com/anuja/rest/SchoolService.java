package com.anuja.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.QueryParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.anuja.dao.SchoolDAO;
import com.anuja.entity.School;
import com.anuja.test.TestSchoolDAO;
import com.anuja.util.MongoDBUtil;
import com.anuja.util.SchoolTransformer;
import com.google.code.morphia.Morphia;
import com.mongodb.Mongo;

@Path("/anuja/schools")
public class SchoolService {
	private Mongo mongo;
	private Morphia morphia;
	private SchoolDAO schoolDao;
	private final String dbname = "peopledb";
	
	private final static Logger logger = LoggerFactory
	.getLogger(SchoolService.class);
	
	@POST
	@Path("/get")
	@Produces("application/json")
	public String schoolListBySubject(@QueryParam("sname") String subjectName){
		String schools = null;
		try
		{
			List<School> schoolData = null;
			initiate();
			schoolData = schoolDao.getSchoolListBySubject(subjectName);
			schools= SchoolTransformer.schoolsList(schoolData);
		}
		catch (Exception e)
		{
			logger.debug("Error occured while getting School List by Subject Name Exception details are " + e);
		}
		return schools;
}
	
	public void initiate() {
		mongo = MongoDBUtil.getMongo();
		morphia = new Morphia();
		morphia.map(School.class);
		schoolDao = new SchoolDAO(mongo, morphia, dbname);
	
	}

}
