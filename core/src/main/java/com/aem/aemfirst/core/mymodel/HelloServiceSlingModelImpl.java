package com.aem.aemfirst.core.mymodel;

import java.util.ArrayList;
import java.util.List;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;

import com.aem.aemfirst.core.myinterface.HelloServiceI;

@Model(adaptables = Resource.class)
public class HelloServiceSlingModelImpl implements HelloServiceI {
	
	private List<String> file;

	@Override
	public List<String> getFiles() {
		// TODO Auto-generated method stub
		
		 
		this.file = new ArrayList();

	      this.file.add("Apple");

	      this.file.add("Orange");

	      this.file.add("Peach");

	      this.file.add("Pear");
	      return this.file;
	}

	

}
