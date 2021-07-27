package com.aem.aemfirst.core.mymodel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import com.aem.aemfirst.core.myinterface.AuthorBooks;

@Model(adaptables = Resource.class)

public class AuthorBooksSling implements AuthorBooks{
	

	/*@OSGiService
	AuthorBooks obj;*/
	
	@ValueMapValue
	@Default (values="AEM")
	private String name; 
	
	@ValueMapValue
    private List<String> books;
	
	
	@PostConstruct
	protected void init() {
		
		//name = obj.getAuthorName();
		//books = obj.getAuthorBooks();
	
	}

	@Override
	public String getAuthorName() {
		// TODO Auto-generated method stub
		return name;
	}

	@Override
	public List<String> getAuthorBooks() {
		if(books!=null) {
		// TODO Auto-generated method stub
			return new ArrayList<String> (books);
		}else{
			return Collections.emptyList();
		}
		
	}
	
	
	

}
