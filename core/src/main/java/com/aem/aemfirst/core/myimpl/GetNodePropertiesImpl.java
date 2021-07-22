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

import com.aem.aemfirst.core.bean.NodeOprations;
import com.aem.aemfirst.core.myinterface.GetNodePropertiesI;

@Component(service=GetNodePropertiesI.class, immediate=true)
public class GetNodePropertiesImpl implements GetNodePropertiesI{
	
private static Logger log = LoggerFactory.getLogger(NodeOparationsImpl.class);
	
	@Reference
	ResourceResolverFactory resolverFactory;
	ResourceResolver resourceResolver = null;
	Resource resource;
	String resourcePath = "/content/infopage/jcr:content/employees";
	

	@Override
	public NodeOprations getNodeProperties() {
		// TODO Auto-generated method stub
		boolean flag = false;
		NodeOprations bean = new NodeOprations();
		try {
			
			resourceResolver =	resolverFactory.getServiceResourceResolver(getSubServiceMap());
			resource = resourceResolver.getResource(resourcePath);
			Node node =resource.adaptTo(Node.class);
			String fName = node.getProperty("firstName").getValue().getString();
			String lName = node.getProperty("lastName").getValue().getString();
			String uname = node.getProperty("username").getValue().getString();
			String pwd = node.getProperty("password").getValue().getString();
			
			bean.setFirstName(fName);
			bean.setLastName(lName);
			bean.setUserName(uname);
			bean.setPassword(pwd);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return bean;
	}

	private Map<String, Object> getSubServiceMap() {
		// TODO Auto-generated method stub
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
		return serviceMap;
	}

}
