package com.aem.aemfirst.core.mymodel;

import java.util.ArrayList;
import java.util.List;

import com.adobe.cq.sightly.WCMUsePojo;

public class HelloService extends WCMUsePojo{

	private List<String> file;
	
	@Override
	public void activate() throws Exception {
		// TODO Auto-generated method stub
		
	}

	public List<String> getFiles()

	  {
		
		 this.file = new ArrayList();

	      this.file.add("Apple");

	      this.file.add("Orange");

	      this.file.add("Peach");

	      this.file.add("Pear");
		  
	      return this.file;
		
	  }
	
}
