package com.forms.learn.core.servlets;

import java.util.ArrayList;
import java.util.List;

import javax.jcr.Node;
import javax.jcr.Session;
/*import javax.annotation.Resource;
import javax.jcr.Node;*/
import javax.servlet.Servlet;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.ModifiableValueMap;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.osgi.service.cm.ConfigurationAdmin;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.cm.Configuration;
import com.day.cq.dam.api.*;
import com.day.cq.tagging.TagManager;
import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;

@Component(immediate = true, service = Servlet.class, property = { "sling.servlet.paths=" + "/bin/testing", })
public class TestServlet extends SlingSafeMethodsServlet {

	@Override
	protected void doGet(final SlingHttpServletRequest request, final SlingHttpServletResponse response) {
		try {

			/*
			 * need to check that /jcr:content has to be given or not.
			 * */
			ResourceResolver resourceResolver = request.getResourceResolver();

			Resource resource = resourceResolver.getResource("/content/forms-data-poc/us/en");
			response.getWriter().println("After resource :)");

			/*
			 * ModifiableValueMap properties = resource.adaptTo(ModifiableValueMap.class);
			 * properties.put("jcr:description", "Setting the description from Java.");
			 * properties.put("jcr:title", request.getParameter("tagName"));
			 */
			com.forms.learn.core.models.ChildTitlesModel childObj=resource.adaptTo(com.forms.learn.core.models.ChildTitlesModel.class);
			List<String> childTitles=childObj.getListOfChild();
			
			 response.getWriter().println("Before loop "+childTitles.size());
			 
			for(int i=0;i<childTitles.size();i++) {
				response.getWriter().println(i+" "+childTitles.get(i));
			}
			
			
			
			response.getWriter().println("Response from servlet :) ");
			resource.getResourceResolver().commit();
			resourceResolver.close();

		} catch (Exception e) {
			// resourceResolver.close();
		} finally {

		}

	}
}