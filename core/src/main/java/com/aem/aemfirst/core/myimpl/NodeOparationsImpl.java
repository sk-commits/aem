package com.aem.aemfirst.core.myimpl;


import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.Session;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aem.aemfirst.core.bean.NodeOprations;
import com.aem.aemfirst.core.myinterface.NodeOprationsI;


@Component(service=NodeOprationsI.class, immediate=true)
public class NodeOparationsImpl implements NodeOprationsI{
	private static Logger log = LoggerFactory.getLogger(NodeOparationsImpl.class);
	
	@Reference
	ResourceResolverFactory resolverFactory;
	ResourceResolver resourceResolver = null;
	private Session session;
	Resource resource;
	String resourcePath = "/content/infopage/jcr:content/employees";
	
	
	@Override
	public boolean registerEmployDetails(String firstName, String lastName, String userName, String password) {
		// TODO Auto-generated method stub
		boolean flag = false;
		log.info("Impl form values called"+firstName+lastName+userName+password);
		try {
			resourceResolver = resolverFactory.getServiceResourceResolver(getSubServiceMap());
			session = resourceResolver.adaptTo(Session.class);		//adapt method is used to convert any type of object, here we are converting resourceResolver object into session object.    
			log.info("register session ****" + session);
			resource = resourceResolver.getResource(resourcePath);
		//create Random numbers
		
		java.util.Random r = new java.util.Random();
		
		int low = 1;
		int high = 100;
		int result = r.nextInt(high-low)+low;
		log.info("result=="+result);
		
		String numberValues = "employees" + result;
		
		Node node = resource.adaptTo(Node.class);		//converting resource object into node
		
		
		if (node != null) {
			Node empRoot = node.addNode(numberValues, "nt:unstructured");		//addNode is a predefined method;
			empRoot.setProperty("FirstName", firstName);
			empRoot.setProperty("LastName", lastName);
			empRoot.setProperty("UserName", userName);
			empRoot.setProperty("Password", password);
			session.save();
			flag = true;
			
		} 

} catch (Exception e) {
	// TODO: handle exception
	e.printStackTrace();
} finally {
	if (session != null) {
		session.logout();
	}

}
		
		return flag;
	}
	
	@Override
	public boolean employLogin(String userName, String password) {
		// TODO Auto-generated method stub
		boolean flag = false;
		
		log.info("username ="+userName+"password ="+password);
		try {

			resourceResolver = resolverFactory.getServiceResourceResolver(getSubServiceMap());
			session = resourceResolver.adaptTo(Session.class);
			log.info("login session ****" + session);
			resource = resourceResolver.getResource(resourcePath);
			
			Node node = resource.adaptTo(Node.class);

			NodeIterator nodeItr = node.getNodes();		//getting nodes which is created in above register method

			while (nodeItr.hasNext()) {

				Node cNode = nodeItr.nextNode();
				
				String username = cNode.getProperty("UserName").getValue().getString();
				String pwd = cNode.getProperty("Password").getValue().getString();

				Map<String, String> map = new HashMap<String, String>();

				map.put("usn", username);
				map.put("pswd", pwd);

				if (map.containsValue(userName) && map.containsValue(password)) {
					log.info("login ok");
					flag = true;
					break;
				} else {
					log.info("failed to login");
					flag = false;
				}

			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return flag;
	}
	
	
			//	This method is used for ACCESSING SYSTEM USER by storing into serviceMap Object
	private Map<String, Object> getSubServiceMap() {
		log.info("*****Inside getSubservice method **");
		Map<String, Object> serviceMap = null;

		try {

			serviceMap = new HashMap<String, Object>();
			serviceMap.put(ResourceResolverFactory.SUBSERVICE, "sam");

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			StringWriter errors = new StringWriter();
			e.printStackTrace(new PrintWriter(errors));
			log.info("errors ***" + errors.toString());
		}
		log.info("*****getSubservice Method End**");
		
		return serviceMap;

	}

	@Override
	public NodeOprations getNodeProperties() {
		// TODO Auto-generated method stub
		
		NodeOprations bean=null;
		
	
		try {
			
			
			log.info("entered into impl.......");
			//resourceResolver=resolverFactory.getServiceResourceResolver(getSubServiceMap());
			//resource=resourceResolver.getResource(resourcePath);
			//log.info("resource---------------"+resource);
			//Node node=resource.adaptTo(Node.class);
			
			resourceResolver = resolverFactory.getResourceResolver(getSubServiceMap());
			resource = resourceResolver.getResource(resourcePath);
			log.info(" resource................+ resource");
			
			Node node = resource.adaptTo(Node.class);
			
			String firstName=node.getProperty("firstName").getValue().getString();
			log.info("fname--------------"+firstName);
			String lastName=node.getProperty("lastName").getValue().getString();
			log.info("lastname--------------"+lastName);
			String username=node.getProperty("userName").getValue().getString();
			log.info("username--------------"+username);
			String password=node.getProperty("password").getValue().getString();
			log.info("pwd--------------"+password);
			
			bean=new NodeOprations();
			
			bean.setFirstName(firstName);
			bean.setLastName(lastName);
			bean.setUserName(username);
			bean.setPassword(password);
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return bean;
	}

	
	
}
	
	
