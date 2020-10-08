/*
 *  Copyright 2015 Adobe Systems Incorporated
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.forms.learn.core.servlets;

import com.day.cq.commons.jcr.JcrConstants;
import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletResourceTypes;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.propertytypes.ServiceDescription;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;

/**
 * Servlet that writes some sample content into the response. It is mounted for
 * all resources of a specific Sling resource type. The
 * {@link SlingSafeMethodsServlet} shall be used for HTTP methods that are
 * idempotent. For write operations use the {@link SlingAllMethodsServlet}.
 */
@Component(service = { Servlet.class })
@SlingServletResourceTypes(
        resourceTypes="forms-data-poc/components/page",
        methods=HttpConstants.METHOD_GET,
        extensions="txt")
@ServiceDescription("Simple Demo Servlet")
public class SimpleServlet extends SlingSafeMethodsServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(final SlingHttpServletRequest req,
            final SlingHttpServletResponse resp) throws ServletException, IOException {
        
    	Resource resource = req.getResource();
    	ResourceResolver resourceResolver=resource.getResourceResolver();
    	
    	//resource.isResourceType("forms-data-poc/components/page");
       // Page pages=resource.adaptTo(Page.class);
    	PrintWriter out=resp.getWriter();
        resp.setContentType("text/plain");
        Resource parenResource=resource.getParent();
   out.println("AAAAA");
        
             
           Page page=resourceResolver.adaptTo(PageManager.class).getPage(parenResource.getPath());    
        if(page==null) {
        	 out.println("Page :"+false);
        }else {
        	out.println("Page: "+page.getContentResource().toString());
        }
   Iterator it=resourceResolver.getAttributeNames();
   int i=1;
   if(it.hasNext()) {
	   out.println(i+" "+it.next());
	   i++;
   }
   out.println("Resource Resolver Attribute :"+resourceResolver.getAttribute("referer"));
   
		/*
		 * Iterator<Resource> itt=(Iterator)resourceResolver.getChildren(parenResource);
		 * int l=1; while(itt.hasNext()) {
		 * out.println("Get Children :"+l+" "+itt.next().toString()); l++; }
		 */
   
   			String underLine="-------------------------------------------";
   
   
        //Title = " + resource.getValueMap().get(JcrConstants.JCR_TITLE)
        //resp.getWriter().write("Resource :"+resource.toString());
        //resp.getWriter().write("pages...........mmm");
        
        if(resource.isResourceType("forms-data-poc/components/page")) {
        	
        	out.println("+++++++++++++++++"+req.getParameter("title"));
        	out.println("Resource Resolver UserId: "+resourceResolver.getUserID());
        	out.println(underLine);
        	out.println("Resoucre path: "+resource.getPath().toString());
		    out.println(underLine);
        	out.println(resourceResolver.getParentResourceType(parenResource));
        	out.println(underLine);
        	out.println("Resource getName: "+resource.getName().toString());
        	out.println(underLine);
        	out.println("Resource getParent: "+resource.getParent());
        	out.println(underLine);
        	out.println("Resource getResourceMetada: "+resource.getResourceMetadata());
        	out.println(underLine);
        	out.println("Resource getResourceResolver: "+resource.getResourceResolver());
        	out.println(underLine);
        	out.println("Resource getResourceSuperType: "+resource.getResourceSuperType());
        	out.println(underLine);
        	out.println("Resource getResourceType: "+resource.getResourceType());
        	out.println(underLine);
        	out.println("Resource ValueMap:"+resource.getValueMap().toString());
        	out.println("-----------------------------------------------------------------------");
        	out.println(underLine);
        	out.println("Resource Resolver getParentReourceType:"+resourceResolver.getParentResourceType(resource));
        	out.println(underLine);
        	out.println("Resource Resolver getParentReourceType String: "+resourceResolver.getParentResourceType("forms-data-poc/components/page"));
        	out.println(underLine);
        	out.println("Resource Resolver getResource-----:"+resourceResolver.getResource("/content/forms-data-poc/jcr:content"));
        	out.println(underLine);
        	String[] arr=resourceResolver.getSearchPath();
        	int count=1;
        	for(String elem:arr) {
        		out.println(count+" "+elem);
        		count++;
        	}
        	out.println(underLine);
        	out.println("Resource Resolver resolve:"+resourceResolver.resolve("/content/forms-data-poc/us/en"));
        	out.println(underLine);
        	ValueMap properties = resource.getValueMap();
        	String value = properties.get("jcr:title", "Default title");
        	out.println("Title : "+value);
        	String relativeResourceValue = properties.get("cq:template", "Default value");
        	out.println("Relative Resource Value: "+relativeResourceValue);
        }
       
        out.println(underLine);
        out.println("Resource Resolver hasChildren: "+resourceResolver.hasChildren(resource));
        out.println(underLine);
        Iterator children=resource.listChildren();
        int l=1;
        while(children.hasNext()) {
        	out.println("Resource Resolver Children "+l+" "+children.next());
        	l++;
        }
        out.println(underLine);
        out.println("Resource Resolver map: "+resourceResolver.map("/content/forms-data-poc/us/en/jcr:root"));
        out.println(underLine);
        out.println("Resource Resolver move: "+resourceResolver.move("/content/forms-data-poc/language-masters/en/childpage1/jcr:content", "/content/forms-data-poc/us/jcr:content"));
        out.println(underLine);
        resourceResolver.refresh();
        out.println(underLine);
       // out.println("Resource Resolver copy: "+resourceResolver.copy("/content/forms-data-poc/language-masters/en/childpage1/jcr:content", "/content/forms-data-poc/us/jcr:content"));
        out.println(underLine);
        out.println("Resource Resolver hasChanges:"+resourceResolver.hasChanges());
    }
}
