package com.aem.aemfirst.core.myimpl;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Required;
import org.apache.sling.models.annotations.Via;
import org.apache.sling.models.annotations.injectorspecific.RequestAttribute;
import org.apache.sling.models.annotations.injectorspecific.ResourcePath;
import org.apache.sling.models.annotations.injectorspecific.ScriptVariable;
import org.apache.sling.models.annotations.injectorspecific.Self;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import com.aem.aemfirst.core.myinterface.SlingModelOnlineI;
import com.day.cq.wcm.api.Page;

@Model(adaptables=SlingHttpServletRequest.class, adapters=SlingModelOnlineI.class, defaultInjectionStrategy=DefaultInjectionStrategy.OPTIONAL)

public class SlingModelOnlineImpl implements SlingModelOnlineI{
	
	/*@Inject
	@Via ("resource")*/
	@ValueMapValue
	@Default (values = "Sai")
	String fname;
	
	@Inject
	@Required
	@Via ("resource")
	@Default (values = "Sam")
	String lname;
	
	@Inject
	@Via ("resource")
	boolean isprofessor;
	
	@ScriptVariable
	Page currentPage;
	
	@SlingObject
	ResourceResolver resourceResolver;
	
	@Self
	SlingHttpServletRequest slingHttpServletRequest;
	
	@RequestAttribute(name="rAttribute")
	String reqAttribute;
	
	@ResourcePath(path ="/content/infopage/mypage")
	@Via ("resource")
	Resource resource;
	
	@Inject
	@Via ("resource")
	@Named ("jcr:title")
	String abc;

	@Override
	public String getFirstName() {
		// TODO Auto-generated method stub
		return fname;
	}

	@Override
	public String getLastName() {
		// TODO Auto-generated method stub
		return lname;
	}

	@Override
	public boolean getIsProfessor() {
		// TODO Auto-generated method stub
		return isprofessor;
	}

	@Override
	public String getPageTitle() {
		// TODO Auto-generated method stub
		return currentPage.getTitle();
	}

	@Override
	public String getRequestAttribute() {
		// TODO Auto-generated method stub
		return reqAttribute;
	}

	@Override
	public String getHomePageName() {
		// TODO Auto-generated method stub
		return resource.getName();
	}

	@Override
	public String getMyTitle() {
		// TODO Auto-generated method stub
		return abc;
	}

}
