package com.aem.aemfirst.core.myservlets;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aem.aemfirst.core.dbutils.CrudDbUtil;
import com.aem.aemfirst.core.myinterface.CrudInt;

@Component(service=Servlet.class,
property={
        Constants.SERVICE_DESCRIPTION + "=My Crud Operations Servlet",
        "sling.servlet.methods=" + HttpConstants.METHOD_POST,
        "sling.servlet.paths=" + "/bin/crudserv"
})
public class CrudServ extends SlingAllMethodsServlet {
	/*@Reference
	CrudDbUtil db;*/
	@Reference
	CrudInt crdint;
	protected static final Logger log = LoggerFactory.getLogger(CrudDbUtil.class);
	
	@Override
	protected void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String firstName=request.getParameter("fname");
		String lastName=request.getParameter("lname");
		String email=request.getParameter("email");
		log.info("hitted the Servlet**********");
		

		try {
			boolean b = crdint.addStudent(firstName, lastName, email);
			
			if (b) {
				log.info("successfully registerd the valuesssssssss***"+b);
				response.getWriter().write("firstName"+firstName+"lastName"+lastName+"email"+email);
			} else {
				log.info("Somthing went wrong Please try again later***********");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		//super.doPost(request, response);
	}

}
