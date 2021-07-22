package com.aem.aemfirst.core.myimpl;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.metatype.annotations.Designate;

import com.aem.aemfirst.core.bean.HttpConfiguration;
import com.aem.aemfirst.core.dbutils.Network;
import com.aem.aemfirst.core.myinterface.HttpService;

@Component(service = HttpService.class, immediate = true)
@Designate(ocd = HttpConfiguration.class)

public class HttpServiceImpl implements HttpService{

	private HttpConfiguration config;
	
	@Activate
	protected void activate(HttpConfiguration config ) {
		
		this.config = config;
		
	}
	
	@Override
	public String makeHttpCall() {
		// TODO Auto-generated method stub
		
		try {
			
			boolean enable = config.enableConfig();
			String endpoint = config.getEndpoint();
			String protocol =config.getProtocol();
			String server = config.getServer();
			
			String url = protocol + "://" + server + "/" + endpoint;
			
			if (enable) {
				
				String response = Network.readJson(url);
				
				return response;
				
			} else {
				
				return "Configuration not enabled";
				
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return "Error occurred" + e.getMessage();
		}
		
		
	}

}
