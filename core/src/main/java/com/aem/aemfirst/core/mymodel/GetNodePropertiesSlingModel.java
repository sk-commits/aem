package com.aem.aemfirst.core.mymodel;


import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;

import com.aem.aemfirst.core.bean.NodeOprations;
import com.aem.aemfirst.core.myinterface.GetNodePropertiesI;

@Model(adaptables=Resource.class)
public class GetNodePropertiesSlingModel {
	
	
private String ftName;
private String ltName;
private String usname;
private String pswd;
private String bean;

@OSGiService
GetNodePropertiesI obj;

@PostConstruct
protected void init() {
	
	NodeOprations bean = obj.getNodeProperties();
	ftName = bean.getFirstName();
	ltName = bean.getLastName();
	usname =bean.getPassword();
	pswd = bean.getUserName();
	
}

public String getFtName() {
	return ftName;
}

public String getLtName() {
	return ltName;
}

public String getUsname() {
	return usname;
}

public String getPswd() {
	return pswd;
}

public GetNodePropertiesI getObj() {
	return obj;
}

}
