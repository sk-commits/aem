package com.aem.aemfirst.core.myinterface;

import com.aem.aemfirst.core.bean.NodeOprations;

public interface NodeOprationsI {
	
	public boolean registerEmployDetails(String firstName,String lastName,String userName,String password);
	public boolean employLogin(String userName,String password);
	public NodeOprations getNodeProperties();

}
