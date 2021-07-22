package com.aem.aemfirst.core.myservlets;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.aem.aemfirst.core.myinterface.HttpService;

@Component(service=Servlet.class,
property={
        Constants.SERVICE_DESCRIPTION + "=My database Servlet",
        "sling.servlet.methods=" + HttpConstants.METHOD_GET,
        "sling.servlet.paths=" + "/bin/HttpServlet"
})

public class HttpServlet extends SlingAllMethodsServlet{
	
	@Reference
	HttpService httpservice;
	
	@Override
	protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		//super.doGet(request, response);
		
		try {
			
			String jsonresponse = httpservice.makeHttpCall();
			
			response.getWriter().write(jsonresponse);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			
		}
	}

}
