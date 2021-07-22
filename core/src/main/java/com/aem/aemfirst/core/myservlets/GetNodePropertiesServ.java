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

import com.aem.aemfirst.core.bean.NodeOprations;
import com.aem.aemfirst.core.myinterface.GetNodePropertiesI;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component(service=Servlet.class,
property={
        Constants.SERVICE_DESCRIPTION + "=GetNodePropertiesServ Servlet",
        "sling.servlet.methods=" + HttpConstants.METHOD_GET,
		/*
		 * "sling.servlet.resourceTypes="+ "ayansh/components/structure/page",
		 * "sling.servlet.extensions=" + "txt"
		 */
        "Sling.servlet.paths="+"/bin/GetNodePropertiesServ"
})

public class GetNodePropertiesServ extends SlingAllMethodsServlet {

	@Reference
	GetNodePropertiesI obj;
	@Override
	protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		//super.doGet(request, response);
		
	NodeOprations bean = 	obj.getNodeProperties();
	String fn = bean.getFirstName();
	String ln = bean.getLastName();
	String pd = bean.getPassword();
	String un = bean.getUserName();
	
	// Creating Object of ObjectMapper define in Jackson API  
	ObjectMapper Obj = new ObjectMapper();  
    // Converting the Java object into a JSON string  
    String jsonStr = Obj.writeValueAsString(bean);  
    // Displaying Java object into a JSON string  
    response.getWriter().write("json==="+jsonStr);
  //  String json = new Gson().toJson(map);
	//response.getWriter().write(fn+ln+pd+un);
		
	}
}
