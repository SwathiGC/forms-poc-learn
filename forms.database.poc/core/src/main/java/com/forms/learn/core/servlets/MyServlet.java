package com.forms.learn.core.servlets;

import com.day.cq.commons.jcr.JcrConstants;
import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.ModifiableValueMap;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletResourceTypes;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.propertytypes.ServiceDescription;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;


@Component( immediate = true, service = Servlet.class, property = {
  //"sling.servlet.methods=GET", 
  "sling.servlet.resourceTypes=mysite/components/page",
  //"sling.servlet.selectors=search",
  "sling.servlet.selectors=" +"sample"
  //"sling.servlet.resourceTypes="+"/content/dam/adobe-logo.jpg" 
	} )
 

public class MyServlet extends SlingSafeMethodsServlet {
	@Override
	protected void doGet(final SlingHttpServletRequest request, final SlingHttpServletResponse response)
			throws ServletException, IOException {
		ResourceResolver resource_resolver=request.getResourceResolver();
		Resource resource = request.getResource();
		

		
		
		ModifiableValueMap properties = resource.adaptTo(ModifiableValueMap.class);

		
		properties.put("jcr:title",request.getParameter("title"));
		
		resource.getResourceResolver().commit();
		
	}
}
