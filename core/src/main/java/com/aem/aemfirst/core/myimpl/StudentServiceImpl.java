package com.aem.aemfirst.core.myimpl;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import javax.jcr.Node;
import javax.jcr.Session;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aem.aemfirst.core.myinterface.StudentService;

@Component(service=StudentService.class, immediate=true)
public class StudentServiceImpl implements StudentService {
	private static Logger log = LoggerFactory.getLogger(NodeOparationsImpl.class);
	
	@Reference
	private ResourceResolverFactory resolverFactory;
	Session session;
	ResourceResolver resolver = null;
	Resource resource;
	String resourcePath = "/content/aemfirst/en/jcr:content/student";
	
	//FIRST STEP : ACCESSING SYSTEM USER
	private Map<String, Object> getSubServiceMap(){
		log.info("****Inside getSubServiceMenu*****");
		Map<String, Object> serviceMap = null;
		
		try {
			serviceMap = new HashMap<String, Object>();
			serviceMap.put(ResourceResolverFactory.SUBSERVICE, "sai"
					+ "");
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			StringWriter errors = new StringWriter();
			e.printStackTrace(new PrintWriter(errors));
			log.info("error*****"+ errors.toString());
		}
		
			log.info("****getSubServiceMenu method end****");
		return serviceMap;
		
	}
	
	@Override
	public void studentsData() {
		// TODO Auto-generated method stub
		try {
			resolver = resolverFactory.getServiceResourceResolver(getSubServiceMap());
			session = resolver.adaptTo(Session.class);
			log.info("Session*****"+session);
			resource = resolver.getResource(resourcePath);
			
			Node node = resource.adaptTo(Node.class);		//converting resource object into node
			log.info("Node****"+node);
			String stdId = node.getProperty("StudentId").getValue().getString();
			String stdName = node.getProperty("StudentName").getValue().getString();
			
			

			log.info("Student Id====="+stdId+"Student Name====="+stdName);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}




}
