package com.aem.aemfirst.core.myservlets;

import java.io.IOException;
import java.util.List;

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
import com.aem.aemfirst.core.bean.StudentDetails;
import com.aem.aemfirst.core.myinterface.CrudInt;

@Component(service=Servlet.class,
property={
        Constants.SERVICE_DESCRIPTION + "=GetAllStudentRecords Servlet",
        "sling.servlet.methods=" + HttpConstants.METHOD_POST,
		/*
		 * "sling.servlet.resourceTypes="+ "ayansh/components/structure/page",
		 * "sling.servlet.extensions=" + "txt"
		 */
        "Sling.servlet.paths="+"/bin/getAllStudentRecords"
})
public class GetAllStudentRecords extends SlingAllMethodsServlet {
	protected static final Logger log=LoggerFactory.getLogger(GetAllStudentRecords.class);

	@Reference
	CrudInt stdAllRecords;
@Override
protected void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response)
		throws ServletException, IOException {
	// TODO Auto-generated method stub
	//super.doPost(request, response);
	try {
		List<StudentDetails> bean=stdAllRecords.getAllStudentRecords();
		//request.setAttribute("bean", bean);
		
		response.getWriter().write("all records************"+bean);
		//System.out.println("Student all data=====" + bean);
		log.info("getting all student records****************"+bean);
		
		
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
	}
	
}
}