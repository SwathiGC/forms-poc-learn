package com.forms.learn.core.servlets;

import javax.jcr.Node;
import javax.jcr.Session;
/*import javax.annotation.Resource;
import javax.jcr.Node;*/
import javax.servlet.Servlet;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;
import org.osgi.service.cm.ConfigurationAdmin;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.cm.Configuration;

import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;

@Component(immediate = true, service = Servlet.class, property = { "sling.servlet.paths=" + "/bin/testing", })
public class TestServlet extends SlingSafeMethodsServlet {

	@Override
	protected void doGet(final SlingHttpServletRequest request, final SlingHttpServletResponse response) {
		try {

			ResourceResolver resourceResolver = request.getResourceResolver();
			Resource resource = resourceResolver.getResource("/content/dam/adobe-logo.jpg/jcr:content");

			Session session = resourceResolver.adaptTo(Session.class);

			Node node = resource.adaptTo(Node.class);
			
			node.setProperty("jcr:title", "From property");
			node.setProperty("cq:tags", "custom-fruits");
			/*
			 * response.getWriter().println("This is test servlet :)");
			 * 
			 * response.getWriter().println(node.getProperty("jcr:title"));
			 * response.getWriter().println(node.getProperty("cq:tags"));
			 */
			response.getWriter().println("Response from Servlet");

			resource.getResourceResolver().commit();
			resourceResolver.close();

		} catch (Exception e) {
			// resourceResolver.close();
		} finally {

		}

	}
}